<template>
  <div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-10">
      <div class="title-table">
        <h3>Bed In Room</h3>
        <mdb-btn color="success" @click="openModalAddBed">ADD BED</mdb-btn>
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
                  class="btn-sm btn-danger"
                  @click="openModalRemoveBed(data.bedId)"
                >
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
        <mdb-btn color="success" @click="openModalAddService"
          >ADD SERVICE</mdb-btn
        >
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
                  class="btn-sm btn-danger"
                  @click="openModalRemoveService(data.serviceId)"
                >
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
      <div class="modal-bed">
        <mdb-modal centered :show="modal" @close="modal = false">
          <mdb-modal-header>
            <mdb-modal-title>ADD BED TO THIS ROOM</mdb-modal-title>
          </mdb-modal-header>
          <mdb-modal-body
            ><div>
              <p>
                <strong
                  ><center>CHOOSE BED INFORMATION WANT TO ADD</center></strong
                >
              </p>
              <div v-for="item in bedAvaiAdd" v-bind:key="item.bedId">
                <input type="checkbox" :value="item.bedId" v-model="idBedAdd" />
                <label>{{ item }}</label>
              </div>
              <br />
              <span>Checked names: {{ idBedAdd }}</span>
            </div>
          </mdb-modal-body>
          <mdb-modal-footer>
            <mdb-btn color="danger" @click.native="modal = false"
              >Close</mdb-btn
            >
            <mdb-btn color="primary" @click="addBedToRoom(roomCode)"
              >Save changes</mdb-btn
            >
          </mdb-modal-footer>
        </mdb-modal>
      </div>
      <div class="modal-remove-bed">
        <mdb-modal centered :show="modalRemove" @close="modalRemove = false">
          <mdb-modal-header>
            <mdb-modal-title>ARE YOU SURE?</mdb-modal-title>
          </mdb-modal-header>
          <mdb-modal-body>
            <p>
              <strong
                ><center>
                  This action will remove this bed out of room
                </center></strong
              >
            </p>
          </mdb-modal-body>
          <mdb-modal-footer>
            <mdb-btn color="danger" @click.native="modalRemove = false"
              >NO</mdb-btn
            >
            <mdb-btn color="primary" @click="removeBedOutRoom(roomCode)"
              >YES</mdb-btn
            >
          </mdb-modal-footer>
        </mdb-modal>
      </div>
      <div class="modal-service">
        <mdb-modal centered :show="modalService" @close="modalService = false">
          <mdb-modal-header>
            <mdb-modal-title>ADD SERVICES TO THIS ROOM</mdb-modal-title>
          </mdb-modal-header>
          <mdb-modal-body
            ><div>
              <p>
                <strong
                  ><center>
                    CHOOSE SERVICES INFORMATION WANT TO ADD
                  </center></strong
                >
              </p>
              <div v-for="item in serviceAvailAdd" v-bind:key="item.serviceId">
                <input
                  type="checkbox"
                  :value="item.serviceId"
                  v-model="idServiceAdd"
                />
                <label>{{ item }}</label>
              </div>
              <br />
              <span>Checked names: {{ idServiceAdd }}</span>
            </div>
          </mdb-modal-body>
          <mdb-modal-footer>
            <mdb-btn color="danger" @click.native="modalService = false"
              >Close</mdb-btn
            >
            <mdb-btn color="primary" @click="addServiceToRoom(roomCode)"
              >Save changes</mdb-btn
            >
          </mdb-modal-footer>
        </mdb-modal>
      </div>
      <div class="modal-remove-service">
        <mdb-modal
          centered
          :show="modalRemoveService"
          @close="modalRemoveService = false"
        >
          <mdb-modal-header>
            <mdb-modal-title>ARE YOU SURE?</mdb-modal-title>
          </mdb-modal-header>
          <mdb-modal-body>
            <p>
              <strong
                ><center>
                  This action will remove this service out of room
                </center></strong
              >
            </p>
          </mdb-modal-body>
          <mdb-modal-footer>
            <mdb-btn color="danger" @click.native="modalRemoveService = false"
              >NO</mdb-btn
            >
            <mdb-btn color="primary" @click="removeServiceOutRoom(roomCode)"
              >YES</mdb-btn
            >
          </mdb-modal-footer>
        </mdb-modal>
      </div>
    </div>
  </div>
</template>

