<template>
  <form class="form-add" v-on:submit.prevent="updateService">
    <h3>Update Service</h3>
    <div class="form-outline mb-4">
      <label for="serviceCode">Service Code</label>
      <input
        type="text"
        id="serviceCode"
        class="form-control"
        v-model="services.serviceCode"
        required
        :disabled="true"
      />
    </div>
    <div class="form-outline mb-4">
      <label for="name">Service Name</label>
      <input
        type="text"
        id="name"
        class="form-control"
        v-model="services.name"
        required
      />
    </div>
    <div class="form-outline mb-4">
      <label for="price">Price</label>
      <input
        type="text"
        id="price"
        class="form-control"
        v-model="services.price"
        required
      />
    </div>
    <div class="form-outline mb-4">
      <label for="description">Description</label>
      <textarea
        id="description"
        rows="4"
        cols="50"
        class="form-control"
        v-model="services.description"
        required
      />
    </div>
    <button
      type="submit"
      class="btn btn-primary btn-block mb-4"
      @submit="updateService"
    >
      Update Service
    </button>
  </form>
</template>

<script>
import ServicesService from '../../services/ServicesService';
import commonConstant from '../../utils/commonConstant';
export default {
  name: 'add-services',
  data() {
    return {
      services: {
        serviceId: '',
        serviceCode: '',
        name: '',
        price: '',
        description: '',
      },
      submitted: false,
      code: this.$route.params.code,
    };
  },

  methods: {
    getServiceCode() {
      console.log(this.code);
    },
    getServiceByServiceCode(code) {
      ServicesService.get(code).then((response) => {
        this.services.serviceId = response.data.serviceId;
        this.services.serviceCode = response.data.serviceCode;
        this.services.name = response.data.name;
        this.services.price = response.data.price;
        this.services.description = response.data.description;
        console.log(this.services);
      });
    },

    updateService() {
      var data = {
        serviceId: this.services.serviceId,
        serviceCode: this.services.serviceCode,
        name: this.services.name,
        price: this.services.price,
        description: this.services.description,
      };
      ServicesService.create(data)
        .then((response) => {
          console.log(response.data);
          alert(commonConstant.MessageConstant.UPDATE_SUCCESS_MSG);
          this.$router.push('/reloadService');
        })
        .catch((e) => {
          console.log(e);
          alert('Update failed');
        });
    },
  },
  mounted() {
    this.getServiceCode();
    this.getServiceByServiceCode(this.code);
  },
};
</script>

<style scoped>
h3 {
  text-align: center;
}
</style>
