<template>
  <!--Navbar-->
  <mdb-navbar class="flexible-navbar white" light position="top" scrolling>
    <mdb-navbar-brand href="" target="_blank"
      >Manage Dashboard</mdb-navbar-brand
    >
    <mdb-navbar-toggler>
      <mdb-navbar-nav left>
        <!-- <mdb-nav-item to="/account" waves-fixed>Home</mdb-nav-item> -->
      </mdb-navbar-nav>
      <mdb-navbar-nav right>
        <mdb-nav-item to="/" waves-fixed>Logout</mdb-nav-item>
        <!--Avatar-->
        <div class="avatar mx-auto white">
          <img
            src="https://mdbootstrap.com/img/Photos/Avatars/img%20(20).jpg"
            alt="avatar mx-auto white"
            class="rounded-circle img-fluid"
          />
        </div>
      </mdb-navbar-nav>
    </mdb-navbar-toggler>
  </mdb-navbar>
  <!--/.Navbar-->
</template>
<script>
import {
  mdbNavbarBrand,
  mdbNavItem,
  mdbNavbarNav,
  mdbNavbarToggler,
  mdbNavbar,
} from 'mdbvue';
export default {
  components: {
    mdbNavbarBrand,
    mdbNavItem,
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
</style>
