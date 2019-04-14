import Vue from 'vue';
import VueRouter from 'vue-router';

import First from '@/views/First';
import Second from '@/views/Second';

Vue.use(VueRouter);

export default new VueRouter({
  mode: 'history',
  routes: [
    { path: '/', component: First },
    { path: '/second', component: Second },
  ],
});
