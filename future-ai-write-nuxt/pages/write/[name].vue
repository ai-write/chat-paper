<script setup lang="ts">
import type {FormInst, FormRules} from "naive-ui";
import {useApiKey} from "~/composables/useApiKey";

const steps = ref([
  {
    title: '输入论文题目',
    description: '生成千字大纲',
  },
  {
    title: '编辑大纲',
    description: '生成论文初稿',
  },
  {
    title: '生成全文',
    description: '永久有效',
  },
  {
    title: '下载论文',
    description: '永久有效',
  },
])

/*// 配置列表
const {data: configList} = await httpGet('/write/config/list')
const route = useRoute()
const configKey = ref(route.params.name)
const configDetail = await httpGet('/write/config/detail', {
  params: {configKey: configKey.value}
})
const {inputs: configInputList, materialList} = configDetail.data


const writeRequestForm = ref({})
const writeRequestFormRef = ref<FormInst>()
const writeRequestFormRules: FormRules = {};
configInputList.forEach(item => {
  writeRequestFormRules[item.formKey] = JSON.parse(item.rule)
})

let paperOutlineList = ref([])
let paperOutlineGenerateLoading = ref(false)
let paperOutlineGeneratePercentage = ref(0)
let paperRespId = ref('')

const apiKey = useApiKey()
const message = useMessage()
const checkKey = ()=>{
  let res = true
  if (apiKey.apiKey.length===0){
    message.error("获取Key失败，请点击右侧【Key配置】按钮配置Key")
    res = false
  }
  return res
}

const onCreateOutlineClickFn = (e: MouseEvent) => {
  e.preventDefault()
  writeRequestFormRef.value?.validate((errors) => {
    if (!errors) {
      if (!checkKey()){
        return
      }
      const params = Object.keys(writeRequestForm.value).map(key => {
        return {
          key,
          value: writeRequestForm.value[key]
        }
      })

      const paperOutlineGenerateTimer = setInterval(() => {
        if (paperOutlineGeneratePercentage.value > 90) {
          clearInterval(paperOutlineGenerateTimer)
        }
        paperOutlineGeneratePercentage.value += Math.ceil(Math.random() * 3)
      }, 800)

      paperOutlineGenerateLoading.value = true
      httpPost('/write/generate/outline', {
        configKey: configKey.value,
        apiKey: apiKey.apiKey,
        params
      }).then(res => {
        let {outlines, paperId} = res.data
        paperOutlineList = outlines
        paperRespId.value = paperId
      }).finally(() => {
        paperOutlineGenerateLoading.value = false
        paperOutlineGeneratePercentage.value = 0
        clearInterval(paperOutlineGenerateTimer)
      })
    }
  })
}

let paperGenerateLoading = ref<boolean>(false)
let paperGenerateText = ref<string>('')
const onGeneratePaperClickFn = async () => {
  if (!checkKey()){
    return
  }
  paperGenerateLoading.value = true
  const payload = {
    paperId: paperRespId.value,
    apiKey: apiKey.apiKey,
  }
  const ai = useAI('/write/generate/paper', payload)
  const onText = (text: string) => {
    paperGenerateText.value = text
  }
  const onComplete = () => {
    paperGenerateLoading.value = false
  }

  await ai.complete({onText, onComplete})
}*/

const route = useRoute()
const apiKey = useApiKey()
const message = useMessage()

const checkKey = () => {
  let res = true
  if (apiKey.apiKey.length === 0) {
    message.error("获取Key失败，请点击右侧【Key配置】按钮配置Key")
    res = false
  }
  return res
}

const paperOutlineList = ref<Array<PaperOutline>>([])
const paperOutlineGenerateLoading = ref<boolean>(false)
const paperOutlineGeneratePercentage = ref<number>(0)
const paperId = ref<string>('')
const paperMaterialList = ref<Array<WriteConfigMaterial>>([])
const paperProperties = ref<Array<KeyValue>>([])

const onGenerateOutlineSubmit = async (params: PaperOutlineSubmit) => {

  const {forms, materials} = params

  paperOutlineGenerateLoading.value = true
  paperMaterialList.value = materials ? materials : []
  paperProperties.value = forms

  const paperOutlineGenerateTimer = setInterval(() => {
    if (paperOutlineGeneratePercentage.value > 90) {
      clearInterval(paperOutlineGenerateTimer)
    }
    paperOutlineGeneratePercentage.value += Math.ceil(Math.random() * 3)
  }, 800)
  const paperOutlineResponse: PaperOutlineResponse = await httpPost('/write/generate/outline', {
    configKey: route.params.name,
    apiKey: apiKey.apiKey,
    params: forms
  })

  paperId.value = paperOutlineResponse.paperId
  paperOutlineList.value = paperOutlineResponse.outlines

  paperOutlineGenerateLoading.value = false
  paperOutlineGeneratePercentage.value = 0
  clearInterval(paperOutlineGenerateTimer)
}


