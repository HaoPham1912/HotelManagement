import '@fortawesome/fontawesome-free/css/all.min.css'
import 'bootstrap-css-only/css/bootstrap.min.css'
import 'mdbvue/lib/css/mdb.min.css'
import Vue from 'vue'
import router from './router/router'
import store from '../src/store'
import App from './App.vue'

import VeeValidate  from 'vee-validate'



import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
// Install BootstrapVue
Vue.use(BootstrapVue)

// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)
Vue.use(VeeValidate)

Vue.config.productionTip = false;


new Vue({
  router,
  store,

  render: h => h(App)
}).$mount('#app')
