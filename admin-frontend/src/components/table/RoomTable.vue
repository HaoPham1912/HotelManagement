<template>
  <section id="dashboard">
    <mdb-row>
      <mdb-col md="2"></mdb-col>
      <mdb-col md="10">
        <mdb-card class="mb-4">
          <div class="row">
            <div class="col-md-6">
              <a href="/admin/add-room" type="button" class="btn btn-success">
                Add new Room
              </a>
            </div>
            <div class="col-md-3">
              <mdb-btn class="btn-showall" color="info" @click="showAll"
                >Show All</mdb-btn
              >
            </div>
            <div class="col-md-3">
              <div class="input-group md-form form-sm form-2 pl-0">
                <input
                  class="form-control my-0 py-1 lime-border"
                  type="text"
                  placeholder="Search by Room Code"
                  aria-label="Search"
                  name="roomCode"
                  v-model="roomCode"
                  @keyup.enter="handelSearch"
                />
                <div class="input-group-append">
                  <button
                    class="input-group-text lime lighten-2"
                    id="basic-text1"
                    type="submit"
                    @click="handelSearch"
                  >
                    <span>
                      <mdbIcon icon="search" />
                    </span>
                  </button>
                </div>
              </div>
            </div>
          </div>
          <mdb-card-body>
            <mdb-tbl>
              <thead class="blue-grey lighten-4">
                <tr>
                  <th>RoomID</th>
                  <th>RoomCode</th>
                  <th>Name</th>
                  <th>Price</th>
                  <th>In Branch</th>
                  <th>Description</th>
                  <th>Room Image</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(data, index) in rooms" :key="index">
                  <td>{{ data.roomId }}</td>
                  <td>
                    <a :href="'detail-room/' + data.roomCode">{{
                      data.roomCode
                    }}</a>
                  </td>
                  <td>{{ data.name }}</td>
                  <td>{{ data.price }}</td>
                  <td>{{ data.branchCode }}</td>
                  <td class="descrip-room">{{ data.description }}</td>
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
            </mdb-tbl>
            <div>
              <mdb-modal
                centered
                :show="modalDelete"
                @close="modalDelete = false"
              >
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
                  <mdb-btn
                    color="success"
                    @click="disableRoom(currentRoom.roomId)"
                    >OK</mdb-btn
                  >
                </mdb-modal-footer>
              </mdb-modal>
            </div>
          </mdb-card-body>
          <br />
          <div id="paging">
            <b-pagination
              v-model="page"
              :total-rows="count"
              :per-page="pageSize"
              first-text="First"
              prev-text="Prev"
              next-text="Next"
              last-text="Last"
              @change="handlePageChange"
            ></b-pagination>
          </div>
        </mdb-card>
      </mdb-col>
    </mdb-row>
  </section>
</template>
<script>
import {
  mdbRow,
  mdbCol,
  mdbCard,
  mdbCardBody,
  mdbTbl,
  mdbIcon,
  mdbModal,
  mdbModalHeader,
  mdbModalTitle,
  mdbModalBody,
  mdbModalFooter,
  mdbBtn,
} from 'mdbvue';

import RoomService from '../../services/RoomService';
import { AUTH_LOGOUT } from '../../store/actions/auth';
export default {
  data() {
    return {
      rooms: [],
      currentRoom: {},
      currentIndex: -1,
      roomCode: '',

      page: 1,
      count: 0,
      pageSize: 3,

      pageSizes: [3, 6, 9],

      modalDelete: false,
    };
  },
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbCardBody,
    mdbTbl,
    mdbIcon,
    mdbModal,
    mdbModalHeader,
    mdbModalTitle,
    mdbModalBody,
    mdbModalFooter,
    mdbBtn,
  },
  methods: {
    getRequestParams(roomCode, page, pageSize) {
      let params = {};

      if (roomCode) {
        params['roomCode'] = roomCode;
      }
      if (page) {
        params['pageNo'] = page - 1;
      }
      if (pageSize) {
        params['size'] = pageSize;
      }

      return params;
    },
    retrieveRoom() {
      const params = this.getRequestParams(
        this.roomCode,
        this.page,
        this.pageSize
      );
      RoomService.getAll(params)
        .then((response) => {
          const { rooms, totalItems } = response.data;
          this.rooms = rooms;
          this.count = totalItems;
          if (Object.entries(this.rooms).length === 0) {
            alert('Session time out!!!');
            this.$store
              .dispatch(AUTH_LOGOUT)
              .then(() => this.$router.push('/login'));
          }
        })
        .catch((e) => {
          console.log(e);
          alert('Session time out!!!');
          this.$store
            .dispatch(AUTH_LOGOUT)
            .then(() => this.$router.push('/login'));
        });
    },
    handlePageChange(value) {
      this.page = value;
      this.retrieveRoom();
    },

    handlePageSizeChange(event) {
      this.pageSize = event.target.value;
      this.page = 1;
      this.retrieveRoom();
    },

    editRoom(id) {
      console.log(`id is ${id}`);
      this.$router.push(`room/${id}`);
    },

    disableRoom(id) {
      RoomService.disableRoom(id).then(() => {
        RoomService.getAll().then((response) => {
          const { rooms, totalItems } = response.data;
          this.rooms = rooms;
          this.count = totalItems;
          console.log(response.data);
          if (this.rooms.status === 'true') {
            this.messageTooltip = 'Disable this room';
          } else {
            this.messageTooltip = 'Enable this room';
          }
          this.modalDelete = false;
        });
      });
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
    handelSearch() {
      this.page = 1;
      this.retrieveRoom();
    },

    showAll() {
      this.roomCode = '';
      this.retrieveRoom();
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
    this.retrieveRoom();
  },
};
</script>
<style scoped>
.action {
  display: flex;
  justify-content: space-evenly;
}
a {
  right: 0;
  width: 200px;
}
.link-add {
  margin-right: auto;
}
img {
  width: 200px;
  height: 120px;
  text-align: center;
}
.descrip-room {
  width: 300px;
}
</style>
