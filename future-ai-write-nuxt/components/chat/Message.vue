<script setup lang="ts">

import MarkdownIt from 'markdown-it'
import mdKatex from '@traptitech/markdown-it-katex'

interface Props {
  text?: string
  loading?: boolean
}

const props = defineProps<Props>()

const mdi = new MarkdownIt({
  html: false,
  linkify: true,
})

mdi.use(mdKatex, { blockClass: 'katexmath-block rounded-md p-[10px]', errorColor: ' #cc0000' })

const wrapClass = computed(() => {
  return [
    'text-wrap',
    'min-w-[20px]',
    'rounded-md'
  ]
})

const text = computed(() => {
  const value = props.text ?? ''
  return mdi.render(value)
})

</script>

<template>
  <div class="text-black" :class="wrapClass">
    <div ref="textRef" class="leading-relaxed break-words">
      <div class="markdown-body" :class="{ 'markdown-body-generate': loading }" v-html="text" />
    </div>
  </div>
</template>

<style scoped lang="scss">
.markdown-body {
  background-color: transparent;

  h1{
    font-size: 28px ;
    font-weight: bold;
  }

  h2{
    font-size: 24px;
    font-weight: bold;
  }

  h3{
    font-size: 20px;
    font-weight: bold;
  }

  h4{
    font-size: 18px;
    font-weight: bold;
  }

  p {
    white-space: pre-wrap;
  }

  ol {
    list-style-type: decimal;
  }

  ul {
    list-style-type: disc;
  }

  pre code,
  pre tt {
    line-height: 1.65;
  }

  .highlight pre,
  pre {
    background-color: #fff;
  }

  code.hljs {
    padding: 0;
  }

  .code-block {
    &-wrapper {
      position: relative;
      padding-top: 24px;
    }

    &-header {
      position: absolute;
      top: 5px;
      right: 0;
      width: 100%;
      padding: 0 1rem;
      display: flex;
      justify-content: flex-end;
      align-items: center;
      color: #b3b3b3;

      &__copy {
        cursor: pointer;
        margin-left: 0.5rem;
        user-select: none;

        &:hover {
          color: #65a665;
        }
      }
    }
  }


  &.markdown-body-generate>dd:last-child:after,
  &.markdown-body-generate>dl:last-child:after,
  &.markdown-body-generate>dt:last-child:after,
  &.markdown-body-generate>h1:last-child:after,
  &.markdown-body-generate>h2:last-child:after,
  &.markdown-body-generate>h3:last-child:after,
  &.markdown-body-generate>h4:last-child:after,
  &.markdown-body-generate>h5:last-child:after,
  &.markdown-body-generate>h6:last-child:after,
  &.markdown-body-generate>li:last-child:after,
  &.markdown-body-generate>ol:last-child li:last-child:after,
  &.markdown-body-generate>p:last-child:after,
  &.markdown-body-generate>pre:last-child code:after,
  &.markdown-body-generate>td:last-child:after,
  &.markdown-body-generate>ul:last-child li:last-child:after {
    animation: blink 1s steps(5, start) infinite;
    color: #000;
    content: '_';
    font-weight: 700;
    margin-left: 3px;
    vertical-align: baseline;
  }

  @keyframes blink {
    to {
      visibility: hidden;
    }
  }
}
</style>