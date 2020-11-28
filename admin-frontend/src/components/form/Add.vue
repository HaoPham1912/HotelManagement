<template>
  <form class="form-add">
    <!-- 2 column grid layout with text inputs for the first and last names -->
    <h3>Add New Employee</h3>
    <br />
    <div class="row mb-4">
      <div class="col">
        <div class="form-outline">
          <input type="text" id="form6Example1" class="form-control" />
          <label class="form-label" for="form6Example1">Employee Code</label>
        </div>
      </div>
      <div class="col">
        <div class="form-outline">
          <input type="text" id="form6Example2" class="form-control" />
          <label class="form-label" for="form6Example2">Id Card</label>
        </div>
      </div>
    </div>

    <!-- Text input -->
    <div class="form-outline mb-4">
      <input type="text" id="form6Example3" class="form-control" />
      <label class="form-label" for="form6Example3">Employee Name</label>
    </div>

    <!-- Text input -->
    <div class="form-outline mb-4">
      <input type="text" id="form6Example4" class="form-control" />
      <label class="form-label" for="form6Example4">Phone</label>
    </div>

    <!-- Email input -->
    <div class="form-outline mb-4">
      <input type="email" id="form6Example5" class="form-control" />
      <label class="form-label" for="form6Example5">Email</label>
    </div>

    <!-- Number input -->
    <div class="form-outline mb-4">
      <input type="text" id="form6Example6" class="form-control" />
      <label class="form-label" for="form6Example6">Username Account</label>
    </div>

    <!-- Number input -->
    <div class="form-outline mb-4">
      <input type="text" id="form6Example7" class="form-control" />
      <label class="form-label" for="form6Example7">Password Account</label>
    </div>

    <!-- Message input -->
    <div class="form-outline mb-4">
      <select name="form6Example8" class="browser-default custom-select">
        <option selected>CN1</option>
        <option value="1">CN2</option>
        <option value="2">CN3</option>
        <option value="3">CN4</option>
      </select>
      <label class="form-label" for="form6Example8">Branch Code</label>
    </div>

    <!-- Checkbox
    <div class="form-check d-flex justify-content-center mb-4">
      <input
        class="form-check-input mr-2"
        type="checkbox"
        value=""
        id="form6Example8"
        checked
      />
      <label class="form-check-label" for="form6Example8">
        Create an account?
      </label>
    </div> -->

    <!-- Submit button -->
    <button type="submit" class="btn btn-primary btn-block mb-4">
      Add New Employee
    </button>
  </form>
</template>
<style>
.form-add {
  width: 500px;
  display: block;
  margin: auto;
}
</style>
