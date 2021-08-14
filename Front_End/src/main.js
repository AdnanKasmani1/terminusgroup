import Vue from 'vue'
import App from './App.vue'
import router from './router'
import VueSidebarMenu from 'vue-sidebar-menu'


import 'bootstrap/dist/css/bootstrap.min.css'
import 'vue-sidebar-menu/dist/vue-sidebar-menu.css'
import '@/assets/css/main.css'

import axios from 'axios';
Vue.prototype.$http = axios;
Vue.use(VueSidebarMenu)

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
