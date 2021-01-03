<template>
  <div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-10">
      <div style="display: flex;">
        <h3>Bed In Room</h3>
        <button>Add Bed</button>
      </div>
      <table class="table">
        <thead>
          <tr>
            <th scope="col">Bed Id</th>
            <th scope="col">Bed Name</th>
            <th scope="col">Ammount People</th>
            <th scope="col">Price</th>
            <th scope="col">Description</th>
            <th></th>
          </tr>
        </thead>
        <tbody v-if="rooms.beds">
          <tr v-for="(data, index) in rooms.beds" :key="index">
            <td>{{ data.bedId }}</td>
            <td>{{ data.name }}</td>
            <td>{{ data.amountPeople }}</td>
            <td>{{ data.price }}</td>
            <td>{{ data.description }}</td>
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
        <h3>Service In Room</h3>
        <button>Add Service</button>
      </div>
      <table class="table">
        <thead>
          <tr>
            <th scope="col">Service Id</th>
            <th scope="col">Service Code</th>
            <th scope="col">Service Name</th>
            <th scope="col">Service Price</th>
            <th scope="col">Description</th>
            <th></th>
          </tr>
        </thead>
        <tbody v-if="rooms.services">
          <tr v-for="(data, index) in rooms.services" :key="index">
            <td>{{ data.serviceId }}</td>
            <td>{{ data.serviceCode }}</td>
            <td>{{ data.name }}</td>
            <td>{{ data.price }}</td>
            <td>{{ data.description }}</td>
            <td class="action">
              <div>
                <button
                  class="btn-sm btn-warning"
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
import RoomService from '../../services/RoomService';
export default {
  name: 'branch',
  data() {
    return {
      roomCode: this.$route.params.roomCode,
      rooms: {
        beds: [],
        mainImage: '',
        thumbnailsBranchSet: [],
        services: [],
      },
    };
  },
  methods: {
    getDetailBranch(roomCode) {
      RoomService.getRoomByRoomCode(roomCode).then((response) => {
        console.log(response.data);
        this.rooms.beds = response.data.bedDTOList;
        this.rooms.services = response.data.servicesDTOList;
      });
    },
  },

  mounted() {
    this.getDetailBranch(this.roomCode);
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
