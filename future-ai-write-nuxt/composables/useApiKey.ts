export const useApiKey = defineStore("key", {
    state: () => {
        return {
            apiKey: ''
        }
    },
    actions:{
        setApiKey(apiKey:string){
            this.apiKey = apiKey
        }
    },
    persist: true
})