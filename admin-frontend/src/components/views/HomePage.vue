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
            <mdb-nav-item anchorClass="white-text"
              ><strong @click="logout">SIGN OUT</strong></mdb-nav-item
            >
            <mdb-nav-item anchorClass="white-text" v-if="!isAuthenticate"
              ><strong>WELCOME</strong></mdb-nav-item
            >
            <mdb-nav-item anchorClass="white-text" v-else
              ><strong>{{ `WELCOME ${username}` }}</strong></mdb-nav-item
            >
          </mdb-navbar-nav>
        </span>
      </mdb-navbar-toggler>
    </mdb-navbar>
    <div class="main-content">
      <div style="margin-top: 200px">
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
import { AUTH_LOGOUT } from '../../store/actions/auth';
import store from '../../store';
export default {
  data() {
    return {
      isAuthenticate: '',
      username: '',
    };
  },
  methods: {
    logout: function() {
      this.$store.dispatch(AUTH_LOGOUT).then(() => this.$router.push('/login'));
    },

    logoutaftertime() {
      this.$store.dispatch(AUTH_LOGOUT).then(() => this.$router.push('/login'));
    },

    checkAuthenticate() {
      this.isAuthenticate = store.getters.isAuthenticated;
      this.username = store.getters.username;
    },
  },
  components: {
    mdbNavbar,
    mdbNavbarBrand,
    mdbNavbarToggler,
    mdbNavbarNav,
    mdbNavItem,
  },
  mounted() {
    this.checkAuthenticate();
  },
};
</script>
<style scoped></style>
