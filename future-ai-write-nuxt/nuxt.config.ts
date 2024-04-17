// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
    devtools: {enabled: true},
    runtimeConfig: {
        apiSecret: '',
        public: {
            apiBase: process.env.NUXT_PUBLIC_API_BASE
        }
    },
    tailwindcss: {
        exposeConfig: {
            write: true,
        },
        viewer: true,
    },

    modules: [
        "@bg-dev/nuxt-naiveui",
        '@nuxtjs/tailwindcss',
        [
            '@pinia/nuxt',
            {
                autoImports:[
                    'defineStore',
                    'storeToRefs'
                ]
            }
        ],
        '@pinia-plugin-persistedstate/nuxt',
        '@vueuse/nuxt',
    ]
})
