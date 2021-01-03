<template>
  <mdb-container fluid>
    <!--Navbar-->
    <mdb-navbar class="flexible-navbar white" light position="top" scrolling>
      <mdb-navbar-brand href="" target="_blank"
        >Employee Dashboard</mdb-navbar-brand
      >
      <mdb-navbar-toggler>
        <mdb-navbar-nav right>
          <mdb-dropdown>
            <mdb-dropdown-toggle slot="toggle"
              ><img
                src="https://cdn1.iconfinder.com/data/icons/avatar-97/32/avatar-02-512.png"
                alt=""
            /></mdb-dropdown-toggle>

            <mdb-dropdown-menu>
              <mdb-dropdown-item to="/emp/editProfie">Profie</mdb-dropdown-item>
              <div class="dropdown-divider"></div>
              <mdb-dropdown-item>Log out</mdb-dropdown-item>
            </mdb-dropdown-menu>
          </mdb-dropdown>
        </mdb-navbar-nav>
      </mdb-navbar-toggler>
    </mdb-navbar>
    <!--/.Navbar-->
    <!-- Sidebar -->
    <div class="sidebar-fixed position-fixed col-4">
      <a class="logo-wrapper"
        ><img alt="" class="img" src="../../assets/h2logo.png"
      /></a>
      <mdb-list-group class="list-group-flush">
        <br />
        <router-link to="/emp/booking" @click.native="activeItem = 1">
          <mdb-list-group-item
            :action="true"
            :class="activeItem === 1 && 'active'"
            ><mdb-icon icon="table" class="mr-3" />Manage
            Booking</mdb-list-group-item
          >
        </router-link>
        <br />
        <router-link to="/emp/bill" @click.native="activeItem = 2">
          <mdb-list-group-item
            :action="true"
            :class="activeItem === 2 && 'active'"
            ><mdb-icon icon="table" class="mr-3" />Manage
            Bill</mdb-list-group-item
          >
        </router-link>
      </mdb-list-group>
    </div>
    <!-- /Sidebar -->
    <Content />
  </mdb-container>
</template>

<script>
import {
  mdbContainer,
  mdbNavbarBrand,
  mdbNavbarNav,
  mdbNavbarToggler,
  mdbNavbar,
  mdbDropdown,
  mdbDropdownItem,
  mdbDropdownMenu,
  mdbDropdownToggle,
  mdbIcon,
  mdbListGroup,
  mdbListGroupItem,
} from 'mdbvue';
import Content from '../../components/layout/Content';
export default {
  components: {
    mdbContainer,
    mdbNavbarBrand,
    mdbNavbarNav,
    mdbNavbarToggler,
    mdbNavbar,
    mdbDropdown,
    mdbDropdownItem,
    mdbDropdownMenu,
    mdbDropdownToggle,
    mdbIcon,
    mdbListGroup,
    mdbListGroupItem,
    Content,
  },
  data() {
    return {
      activeItem: 0,
    };
  },
  beforeMount() {
    this.activeItem = this.$route.matched[0];
  },
};
</script>

<style>
@import url('https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap');
.navbar-light .navbar-brand {
  margin-left: 15px;
  color: blue !important;
  font-weight: bolder;
}

@media (max-width: 1199.98px) {
  .sidebar-fixed {
    display: none;
  }
  .flexible-content {
    padding-left: 0;
  }
  .flexible-navbar {
    padding-left: 10px;
  }
}
</style>
<style scoped>
.flexible-navbar {
  transition: padding-left 0.5s;
  padding-left: 270px;
}

.avatar {
  width: 50px;
  height: 50px;
}
.bg-custom-1 {
  background-color: #85144b;
}

.bg-custom-2 {
  background-image: linear-gradient(15deg, #13547a 0%, #80d0c7 100%);
}
img {
  height: 50px;
}
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

.img {
  height: 200px;
}
</style>
