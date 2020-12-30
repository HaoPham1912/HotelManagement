<template>
  <section id="dashboard">
    <mdb-row>
      <mdb-col md="2"></mdb-col>
      <mdb-col md="10">
        <mdb-card class="mb-4">
          <div class="link-add"></div>
          <div class="row">
            <div class="col-md-9">
              <a href="/room/add" type="button" class="btn btn-success">
                Add new Room
              </a>
            </div>
            <div class="col-md-3">
              <div class="input-group md-form form-sm form-2 pl-0">
                <input
                  class="form-control my-0 py-1 lime-border"
                  type="text"
                  placeholder="Search by Name"
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
                  <th>In Room</th>
                  <th>Description</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(data, index) in rooms" :key="index">
                  <td>{{ data.roomId }}</td>
                  <td>
                    <a href="">{{ data.roomCode }}</a>
                  </td>
                  <td>{{ data.name }}</td>
                  <td>{{ data.price }}</td>
                  <td>{{ data.branchCode }}</td>
                  <td>{{ data.description }}</td>
                  <td class="action">
                    <div>
                      <button class="btn-sm btn-warning">
                        <a
                          class="btn-link-edit action-button"
                          @click="edit(scope.row)"
                        >
                          <i class="fas fa-pencil-alt"></i> </a
                        >EDIT
                      </button>
                    </div>
                    <div>
                      <button class="btn-sm btn-danger">
                        <a
                          class="btn-link-delete action-button"
                          @click="remove(scope.row)"
                        >
                          <i class="fas fa-trash"></i>
                        </a>
                        DELETE
                      </button>
                    </div>
                  </td>
                </tr>
              </tbody>
            </mdb-tbl>
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
import { mdbRow, mdbCol, mdbCard, mdbCardBody, mdbTbl, mdbIcon } from 'mdbvue';

import RoomService from '../../services/RoomService';
export default {
  data() {
    return {
      rooms: [],
      currentRoom: null,
      currentIndex: -1,
      roomCode: '',

      page: 1,
      count: 0,
      pageSize: 3,

      pageSizes: [3, 6, 9],
    };
  },
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbCardBody,
    mdbTbl,
    mdbIcon,
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
        })
        .catch((e) => {
          console.log(e);
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

    handelSearch() {
      this.page = 1;
      this.retrieveRoom();
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
</style>
