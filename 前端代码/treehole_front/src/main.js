import Vue from 'vue'
// import App from './App.vue'
import App from './views/layout/common.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'

axios.default.withCredentials = true //允许axiso携带cookie

Vue.config.productionTip = false

Vue.use(ElementUI)

new Vue({
  router,
  store,
  render: function (h) { return h(App) }
// }).$mount('#app')
}).$mount('#app')

// console.log(process.env)
// console.log(process.env.VUE_APP_PUBLIC_PATH)