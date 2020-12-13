<template>
  <form action="" class="form-add">
    <h3>Add New Cancel Policy</h3>
    <div class="form-outline mb-4">
      <label for="policyCode">Policy Code</label>
      <input type="text" id="policyCode" class="form-control" required />
    </div>
    <div class="form-outline mb-4">
      <label for="long">Day Long</label>
      <input type="text" id="long" class="form-control" required />
    </div>
    <div class="form-outline mb-4">
      <label for="title">Title</label>
      <input type="text" id="title" class="form-control" required />
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
      Add New Policy
    </button>
  </form>
</template>

<style scoped>
h3 {
  text-align: center;
}
</style>
