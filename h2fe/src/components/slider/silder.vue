<template>
    <!-- slider Area Start-->
        <div class="slider-area ">
            <!-- Mobile Menu -->
            <div class="slider-active dot-style">
                <vue-slider class="single-slider  hero-overly slider-height d-flex align-items-center">

                </vue-slider>
                <div class="single-slider  hero-overly slider-height d-flex align-items-center" data-background="../../assets/img/hero/h1_hero.jpg">
                    <div class="container">
                        <div class="row justify-content-center text-center">
                            <div class="col-xl-9">
                                <div class="h1-slider-caption">
                                    <h1 data-animation="fadeInUp" data-delay=".4s">top hotel in the city</h1>
                                    <h3 data-animation="fadeInDown" data-delay=".4s">Hotel & Resourt</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="single-slider  hero-overly slider-height d-flex align-items-center" data-background="../../assets/img/hero/h1_hero.jpg" >
                    <div class="container">
                        <div class="row justify-content-center text-center">
                            <div class="col-xl-9">
                                <div class="h1-slider-caption">
                                    <h1 data-animation="fadeInUp" data-delay=".4s">top hotel in the city</h1>
                                    <h3 data-animation="fadeInDown" data-delay=".4s">Hotel & Resourt</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="single-slider  hero-overly slider-height d-flex align-items-center" data-background="../../assets/img/hero/h1_hero.jpg" >
                    <div class="container">
                        <div class="row justify-content-center text-center">
                            <div class="col-xl-9">
                                <div class="h1-slider-caption">
                                    <h1 data-animation="fadeInUp" data-delay=".4s">top hotel in the city</h1>
                                    <h3 data-animation="fadeInDown" data-delay=".4s">Hotel & Resourt</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- slider Area End-->
</template>
<script>
import VueSlider from 'vue-slider-component'
import 'vue-slider-component/theme/default.css'
export default {
    name:"Slider",
    components: {
    VueSlider
  },

}
</script>
<style scoped>

</style>