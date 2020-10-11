<template>
  <div class="container-fluid main-wrapper">
    <div class="row h-100">
      <div class="col-2" style="background-color: #1E1E21">
        <SidebarMenu :relative="true" :collapsed="false" :hideToggle="true" :menu="menu" />
      </div>
      <div class="col-10">
        <div class="row">
          <AdminHeader />
        </div>
        <div class="row">
          <!-- Content here -->
          <router-view></router-view>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AdminHeader from "./AdminHeader.vue";
// import AdminFooter from "./AdminFooter.vue";
import { SidebarMenu } from "vue-sidebar-menu";

export default {
  components: {
    AdminHeader,
    // AdminFooter,
    SidebarMenu
  },
  data() {
    return {
      menu: [
        {
          header: true,
          title: "H2 Dashboard",
          hiddenOnCollapse: true
        },
        {
          href: "/admin/dashboard",
          title: "Dashboard",
          icon: "fa fa-user"
        },
        {
          href: "/admin/profile",
          title: "Profile",
          icon: "fa fa-user"
        },
        {
          href: "",
          title: "Order",
          icon: "fa fa-chart-area",
          child: [
            {
              href: "",
              title: "History transaction"
            },
            {
              href: "",
              title: "New order"
            }
          ]
        }
      ]
    };
  }
};
</script>

<style>
body {
  overflow-y: visible;
}
.main-wrapper {
  height: 100vh;
}
</style>