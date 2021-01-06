import '@fortawesome/fontawesome-free/css/all.min.css'
import 'bootstrap-css-only/css/bootstrap.min.css'
import 'mdbvue/lib/css/mdb.min.css'

import VuejsDialog from 'vuejs-dialog';

 
// include the default style
import 'vuejs-dialog/dist/vuejs-dialog.min.css';

import Vue from 'vue'
import VeeValidate  from 'vee-validate'
import { VTooltip, VPopover, VClosePopover } from 'v-tooltip'

import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import firebase from 'firebase'

import router from './router/router'
import store from './store'
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

Vue.use(VuejsDialog);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App),
  created () {
    var firebaseConfig = {
      apiKey: 'AIzaSyAF6fHzPK8nDH0YyuhOPLrUvQw-F1KZMIM',
      authDomain: 'h2hotel-7b674.firebaseapp.com',
      databaseURL: 'https://h2hotel-7b674-default-rtdb.firebaseio.com',
      projectId: 'h2hotel-7b674',
      storageBucket: 'h2hotel-7b674.appspot.com',
      messagingSenderId: '940644779162',
      appId: '1:940644779162:web:5d9c8d36a45f67f3c70b23',
      measurementId: 'G-2TVP58Z3KJ',
    };
    // Initialize Firebase
    firebase.initializeApp(firebaseConfig);
    firebase.analytics();
  }
}).$mount('#app')
