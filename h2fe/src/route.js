import Vue from "vue";
import VueRouter from "vue-router";


Vue.use(VueRouter);



const router = new VueRouter({
    mode: "history",
    routes: appRoutes,
    scrollBehavior: function(to, from, savedPosition) {
        if(savedPosition){
            return savedPosition;
        } else {
            const position = {};
            if (to.hash) {
                position.selector = to.hash;
                return false;
            }
        }
    },
});


export default router;
