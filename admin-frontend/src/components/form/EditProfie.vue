<template>
  <form action="">
    <div class="container rounded bg-white mt-5 mb-5">
      <div class="row">
        <div class="col-md-3 border-right"></div>
        <div class="col-md-5 border-right">
          <div class="p-3 py-5">
            <div class="d-flex justify-content-between align-items-center mb-3">
              <h4 class="text-right">Profile Settings</h4>
            </div>

            <div class="row mt-3">
              <div class="col-md-12">
                <label class="labels">Code</label
                ><input
                  type="text"
                  class="form-control"
                  placeholder="Employee Code"
                  value=""
                  v-model="employee.employeeCode"
                  disabled
                />
              </div>
              <div class="col-md-12">
                <label class="labels">ID Card</label
                ><input
                  type="text"
                  class="form-control"
                  placeholder="enter your id card"
                  v-model="employee.empIdCard"
                  required
                />
              </div>
              <div class="col-md-12">
                <label class="labels">Name</label
                ><input
                  type="text"
                  class="form-control"
                  placeholder="Employee Name"
                  v-model="employee.empName"
                  required
                />
              </div>
              <div class="col-md-12">
                <label class="labels">Email</label
                ><input
                  type="text"
                  class="form-control"
                  placeholder="Email"
                  v-model="employee.email"
                  required
                />
              </div>
              <div class="col-md-12">
                <label class="labels">Phone</label
                ><input
                  type="text"
                  class="form-control"
                  placeholder="Phone"
                  v-model="employee.empPhone"
                  required
                />
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <!-- <div class="p-3 py-5">
            <div
              class="d-flex justify-content-between align-items-center experience"
            >
              <span></span
              ><span class="border px-3 p-1 add-experience"
                ><i class="fa fa-plus"></i>&nbsp;Edit Account</span
              >
            </div>
            <br />
            <div class="col-md-12">
              <label class="labels">Old Password</label
              ><input
                type="text"
                class="form-control"
                placeholder="Old password"
                v-model="employee.password"
                value=""
              />
            </div>
            <br />
            <div class="col-md-12">
              <label class="labels">New Password</label
              ><input
                type="text"
                class="form-control"
                placeholder="New Password"
                v-model="employee.newPass"
              />
            </div>
            <br />
            <div class="col-md-12">
              <label class="labels">Confirm New Password</label
              ><input
                type="text"
                class="form-control"
                placeholder="New Password"
                value=""
              />
            </div>
          </div> -->
        </div>
      </div>
      <div class="mt-5 text-center">
        <button
          class="btn btn-primary profile-button"
          type="button"
          @click="updateCurrentInformation(username)"
        >
          Save Profile
        </button>
      </div>
    </div>
  </form>
</template>
<script>
import EmployeeService from '../../services/EmployeeService';
// import BranchService from '../../services/BranchService';
import store from '../../store';
export default {
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
        newPass: '',
      },
      username: store.getters.username,
    };
  },
  methods: {
    getCurrentInformation(username) {
      console.log(username);
      EmployeeService.getCurrentInfoEmployee(username)
        .then((response) => {
          console.log(response.data);
          this.employee = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },

    updateCurrentInformation(username) {
      var data = {
        employeeCode: this.employee.employeeCode,
        empIdCard: this.employee.empIdCard,
        empName: this.employee.empName,
        empPhone: this.employee.empPhone,
        email: this.employee.email,
        newPass: this.employee.newPass,
      };
      EmployeeService.updateCurrentInfoEmployee(username, data)
        .then((response) => {
          console.log(response.data);
          alert('Your information have been updated!!!');
          this.getCurrentInformation(username);
          window.location.reload();
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  name: 'add-employee',

  mounted() {
    this.getCurrentInformation(this.username);
  },
};
</script>
<style scoped>
.form-add {
  width: 500px;
  display: block;
  margin: auto;
}
.img-user {
  height: 200px;
  border-radius: 50%;
}
</style>
