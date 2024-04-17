<script setup lang="ts">
// 获取默认配置，并跳转至对应的页面
const configList: Array<WriteConfig> = await httpGet('/write/config/list')
const defaultConfig = configList.find(item => item.isDefault)
const router = useRouter()
router.push(`/write/${defaultConfig?.configKey || 'bylw'}`)
</script>

<template>
</template>

<style scoped>

</style>
