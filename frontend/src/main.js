import Vue from 'vue';

import router from './router';

import { Button, Icon } from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';

import App from './App.vue';

Vue.component(Button.name, Button);

Vue.config.productionTip = false;

new Vue({
  router,
  render: h => h(App)
}).$mount('#app');
