<script setup lang="ts">
interface Props {
  no?: string
  desc?: string
}

defineProps<Props>()

</script>

<template>
  <div class="hidden sm:flex text-primary text-lg justify-center items-center space-x-1 description mb-7">
    <span class="w-[20px] h-[20px] rounded-full border-primary border-2 flex justify-center items-center">{{no}}</span>
    <span class="font-medium">{{desc}}</span>
  </div>
</template>

<style scoped lang="scss">

</style>