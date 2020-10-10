<template>
    <div>
        <AdminHeader/>
        <router-view></router-view>
        <AdminFooter/>
    </div>
</template>

<script>

import AdminHeader from './AdminFooter.vue';
import AdminFooter from './AdminFooter.vue';

export default {
    components: {
        AdminHeader,
        AdminFooter
    }
}
</script>