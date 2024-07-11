import { createRouter, createWebHistory } from 'vue-router';
import { computed } from 'vue';
import { useStore } from 'vuex';

import Signup from '@/pages/Signup.vue';
import Login from '@/pages/Login.vue';
import Profile from '@/pages/profile/ViewProfile.vue';
import EditProfile from '@/pages/profile/EditProfile.vue';

const store = useStore();

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: 'login'
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/signup',
      component: Signup
    },
    {
      path: '/contacts',
      component: ViewContacts
    },
    {
      path: '/contact/:username/edit',
      component: EditContact
    },
    {
      path: '/contact/new',
      component: NewContact
    },
    {
      path: '/messages',
      component: Messages
    },
    {
      path: '/profile',
      component: Profile
    },
    {
      path: '/profile/edit',
      component: EditProfile
    }
  ]
});

// Global Navigation Guard
// Currently Does Not Work
router.beforeEach((to, from, next) => {
  const isAuthenticated = computed(() => store.getters['user/isAuthenticated']);
  const publicPages = ['/login', '/signup'];
  const authRequired = !publicPages.includes(to.path);

  if(authRequired && !isAuthenticated){
    return next({ path: '/login' });
  }else{
    return next();
  }
});

export default router

// Note for future, look into using Fieldset for messages