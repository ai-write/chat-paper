export const useAI = (url: string, payload: Object) => {

    const config = useRuntimeConfig()
    const baseURL = config.public.apiBase

    const requestUrl = baseURL + url
    const complete = async (
        {
            onText = (data: string) => {
            },
            onComplete = () => {
            }
        }
    ) => {

        const {body} = await fetch(requestUrl, {
            method: 'POST',
            headers: {
                accept: 'text/event-stream',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(payload)
        })

        const reader = body?.getReader()
        const decoder = new TextDecoder()
        let finished = false, completion = ''

        let textArr = []

        while (!finished && reader) {
            const {value, done} = await reader.read()
            finished = done
            let chunkValue = decoder.decode(value)
            chunkValue = chunkValue.replaceAll("\n", "")
            chunkValue = chunkValue.replaceAll(" ", "")
            chunkValue = chunkValue.replaceAll("data:", "")
            chunkValue = chunkValue.replaceAll("↖emsp↘", " ")
            chunkValue = chunkValue.replaceAll("↖br↘", "\n")
            textArr.push(chunkValue)
            completion = textArr.join('')
            onText(completion)
        }
        onComplete()
    }

    return {complete}
}