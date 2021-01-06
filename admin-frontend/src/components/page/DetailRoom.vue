<template>
  <div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-10">
      <div class="title-table">
        <h3>Bed In Room</h3>
        <mdb-btn color="success">ADD BED</mdb-btn>
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
              <!-- <div>
                <button
                  class="btn-sm btn-warning"
                  @click="getIdEmp(data.employeeId)"
                  data-mdb-toggle="modal"
                  data-mdb-target="#exampleModal"
                >
                  <i class="fas fa-pencil-alt"></i>
                  EDIT
                </button>
              </div> -->
              <div>
                <button class="btn-sm btn-danger" @click="remove(scope.row)">
                  <i class="fas fa-trash"></i>
                  REMOVE
                </button>
              </div>
            </td>
          </tr>
        </tbody>
        <tbody v-else>
          <p style="color: red;"><strong>DO NOT HAVE ANY BEDS</strong></p>
        </tbody>
      </table>
      <div class="title-table">
        <h3>Service In Room</h3>
        <mdb-btn color="success">ADD SERVICE</mdb-btn>
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
                <button class="btn-sm btn-danger" @click="remove(scope.row)">
                  <i class="fas fa-trash"></i>
                  REMOVE
                </button>
              </div>
            </td>
          </tr>
        </tbody>
        <tbody v-else>
          <p style="color: red;"><strong>DO NOT HAVE ANY SERVICES</strong></p>
        </tbody>
      </table>
      <div>
        <mdb-modal centered :show="modal" @close="modal = false">
          <mdb-modal-header>
            <mdb-modal-title>Update Account Information</mdb-modal-title>
          </mdb-modal-header>
          <mdb-modal-body> </mdb-modal-body>
          <mdb-modal-footer>
            <mdb-btn color="danger" @click.native="modal = false"
              >Close</mdb-btn
            >
            <mdb-btn color="primary">Save changes</mdb-btn>
          </mdb-modal-footer>
        </mdb-modal>
      </div>
    </div>
  </div>
</template>

<script>
import RoomService from '../../services/RoomService';
import {
  mdbModal,
  mdbModalHeader,
  mdbModalTitle,
  mdbModalBody,
  mdbModalFooter,
  mdbBtn,
} from 'mdbvue';
export default {
  watch: {},
  name: 'branch',
  components: {
    mdbModal,
    mdbModalHeader,
    mdbModalTitle,
    mdbModalBody,
    mdbModalFooter,
    mdbBtn,
  },
  data() {
    return {
      roomCode: this.$route.params.roomCode,
      rooms: {
        beds: [],
        mainImage: '',
        thumbnailsBranchSet: [],
        services: [],
      },
      modal: false,
    };
  },
  methods: {
    getDetailRoom(roomCode) {
      RoomService.getRoomByRoomCode(roomCode).then((response) => {
        console.log(response.data);
        this.rooms.beds = response.data.bedDTOList;
        this.rooms.services = response.data.servicesDTOList;
      });
    },
  },

  mounted() {
    this.getDetailRoom(this.roomCode);
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

.title-table {
  display: flex;
}
.title-table button {
  margin-left: 30px;
}
</style>
