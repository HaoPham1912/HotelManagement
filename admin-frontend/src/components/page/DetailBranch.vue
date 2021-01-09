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
            <!-- <th></th> -->
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
          </tr>
        </tbody>
        <tbody v-else>
          <td>DO NOT HAVE ANY EMPLOYEE</td>
        </tbody>
      </table>
      <div style="display: flex;">
        <h3>Room In Branch</h3>
        <!-- <button>Add Room</button> -->
      </div>
      <table class="table">
        <thead>
          <tr>
            <th scope="col">Room Id</th>
            <th scope="col">Room Code</th>
            <th scope="col">Policy Code</th>
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
            <td>{{ data.roomId }}</td>
            <td>{{ data.roomCode }}</td>
            <td>{{ data.policyCode }}</td>
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
                <mdb-btn
                  color="warning"
                  class="btn-sm btn-warning"
                  v-tooltip.top-center="{
                    content: 'Edit this room',
                  }"
                  @click="editRoom(data.roomId)"
                >
                  <a class="btn-link-edit action-button">
                    <i class="fas fa-pencil-alt"></i>
                  </a>
                </mdb-btn>
              </div>
              <div>
                <mdb-btn
                  :class="{
                    'btn-sm btn-danger': data.status === 'true',
                    'btn-sm btn-success': data.status === 'false',
                  }"
                  color="data.status : danger ? success"
                  @click="ShowModalDisable(data.roomId)"
                  v-tooltip.top-center="{
                    content: setTextTooltip(data.status),
                  }"
                >
                  <i
                    :class="{
                      'fas fa-ban': data.status === 'true',
                      'fas fa-plus': data.status === 'false',
                    }"
                  ></i>
                </mdb-btn>
              </div>
            </td>
          </tr>
        </tbody>
        <tbody v-else>
          <td>DO NOT HAVE ANY ROOM</td>
        </tbody>
        <div>
          <mdb-modal size="md" :show="modal" @close="modal = false">
            <mdb-modal-header>
              <mdb-modal-title>Modal title</mdb-modal-title>
            </mdb-modal-header>
            <mdb-modal-body>
              <div class="form-outline mb-4">
                <label class="form-label" for="branchCode">Branch Code</label>
                <input v-model="currentRoom.branchCode" disabled />
              </div>
              <div class="form-outline mb-4">
                <label for="roomCode">Room Code</label>
                <input
                  type="text"
                  id="roomCode"
                  class="form-control"
                  v-model="currentRoom.roomCode"
                  disabled
                  required
                />
              </div>
              <div class="form-outline mb-4">
                <label class="form-label" for="policyCode"
                  >Choose Cancel Policy</label
                >
                <b-form-select
                  v-model="currentRoom.policyCode"
                  :options="policyCodes"
                ></b-form-select>
              </div>

              <div class="form-outline mb-4">
                <label for="name">Room Name</label>
                <input
                  type="text"
                  id="name"
                  class="form-control"
                  v-model="currentRoom.name"
                  required
                />
              </div>
              <div class="form-outline mb-4">
                <label for="policy">Choose Ammount People</label>
                <select
                  class="browser-default custom-select"
                  v-model="currentRoom.ammountPeople"
                >
                  <option value="1">1</option>
                  <option value="2">2</option>
                  <option value="3">3</option>
                  <option value="4">4</option>
                </select>
              </div>
              <div class="form-outline mb-4">
                <label for="price">Price</label>
                <input
                  type="text"
                  id="price"
                  class="form-control"
                  v-model="currentRoom.price"
                  required
                />
              </div>
              <div class="form-outline mb-4">
                <label for="description">Description</label>
                <textarea
                  id="description"
                  rows="4"
                  cols="50"
                  class="form-control"
                  v-model="currentRoom.description"
                  required
                />
              </div>
              <div class="form-outline mb-4">
                <label for="image">Choose Image Of Room</label>
                <div class="input-group">
                  <div>
                    <b-button variant="success" @click="onPickFile">
                      Upload Image
                    </b-button>
                  </div>
                  <div class="custom-file">
                    <input
                      type="file"
                      class="custom-file-input"
                      style="display:none"
                      ref="fileInput"
                      accept="image/*"
                      @change="onFilePicked"
                    />
                  </div>
                </div>
                <div>
                  <img :src="imageUrl" alt="" height="150" />
                </div>
              </div>
            </mdb-modal-body>
            <mdb-modal-footer>
              <mdb-btn color="secondary" size="md" @click.native="modal = false"
                >Close</mdb-btn
              >
              <mdb-btn
                color="primary"
                size="md"
                @click="updateRoom(currentRoom.roomId)"
                >Save changes</mdb-btn
              >
            </mdb-modal-footer>
          </mdb-modal>
        </div>
        <div>
          <mdb-modal centered :show="modalDelete" @close="modalDelete = false">
            <mdb-modal-header>
              <mdb-modal-title>ARE YOU SURE?</mdb-modal-title>
            </mdb-modal-header>
            <mdb-modal-body
              ><strong>Please check before action</strong></mdb-modal-body
            >
            <mdb-modal-footer>
              <mdb-btn color="danger" @click.native="modalDelete = false"
                >Close</mdb-btn
              >
              <mdb-btn color="success" @click="disableRoom(currentRoom.roomId)"
                >OK</mdb-btn
              >
            </mdb-modal-footer>
          </mdb-modal>
        </div>
      </table>
    </div>
  </div>
