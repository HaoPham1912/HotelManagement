<template>
  <div class="flexible-content">
    <!--Navbar-->
    <Header />
    <!--/.Navbar-->
    <!-- Sidebar -->
    <SlideBar />
    <!-- /Sidebar -->
    <Footer />
  </div>
</template>
<script>
import { waves } from 'mdbvue';
import Header from '../../components/layout/Header';
import SlideBar from '../../components/layout/SlideBar';
import Footer from '../../components/layout/Footer';
export default {
  components: {
    Header,
    SlideBar,
    Footer,
  },
  mixins: [waves],
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
.card.card-cascade .view.gradient-card-header {
  padding: 1rem 1rem;
  text-align: center;
}
.card.card-cascade h3,
.card.card-cascade h4 {
  margin-bottom: 0;
}
.flexible-content {
  transition: padding-left 0.3s;
  padding-left: 270px;
}
</style>
