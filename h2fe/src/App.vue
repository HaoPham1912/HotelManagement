<template>
  <div>
  <!-- Preloader Start -->
    <!-- <div id="preloader-active">
        <div class="preloader d-flex align-items-center justify-content-center">
            <div class="preloader-inner position-relative">
                <div class="preloader-circle"></div>
                <div class="preloader-img pere-text">
                    <strong>Hotel</strong>
                </div>
            </div>
        </div>
    </div> -->
    <!-- Preloader Start -->
  <div id="app">
    <Header></Header>
    <Silder></Silder>
  </div>
  </div>
</template>

<script>
import Header from './components/header/header.vue'
import Silder from './components/slider/silder.vue'
export default {
  name: 'App',
  components: {
    Header,
    Silder,
  },
  created(){
    // document.getElementById('preloader-active').delay(300).fadeOut('slow');
    // document.getElementById('app').delay(450).css({
    //     'overflow': 'visible'
    //   });
  }
}
</script>

<style>
@import './assets/css/bootstrap.min.css';
@import './assets/css/owl.carousel.min.css';
@import './assets/css/gijgo.css';
@import './assets/css/slicknav.css';
@import './assets/css/animate.min.css';
@import './assets/css/magnific-popup.css';
@import './assets/css/fontawesome-all.min.css';
@import './assets/css/themify-icons.css';
@import './assets/css/slick.css';
@import './assets/css/nice-select.css';
@import './assets/css/style.css';
@import './assets/css/responsive.css';
</style>