</template>

<script>
import BranchService from '../../services/BranchService';
import RoomService from '../../services/RoomService';
import CancelPolicyService from '../../services/CancelPolicyService';
import firebase from 'firebase';
import {
  mdbModal,
  mdbModalHeader,
  mdbModalTitle,
  mdbModalBody,
  mdbModalFooter,
  mdbBtn,
} from 'mdbvue';
export default {
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
      branchCode: this.$route.params.branchCode,
      branchs: {
        employees: [],
        mainImage: '',
        thumbnailsBranchSet: [],
        rooms: [],
        roomCode: '',

        page: 1,
        count: 0,
        pageSize: 3,

        pageSizes: [3, 6, 9],
      },
      currentRoom: {
        branchCode: '',
        roomCode: '',
        policyCode: '',
        name: '',
        ammountPeople: '',
        price: '',
        description: '',
        mainImage: '',
      },
      modal: false,
      modalDelete: false,

      branch: [],
      branchCodes: [],
      policies: [],
      policyCodes: [],

      imageUrl: '',
      image: null,
    };
  },
  methods: {
    onPickFile() {
      this.$refs.fileInput.click();
    },
    ShowModalDisable(id) {
      console.log(id);
      this.modalDelete = true;
      RoomService.getRoomById(id)
        .then((response) => {
          this.currentRoom = response.data;
          console.log(this.currentRoom);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    onFilePicked(event) {
      const files = event.target.files;
      let fileName = files[0].name;
      if (fileName.lastIndexOf('.') <= 0) {
        return alert('Please add a valid file');
      }
      const fileReader = new FileReader();
      fileReader.addEventListener('load', () => {
        console.log(fileReader.result);
        this.imageUrl = fileReader.result;
      });
      fileReader.readAsDataURL(files[0]);
      this.image = event.target.files[0];
      this.onUpload();
    },
    onUpload() {
      return new Promise((resolve, reject) => {
        const storageRef = firebase
          .storage()
          .ref(`${this.image.name}`)
          .put(this.image);
        storageRef.on(
          `state_changed`,
          (snapshot) => {
            this.uploadValue =
              (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
          },
          (error) => {
            console.log(error.message);
            reject();
          },
          () => {
            this.uploadValue = 100;
            storageRef.snapshot.ref.getDownloadURL().then((url) => {
              console.log('=------------------');
              console.log(url);
              if (url !== '') {
                this.rooms.mainImage = url;

                console.log('dasdasdasd');

                console.log(this.rooms.mainImage);
              }
            });
            resolve();
          }
        );
      });
    },

    async updateRoom(id) {
      if (this.imageUrl !== this.currentRoom.mainImage) {
        await this.onUpload();
      }
      var data = {
        roomCode: this.currentRoom.roomCode,
        policyCode: this.currentRoom.policyCode,
        branchCode: this.currentRoom.branchCode,
        name: this.currentRoom.name,
        ammountPeople: this.currentRoom.ammountPeople,
        price: this.currentRoom.price,
        description: this.currentRoom.description,
        mainImage: this.currentRoom.mainImage,
      };

      RoomService.updateRoom(id, data).then((response) => {
        console.log(response.data);
        alert('Room information have been updated');
        this.modal = false;
        window.location.reload();
      });
    },

    setPolicyCode() {
      CancelPolicyService.getAll()
        .then((response) => {
          this.policies = response.data;

          console.log(this.policies);

          for (var i = 0; i < this.policies.length; i++) {
            var options = [];
            for (var key in this.policies[i]) {
              if (key == 'policyCode') {
                options['value'] = this.policies[i][key];
                options['text'] = this.policies[i][key];
              }
            }
            this.policyCodes.push(Object.assign({}, options));
          }
          console.log(this.policyCodes);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    bidingdataRoom(id) {
      this.modal = true;
      RoomService.getRoomById(id).then((response) => {
        console.log(response.data);
        this.currentRoom = response.data;
        this.imageUrl = response.data.mainImage;
      });
    },
    getDetailBranch(branchCode) {
      BranchService.getBranchByCode(branchCode).then((response) => {
        console.log(response.data);
        this.branchs.employees = response.data.employeeDTOSet;
        this.branchs.mainImage = response.data.mainImage;
        this.branchs.rooms = response.data.roomDTOList;
      });
    },
    disableRoom(id) {
      RoomService.disableRoom(id).then(() => {
        alert('Room have been disable');
        this.modalDelete = false;
        window.location.reload();
      });
    },
    setTextTooltip(text) {
      if (text === 'true') {
        return 'Disable this room';
      } else {
        return 'Enable this room';
      }
    },
  },

  mounted() {
    this.getDetailBranch(this.branchCode);
    this.setPolicyCode();
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
