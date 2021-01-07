import Vue from 'vue';
import Router from 'vue-router';
import store from "../store";

import Dashboard from '../components/views/Dashboard.vue';
import EmployeeDashboard from '../components/views/EmployeeDashboard.vue';
import HomePage from '../components/views/HomePage.vue';

import CustomerTable from '../components/table/CustomerTable.vue';
import AccountTable from '../components/table/AccountTable.vue';
import EmployeeTable from '../components/table/EmployeeTable.vue';
import BookingTable from '../components/table/BookingTable.vue'
import BranchTable from '../components/table/BranchTable.vue';
import RoomTable from '../components/table/RoomTable.vue'
import PolicyTable from '../components/table/PolicyTable.vue'
import BedTable from '../components/table/BedTable.vue';
import ServicesTable from '../components/table/ServicesTable.vue';
import PromotionTable from '../components/table/PromotionTable.vue';
import BillTable from '../components/table/BillTable.vue';


import Login from '../components/form/Login.vue';
import Register from '../components/form/Register.vue';
import AddNewEmployee from '../components/form/AddEmployee.vue';

import AddPolicy from '../components/form/AddPolicy.vue';
import AddRoom from '../components/form/AddRoom.vue';
import AddBed from '../components/form/AddBed.vue';
import AddService from '../components/form/AddService.vue';
import AddPromo from '../components/form/AddPromo.vue';
import AddBranch from '../components/form/AddBranch.vue';

import EditService from '../components/form/EditService.vue';
import EditCustomer from '../components/form/EditCustomer.vue';
import EditProfie from '../components/form/EditProfie.vue';
import EditEmployee from '../components/form/EditEmployee.vue';
import EditBranch from '../components/form/EditBranch.vue';
import EditRoom from '../components/form/EditRoom.vue';

import DetailBranch from '../components/page/DetailBranch.vue';
import DetailBill from '../components/form/DetailBill.vue';
import DetailRoom from '../components/page/DetailRoom.vue';

Vue.use(Router);
// const ifNotAuthenticated = (to, from, next) => {
//   if (!store.getters.isAuthenticated) {
//     next();
//     return;
//   }
//   next("/");
// };

