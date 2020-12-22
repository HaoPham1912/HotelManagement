<template>
  <div>
    <h3>Employee Of Branch</h3>
    <table class="table">
      <thead>
        <tr>
          <th scope="col">Employee Code</th>
          <th scope="col">Id Card</th>
          <th scope="col">EmployeeName</th>
          <th scope="col">Employee Phone</th>
          <th scope="col">Employee Email</th>
          <th scope="col">UserName</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(data, index) in branchs.employees" :key="index">
          <td>{{ data.employeeCode }}</td>
          <td>{{ data.empIdCard }}</td>
          <td>{{ data.empName }}</td>
          <td>{{ data.empPhone }}</td>
          <td>{{ data.email }}</td>
          <td>{{ data.username }}</td>
          <td class="action">
            <div>
              <button
                class="btn-warning"
                @click="getIdEmp(data.employeeId)"
                data-mdb-toggle="modal"
                data-mdb-target="#exampleModal"
              >
                <i class="fas fa-pencil-alt"></i>
                EDIT
              </button>
            </div>
            <div>
              <button class="btn-danger" @click="remove(scope.row)">
                <i class="fas fa-trash"></i>
                DELETE
              </button>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
    <h3>Image Of Branch</h3>
    <div class="img-branch">
      <img :src="branchs.mainImage" />
    </div>
  </div>
</template>

<script>
import BranchService from '../../services/BranchService';
export default {
  name: 'branch',
  data() {
    return {
      branchCode: this.$route.params.branchCode,
      branchs: {
        employees: [],
        mainImage: '',
      },
    };
  },
  methods: {
    getDetailBranch(branchCode) {
      BranchService.getBranchByCode(branchCode).then((response) => {
        console.log(response.data);
        this.branchs.employees = response.data.employeeDTOSet;
        this.branchs.mainImage = response.data.mainImage;
        console.log(this.branchs.employees);
      });
    },
  },

  mounted() {
    this.getDetailBranch(this.branchCode);
  },
};
</script>

<style scoped>
img {
  width: 300px;
  height: 150px;
}

.img-branch {
  margin: 10px;
}
</style>
