import type {IResult} from "~/types/IResult";

type FetchType = typeof $fetch
type ReqType = Parameters<FetchType>[0]
type FetchOptions = Parameters<FetchType>[1]

export function httpRequest<T = unknown>(
    method: any,
    url: ReqType,
    body?: any,
    opts?: FetchOptions,
) {
    const token = useCookie('token')
    const router = useRouter()
    const route = useRoute()
    const config = useRuntimeConfig()
    const baseURL = config.public.apiBase
    const message = useMessage()

    const defaultOpts = {
        method,
        timeout: 5 * 60 * 1000,
        baseURL: baseURL,
        headers: {token: token.value} as any,
        body,
        onResponse({response}) {
            const {success, data, msg} = response._data
            if (!success) {
                message.error(msg)
                if (response._data && response._data.code) {
                    return Promise.reject(response._data)
                }
            }
            response._data = data
        },
        onRequestError() {
            // message.error('请求出错，请重试！')
        },
        onResponseError({response}) {
            switch (response.status) {
                case 400:
                    // message.error('参数错误')
                    break
                case 401:
                    // message.error('没有访问权限')
                    router.push(`/login?callback=${route.path}`)
                    break
                case 403:
                    // message.error('服务器拒绝访问')
                    break
                case 404:
                    // message.error('请求地址错误')
                    break
                case 500:
                    // message.error('服务器故障')
                    break
                default:
                    // message.error('网络连接故障')
                    break
            }
        },
    } as FetchOptions
    return $fetch<T>(url, {...defaultOpts, ...opts})
}

export function httpPost<T = unknown>(
    request: ReqType,
    body?: any,
    opts?: FetchOptions,
) {
    return httpRequest<T>('post', request, body, opts)
}

export function httpGet<T = unknown>(
    request: ReqType,
    opts?: FetchOptions,
) {
    return httpRequest<T>('get', request, null, opts)
}
