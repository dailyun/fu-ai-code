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
      <a-col flex="200px" class="header-right-col">
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



// JS 中引入 Store
import { useLoginUserStore } from '@/stores/loginUser.ts'
const loginUserStore = useLoginUserStore()

const router = useRouter()
// 当前选中菜单
const selectedKeys = ref<string[]>(['/'])
// 监听路由变化，更新当前选中菜单
router.afterEach((to) => {
  selectedKeys.value = [to.path]
})

function HomeOutlined() {

}

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

import { LogoutOutlined } from '@ant-design/icons-vue'
import { userLogout } from '@/api/userController.ts'

// 用户注销
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
  display: flex;
  white-space: nowrap;
}

.ant-menu-horizontal > .ant-menu-item {
  height: 100%;
  line-height: 64px;
  flex: none;
}

.ant-menu-horizontal > .ant-menu-item-hidden {
  display: none !important;
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

.header-menu-col {
  padding: 0 24px;
}

.header-right-col {
  justify-content: flex-end;
  padding-right: 24px;
}

.user-login-status {
  height: 100%;
  display: flex;
  align-items: center;
}
</style>
