import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/signup',
    name: 'signup',
    component: () => import('../components/Signup.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../components/Login.vue')
  },

  {
    path: '/home',
    name: 'home',
    component: () => import('../components/Home.vue')


  },

  {
    path: '/registerdevice',
    name: 'registerdevice',
    component: () => import('../components/RegisterDevice.vue')


  }
]




const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
router.beforeEach((to, from, next) => {

  if(to.name=='login')
  {
  localStorage.removeItem('user-token')
  next();
  }
  else if (to.name != 'login' && to.name != 'signup' && localStorage.getItem('user-token')==null)  
  next({ name: 'login' })
  else 
  next()
})



export default router
