<template>
  <a-layout-footer class="footer">
    <div class="footer-content">
      <p class="copyright">
        FU-AI 网站生成器 © 2025 by Dali
      </p>
    </div>
  </a-layout-footer>
</template>

<script setup lang="ts">
// 无需额外的响应式数据
</script>

<style scoped>
.footer {
  background: rgba(255,255,255,0.7);
  backdrop-filter: blur(16px);
  text-align: center;
  padding: 24px 0;
  border-top: 1px solid #ececec;
  font-size: 15px;
  color: #888;
  box-shadow: none;
}

.copyright {
  margin: 0;
  color: #666;
  font-size: 14px;
}
</style>
