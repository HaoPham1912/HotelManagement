<template>
  <form action="" class="form-add">
    <h3>Add New Service</h3>
    <div class="form-outline mb-4">
      <label for="serviceCode">Service Code</label>
      <input type="text" id="serviceCode" class="form-control" required />
    </div>
    <div class="form-outline mb-4">
      <label for="name">Service Name</label>
      <input type="text" id="name" class="form-control" required />
    </div>
    <div class="form-outline mb-4">
      <label for="price">Price</label>
      <input type="text" id="price" class="form-control" required />
    </div>
    <div class="form-outline mb-4">
      <label for="description">Description</label>
      <textarea
        id="description"
        name="description"
        rows="4"
        cols="50"
        class="form-control"
        required
      />
    </div>
    <button type="submit" class="btn btn-primary btn-block mb-4">
      Add New Service
    </button>
  </form>
</template>

<style scoped>
h3 {
  text-align: center;
}
</style>
