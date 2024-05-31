import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/pages/Home.vue'
import Signup from '@/pages/Signup.vue'
import Login from '@/pages/Login.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: 'login'
    },
    {
      path: '/home',
      component: Home
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/signup',
      component: Signup
    }
  ]
})

export default router
