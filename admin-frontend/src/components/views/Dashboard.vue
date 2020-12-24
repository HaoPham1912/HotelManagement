<template>
  <!-- <b-container class="bv-example-row">
    <b-row>
      <b-col cols="6"><SlideBar /></b-col>
      <b-col>
        <Header />
      </b-col>
    </b-row>
    <br />
    <br />
    <br />
    <br />
    <div class="content-main">
      <b-row>
        <b-col></b-col>
      </b-row>
    </div>
    <div class="content-main">
      <b-row>
        <b-col> <Content /></b-col>
      </b-row>
    </div>
  </b-container> -->
  <mdb-container fluid>
    <Header />
    <SlideBar />
    <Content />
  </mdb-container>
</template>
<script>
import Header from '../../components/layout/Header';
import SlideBar from '../../components/layout/SlideBar';
import Content from '../../components/layout/Content';

import { mdbContainer } from 'mdbvue';
export default {
  components: {
    Header,
    SlideBar,
    Content,
    mdbContainer,
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
