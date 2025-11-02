<template>
  <div class="app-card" :class="{ 'app-card--featured': featured }">
    <div class="app-preview">
      <img v-if="app.cover" :src="app.cover" :alt="app.appName" class="app-cover-img" />
      <img v-else class="app-default-ai-icon" src="@/assets/default-ai-cover.png" alt="默认封面" />
      <div class="app-overlay">
        <a-space>
          <a-button type="primary" @click="handleViewChat">查看对话</a-button>
          <a-button v-if="app.deployKey" type="default" @click="handleViewWork">查看作品</a-button>
        </a-space>
      </div>
    </div>
    <div class="app-info">
      <div class="app-info-left">
        <a-avatar :src="app.user?.userAvatar" :size="40">
          {{ app.user?.userName?.charAt(0) || 'U' }}
        </a-avatar>
      </div>
      <div class="app-info-right">
        <h3 class="app-title">{{ app.appName || '未命名应用' }}</h3>
        <p class="app-author">
          {{ app.user?.userName || (featured ? '官方' : '未知用户') }}
        </p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
interface Props {
  app: API.AppVO
  featured?: boolean
}

interface Emits {
  (e: 'view-chat', appId: string | number | undefined): void
  (e: 'view-work', app: API.AppVO): void
}

const props = withDefaults(defineProps<Props>(), {
  featured: false,
})

const emit = defineEmits<Emits>()

const handleViewChat = () => {
  emit('view-chat', props.app.id)
}

const handleViewWork = () => {
  emit('view-work', props.app)
}
</script>

<style scoped>
.app-card {
  background: #fff;
  border-radius: 16px;
  overflow: hidden;
  box-shadow: 0 4px 24px 0 rgba(20,36,66,0.08);
  border: 1.5px solid #f3f5f7;
  transition: box-shadow 0.24s, transform 0.24s;
  cursor: pointer;
}
.app-card:hover {
  transform: translateY(-6px) scale(1.02);
  box-shadow: 0 10px 32px 0 rgba(20,36,66,0.15);
}
.app-preview {
  height: 160px;
  background: #f5f8fc;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  border-radius: 18px 18px 0 0;
}

.app-preview img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.app-placeholder {
  font-size: 48px;
  color: #d9d9d9;
}

.app-cover-img, .app-default-ai-icon {
  height: 110px;
  max-width: 92%;
  object-fit: contain;
  border-radius: 16px;
  margin: 0 auto;
  display: block;
}

.app-overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  opacity: 0;
  transition: opacity 0.3s;
}

.app-card:hover .app-overlay {
  opacity: 1;
}

.app-info {
  padding: 20px 18px 16px 18px;
  display: flex;
  align-items: center;
  gap: 12px;
}

.app-info-left {
  flex-shrink: 0;
}

.app-info-right {
  flex: 1;
  min-width: 0;
}

.app-title {
  font-size: 17px;
  font-weight: 700;
  color: #222;
}
.app-author {
  font-size: 13px;
  color: #8a8f98;
}
</style>
