
window.Vue = require('vue');
import Vue from 'vue'

import App from './App.vue'
import VueRouter from "vue-router";
import VueSidebarMenu from 'vue-sidebar-menu'
import 'vue-sidebar-menu/dist/vue-sidebar-menu.css'

/* PrimeVue Control */
Vue.use(VueRouter);
Vue.use(VueSidebarMenu);

import router from './route';
import store from './store';

// const router = routes;
Vue.config.productionTip = false;

new Vue({
    store,
    router,
    render: h => h(App),
}).$mount('#app');
