import '@fortawesome/fontawesome-free/css/all.min.css'
import 'bootstrap-css-only/css/bootstrap.min.css'
import 'mdbvue/lib/css/mdb.min.css'

import Vue from 'vue'
import VeeValidate  from 'vee-validate'
import { VTooltip, VPopover, VClosePopover } from 'v-tooltip'


import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import router from './router/router'
import App from './App.vue'
// Install BootstrapVue
Vue.use(BootstrapVue)
Vue.use(VTooltip)
Vue.directive('tooltip', VTooltip)
Vue.directive('close-popover', VClosePopover)
Vue.component('v-popover', VPopover)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)
Vue.use(VeeValidate)

Vue.config.productionTip = false;

new Vue({
  router,

  render: h => h(App)
}).$mount('#app')
