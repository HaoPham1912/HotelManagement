<template>
  <div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
      <div class="col-md-3 border-right">
        <!-- <div class="d-flex flex-column align-items-center text-center p-3 py-5">
          <img
            class="img-user"
            src="https://mdbootstrap.com/img/Photos/Horizontal/People/6-col/img%20%283%29.jpg"
          />

          <span class="font-weight-bold">Amelly</span
          ><span class="text-black-50">amelly12@bbb.com</span><span> </span>
        </div> -->
      </div>
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
                disabled
              />
            </div>
            <div class="col-md-12">
              <label class="labels">ID Card</label
              ><input
                type="text"
                class="form-control"
                placeholder="enter your id card"
                value=""
              />
            </div>
            <div class="col-md-12">
              <label class="labels">Name</label
              ><input
                type="text"
                class="form-control"
                placeholder="Employee Name"
                value=""
              />
            </div>
            <div class="col-md-12">
              <label class="labels">Email</label
              ><input
                type="text"
                class="form-control"
                placeholder="Email"
                value=""
              />
            </div>
            <div class="col-md-12">
              <label class="labels">Phone</label
              ><input
                type="text"
                class="form-control"
                placeholder="Phone"
                value=""
              />
            </div>
          </div>

          <div class="mt-5 text-center">
            <button class="btn btn-primary profile-button" type="button">
              Save Profile
            </button>
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="p-3 py-5">
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
              value=""
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
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import EmployeeService from '../../services/EmployeeService';
import BranchService from '../../services/BranchService';
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
      },
      submitted: false,
      branchs: [],
      branchCodes: [],
    };
  },
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

  mounted() {
    this.setBracnhCodes();
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
