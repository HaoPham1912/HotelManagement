<template>
  <!-- Sidebar -->
  <div class="sidebar-fixed position-fixed">
    <a class="logo-wrapper"
      ><img alt="" class="img-fluid" src="../../assets/h2logo.png"
    /></a>
    <mdb-list-group class="list-group-flush">
      <router-link to="/account" @click.native="activeItem = 1">
        <mdb-list-group-item
          :action="true"
          :class="activeItem === 1 && 'active'"
        >
          <mdb-icon icon="table" class="mr-3" />Manage
          Account</mdb-list-group-item
        >
      </router-link>
      <router-link to="/customer" @click.native="activeItem = 2">
        <mdb-list-group-item
          :action="true"
          :class="activeItem === 2 && 'active'"
        >
          <mdb-icon icon="table" class="mr-3" />
          Manage Customer</mdb-list-group-item
        >
      </router-link>
      <router-link to="/employee" @click.native="activeItem = 3">
        <mdb-list-group-item
          :action="true"
          :class="activeItem === 3 && 'active'"
          ><mdb-icon icon="table" class="mr-3" />Manage
          Employee</mdb-list-group-item
        >
      </router-link>
      <router-link to="/booking" @click.native="activeItem = 4">
        <mdb-list-group-item
          :action="true"
          :class="activeItem === 4 && 'active'"
          ><mdb-icon icon="table" class="mr-3" />Manage
          Booking</mdb-list-group-item
        >
      </router-link>

      <router-link to="/branch" @click.native="activeItem = 5">
        <mdb-list-group-item
          :action="true"
          :class="activeItem === 5 && 'active'"
          ><mdb-icon icon="table" class="mr-3" />Manage
          Branch</mdb-list-group-item
        >
      </router-link>
      <router-link to="/room" @click.native="activeItem = 6">
        <mdb-list-group-item
          :action="true"
          :class="activeItem === 6 && 'active'"
          ><mdb-icon icon="table" class="mr-3" />Manage
          Room</mdb-list-group-item
        >
      </router-link>
      <router-link to="/policy" @click.native="activeItem = 7">
        <mdb-list-group-item
          :action="true"
          :class="activeItem === 7 && 'active'"
          ><mdb-icon icon="table" class="mr-3" />Manage
          Policy</mdb-list-group-item
        >
      </router-link>
      <router-link to="/bed" @click.native="activeItem = 8">
        <mdb-list-group-item
          :action="true"
          :class="activeItem === 8 && 'active'"
          ><mdb-icon icon="table" class="mr-3" />Manage Bed</mdb-list-group-item
        >
      </router-link>
      <router-link to="/service" @click.native="activeItem = 9">
        <mdb-list-group-item
          :action="true"
          :class="activeItem === 9 && 'active'"
          ><mdb-icon icon="table" class="mr-3" />Manage
          Service</mdb-list-group-item
        >
      </router-link>
      <router-link to="/revenue" @click.native="activeItem = 10">
        <mdb-list-group-item
          :action="true"
          :class="activeItem === 10 && 'active'"
          ><mdb-icon icon="table" class="mr-3" />Manage
          Revenue</mdb-list-group-item
        >
      </router-link>
    </mdb-list-group>
  </div>
  <!-- /Sidebar -->
</template>
<script>
import { mdbIcon, mdbListGroup, mdbListGroupItem } from 'mdbvue';
export default {
  components: {
    mdbIcon,
    mdbListGroup,
    mdbListGroupItem,
  },
  data() {
    return {
      activeItem: 0,
    };
  },
  beforeMount() {
    this.activeItem = this.$route.matched[0].props.default.page;
  },
};
</script>
<style scoped>
.sidebar-fixed {
  left: 0;
  top: 0;
  height: 100vh;
  width: 270px;
  box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
  z-index: 1050;
  background-color: #fff;
  padding: 1.5rem;
  padding-top: 0;
}
.sidebar-fixed .logo-wrapper img {
  width: 100%;
  padding: 2.5rem;
}

.sidebar-fixed .list-group-item {
  display: block !important;
  transition: background-color 0.3s;
}

.sidebar-fixed .list-group .active {
  box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
  border-radius: 5px;
}
</style>
