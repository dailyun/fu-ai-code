<template>
  <a-layout-header class="header">
    <a-row :wrap="false" align="middle" class="header-row">
      <!-- 左侧：Logo和标题 -->
      <a-col flex="200px" class="header-left-col">
        <RouterLink to="/">
          <div class="header-left">
            <img class="logo" src="@/assets/logo.png" alt="Logo" />
            <h1 class="site-title">FUFU应用生成</h1>
          </div>
        </RouterLink>
      </a-col>
      <!-- 中间：导航菜单 -->
      <a-col flex="auto" class="header-menu-col">
        <a-menu
          v-model:selectedKeys="selectedKeys"
          mode="horizontal"
          :items="menuItems"
          @click="handleMenuClick"
        />
      </a-col>
      <!-- 右侧：用户操作区域 -->
      <a-col class="header-right-col">
        <div class="user-login-status">
          <a-button type="primary">登录</a-button>
        </div>
      </a-col>
    </a-row>
  </a-layout-header>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import type { MenuProps } from 'ant-design-vue'

const router = useRouter()
// 当前选中菜单
const selectedKeys = ref<string[]>(['/'])
// 监听路由变化，更新当前选中菜单
router.afterEach((to) => {
  selectedKeys.value = [to.path]
})

// 菜单配置项
const menuItems = ref([
  {
    key: '/',
    label: '首页',
    title: '首页',
  },
  {
    key: '/about',
    label: '关于',
    title: '关于我们',
  },
])

// 处理菜单点击
const handleMenuClick: MenuProps['onClick'] = (e) => {
  const key = e.key as string
  selectedKeys.value = [key]
  // 跳转到对应页面
  if (key.startsWith('/')) {
    router.push(key)
  }
}
</script>

<style scoped>
.header {
  background: #fff;
  padding: 0;
  height: 64px;
}

.header-row {
  height: 100%;
  margin: 0;
}

.header-left {
  display: flex;
  align-items: center;
  gap: 12px;
  height: 100%;
}

.logo {
  height: 32px;
  width: 32px;
}

.site-title {
  margin: 0;
  font-size: 18px;
  color: #1890ff;
  white-space: nowrap;
}

.ant-menu-horizontal {
  border-bottom: none !important;
  height: 64px;
  line-height: 64px;
}

.ant-menu-horizontal > .ant-menu-item {
  height: 100%;
  line-height: 64px;
}

.header-left-col,
.header-menu-col,
.header-right-col {
  background: #fff;
  height: 100%;
  display: flex;
  align-items: center;
}

.header-left-col {
  padding-left: 24px;
}

.header-right-col {
  padding-right: 24px;
}

.user-login-status {
  height: 100%;
  display: flex;
  align-items: center;
}
</style>
