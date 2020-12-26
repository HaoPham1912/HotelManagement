<template>
  <div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-10">
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
        <tbody v-if="branchs.employees">
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
                  class="btn-sm btn-warning"
                  @click="getIdEmp(data.employeeId)"
                  data-mdb-toggle="modal"
                  data-mdb-target="#exampleModal"
                >
                  <i class="fas fa-pencil-alt"></i>
                  EDIT
                </button>
              </div>
              <div>
                <button class="btn-sm btn-danger" @click="remove(scope.row)">
                  <i class="fas fa-trash"></i>
                  DELETE
                </button>
              </div>
            </td>
          </tr>
        </tbody>
        <tbody v-else>
          <td>DO NOT HAVE ANY EMPLOYEE</td>
        </tbody>
      </table>
      <h3>Image Of Branch</h3>
      <div
        style="display: flex; justify-content:space-between ; flex-wrap: wrap"
      >
        <div class="img-branch" v-if="branchs.thumbnailsBranchSet[0]">
          <img :src="branchs.thumbnailsBranchSet[0]" />
          <button style="margin-left: 80px" class="btn btn-outline-primary">
            Change Image
          </button>
        </div>

        <div class="img-branch" v-if="branchs.thumbnailsBranchSet[1]">
          <img :src="branchs.thumbnailsBranchSet[1]" />
          <button style="margin-left: 80px" class="btn btn-outline-primary">
            Change Image
          </button>
        </div>
        <div class="img-branch" v-if="branchs.thumbnailsBranchSet[2]">
          <img :src="branchs.thumbnailsBranchSet[2]" />
          <button style="margin-left: 80px" class="btn btn-outline-primary">
            Change Image
          </button>
        </div>
        <div class="img-branch" v-if="branchs.thumbnailsBranchSet[3]">
          <img :src="branchs.thumbnailsBranchSet[3]" />
          <button style="margin-left: 80px" class="btn btn-outline-primary">
            Change Image
          </button>
        </div>
      </div>
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
        thumbnailsBranchSet: [],
      },
    };
  },
  methods: {
    getDetailBranch(branchCode) {
      BranchService.getBranchByCode(branchCode).then((response) => {
        console.log(response.data);
        this.branchs.employees = response.data.employeeDTOSet;
        this.branchs.mainImage = response.data.mainImage;
        this.branchs.thumbnailsBranchSet = response.data.thumbnailsBranchSet;
        console.log(this.branchs.thumbnailsBranchSet);
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
  width: 400px;
  height: 250px;
}

.img-branch {
  margin: 10px;
}
</style>
