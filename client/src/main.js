import 'material-design-icons-iconfont/dist/material-design-icons.css'
import Vue from 'vue'
import App from './App.vue'

import Vuetify from 'vuetify';
import VueRouter from 'vue-router';
import 'vuetify/dist/vuetify.min.css';
import store from './store/store';
import { il8n } from './il8n';

Vue.config.productionTip = false;
Vue.use(Vuetify, {
  iconfont: 'mdi'
});
Vue.use(VueRouter);

import routes from './routes';

const router = new VueRouter({
  routes: routes,
  mode:'history'
});

new Vue({
  router: router,
  store,
  i18n: il8n,
  render: h => h(App),
}).$mount('#app');