</script>

<template>
  <NScrollbar>
    <div class="flex justify-center">
      <div class="w-[100%] max-w-screen-lg pl-2.5 pr-2.5">
        <WriteTitle/>
        <WriteConfig :config-key="$route.params.name"
                     :loading="paperOutlineGenerateLoading"
                     @on-generate-outline-submit="onGenerateOutlineSubmit"/>
        <WriteGenerateOutline
            :loading="paperOutlineGenerateLoading"
            :percentage="paperOutlineGeneratePercentage"
            :outlines="paperOutlineList"
            :materials="paperMaterialList"
            :properties="paperProperties"/>
      </div>
    </div>
    <!--    <div class="flex justify-center">
          <div class="max-w-screen-lg pl-2.5 pr-2.5">

            <div class="flex justify-center flex-col pt-20">
              <div class="text-normal text-5xl font-semibold mb-4 text-center">
                神笔AI写作
              </div>
              <div class="text-lg text-gray-400 text-center mb-14">
                300万大学生都在用的AI写作平台
              </div>
              <div class="text-normal text-2xl font-medium text-center mb-4">
                搞定论文，只需四步
              </div>
              <div class="text-primary text-lg flex justify-center items-center space-x-1 description mb-7">
                <span
                    class="w-[24px] h-[24px] rounded-full border-primary border-2 flex justify-center items-center">1</span>
                <span>输入论文题目，生成千字大纲</span>
              </div>
              <div
                  class="flex justify-center gap-x-1.5 sm:gap-x-1.5 md:gap-x-3 lg:gap-x-6 xl:gap-x-8 gap-y-2 flex-wrap config-select mb-4">
                <div
                    class="rounded-lg cursor-pointer bg-white border opacity-60 pt-1 pb-1 pl-3 pr-3"
                    v-for="config in configList"
                    :key="config.configKey"
                    :class="{ 'config-select&#45;&#45;active': config.configKey === configKey }"
                    @click="$router.push(`/write/${config.configKey}`)"
                >
                  <div class="flex justify-center items-center gap-2">
                    <NIcon size="30">
                      <img :src="config.configIcon" alt="">
                    </NIcon>
                    {{ config.configName }}
                  </div>
                </div>
              </div>
              <NForm
                  ref="writeRequestFormRef"
                  :rules="writeRequestFormRules"
                  label-placement="left"
                  :inline="false"
                  :model="writeRequestForm">
                <NGrid x-gap="6" item-responsive responsive="screen">
                  <NGridItem
                      v-for="input in configInputList"
                      :span="input.span">
                    <NFormItem
                        class="w-full"
                        :label="input.name"
                        :path="input.formKey">
                      <NRadioGroup
                          :name="input.formKey"
                          v-if="input.formType==='radio'"
                          v-model:value="writeRequestForm[input.formKey]">
                        <NRadio
                            v-for="dict in input.dictData"
                            :key="dict.value"
                            :value="dict.label">
                          {{ dict.label }}
                        </NRadio>
                      </NRadioGroup>
                      <NInput
                          v-if="input.formType==='input'"
                          v-model:value="writeRequestForm[input.formKey]"
                          :placeholder="input.description"/>
                      <NCascader
                          v-if="input.formType==='cascader'"
                          v-model:value="writeRequestForm[input.formKey]"
                          :options="input.dictData" :placeholder="input.description">
                      </NCascader>
                    </NFormItem>
                  </NGridItem>
                  <NGridItem span="24" class="flex justify-center">
                    <NFormItem>
                      <NButton
                          class="text-base rounded-xl h-12 pl-7 pr-7"
                          type="primary"
                          size="large"
                          icon-placement="right"
                          :loading="paperOutlineGenerateLoading"
                          @click="onCreateOutlineClickFn">
                        <template #icon>
                          <NIcon size="20">
                            <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
                                 viewBox="0 0 512 512">
                              <path
                                  d="M476 3.2L12.5 270.6c-18.1 10.4-15.8 35.6 2.2 43.2L121 358.4l287.3-253.2c5.5-4.9 13.3 2.6 8.6 8.3L176 407v80.5c0 23.6 28.5 32.9 42.5 15.8L282 426l124.6 52.2c14.2 6 30.4-2.9 33-18.2l72-432C515 7.8 493.3-6.8 476 3.2z"
                                  fill="currentColor"></path>
                            </svg>
                          </NIcon>
                        </template>
                        极速生成大纲
                      </NButton>
                    </NFormItem>
                  </NGridItem>
                </NGrid>
              </NForm>
            </div>

            <div class="flex justify-center flex-col pt-20">
              <div class="text-primary text-lg flex justify-center items-center space-x-1 description mb-7">
                <span
                    class="w-[24px] h-[24px] rounded-full border-primary border-2 flex justify-center items-center">2</span>
                <span>自动生成大纲</span>
              </div>
              <div class="flex justify-center items-center pt-1 pb-1">
                <div class="flex flex-col justify-center items-center space-y-3"
                     v-if="paperOutlineGenerateLoading">
                  <NProgress
                      type="circle"
                      color="#782AFF"
                      :stroke-width="4"
                      :processing="true"
                      :percentage="paperOutlineGeneratePercentage"
                  ></NProgress>
                  <div class="text-sm">大纲生成中，大约需要40秒，请不要刷新页面！</div>
                </div>
                <NGrid x-gap="10"
                       v-else-if="paperOutlineList.length!=0"
                       item-responsive
                       responsive="screen">
                  <NGridItem span="24 m:17">
                    <div class="bg-white sm:p-2 md:p-5">
                      <div class="flex gap-x-2.5 items-end mb-4">
                        <div class="text-normal font-medium text-lg">论文大纲</div>
                        <div class="text-neutral-500 text-sm">可添加、删除、编辑</div>
                      </div>
                      <NCollapse accordion>
                        <NCollapseItem
                            class="bg-neutral-100"
                            v-for="(outline,index) in paperOutlineList"
                            :key="outline.id"
                            :title="`${outline.chapter} ${outline.title}`"
                            :name="outline.id">
                          <div class="bg-white pl-5 pt-6 pb-6 "
                               v-if="outline.children?.length>0">
                            <div class=""
                                 v-for="child in outline.children"
                                 :key="child.id">
                              <div class="text-gray-950 txt-base font-medium mb-1.5">{{ child.chapter }} {{
                                  child.title
                                }}
                              </div>
                              <div class="mb-5 text-gray-400 txt-base">{{ child.description }}</div>
                            </div>
                          </div>
                        </NCollapseItem>
                      </NCollapse>
                    </div>
                  </NGridItem>
                  <NGridItem span="24  m:7">
                    <div class="bg-white sm:p-1.5 md:p-2.5">
                      <div class="text-normal font-medium text-lg mb-4">材料清单</div>
                      <div class="flex gap-x-1.5 items-start mb-1.5">
                        <NIcon size="15">
                          <img src="~/assets/icon/icon_paper.png">
                        </NIcon>
                        <div class="flex flex-col justify-start">
                          <div class="text-normal font-medium text-base">{{ writeRequestForm.title }}</div>
                          <div class="text-gray-400 text-sm mt-1">{{ writeRequestForm.degree }}</div>
                        </div>
                      </div>
                      <NGrid x-gap="10"
                             y-gap="6"
                             item-responsive
                             responsive="screen">
                        <NGridItem span="12"
                                   v-for="(material,index) in materialList">
                          <NCheckbox :value="index" checked>
                            <div class="flex items-center gap-x-1">
                              <NIcon size="10">
                                <img :src="material.value">
                              </NIcon>
                              <div>{{ material.key }}</div>
                            </div>
                          </NCheckbox>
                        </NGridItem>
                      </NGrid>
                      <div class="mt-2 mb-2">
                        <NButton type="primary" block @click="onGeneratePaperClickFn" :loading="paperGenerateLoading">
                          生成全文
                        </NButton>
                      </div>
                    </div>
                  </NGridItem>
                </NGrid>
                <NIcon size="50" v-else>
                  <img src="~/assets/img/icon_content.png">
                </NIcon>

              </div>
            </div>

            <div class="flex justify-center flex-col pt-14">
              <div class="text-primary text-lg flex justify-center items-center space-x-1 description mb-7">
                <span
                    class="w-[24px] h-[24px] rounded-full border-primary border-2 flex justify-center items-center">3</span>
                <span>生成全文</span>
              </div>
              <ChatMessage :loading="paperGenerateLoading" :text="paperGenerateText"></ChatMessage>
            </div>
          </div>
        </div>-->
    <FloatRight/>
  </NScrollbar>
</template>

<style lang="scss" scoped>
.step {
  width: 30%;
}

.text-normal {
  color: var(--normal-color)
}

.text-primary {
  color: var(--primary-color)
}

.border-primary {
  border-color: var(--primary-color)
}

.config-select--active {
  opacity: 100;
  background-color: white;
  border-color: var(--primary-color);
}

::v-deep .n-collapse-item__header-main {
  padding-top: 10px;
  padding-bottom: 10px;
}

::v-deep .n-collapse .n-collapse-item .n-collapse-item__content-wrapper .n-collapse-item__content-inner {
  padding-top: 0;
}
</style>
