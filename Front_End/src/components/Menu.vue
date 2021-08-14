<template>
  <sidebar-menu :menu="menu" />
</template>

<script>
    export default {
      name : 'globalmenu',
        data() {
            return {
                menu: [
                    {
                        header: true,
                        title: 'Main Navigation',
                       
                    },
                    {
                        href: '/home',
                        title: 'Dashboard',
                        icon: 'fa fa-user'
                    },
                    {
                        href: '/registerDevice',
                        title: 'Register Device',
                        icon: 'fa fa-user',
                  
                    },
                    {
                        href: '/login',
                        title: 'Logout',
                        icon: 'fa fa-user',
              
                    }
                ]
            }
        }
    }
</script>