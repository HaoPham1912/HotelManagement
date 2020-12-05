import Vue from 'vue';
import Router from 'vue-router';

import Dashboard from '../components/views/Dashboard.vue';
import CustomerTable from '../components/table/CustomerTable.vue';
import AccountTable from '../components/table/AccountTable.vue';
import EmployeeTable from '../components/table/EmployeeTable.vue';
import BookingTable from '../components/table/BookingTable.vue'
import Chart from '../components/views/Chart.vue';
import Login from '../components/form/Login.vue';
import Register from '../components/form/Register.vue';
import AddNewEmployee from '../components/form/Add.vue';
import BranchTable from '../components/table/BranchTable.vue';
import RoomTable from '../components/table/RoomTable.vue'
import PolicyTable from '../components/table/PolicyTable.vue'
import BedTable from '../components/table/BedTable.vue';
import ServicesTable from '../components/table/ServicesTable.vue';

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
          name: 'Chart',
          component: BranchTable,
          props: { page: 10 },
        },
        {
          path: '/employee/add',
          name: 'Add New Emp',
          component: AddNewEmployee,
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