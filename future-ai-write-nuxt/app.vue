<template>
  <NConfigProvider :theme-overrides="themeOverrides">
    <NMessageProvider>
      <NuxtLayout>
        <NuxtPage />
      </NuxtLayout>
    </NMessageProvider>
  </NConfigProvider>
</template>
<script setup lang="ts">
import { NConfigProvider, type GlobalThemeOverrides } from 'naive-ui'

const themeOverrides: GlobalThemeOverrides = {
  common: {
    primaryColor: '#A33AFE',
    hoverColor:'#A33AFE',
    infoColorHover:'#A33AFE',
  },
  Input:{
    borderHover:'#A33AFE',
    borderFocus:'#A33AFE',
  },
  Cascader:{
    borderHover:'#A33AFE',
    borderFocus:'#A33AFE',
  },
  InternalSelection:{
    borderHover:'#A33AFE',
    borderFocus:'#A33AFE',
  },
  Button:{
    borderHoverPrimary:'#A33AFE',
    borderFocusPrimary:'#A33AFE',
    borderPressedPrimary:'#782AFF',
    colorHoverPrimary:'#A33AFE',
    colorFocusPrimary:'#A33AFE',
    colorPressedPrimary:'#782AFF',
  },
  Collapse:{
    dividerColor: '#FFFFFF',
    titlePadding: '10',
    itemMargin:'0',
    titleTextColor:'#050910',
    titleFontSize:'16px',
    titleFontWeight:'400'
  },
}
</script>