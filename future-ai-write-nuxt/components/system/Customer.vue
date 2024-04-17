<script setup lang="ts">

import {CustomerServiceOutlined} from '@vicons/antd'
</script>

<template>
  <div class="cursor-pointer last-of-type:border-0 border-b border-gray-100">
    <NPopover trigger="click" placement="left">
      <template #trigger>
        <div
            class="flex box-border flex-col justify-center items-center pt-2 pb-2 pl-1 pr-1 ">
          <NIcon size="30" color="white" :component="CustomerServiceOutlined"/>
          <div class="text-xs">
            <p>客服咨询</p>
            <p>商务合作</p>
          </div>
        </div>
      </template>
      <div class="pl-2.5">
        <div class="flex gap-x-1 items-center">
          <NIcon size="24" color="#999999" :component="CustomerServiceOutlined"/>
          <div class="text-lg font-medium">在线客服</div>
        </div>
        <div class="mt-2">
          <img src="~/assets/img/customer.png" width="240px">
        </div>
      </div>

    </NPopover>

  </div>

</template>

<style scoped lang="scss">

</style>