// const ifAuthenticated = (to, from, next) => {
//   if (store.getters.isAuthenticated) {
//     next();
//     return;
//   }
//   next("/login");
// };
const router = new Router({
    mode: 'history',
    routes: [
        {
          path:"*",
          redirect:"/home",
          
        },
        {
          path:"/home",
          component: HomePage,
          //beforeEnter: ifAuthenticated,
          meta: { authorize: '' } 
        },
        {
        path:'/admin', component:Dashboard,
        name:'AdminDashboard',
        meta: { authorize: 'ADMIN' } ,
        children:[    
          {
          path: '/admin/account',
          name: 'Account',
          component: AccountTable,
          props: { page: 1 },
          meta: { authorize: 'ADMIN' } ,
        },
        {
          path: '/admin/customer',
          name: 'Customer',
          component: CustomerTable,
          props: { page: 2 },
          meta: { authorize: 'ADMIN' } ,
        },
        {
          path: '/admin/employee',
          name: 'Employee',
          component: EmployeeTable,
          props: { page: 3 },
          meta: { authorize: 'ADMIN' } ,
        },
        {
          path: '/admin/booking',
          name: 'Booking',
          component: BookingTable,
          props: { page: 4 },
          meta: { authorize: 'ADMIN' } ,
        },
        {
          path: '/admin/bill',
          name: 'BillTable',
          component: BillTable,
          props: { page: 5 },
          meta: { authorize: 'ADMIN' } ,
        },
        
        {
          path: '/admin/room',
          name: 'Room',
          component: RoomTable,
          props: { page: 6 },
          meta: { authorize: 'ADMIN' } ,
        },
        {
          path: '/admin/policy',
          name: 'PolicyTable',
          component: PolicyTable,
          props: { page:  7},
          meta: { authorize: 'ADMIN' } ,
        },
        {
          path: '/admin/bed',
          name: 'Bed',
          component: BedTable,
          props: { page: 8 },
          meta: { authorize: 'ADMIN' } ,
        },
        {
          path: '/admin/services',
          name: 'Services',
          component: ServicesTable,
          props: { page: 9 },
          meta: { authorize: 'ADMIN' } ,
        },
        {
          path: '/admin/branch',
          name: 'Branch',
          component: BranchTable,
          props: { page: 10 },
          meta: { authorize: 'ADMIN' } ,
        },
        {
          path: '/admin/promotion',
          name: 'Promotion',
          component: PromotionTable,
          props: { page: 11 },
          meta: { authorize: 'ADMIN' } ,
        },
        {
          path: '/admin/add-employee',
          name: 'Add New Emp',
          component: AddNewEmployee,
          meta: { authorize: 'ADMIN' } ,
        },
        {
          path: '/admin/add-policy',
          name: 'Add New Policy',
          component: AddPolicy,
          meta: { authorize: 'ADMIN' } ,
        },
        {
          path: '/admin/add-room',
          name: 'AddNewRoom',
          component: AddRoom,
          meta: { authorize: 'ADMIN' } ,
        },
        {
          path: '/admin/add-bed',
          name: 'Add New Bed',
          component: AddBed,
          meta: { authorize: 'ADMIN' } ,
        },
        {
          path: '/admin/add-service',
          name: 'Add New Service',
          component: AddService,
          meta: { authorize: 'ADMIN' } ,
        },
        {
          path: '/admin/add-promo',
          name: 'Add New Promotion',
          component: AddPromo,
          meta: { authorize: 'ADMIN' } ,
        },
        {
          path: '/admin/add-branch',
          name: 'Add New Branch',
          component: AddBranch,
          meta: { authorize: 'ADMIN' } ,
        },
        {
          path: '/admin/branch/:id',
          name: 'Update Branch',
          component: EditBranch,
          meta: { authorize: 'ADMIN' } ,
        },
        {
          path: '/admin/room/:id',
          name: 'EditRoom',
          component: EditRoom,
          meta: { authorize: 'ADMIN' } ,
        },
        {
          path: '/admin/detail-branch/:branchCode',
          name: 'Branch Detail',
          component: DetailBranch,
          meta: { authorize: 'ADMIN' } ,
        },
        {
          path: '/admin/detail-room/:roomCode',
          name: 'Room Detail',
          component: DetailRoom,
          meta: { authorize: 'ADMIN' } ,
        },
        {
          path: '/admin/services/:code',
          name: 'Update Service',
          component: EditService,
          meta: { authorize: 'ADMIN' } ,
          props: true,
        },
        {
          path: '/admin/customer/:id',
          name: 'Update Customer',
          component: EditCustomer,
          meta: { authorize: 'ADMIN' } ,
          props: true,
        },
        {
          path: '/admin/employee/:id',
          name: 'Update Employee',
          component: EditEmployee,
          meta: { authorize: 'ADMIN' } ,
          props: true,
        },
        {
          path: '/admin/detailBill/:id',
          name: 'AdminDetailBill',
          component: DetailBill,
          meta: { authorize: 'ADMIN' } ,
          props: true,
        },
        {
          path: '/admin/editProfie',
          name: 'editProfie',
          component: EditProfie,
          meta: { authorize: 'ADMIN' } ,
          props: true,
        },
        { path: '/reloadService', redirect: '/admin/services' },
        { path: '/reloadAccount', redirect: '/admin/account' },
        { path: '/reloadPolicy', redirect: '/admin/policy' },
        { path: '/reloadEmployee', redirect: '/admin/employee' },
        { path: '/reloadPromo', redirect: '/admin/promotion' },
        { path: '/reloadBranch', redirect: '/admin/branch' },
        { path: '/reloadRoom', redirect: '/admin/room' },
        { path: '/redirectHome', redirect: '/home' }
      ]
      },
        {
          path: '/login',
          name: 'Login',
          component: Login,
         
        },
        {
          path: '/register',
          name: 'Register',
          component: Register,
        },
        {
          path:'/emp',
          name: 'EmployeeDashboard',
          component:EmployeeDashboard,
          meta: { authorize: 'EMPLOYEE'} ,
          children:[
              {
                path: '/emp/editProfie',
                name: 'editProfie',
                component: EditProfie,
                props: true,
              },
              {
                path: '/emp/detailBill/:id',
                name: 'EmpDetailBill',
                component: DetailBill,
                props: true,
              },
              {
                path: '/emp/booking',
                name: 'Booking',
                component: BookingTable,
                props: { page: 4 },
              },
              {
                path: '/emp/bill',
                name: 'BillTable',
                component: BillTable,
                props: { page: 5 },
              },
          ]
        }
      ]
});
router.beforeEach((to, from, next) => {
  // redirect to login page if not logged in and trying to access a restricted page
  const { authorize } = to.meta;
  const currentUser = store.getters.currentRole;

  if (authorize) {
      if (!currentUser) {
          // not logged in so redirect to login page with the return url
          return next({ path: '/login'});
      }

      // check if route is restricted by role
      if (authorize.length && !currentUser.includes(authorize)) {
          alert("You have not permission to access this page!!!!");
          // role not authorised so redirect to home page
          return next({ path: '/home' });
      }
  }
  next();
})
export default router;