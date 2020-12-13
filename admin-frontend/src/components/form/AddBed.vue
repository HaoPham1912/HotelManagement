<template>
  <form action="" class="form-add">
    <h3>Add New Bed</h3>
    <div class="form-outline mb-4">
      <label for="name">Bed Name</label>
      <input type="text" id="name" class="form-control" required />
    </div>
    <div class="form-outline mb-4">
      <label for="amount">Amount People</label>
      <input type="text" id="address" class="form-control" required />
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
      Add New Bed
    </button>
  </form>
</template>

<style scoped>
h3 {
  text-align: center;
}
</style>
