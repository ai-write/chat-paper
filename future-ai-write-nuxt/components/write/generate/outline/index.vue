<script setup lang="ts">
interface Props {
  outlines?: Array<PaperOutline>,
  percentage?: number,
  loading?: boolean,
  materials?: Array<WriteConfigMaterial>,
  properties?: Array<KeyValue>
}

const props = defineProps<Props>()

</script>

<template>
  <div class="flex justify-center flex-col pt-20">
    <WriteStepNo no="2" desc="自动生成大纲"/>
    <div class="flex flex-col justify-center items-center pt-1 pb-1">
      <div class="flex flex-col justify-center items-center space-y-3"
           v-if="loading">
        <NProgress
            type="circle"
            color="#782AFF"
            :stroke-width="4"
            :processing="true"
            :percentage="percentage"/>
      </div>
      <div class="w-full flex flex-wrap gap-y-1.5 md:gap-x-3" v-else-if="outlines?.length>0">
        <div class="w-full md:w-2/3">
          <WriteGenerateOutlineList :outlines="outlines"/>
        </div>
        <div class="w-full md:flex-1">
          <WriteGenerateOutlineMaterial :materials="materials" :properties="properties"/>
        </div>
      </div>
      <div v-else>
        <NIcon size="50">
          <img src="~/assets/img/icon_content.png">
        </NIcon>
      </div>
<!--      <div class="w-full flex flex-wrap md:gap-x-1.5">
        <div class="w-full md:w-2/3">
          <WriteGenerateOutlineList :outlines="outlines"/>
        </div>
        <div class="w-full md:flex-1">
          <WriteGenerateOutlineMaterial :materials="materials" :properties="properties"/>
        </div>
      </div>-->
    </div>
  </div>
</template>

<style scoped lang="scss">

</style>