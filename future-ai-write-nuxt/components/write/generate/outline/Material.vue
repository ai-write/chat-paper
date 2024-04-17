<script setup lang="ts">
interface Props {
  materials?: Array<WriteConfigMaterial>,
  properties?: Array<KeyValue>
}

const props = defineProps<Props>()

const title = computed(() => getValue("title"))
const degree = computed(() => getValue("degree"))

const getValue = (propertyName: string): string => {
  const data = props.properties?.find(prop => prop.key === propertyName)
  return data?.value || ''
}
</script>

<template>
  <div class="flex md:hidden items-end mb-2.5 mt-4">
    <div class="text-normal text-base font-medium pr-1.5">您将获得</div>
  </div>
  <div class="bg-white p-2.5 md:p-5 rounded-3xl md:rounded-none overflow-hidden">
    <div class="hidden md:block text-normal font-medium text-lg mb-4">材料清单</div>
    <div class="flex gap-x-1.5 items-start mb-1.5">
      <NIcon size="15">
        <img src="~/assets/icon/icon_paper.png">
      </NIcon>
      <div class="flex flex-col justify-start">
        <div class="text-normal font-medium text-base">{{ title }}</div>
        <div class="text-gray-400 text-sm mt-1">{{ degree }}</div>
      </div>
    </div>
    <div class="flex justify-between flex-wrap">
      <NCheckbox
          v-for="(material, index) in materials"
          :value="index"
          checked>
        <div class="flex items-center gap-x-1">
          <NIcon size="10">
            <img :src="material.value">
          </NIcon>
          <div>{{ material.key }}</div>
        </div>
      </NCheckbox>
    </div>
    <div class="mt-2 mb-2">
      <NButton
          class="rounded-full md:rounded-xl"
          type="primary"
               size="large"
               block>
        生成全文
      </NButton>
    </div>
  </div>
</template>

<style scoped lang="scss">

</style>