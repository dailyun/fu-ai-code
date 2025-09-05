<script setup lang="ts">
import { ref } from 'vue'
const features = ref([
  {
    title: 'AI驱动',
    description: '利用先进的人工智能技术，自动生成应用程序代码'
  },
  {
    title: '快速开发',
    description: '大幅缩短应用开发周期，提高开发效率'
  },
  {
    title: '易于使用',
    description: '简洁直观的界面，无需专业技术背景'
  }
])
</script>

<template>
  <div class="home-page">
    <div class="hero-section">
      <h1 class="hero-title">欢迎使用FUFU应用生成平台</h1>
      <p class="hero-subtitle">一键生成您专属的应用程序</p>
      <a-button type="primary" size="large" class="cta-button">立即开始</a-button>
    </div>
    
    <div class="features-section">
      <a-row :gutter="[24, 24]">
        <a-col :span="8" v-for="feature in features" :key="feature.title">
          <a-card hoverable>
            <template #title>
              <h3>{{ feature.title }}</h3>
            </template>
            <p>{{ feature.description }}</p>
          </a-card>
        </a-col>
      </a-row>
    </div>
  </div>
</template>

<style scoped>
.home-page {
  padding: 24px 0;
}

.hero-section {
  text-align: center;
  padding: 48px 0;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-radius: 8px;
  margin-bottom: 48px;
  color: white;
}

.hero-title {
  font-size: 36px;
  margin-bottom: 16px;
  font-weight: bold;
}

.hero-subtitle {
  font-size: 18px;
  margin-bottom: 32px;
}

.cta-button {
  font-size: 16px;
  height: 48px;
  padding: 0 32px;
}

.features-section {
  margin-bottom: 48px;
}

.features-section :deep(.ant-card) {
  text-align: center;
}

.features-section :deep(.ant-card-head-title) {
  padding: 12px 0;
}

.features-section :deep(.ant-card-body) {
  min-height: 120px;
}
</style>