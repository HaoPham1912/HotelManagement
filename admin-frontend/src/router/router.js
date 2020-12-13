import Vue from 'vue';
import Router from 'vue-router';

import Dashboard from '../components/views/Dashboard.vue';
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

import Chart from '../components/views/Chart.vue';
import Login from '../components/form/Login.vue';
import Register from '../components/form/Register.vue';
import AddNewEmployee from '../components/form/AddEmployee.vue';
import AddCustomer from '../components/form/AddCustomer.vue';
import AddPolicy from '../components/form/AddPolicy.vue';
import AddRoom from '../components/form/AddRoom.vue';
import AddBed from '../components/form/AddBed.vue';
import AddService from '../components/form/AddService.vue';
import AddPromo from '../components/form/AddPromo.vue';
import AddBranch from '../components/form/AddBranch.vue';

import DetailBranch from '../components/page/DetailBranch.vue';

Vue.use(Router);

export default new Router({
    mode: 'history',
    routes: [
        {
        path:'/', component:Dashboard,
        children:[    {
          path: '/account',
          name: 'Account',
          component: AccountTable,
          props: { page: 1 },
        },
        {
          path: '/customer',
          name: 'Customer',
          component: CustomerTable,
          props: { page: 2 },
        },
        {
          path: '/employee',
          name: 'Employee',
          component: EmployeeTable,
          props: { page: 3 },
        },
        {
          path: '/booking',
          name: 'Booking',
          component: BookingTable,
          props: { page: 4 },
        },
        {
          path: '/revenue',
          name: 'Chart',
          component: Chart,
          props: { page: 5 },
        },
        
        {
          path: '/room',
          name: 'Room',
          component: RoomTable,
          props: { page: 6 },
        },
        {
          path: '/policy',
          name: 'PolicyTable',
          component: PolicyTable,
          props: { page:  7},
        },
        {
          path: '/bed',
          name: 'Bed',
          component: BedTable,
          props: { page: 8 },
        },
        {
          path: '/service',
          name: 'Services',
          component: ServicesTable,
          props: { page: 9 },
        },
        {
          path: '/branch',
          name: 'Branch',
          component: BranchTable,
          props: { page: 10 },
        },
        {
          path: '/promotion',
          name: 'Promotion',
          component: PromotionTable,
          props: { page: 11 },
        },
        {
          path: '/employee/add',
          name: 'Add New Emp',
          component: AddNewEmployee,
        },
        {
          path: '/customer/add',
          name: 'Add New Customer',
          component: AddCustomer,
        },
        {
          path: '/policy/add',
          name: 'Add New Policy',
          component: AddPolicy,
        },
        {
          path: '/room/add',
          name: 'Add New Room',
          component: AddRoom,
        },
        {
          path: '/bed/add',
          name: 'Add New Bed',
          component: AddBed,
        },
        {
          path: '/service/add',
          name: 'Add New Service',
          component: AddService,
        },
        {
          path: '/promo/add',
          name: 'Add New Promotion',
          component: AddPromo,
        },
        {
          path: '/branch/add',
          name: 'Add New Branch',
          component: AddBranch,
        },
        {
          path: '/branch/:branchCode',
          name: 'Branch Detail',
          component: DetailBranch,
        },
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
      ]
});