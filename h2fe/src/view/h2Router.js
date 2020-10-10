import path from "../util/path";


/* Admin components */
import Room from './admin/Room.vue';
import ClientLayout from '../components/layout/ClientLayout.vue';
import AdminLayout from '../components-admin/layout/AdminLayout.vue';

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
            }
        ]
    }
];
export default h2f_Routes;
