import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Test from '../views/Test.vue'
import Index from '../views/Index.vue'


Vue.use(VueRouter)

const routes = [
  // {
  //   path: '/',
  //   name: 'Home',
  //   component: Home
  // },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: function () {
      return import(/* webpackChunkName: "about" */ '../views/About.vue')
    },
  },
  {
    path: '/test',
    name: 'Test',
    component: Test
  },
  {
    path: '/',
    name: 'Index',
    component: Index
  },
  {
    path: '/user/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/user/register',
    name: 'Register',
    component: () => import('../views/user/Register.vue')
  },
  {
    path: '/user/center',
    name: 'userCenter',
    component: () => import('../views/user/Center.vue')
  },
  {
    path: '/editPersonalInfo/:id',
    name: 'EditPersonalInfo',
    component: () => import('../views/user/EditPersonalInfo.vue')
  },
  {
    path: '/question/:id',
    name: 'QuestionDetail',
    component: () => import('../views/question/Detail.vue')
	
  },
  {
    path: '/writeQuestion',
    name: 'AddQuestion',
    component: () => import('../views/question/AddQuestion.vue')
  },
  {
    path: '/admin/login',
    name: 'AdminLogin',
    component: () => import('../views/admin/Login.vue')
  },
  {
    path: '/admin/index',
    name: 'AdminIndex',
    component: () => import('../views/admin/Index.vue')
  },
  {
    path: '/complain/detail/:id',
    name: 'ComplainDetial',
    component: () => import('../views/complain/Detail.vue')
  },
  {
    path: '/searchPage',
    name: 'SearchPage',
    component: () => import('../views/search/SearchPage.vue')
  },
]

const router = new VueRouter({
  mode: "history",
  routes,
  base: process.env.VUE_APP_PUBLIC_PATH
})

export default router
