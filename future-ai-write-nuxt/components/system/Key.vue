<script setup lang="ts">

import {Key} from '@vicons/ionicons5'
import type {FormInst} from "naive-ui";
import {useApiKey} from "~/composables/useApiKey";

const confirmKeyRef = ref<FormInst | null>(null)
const confirmKeyVisible = ref(false)
const confirmKeyForm = ref({
  apiKey:''
})
const confirmKeyRules = {
  apiKey:{
    required: true,
    message: '请输入Key',
    trigger: ['input', 'blur']
  }
}

const onSetKeyClick = (e: MouseEvent) => {
  e.preventDefault()
  confirmKeyVisible.value = true
}

const apiKey = useApiKey()

const onApiKeySubmit = (e: MouseEvent)=>{
  e.preventDefault()
  confirmKeyRef.value?.validate((errors)=>{
    if (!errors){
      apiKey.setApiKey(confirmKeyForm.value?.apiKey)
      confirmKeyVisible.value = false
    }
  })
}
</script>

<template>
  <div
      @click="onSetKeyClick"
      class="flex box-border flex-col justify-center items-center pt-2 pb-2 pl-1 pr-1 cursor-pointer last-of-type:border-0 border-b border-gray-200">
    <NIcon size="30" color="white" :component="Key"/>
    <div class="text-xs">key配置</div>
  </div>
  <NModal v-model:show="confirmKeyVisible">
    <div class="w-[85%] md:w-[70%] lg:w-[60%] p-4 bg-white rounded-lg">
      <div class="text-xl mb-4">配置Key</div>
      <NForm
          :rules="confirmKeyRules"
          :label-width="0"
          :model="confirmKeyForm"
          ref="confirmKeyRef">
        <NFormItem path="apiKey">
          <NInput v-model:value="confirmKeyForm.apiKey" placeholder="请输入Key"/>
        </NFormItem>
      </NForm>
      <div class="flex justify-end">
        <NButton @click="onApiKeySubmit" type="primary">提交</NButton>
      </div>
    </div>
  </NModal>
</template>

<style scoped lang="scss">

</style>