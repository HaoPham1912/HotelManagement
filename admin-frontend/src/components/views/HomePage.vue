<template>
  <div>
    <mdb-navbar color="indigo" class="white-text">
      <mdb-navbar-brand href="#"> </mdb-navbar-brand>
      <mdb-navbar-toggler>
        <mdb-navbar-nav>
          <mdb-nav-item anchorClass="white-text" href="#"
            ><strong>HOME PAGE</strong></mdb-nav-item
          >
          <mdb-nav-item anchorClass="white-text" href="/admin"
            ><strong>ADMIN PAGE</strong></mdb-nav-item
          >
          <mdb-nav-item anchorClass="white-text" href="/emp"
            ><strong>EMPLOYEE PAGE</strong></mdb-nav-item
          >
        </mdb-navbar-nav>
        <span class="navbar-text">
          <mdb-navbar-nav>
            <mdb-nav-item anchorClass="white-text" href="/login"
              ><strong>SIGN IN</strong></mdb-nav-item
            >
          </mdb-navbar-nav>
        </span>
      </mdb-navbar-toggler>
    </mdb-navbar>
    <div style="margin-top: 100px">
      <h1 class="text-center">
        WELCOME TO H2 HOTEL MANAGEMENT SYSTEM
      </h1>
      <img
        class="rounded mx-auto d-block"
        src="../../assets/h2logo.png"
        alt=""
      />
    </div>
  </div>
</template>
<script>
import {
  mdbNavbar,
  mdbNavbarBrand,
  mdbNavbarToggler,
  mdbNavbarNav,
  mdbNavItem,
} from 'mdbvue';
export default {
  components: {
    mdbNavbar,
    mdbNavbarBrand,
    mdbNavbarToggler,
    mdbNavbarNav,
    mdbNavItem,
  },
};
</script>
