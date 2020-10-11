<template>
  <div class="container-fluid bg-light">
    <div class="row my-2">
      <div class="col-3">
        <div class="card text-white bg-success mb-3" v-if="isShow">
            <div class="mx-2 my-2">
                Number of order: 2
            </div>
            <i class="fa fa-file-text-o" aria-hidden="true"></i>
        </div>
      </div>
      <div class="col-3">
       <div class="card text-white bg-success mb-3" v-if="isShow">
            <div class="mx-2 my-2">
                Number of order: 2
            </div>
        </div>
      </div>
      <div class="col-3">
       <div class="card text-white bg-success mb-3" v-if="isShow">
            <div class="mx-2 my-2">
                Number of order: 2
            </div>
        </div>
      </div>
      <div class="col-3">
        <div class="card text-white bg-success mb-3" v-if="isShow">
            <div class="mx-2 my-2">
                Number of order: 2
            </div>
        </div>
      </div>
    </div>
    <div class="row my-2">
      <div class="col-4">
        <transition name="fade">
          <div class="card my-2" v-if="isShow">
            <div class="card-header">
              <p class="h2">Infomation</p>
            </div>
            <div class="card-body">
              <h5 class="card-title">Hi, Duy</h5>
              <div class="row">
                <div class="col-3">
                  <p class="card-text">Branch:</p>
                </div>
                <div class="col-9">
                  <p class="h5">H2 Ho Chi Minh</p>
                </div>
              </div>
              <div class="row">
                <div class="col-3">
                  <p class="card-text">Account:</p>
                </div>
                <div class="col-9">
                  <p class="h5">duylv3</p>
                </div>
              </div>
              <div class="row my-2">
                <div class="col-6">
                  <p class="h6">From</p>
                  <datepicker :value="fromVal" @selected="doSomethingInParentComponentFunction"></datepicker>
                </div>
                <div class="col-6">
                  <p class="h6">To</p>
                  <datepicker :value="toVal" @selected="doSomethingInParentComponentFunction"></datepicker>
                </div>
              </div>
            </div>
          </div>
        </transition>
      </div>
      <div class="col-8">
        <transition name="fade">
          <div class="card my-2" v-if="isShow">
            <div class="mx-2 my-2">
              <BarChart />
            </div>
          </div>
        </transition>
      </div>
    </div>
  </div>
</template>

<script>
import BarChart from "../../components-admin/common/BarChart.vue";
import Datepicker from "vuejs-datepicker";

var state = {
  highlighted: {
    to: new Date(2016, 0, 5), // Highlight all dates up to specific date
    from: new Date(2016, 0, 26), // Highlight all dates after specific date
    days: [6, 0], // Highlight Saturday's and Sunday's
    daysOfMonth: [15, 20, 31], // Highlight 15th, 20th and 31st of each month
    dates: [
      // Highlight an array of dates
      new Date(2016, 9, 16),
      new Date(2016, 9, 17),
      new Date(2016, 9, 18)
    ],
    // a custom function that returns true of the date is highlighted
    // this can be used for wiring you own logic to highlight a date if none
    // of the above conditions serve your purpose
    // this function should accept a date and return true if is highlighted
    customPredictor: function(date) {
      // highlights the date if it is a multiple of 4
      if (date.getDate() % 4 == 0) {
        return true;
      }
    },
    includeDisabled: true // Highlight disabled dates
  }
};

export default {
  components: {
    BarChart,
    Datepicker
  },
  mounted() {
    this.isShow = true;
  },
  data() {
    return {
      isShow: false,
      fromVal: new Date(),
      toVal: new Date(),
      state: state
    };
  },
  methods: {
    doSomethingInParentComponentFunction: function(e) {
      alert(this.fromVal - e);
    }
  },
  watch: {}
};
</script>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}
</style>