<template>
  <form action="" class="form-add">
    <h3>Add New Promotion</h3>
    <div class="form-outline mb-4">
      <label for="promotionCode">Promotion Code</label>
      <input type="text" id="promotionCode" class="form-control" required />
    </div>
    <div class="form-outline mb-4">
      <label for="percent">Percent Discount</label>
      <input type="text" id="percent" class="form-control" required />
    </div>
    <div class="form-outline mb-4">
      <label for="cusType">Choose Customer Type</label>
      <select class="browser-default custom-select">
        <option selected value="NORMAL">NORMAL</option>
        <option value="GOLD">GOLD</option>
        <option value="DIAMOND">DIAMOND</option>
      </select>
    </div>
    <div class="form-outline mb-4">
      <label for="cusType">Choose Range Time Apply Promotion</label>
      <div class="form-group">
        <div class="input-group date" id="datetimepicker1">
          <date-picker v-model="time3" range></date-picker>
        </div>
      </div>
    </div>
    <button type="submit" class="btn btn-primary btn-block mb-4">
      Add New Promotion
    </button>
  </form>
</template>

<script>
import DatePicker from 'vue2-datepicker';
import 'vue2-datepicker/index.css';
export default {
  components: { DatePicker },
  data() {
    return {
      time1: null,
      time2: null,
      time3: null,
    };
  },
};
</script>

<style scoped>
h3 {
  text-align: center;
}
</style>
