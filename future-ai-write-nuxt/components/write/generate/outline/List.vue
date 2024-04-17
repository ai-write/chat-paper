<script setup lang="ts">
interface Props {
  outlines?: Array<PaperOutline>
}

defineProps<Props>()
</script>

<template>
  <div class="flex md:hidden items-end mb-2.5">
    <div class="text-normal text-base font-medium pr-1.5">论文大纲</div>
    <div class="text-description">可添加、删除、编辑</div>
  </div>
  <div class="bg-white md:p-5 rounded-3xl md:rounded-none overflow-hidden">
    <div class="hidden md:flex gap-x-2.5 items-end mb-4">
      <div class="text-normal font-medium text-lg">论文大纲</div>
      <div class="text-neutral-500 text-sm">可添加、删除、编辑</div>
    </div>
    <NCollapse accordion>
      <NCollapseItem
          class="bg-neutral-100"
          v-for="outline in outlines"
          :key="outline.id"
          :title="`${outline.chapter} ${outline.title}`"
          :name="outline.id">
        <div class="bg-white pl-5 pt-6 pb-6 ">
          <div class=""
               v-for="child in outline.children"
               :key="child.id">
            <div class="text-gray-950 txt-base font-medium mb-1.5">{{ child.chapter }} {{
                child.title
              }}
            </div>
            <div class="mb-5 text-gray-400 txt-base">{{ child.description }}</div>
          </div>
        </div>
      </NCollapseItem>
    </NCollapse>
  </div>
</template>

<style scoped lang="scss">
.n-collapse {
  overflow: hidden;
}

.bg-gray{
  background-color: #f5f5f5;
}

.bg-white{
  background-color: #FFFFFF;
}
</style>