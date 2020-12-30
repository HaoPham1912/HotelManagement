<template>
  <div>
    <section id="dashboard">
      <mdb-row>
        <mdb-col md="2"></mdb-col>
        <mdb-col md="10">
          <mdb-card class="mb-4">
            <div class="row">
              <div class="col-md-9">
                <a href="/bed/add" type="button" class="btn btn-success">
                  Add new Bed
                </a>
              </div>
              <div class="col-md-3">
                <div class="input-group md-form form-sm form-2 pl-0">
                  <input
                    class="form-control my-0 py-1 lime-border"
                    type="text"
                    placeholder="Search by Name"
                    aria-label="Search"
                    name="searchName"
                    v-model="searchName"
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
                    <th>Bed ID</th>
                    <th>Name</th>
                    <th>Amount People</th>
                    <th>Price</th>
                    <th>In Room</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(data, index) in beds" :key="index">
                    <td>{{ data.bedId }}</td>
                    <td>{{ data.name }}</td>
                    <td>{{ data.amountPeople }}</td>
                    <td>{{ data.price }}</td>
                    <td>{{ data.roomCodeSet }}</td>
                    <td class="action">
                      <div>
                        <mdb-btn
                          color="warning"
                          class="btn-sm btn-warning"
                          v-tooltip.top-center="{
                            content: 'Edit this account',
                          }"
                          @click="bindingDataToModal(data.bedId)"
                        >
                          <i class="fas fa-pencil-alt"></i>
                          <a class="btn-link-edit action-button"> </a>
                        </mdb-btn>
                      </div>
                      <div>
                        <mdb-btn
                          :class="{
                            'btn-sm btn-danger': data.status === 'true',
                            'btn-sm btn-success': data.status === 'false',
                          }"
                          color="data.status : danger ? success"
                          @click="ShowModalDisable(data.bedId)"
                          v-tooltip.top-center="{
                            content: setTextTooltip(data.status),
                          }"
                        >
                          <a :href="'bed/' + data.bedId"> </a>
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
                <mdb-modal centered :show="modal" @close="modal = false">
                  <mdb-modal-header>
                    <mdb-modal-title>Update Bed Information</mdb-modal-title>
                  </mdb-modal-header>
                  <mdb-modal-body>
                    <div class="form-outline mb-4">
                      <label for="name">Bed Name</label>
                      <input
                        type="text"
                        id="name"
                        class="form-control"
                        v-model="currentBed.name"
                        required
                      />
                    </div>
                    <div class="form-outline mb-4">
                      <label for="amount">Amount People</label>
                      <input
                        type="text"
                        id="address"
                        class="form-control"
                        v-model="currentBed.amountPeople"
                        required
                      />
                    </div>
                    <div class="form-outline mb-4">
                      <label for="price">Price</label>
                      <input
                        type="text"
                        id="price"
                        class="form-control"
                        v-model="currentBed.price"
                        required
                      />
                    </div>
                    <div class="form-outline mb-4">
                      <label for="description">Description</label>
                      <textarea
                        id="description"
                        name="description"
                        rows="4"
                        cols="50"
                        class="form-control"
                        v-model="currentBed.description"
                        required
                      />
                    </div>
                  </mdb-modal-body>
                  <mdb-modal-footer>
                    <mdb-btn color="danger" @click.native="modal = false"
                      >Close</mdb-btn
                    >
                    <mdb-btn
                      color="primary"
                      @click="updateBed(currentBed.bedId)"
                      >Save changes</mdb-btn
                    >
                  </mdb-modal-footer>
                </mdb-modal>
              </div>
              <div>
                <mdb-modal
                  centered
                  :show="modalDelete"
                  @close="modalDelete = false"
                >
                  <mdb-modal-header>
                    <mdb-modal-title>ARE YOU SURE?</mdb-modal-title>
                  </mdb-modal-header>
                  <mdb-modal-body>PLEASE CHECK BEFORE ACTION</mdb-modal-body>
                  <mdb-modal-footer>
                    <mdb-btn color="danger" @click.native="modalDelete = false"
                      >Close</mdb-btn
                    >
                    <mdb-btn
                      color="primary"
                      @click="deleteBed(currentBed.bedId)"
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
  </div>
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
import BedService from '../../services/BedService';
export default {
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
  data() {
    return {
      beds: [],
      currentBed: {
        name: '',
        amountPeople: '',
        price: '',
        description: '',
      },
      currentIndex: -1,
      searchName: '',

      page: 1,
      count: 0,
      pageSize: 3,

      pageSizes: [3, 6, 9],
      modal: false,
      modalDelete: false,
    };
  },

  methods: {
    getRequestParams(searchName, page, pageSize) {
      let params = {};

      if (searchName) {
        params['name'] = searchName;
      }
      if (page) {
        params['pageNo'] = page - 1;
      }
      if (pageSize) {
        params['size'] = pageSize;
      }

      return params;
    },
    bindingDataToModal(id) {
      console.log(id);
      this.modal = true;
      BedService.get(id)
        .then((response) => {
          this.currentBed = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    ShowModalDisable(id) {
      console.log(id);
      this.modalDelete = true;
      BedService.get(id)
        .then((response) => {
          this.currentBed = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    retrieveAll() {
      const params = this.getRequestParams(
        this.searchName,
        this.page,
        this.pageSize
      );
      BedService.getAll(params)
        .then((response) => {
          const { beds, totalItems } = response.data;
          this.beds = beds;
          this.count = totalItems;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    updateBed(id) {
      var data = {
        name: this.currentBed.name,
        amountPeople: this.currentBed.amountPeople,
        price: this.currentBed.price,
        description: this.currentBed.description,
      };

      BedService.update(id, data)
        .then(() => {
          this.modal = false;
          alert('Update Success!');
          this.retrieveAll();
        })
        .catch((e) => {
          console.log(e);
          alert('Update Failed!');
        });
    },
    deleteBed(id) {
      console.log(id);
      BedService.delete(id)
        .then(() => {
          BedService.getAll().then((response) => {
            const { beds, totalItems } = response.data;
            this.beds = beds;
            this.count = totalItems;
            console.log(response.data);
            if (this.beds.status === 'true') {
              this.messageTooltip = 'Disable this account';
            } else {
              this.messageTooltip = 'Enable this account';
            }
            this.modalDelete = false;
          });
        })
        .catch((e) => {
          console.log(e);
        });
    },
    handlePageChange(value) {
      this.page = value;
      this.retrieveAll();
    },

    handlePageSizeChange(event) {
      this.pageSize = event.target.value;
      this.page = 1;
      this.retrieveAll();
    },

    handelSearch() {
      this.page = 1;
      this.retrieveAll();
    },
    setTextTooltip(text) {
      if (text === 'true') {
        return 'Disable this acoount';
      } else {
        return 'Enable this account';
      }
    },
  },

  mounted() {
    this.retrieveAll();
  },
};
</script>
<style>
.action {
  display: flex;
  justify-content: space-evenly;
}
.input-group.md-form.form-sm.form-1 input {
  border: 1px solid #bdbdbd;
  border-top-right-radius: 0.25rem;
  border-bottom-right-radius: 0.25rem;
}
.input-group.md-form.form-sm.form-2 input {
  border: 1px solid #bdbdbd;
  border-top-left-radius: 0.25rem;
  border-bottom-left-radius: 0.25rem;
}
.input-group.md-form.form-sm.form-2 input.red-border {
  border: 1px solid #ef9a9a;
}
.input-group.md-form.form-sm.form-2 input.lime-border {
  border: 1px solid #cddc39;
}
.input-group.md-form.form-sm.form-2 input.amber-border {
  border: 1px solid #ffca28;
}

#paging {
  position: absolute;
  bottom: 0;
  right: 80px;
}
</style>
