<template>
  <form class="form-add">
    <!-- 2 column grid layout with text inputs for the first and last names -->
    <h3>Add New Employee</h3>
    <br />
    <div class="row mb-4">
      <div class="col">
        <div class="form-outline">
          <input
            type="text"
            id="employeeCode"
            class="form-control"
            v-model="employee.employeeCode"
            required
          />
          <label class="form-label" for="employeeCode">Employee Code</label>
        </div>
      </div>
      <div class="col">
        <div class="form-outline">
          <input
            type="text"
            id="empIdCard"
            class="form-control"
            v-model="employee.empIdCard"
          />
          <label class="form-label" for="empIdCard">Id Card</label>
        </div>
      </div>
    </div>

    <!-- Text input -->
    <div class="form-outline mb-4">
      <input
        type="text"
        id="empName"
        class="form-control"
        v-model="employee.empName"
      />
      <label class="form-label" for="empName">Employee Name</label>
    </div>

    <!-- Text input -->
    <div class="form-outline mb-4">
      <input
        type="text"
        id="empPhone"
        class="form-control"
        v-model="employee.empPhone"
      />
      <label class="form-label" for="empPhone">Phone</label>
    </div>

    <!-- Email input -->
    <div class="form-outline mb-4">
      <input
        type="email"
        id="email"
        class="form-control"
        v-model="employee.email"
      />
      <label class="form-label" for="email">Email</label>
    </div>

    <!-- Number input -->
    <div class="form-outline mb-4">
      <input
        type="text"
        id="username"
        class="form-control"
        v-model="employee.username"
      />
      <label class="form-label" for="username">Username Account</label>
    </div>

    <!-- Number input -->
    <div class="form-outline mb-4">
      <input
        type="text"
        id="password"
        class="form-control"
        v-model="employee.password"
      />
      <label class="form-label" for="password">Password Account</label>
    </div>

    <div class="form-outline mb-4">
      <input
        type="text"
        id="password"
        class="form-control"
        v-model="employee.password"
      />
      <label class="form-label" for="password">Confirm Password</label>
    </div>

    <!-- Message input -->
    <div class="form-outline mb-4">
      <b-form-select
        v-model="employee.branchCode"
        :options="branchCodes"
      ></b-form-select>
      <label class="form-label" for="branchCode">Choose Branch Code</label>
    </div>
    <button
      type="submit"
      class="btn btn-primary btn-block mb-4"
      v-on:click="saveEmployee"
    >
      Add New Employee
    </button>
  </form>
</template>
<script>
import EmployeeService from '../../services/EmployeeService';
import BranchService from '../../services/BranchService';
export default {
  methods: {
    saveEmployee() {
      var data = {
        employeeCode: this.employee.employeeCode,
        empIdCard: this.employee.empIdCard,
        empName: this.employee.empName,
        empPhone: this.employee.empPhone,
        username: this.employee.username,
        password: this.employee.password,
        email: this.employee.email,
        branchCode: this.employee.branchCode,
      };

      EmployeeService.create(data)
        .then((response) => {
          this.employee.employeeId = response.data.employeeId;
          console.log(response.data);
          this.submitted = true;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    newEmployee() {
      this.submitted = false;
      this.employee = {};
    },
    setBracnhCodes() {
      BranchService.getAll()
        .then((response) => {
          this.branchs = response.data;

          console.log(this.branchs);

          for (var i = 0; i < this.branchs.length; i++) {
            var options = [];
            for (var key in this.branchs[i]) {
              if (key == 'branchCode') {
                options['value'] = this.branchs[i][key];
                options['text'] = this.branchs[i][key];
              }
            }
            this.branchCodes.push(Object.assign({}, options));
          }
          console.log(this.branchCodes);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
  name: 'add-employee',
  data() {
    return {
      employee: {
        employeeCode: '',
        empIdCard: '',
        empName: '',
        empPhone: '',
        username: '',
        password: '',
        email: '',
        branchCode: '',
      },
      submitted: false,
      branchs: [],
      branchCodes: [],
    };
  },
  mounted() {
    this.setBracnhCodes();
  },
};
</script>
<style>
.form-add {
  width: 500px;
  display: block;
  margin: auto;
}
</style>
