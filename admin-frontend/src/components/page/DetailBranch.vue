<template>
  <div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-10">
      <div style="display: flex;">
        <h3>Employee Of Branch</h3>
        <!-- <button>Add Employee</button> -->
      </div>
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
      </table>
      <div style="display: flex;">
        <h3>Room In Branch</h3>
        <!-- <button>Add Room</button> -->
      </div>
      <table class="table">
        <thead>
          <tr>
            <th scope="col">Room Code</th>
            <th scope="col">Room Name</th>
            <th scope="col">Room Price</th>
            <th scope="col">Ammount People</th>
            <th scope="col">Description</th>
            <th scope="col">Main Image</th>
            <th></th>
          </tr>
        </thead>
        <tbody v-if="branchs.rooms">
          <tr v-for="(data, index) in branchs.rooms" :key="index">
            <td>{{ data.roomCode }}</td>
            <td>{{ data.name }}</td>
            <td>{{ data.price }}</td>
            <td>{{ data.ammountPeople }}</td>
            <td>{{ data.description }}</td>
            <td>
              <div class="img-table">
                <img :src="data.mainImage" alt="" />
              </div>
            </td>
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
        rooms: [],
      },
    };
  },
  methods: {
    getDetailBranch(branchCode) {
      BranchService.getBranchByCode(branchCode).then((response) => {
        console.log(response.data);
        this.branchs.employees = response.data.employeeDTOSet;
        this.branchs.mainImage = response.data.mainImage;
        this.branchs.rooms = response.data.roomDTOList;
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
  width: 200px;
  height: 120px;
  text-align: center;
}

.img-branch {
  margin: 10px;
}
</style>
