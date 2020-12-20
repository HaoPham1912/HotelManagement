<template>
  <form class="form-add" v-on:submit.prevent="createCancelPolicy">
    <h3>Add New Cancel Policy</h3>
    <div class="form-outline mb-4">
      <label for="policyCode">Policy Code</label>
      <input
        type="text"
        id="policyCode"
        class="form-control"
        required
        v-model="cancelPolicy.policyCode"
      />
    </div>
    <div class="form-outline mb-4">
      <label for="rangeDate">Day Long</label>
      <input
        type="text"
        id="rangeDate"
        class="form-control"
        required
        v-model="cancelPolicy.rangeDate"
      />
    </div>
    <div class="form-outline mb-4">
      <label for="title">Title</label>
      <input
        type="text"
        id="title"
        class="form-control"
        required
        v-model="cancelPolicy.title"
      />
    </div>
    <div class="form-outline mb-4">
      <label for="description">Description</label>
      <textarea
        id="description"
        rows="4"
        cols="50"
        class="form-control"
        required
        v-model="cancelPolicy.detail"
      />
    </div>

    <button
      type="submit"
      class="btn btn-primary btn-block mb-4"
      @submit="createCancelPolicy"
    >
      Add New Policy
    </button>
  </form>
</template>
<script>
import CancelPolicyService from '../../services/CancelPolicyService';
export default {
  data() {
    return {
      cancelPolicy: {
        policyCode: '',
        title: '',
        detail: '',
        rangeDate: '',
      },
    };
  },
  methods: {
    createCancelPolicy() {
      var data = {
        policyCode: this.cancelPolicy.policyCode,
        title: this.cancelPolicy.title,
        detail: this.cancelPolicy.detail,
        rangeDate: this.cancelPolicy.rangeDate,
      };
      CancelPolicyService.create(data)
        .then((response) => {
          console.log(response.data);
          this.$router.push('/reloadPolicy');
        })
        .catch((e) => {
          console.log(e);
        });
    },
    newPolicy() {
      this.cancelPolicy = {};
    },
  },
};
</script>
<style scoped>
h3 {
  text-align: center;
}
</style>
