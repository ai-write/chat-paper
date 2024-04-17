package com.futureai.write.infrastructure.external.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.futureai.write.common.api.ResultCode;
import com.futureai.write.common.exception.ServiceException;
import com.futureai.write.common.handler.MessageSendHandler;
import com.futureai.write.infrastructure.external.service.IOpenAIChatService;
import com.futureai.write.types.dp.AIChatRequest;
import com.futureai.write.types.dp.AIChatResponse;
import com.theokanning.openai.client.OpenAiApi;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author max
 * @description AI聊天对话实现
 * @date 2024/3/14 18:18
 */
@Slf4j
@Service
public class OpenAIChatServiceImpl implements IOpenAIChatService {

    public final static String LP = "↖";
    public final static String RP = "↘";

    @Override
    public AIChatResponse chatNormal(AIChatRequest request) {
        AIChatResponse response = new AIChatResponse();
        final TimeInterval timer = DateUtil.timer();
        final Date startTime = new Date();

        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage("user", request.getRequestMessage()));

        OpenAiService openAiService = init(request.getApiKey(), request.getProxyUrl(), 120L);
        ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest();
        chatCompletionRequest.setN(1);
        chatCompletionRequest.setModel(request.getModelName());
        chatCompletionRequest.setMessages(messages);

        try {
            ChatCompletionResult completionResult = openAiService.createChatCompletion(chatCompletionRequest);
            ChatMessage message = completionResult.getChoices().get(0).getMessage();

            final Date finishTime = new Date();

            response.setResponseContent(message.getContent());
            response.setFinishTime(finishTime);
            response.setStartTime(startTime);
            response.setExecuteTime(timer.intervalSecond());

        } catch (Exception e) {
            throw new ServiceException("模型请求失败");
        }

        return response;
    }

    @Override
    public AIChatResponse chatStream(String connectId, AIChatRequest request) {
        AIChatResponse response = new AIChatResponse();
        final TimeInterval timer = DateUtil.timer();
        final Date startTime = new Date();

        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage("user", request.getRequestMessage()));

        OpenAiService openAiService = init(request.getApiKey(), request.getProxyUrl(), 120L);
        ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest();
        chatCompletionRequest.setN(1);
        chatCompletionRequest.setModel(request.getModelName());
        chatCompletionRequest.setMessages(messages);

        StringBuilder responseStr = new StringBuilder();
        MessageSendHandler messageSendHandler = new MessageSendHandler(connectId);
        try {
            openAiService.streamChatCompletion(chatCompletionRequest)
                    .blockingForEach(chunk -> {
                        if (CollUtil.isNotEmpty(chunk.getChoices())){
                            ChatCompletionChoice choice = chunk.getChoices().get(0);
                            ChatMessage chatMessage = Optional.ofNullable(choice.getMessage()).orElseGet(ChatMessage::new);
                            String chunkContent = Optional.ofNullable(chatMessage.getContent()).orElse(StrUtil.EMPTY);
                            responseStr.append(chunkContent);

                            if (StrUtil.isNotEmpty(chunkContent)){
                                chunkContent = chunkContent.replace(" ", String.format("%semsp%s", LP, RP));
                                chunkContent = chunkContent.replace("\n", String.format("%sbr%s", LP, RP));
                                messageSendHandler.queueAdd(chunkContent);
                            }
                        }
                    });
        } catch (ServiceException e) {
            if (e.getResultCode().equals(ResultCode.SUCCESS)) {
                return response;
            }
        }finally {
            final Date finishTime = new Date();
            messageSendHandler.close();
            response.setResponseContent(responseStr.toString());
            response.setFinishTime(finishTime);
            response.setStartTime(startTime);
            response.setExecuteTime(timer.intervalSecond());
        }

        return response;
    }

    /**
     * 初始化OpenAIService
     * @param apiKey ApiKey
     * @param proxyUrl 代理Url
     * @param timeout 连接超时时间
     * @return
     */
    private OpenAiService init(String apiKey, String proxyUrl, Long timeout){

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new AuthenticationInterceptor(apiKey))
                .connectionPool(new ConnectionPool(50, 3L, TimeUnit.MINUTES))
                .build();

        ObjectMapper mapper = OpenAiService.defaultObjectMapper();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(proxyUrl)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        OpenAiApi api = retrofit.create(OpenAiApi.class);
        return  new OpenAiService(api);
    }

    private static class AuthenticationInterceptor implements Interceptor{
        private final String token;

        AuthenticationInterceptor(String token) {
            Objects.requireNonNull(token, "OpenAI token required");
            this.token = token;
        }

        @NotNull
        @Override
        public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .header("Authorization", "Bearer " + token)
                    .build();
            return chain.proceed(request);
        }
    }
}
