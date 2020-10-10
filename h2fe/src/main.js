
window.Vue = require('vue');
import Vue from 'vue'

import App from './App.vue'
import VueRouter from "vue-router";



/* PrimeVue Control */

Vue.use(VueRouter);


import router from './route';
import store from './store';



// const router = routes;
Vue.config.productionTip = false;

new Vue({
    store,
    router,
    render: h => h(App),
}).$mount('#app');
