<script setup lang="ts">

</script>

<template>
  <div class="full-bg">
    <NLayout position="absolute">
      <!--      <NLayoutHeader>这是头部内容</NLayoutHeader> -->
      <NLayoutContent class="layout-content">
        <slot />
      </NLayoutContent>
<!--      <NLayoutFooter position="absolute">
        <div class="bg-stone-950">
          这里写版权
        </div>
      </NLayoutFooter>-->
    </NLayout>
  </div>
</template>

<style lang="scss" scoped>
.full-bg {
  position: relative;
  width: 100%;
  height: 100%;
  background-image: url("~/assets/img/global_bg.png");
  background-size: cover;
  background-position: center center;
  background-repeat: no-repeat;
}

.n-layout, .n-layout-header, .n-layout-footer {
  background-color: transparent;
}

.layout-content{
  //bottom: 60px;
}
</style>
