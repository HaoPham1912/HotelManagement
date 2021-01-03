import Vue from 'vue';
import Router from 'vue-router';

import Dashboard from '../components/views/Dashboard.vue';
import EmployeeDashboard from '../components/views/EmployeeDashboard.vue';

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

export default new Router({
    mode: 'history',
    routes: [
        {
        path:'/admin', component:Dashboard,
        children:[    {
          path: '/admin/account',
          name: 'Account',
          component: AccountTable,
          props: { page: 1 },
        },
        {
          path: '/admin/customer',
          name: 'Customer',
          component: CustomerTable,
          props: { page: 2 },
        },
        {
          path: '/admin/employee',
          name: 'Employee',
          component: EmployeeTable,
          props: { page: 3 },
        },
        {
          path: '/admin/booking',
          name: 'Booking',
          component: BookingTable,
          props: { page: 4 },
        },
        {
          path: '/admin/bill',
          name: 'BillTable',
          component: BillTable,
          props: { page: 5 },
        },
        
        {
          path: '/admin/room',
          name: 'Room',
          component: RoomTable,
          props: { page: 6 },
        },
        {
          path: '/admin/room/:id',
          name: 'EditRoom',
          component: EditRoom,
        
        },
        {
          path: '/admin/policy',
          name: 'PolicyTable',
          component: PolicyTable,
          props: { page:  7},
        },
        {
          path: '/admin/bed',
          name: 'Bed',
          component: BedTable,
          props: { page: 8 },
        },
        {
          path: '/admin/services',
          name: 'Services',
          component: ServicesTable,
          props: { page: 9 },
        },
        {
          path: '/admin/branch',
          name: 'Branch',
          component: BranchTable,
          props: { page: 10 },
        },
        {
          path: '/admin/promotion',
          name: 'Promotion',
          component: PromotionTable,
          props: { page: 11 },
        },
        {
          path: '/admin/employee/add',
          name: 'Add New Emp',
          component: AddNewEmployee,
        },
        {
          path: '/admin/policy/add',
          name: 'Add New Policy',
          component: AddPolicy,
        },
        {
          path: '/admin/room/add',
          name: 'Add New Room',
          component: AddRoom,
        },
        {
          path: '/admin/bed/add',
          name: 'Add New Bed',
          component: AddBed,
        },
        {
          path: '/admin/service/add',
          name: 'Add New Service',
          component: AddService,
        },
        {
          path: '/admin/promo/add',
          name: 'Add New Promotion',
          component: AddPromo,
        },
        {
          path: '/admin/branch/add',
          name: 'Add New Branch',
          component: AddBranch,
        },
        {
          path: '/admin/branch/:id',
          name: 'Update Branch',
          component: EditBranch,
        },
        {
          path: '/admin/detail-branch/:branchCode',
          name: 'Branch Detail',
          component: DetailBranch,
        },
        {
          path: '/admin/detail-room/:roomCode',
          name: 'Room Detail',
          component: DetailRoom,
        },
        {
          path: '/admin/services/:code',
          name: 'Update Service',
          component: EditService,
          props: true,
        },
        {
          path: '/admin/customer/:id',
          name: 'Update Customer',
          component: EditCustomer,
          props: true,
        },
        {
          path: '/admin/employee/:id',
          name: 'Update Employee',
          component: EditEmployee,
          props: true,
        },
        {
          path: '/admin/detailBill/:id',
          name: 'DetailBill',
          component: DetailBill,
          props: true,
        },
        {
          path: '/admin/editProfie',
          name: 'editProfie',
          component: EditProfie,
          props: true,
        },
        { path: '/reloadService', redirect: '/admin/services' },
        { path: '/reloadAccount', redirect: '/admin/account' },
        { path: '/reloadPolicy', redirect: '/admin/policy' },
        { path: '/reloadEmployee', redirect: '/admin/employee' },
        { path: '/reloadPromo', redirect: '/admin/promotion' },
        { path: '/reloadBranch', redirect: '/admin/branch' },
        { path: '/reloadRoom', redirect: '/admin/room' }
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
          name: 'Employee Dashboard',
          component:EmployeeDashboard,
          children:[
            
              {
                path: '/emp/editProfie',
                name: 'editProfie',
                component: EditProfie,
                props: true,
              },
              {
                path: '/emp/detailBill/:id',
                name: 'DetailBill',
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