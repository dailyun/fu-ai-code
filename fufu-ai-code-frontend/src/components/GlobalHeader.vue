<template>
  <a-layout-header class="header">
    <a-row :wrap="false">
      <!-- 左侧：Logo和标题 -->
      <a-col flex="200px">
        <RouterLink to="/">
          <div class="header-left">
            <img class="logo" src="@/assets/logo.png" alt="Logo" />
            <h1 class="site-title">FU-AI 网站生成器</h1>
          </div>
        </RouterLink>
      </a-col>
      <!-- 中间：导航菜单 -->
      <a-col flex="auto">
        <a-menu
          v-model:selectedKeys="selectedKeys"
          mode="horizontal"
          :items="menuItems"
          @click="handleMenuClick"
        />
      </a-col>
      <!-- 右侧：用户操作区域 -->
      <a-col>
        <div class="user-login-status">
          <div v-if="loginUserStore.loginUser.id">
            <a-dropdown>
              <a-space>
                <a-avatar :src="loginUserStore.loginUser.userAvatar" />
                {{ loginUserStore.loginUser.userName ?? '无名' }}
              </a-space>
              <template #overlay>
                <a-menu>
                  <a-menu-item @click="doLogout">
                    <LogoutOutlined />
                    退出登录
                  </a-menu-item>
                </a-menu>
              </template>
            </a-dropdown>
          </div>
          <div v-else>
            <a-button type="primary" href="/user/login">登录</a-button>
          </div>
        </div>
      </a-col>
    </a-row>
  </a-layout-header>
</template>

<script setup lang="ts">
import { computed, h, ref } from 'vue'
import { useRouter } from 'vue-router'
import { type MenuProps, message } from 'ant-design-vue'
import { useLoginUserStore } from '@/stores/loginUser.ts'
import { userLogout } from '@/api/userController.ts'
import { LogoutOutlined, HomeOutlined } from '@ant-design/icons-vue'

const loginUserStore = useLoginUserStore()
const router = useRouter()
// 当前选中菜单
const selectedKeys = ref<string[]>(['/'])
// 监听路由变化，更新当前选中菜单
router.afterEach((to, from, next) => {
  selectedKeys.value = [to.path]
})

// 菜单配置项
const originItems = [
  {
    key: '/',
    icon: () => h(HomeOutlined),
    label: '主页',
    title: '主页',
  },
  {
    key: '/admin/userManage',
    label: '用户管理',
    title: '用户管理',
  },
  {
    key: '/admin/appManage',
    label: '应用管理',
    title: '应用管理',
  },
  {
    key: '/admin/chatManage',

    label: '对话管理',
    title: '对话管理',
  }

]

// 过滤菜单项
const filterMenus = (menus = [] as MenuProps['items']) => {
  return menus?.filter((menu) => {
    const menuKey = menu?.key as string
    if (menuKey?.startsWith('/admin')) {
      const loginUser = loginUserStore.loginUser
      if (!loginUser || loginUser.userRole !== 'admin') {
        return false
      }
    }
    return true
  })
}

// 展示在菜单的路由数组
const menuItems = computed<MenuProps['items']>(() => filterMenus(originItems))

// 处理菜单点击
const handleMenuClick: MenuProps['onClick'] = (e) => {
  const key = e.key as string
  selectedKeys.value = [key]
  // 跳转到对应页面
  if (key.startsWith('/')) {
    router.push(key)
  }
}

// 退出登录
const doLogout = async () => {
  const res = await userLogout()
  if (res.data.code === 0) {
    loginUserStore.setLoginUser({
      userName: '未登录',
    })
    message.success('退出登录成功')
    await router.push('/user/login')
  } else {
    message.error('退出登录失败，' + res.data.message)
  }
}
</script>

<style scoped>
.header {
  background: linear-gradient(90deg, rgba(60,138,255,0.32) 0%, rgba(60,138,255,0.15) 55%, rgba(255,255,255,0.91) 90%);
  border-bottom: 1.5px solid #e2edfa;
  box-shadow: 0 2px 16px 0 rgba(60,138,255,0.07);
  padding: 0 40px;
  border-radius: 0 0 18px 18px;
  backdrop-filter: blur(18px);
  transition: background 0.3s;
  position: relative;
  z-index: 2;
  overflow: hidden;
}
.header::before {
  content: "";
  position: absolute;
  left: 0; top: 0; right: 0; height: 9px;
  background: linear-gradient(90deg, #4f8cff 8%, #dbeafe 100%);
  opacity: 0.19;
  border-radius: 0 0 120px 120px;
  pointer-events: none;
  z-index: 2;
}
.header::after {
  content: "";
  position: absolute;
  left: -70px; bottom: -38px;
  width: 210px; height: 80px;
  background: radial-gradient(circle, rgba(91,154,255,0.16) 0%, rgba(60,138,255,0.11) 60%, transparent 90%);
  z-index: 1;
  filter: blur(6px);
}

.header-left {
  display: flex;
  align-items: center;
  gap: 10px;
  min-width: 0;
}

.logo {
  height: 48px;
  width: 48px;
}

.site-title {
  margin: 0;
  font-size: 20px;
  color: #222;
  font-weight: 700;
  letter-spacing: 1px;
  font-family: 'Inter', 'Segoe UI', Arial, sans-serif;
  max-width: 220px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  display: inline-block;
  vertical-align: middle;
}
@media (max-width: 600px) {
  .site-title {
    font-size: 16px;
    max-width: 140px;
  }
}

.ant-menu-horizontal {
  border-bottom: none !important;
}
.header,
.a-layout-header,
.ant-layout-header {
  background: none !important;
  box-shadow: none !important;
}
.ant-menu-horizontal.ant-menu-root {
  background: transparent !important;
  box-shadow: none !important;
  border-bottom: none !important;
}
</style>
