import path from "../util/path";


/* Admin components */
import Room from './admin/Room.vue';
import ClientLayout from '../components/layout/ClientLayout.vue';
import AdminLayout from '../components-admin/layout/AdminLayout.vue';
import AdminLogin from './admin/AdminLogin.vue';
import AdminProfile from './admin/AdminProfile.vue';
import AdminDashboard from './admin/AdminDashboard.vue'

const h2f_Routes = [
    {
        path: path.CLIENT,
        component: ClientLayout,
        children: [

        ],
    },
    {
        path: path.ADMIN,
        component: AdminLayout,
        children: [
            {
                path: path.ADMIN_ROOM,
                component: Room,
            },
            {
                path: path.ADMIN_DASHBOARD,
                component: AdminDashboard
            },
            {
                path: path.ADMIN_PROFILE,
                component: AdminProfile
            }
        ]
    }, 
    {
        path: path.ADMIN_LOGIN,
        component: AdminLogin
    }
];
export default h2f_Routes;
