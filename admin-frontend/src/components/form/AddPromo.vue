<template>
  <form action="" class="form-add">
    <h3>Add New Promotion</h3>
    <div class="form-outline mb-4">
      <label for="promotionCode">Promotion Code</label>
      <input
        type="text"
        id="promotionCode"
        class="form-control"
        required
        v-model="promotions.promoCode"
      />
    </div>
    <div class="form-outline mb-4">
      <label for="percent">Percent Discount</label>
      <input
        type="text"
        id="percent"
        class="form-control"
        required
        v-model="promotions.percent"
      />
    </div>
    <!-- Message input -->
    <div class="form-outline mb-4">
      <label class="form-label" for="branchCode">Choose Customer Type</label>
      <b-form-select
        v-model="promotions.customerType"
        :options="customerTypeNames"
      ></b-form-select>
    </div>
    <div class="form-outline mb-4">
      <label for="startDate">Choose Start Date</label>
      <input
        type="date"
        id="percent"
        class="form-control"
        required
        v-model="promotions.startDate"
      />
    </div>
    <div class="form-outline mb-4">
      <label for="endDate">Choose End Date</label>
      <input
        type="date"
        id="percent"
        class="form-control"
        required
        v-model="promotions.endDate"
      />
    </div>

    <button
      type="submit"
      class="btn btn-primary btn-block mb-4"
      @click="createNewPromotion"
    >
      Add New Promotion
    </button>
  </form>
</template>

<script>
import CustomerTypeService from '../../services/CustomerTypeService';
import PromotionService from '../../services/PromotionService';
export default {
  data() {
    return {
      promotions: {
        promoId: '',
        promoCode: '',
        percent: '',
        startDate: '',
        endDate: '',
        customerType: '',
      },
      customerTypes: [],
      customerTypeNames: [],
      time1: null,
    };
  },

  methods: {
    retrieveCustomerType() {
      CustomerTypeService.getAll().then((reponse) => {
        this.customerTypes = reponse.data;
        console.log(this.customerTypes);
        for (var i = 0; i < this.customerTypes.length; i++) {
          var options = [];
          for (var key in this.customerTypes[i]) {
            if (key == 'typeName') {
              options['value'] = this.customerTypes[i][key];
              options['text'] = this.customerTypes[i][key];
            }
          }
          this.customerTypeNames.push(Object.assign({}, options));
        }
      });
    },

    createNewPromotion() {
      var data = {
        promoCode: this.promotions.promoCode,
        percent: this.promotions.percent,
        startDate: this.promotions.startDate,
        endDate: this.promotions.endDate,
        customerType: this.promotions.customerType,
      };
      PromotionService.create(data)
        .then((reponse) => {
          console.log(reponse.data);
          alert('Add new promotion successful');
          this.$router.push('/reloadPromo');
        })
        .catch((e) => {
          console.log(e);
          alert('Add new promotion failed!');
        });
    },
  },
  mounted() {
    this.retrieveCustomerType();
  },
};
</script>

<style scoped>
h3 {
  text-align: center;
}
</style>
