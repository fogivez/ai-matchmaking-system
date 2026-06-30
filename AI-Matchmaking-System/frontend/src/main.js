import App from './App.vue';

// 兼容免构建环境的标准Vue3挂载逻辑
const { createApp } = Vue;
createApp(App).mount('#app');