<script>
import RoomService from '../../services/RoomService';
import ServicesService from '../../services/ServicesService';
import BedService from '../../services/BedService';
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
      modalRemove: false,

      modalService: false,
      modalRemoveService: false,

      bedlist: [],
      bedAvaiAdd: [],
      idBedAdd: [],
      listIdBed: [],
      currentBedId: '',

      servicesList: [],
      serviceAvailAdd: [],
      idServiceAdd: [],
      currentServiceId: '',
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

    getAllBed() {
      BedService.getAllBedNoPaging()
        .then((respose) => {
          this.bedlist = respose.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getAllService() {
      ServicesService.getAllNoPaging()
        .then((response) => {
          this.servicesList = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getAvailBed() {
      var fullBedList = this.bedlist;
      console.log(fullBedList);
      var bedsToRemove = this.rooms.beds;
      console.log(bedsToRemove);
      var props = ['bedId', 'name', 'amountPeople', 'price'];
      var result = fullBedList
        .filter(function(o1) {
          // filter out (!) items in result2
          return !bedsToRemove.some(function(o2) {
            return o1.bedId === o2.bedId; // assumes unique id
          });
        })
        .map(function(o) {
          // use reduce to make objects with only the required properties
          // and map to apply this to the filtered array as a whole
          return props.reduce(function(newo, name) {
            newo[name] = o[name];
            return newo;
          }, {});
        });

      this.bedAvaiAdd = result;
      console.log('aaaaaaaaaaaaaaaaa');
      console.log(this.bedAvaiAdd);
    },
    getAvailService() {
      var fullServiceList = this.servicesList;
      console.log(fullServiceList);
      var servicesToRemove = this.rooms.services;
      console.log(servicesToRemove);
      var props = ['serviceId', 'name', 'serviceCode', 'price'];
      var result = fullServiceList
        .filter(function(o1) {
          // filter out (!) items in result2
          return !servicesToRemove.some(function(o2) {
            return o1.serviceId === o2.serviceId; // assumes unique id
          });
        })
        .map(function(o) {
          // use reduce to make objects with only the required properties
          // and map to apply this to the filtered array as a whole
          return props.reduce(function(newo, serviceCode) {
            newo[serviceCode] = o[serviceCode];
            return newo;
          }, {});
        });

      this.serviceAvailAdd = result;
      console.log(result);
      console.log(this.serviceAvailAdd);
    },

    addBedToRoom(roomCode) {
      console.log(roomCode);
      const toNumbers = (arr) => arr.map(Number);
      this.listIdBed = toNumbers(this.idBedAdd);
      var data = {
        longListIdBed: this.listIdBed,
      };
      RoomService.addBedToRoom(roomCode, data).then(() => {
        alert('Bed have been added!!!');
        this.modal = false;
        window.location.reload();
      });
    },

    addServiceToRoom(roomCode) {
      console.log(roomCode);
      // const toNumbers = (arr) => arr.map(Number);
      //this.listIdBed = toNumbers(this.idBedAdd);
      var data = {
        longListIdService: this.idServiceAdd,
      };
      RoomService.addServiceToRoom(roomCode, data).then(() => {
        alert('Service have been added!!!');
        this.modalService = false;
        window.location.reload();
      });
    },

    removeBedOutRoom(rommCode) {
      let bedId = this.currentBedId;
      RoomService.deleteBedToRoom(rommCode, bedId).then(() => {
        alert('Bed have been removed!!!');
        this.modalRemove = false;
        window.location.reload();
      });
    },

    removeServiceOutRoom(rommCode) {
      let serviceId = this.currentServiceId;
      RoomService.deleteServiceToRoom(rommCode, serviceId).then(() => {
        alert('Service have been removed!!!');
        this.modalRemoveService = false;
        window.location.reload();
      });
    },

    openModalAddBed() {
      this.modal = true;
      this.getAvailBed();
    },

    openModalAddService() {
      this.modalService = true;
      this.getAvailService();
    },

    openModalRemoveBed(id) {
      this.modalRemove = true;
      this.currentBedId = id;
      console.log(this.currentBedId);
    },
    openModalRemoveService(id) {
      this.modalRemoveService = true;
      this.currentServiceId = id;
      console.log(this.currentServiceId);
    },
  },

  mounted() {
    this.getDetailRoom(this.roomCode);
    this.getAllBed();
    this.getAllService();
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
