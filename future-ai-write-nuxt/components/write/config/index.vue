<script setup lang="ts">
import type {FormInst, FormRules} from "naive-ui"
import {PaperPlane} from '@vicons/fa'

interface Props {
  configKey?: string,
  loading: boolean
}

const props = defineProps<Props>()
const configList: Array<WriteConfig> = await httpGet('/write/config/list')

const writeRequestForm = ref({})
const writeRequestFormRef = ref(<FormInst>{});
const writeRequestFormRules: FormRules = {}

const writeConfigDetail: WriteConfigDetail = await httpGet('/write/config/detail', {
  params: {configKey: props.configKey}
})
const {materialList, inputs: configInputList} = writeConfigDetail
configInputList.forEach(input => {
  if (input.rule) {
    writeRequestFormRules[input.formKey] = JSON.parse(input.rule)
  }
})

const emits = defineEmits(['onGenerateOutlineSubmit'])
const onGenerateOutlineClick = (e: MouseEvent) => {
  e.preventDefault()
  writeRequestFormRef?.value.validate((errors) => {
    if (!errors) {
      const forms = Object.keys(writeRequestForm.value).map(key => {
        return<KeyValue> {
          key,
          value: writeRequestForm.value[key]
        }
      })
      const params:PaperOutlineSubmit = {
        forms,
        materials:materialList
      }
      emits('onGenerateOutlineSubmit', params)
    }
  })
}
</script>

<template>
  <div class="w-full">
    <WriteStepNo no="1" desc="输入论文题目，生成千字大纲"/>
    <div class="flex justify-center pt-7 mb-4">
      <NSpace>
        <div
            class="rounded-lg cursor-pointer bg-white border opacity-60 pt-1 pb-1 pl-3 pr-3"
            v-for="config in configList"
            :key="config.configKey"
            :class="{ 'config-select--active': config.configKey === configKey }"
            @click="$router.push(`/write/${config.configKey}`)">
          <div class="flex justify-center items-center gap-2">
            <NIcon size="30">
              <img :src="config.configIcon" alt="">
            </NIcon>
            {{ config.configName }}
          </div>
        </div>
      </NSpace>
    </div>
<!--    <div class="flex items-center flex-wrap">
      <div class="w-full bg-red-400">1</div>
      <div class="w-1/2 md:w-1/3 bg-amber-300">1</div>
      <div class="w-full md:w-2/3 bg-blue-400">1</div>
      <div class="w-full bg-red-400">1</div>
    </div>-->
    <NForm
        ref="writeRequestFormRef"
        :rules="writeRequestFormRules"
        label-placement="left"
        :inline="false"
        :model="writeRequestForm">
      <div class="flex items-center flex-wrap md:gap-x-1.5">
        <div :class="input.span"
             v-for="(input,index) in configInputList"
             :key="input.name">
          <NFormItem
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
                class="w-full"
                v-if="input.formType==='cascader'"
                v-model:value="writeRequestForm[input.formKey]"
                :options="input.dictData" :placeholder="input.description">
            </NCascader>
          </NFormItem>
        </div>
        <div class="w-full flex justify-center">
          <NButton
              class="text-base rounded-full md:rounded-xl h-12 pl-7 pr-7"
              type="primary"
              size="large"
              icon-placement="right"
              :loading="loading"
              @click="onGenerateOutlineClick">
            <template #icon>
              <NIcon size="20">
                <PaperPlane/>
              </NIcon>
            </template>
            极速生成大纲
          </NButton>
        </div>
      </div>
    </NForm>
  </div>

</template>

<style scoped lang="scss">
.config-select--active {
  opacity: 100;
  background-color: white;
  border-color: var(--primary-color);
}
</style>