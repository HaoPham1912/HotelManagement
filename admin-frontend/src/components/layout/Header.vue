<template>
  <!--Navbar-->
  <mdb-navbar class="flexible-navbar white" light position="top" scrolling>
    <mdb-navbar-brand href="" target="_blank">Admin Dashboard</mdb-navbar-brand>
    <mdb-navbar-toggler>
      <mdb-navbar-nav right>
        <mdb-dropdown>
          <mdb-dropdown-toggle slot="toggle"
            ><img
              src="https://cdn1.iconfinder.com/data/icons/avatar-97/32/avatar-02-512.png"
              alt=""
            />Hi Admin</mdb-dropdown-toggle
          >

          <mdb-dropdown-menu>
            <!-- <div class="dropdown-divider"></div> -->
            <mdb-dropdown-item>
              <li @click="logout">
                <span class="logout">Logout</span>
              </li></mdb-dropdown-item
            >
          </mdb-dropdown-menu>
        </mdb-dropdown>
      </mdb-navbar-nav>
    </mdb-navbar-toggler>
  </mdb-navbar>
  <!--/.Navbar-->
</template>
<script>
import {
  mdbNavbarBrand,
  mdbNavbarNav,
  mdbNavbarToggler,
  mdbNavbar,
  mdbDropdown,
  mdbDropdownItem,
  mdbDropdownMenu,
  mdbDropdownToggle,
} from 'mdbvue';
import { AUTH_LOGOUT } from '../../store/actions/auth';
export default {
  methods: {
    logout: function() {
      this.$store.dispatch(AUTH_LOGOUT).then(() => this.$router.push('/login'));
    },
  },
  components: {
    mdbNavbarBrand,
    mdbDropdown,
    mdbDropdownItem,
    mdbDropdownMenu,
    mdbDropdownToggle,
    mdbNavbarNav,
    mdbNavbarToggler,
    mdbNavbar,
  },
};
</script>
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
</style>
