<template>
  <section id="dashboard">
    <mdb-row>
      <mdb-col md="2"></mdb-col>
      <mdb-col md="10">
        <mdb-card class="mb-4">
          <div class="row">
            <div class="col-md-6">
              <a
                href="/admin/add-service"
                type="button"
                class="btn btn-success"
              >
                Add new Service
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
                  placeholder="Search by Name"
                  aria-label="Search"
                  name="searchName"
                  v-model="searchName"
                />
                <div class="input-group-append">
                  <button
                    class="input-group-text lime lighten-2"
                    id="basic-text1"
                    type="submit"
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
                  <th>Service ID</th>
                  <th>Service Code</th>
                  <th>Name</th>
                  <th>Price</th>
                  <th>In Room</th>
                  <th>Status</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(data, index) in services" :key="index">
                  <td>{{ data.serviceId }}</td>
                  <td>{{ data.serviceCode }}</td>
                  <td>{{ data.name }}</td>
                  <td>{{ data.price }}</td>
                  <td>{{ data.roomIdSet }}</td>
                  <td>{{ data.status }}</td>
                  <td class="action">
                    <div>
                      <mdb-btn
                        color="warning"
                        class="btn-sm btn-warning"
                        v-tooltip.top-center="{
                          content: 'Edit this service',
                        }"
                        @click="bindingDataToModal(data.serviceId)"
                      >
                        <i class="fas fa-pencil-alt"></i>
                        <a class="btn-link-edit action-button"> </a>
                      </mdb-btn>
                    </div>
                    <div>
                      <mdb-btn
                        :class="{
                          'btn-sm btn-danger': data.status === true,
                          'btn-sm btn-success': data.status === false,
                        }"
                        color="data.status : danger ? success"
                        @click="ShowModalDisable(data.serviceId)"
                        v-tooltip.top-center="{
                          content: setTextTooltip(data.status),
                        }"
                      >
                        <a :href="'service/' + data.serviceId"> </a>
                        <i
                          :class="{
                            'fas fa-ban': data.status === true,
                            'fas fa-plus': data.status === false,
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
                  <mdb-modal-title>Update Account Information</mdb-modal-title>
                </mdb-modal-header>
                <mdb-modal-body>
                  <div>
                    <div class="form-outline mb-4">
                      <label for="serviceCode">Service Code</label>
                      <input
                        type="text"
                        id="serviceCode"
                        class="form-control"
                        v-model="currentService.serviceCode"
                        required
                      />
                    </div>
                    <div class="form-outline mb-4">
                      <label for="name">Service Name</label>
                      <input
                        type="text"
                        id="name"
                        class="form-control"
                        v-model="currentService.name"
                        required
                      />
                    </div>
                    <div class="form-outline mb-4">
                      <label for="price">Price</label>
                      <input
                        type="text"
                        id="price"
                        class="form-control"
                        v-model="currentService.price"
                        required
                      />
                    </div>
                    <!-- <div class="form-outline mb-4">
                      <label for="description">Description</label>
                      <textarea
                        id="description"
                        rows="4"
                        cols="50"
                        class="form-control"
                        v-model="currentService.description"
                        required
                      />
                    </div> -->
                  </div></mdb-modal-body
                >
                <mdb-modal-footer>
                  <mdb-btn color="danger" @click.native="modal = false"
                    >Close</mdb-btn
                  >
                  <mdb-btn
                    color="primary"
                    @click="updateService(currentService.serviceId)"
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
                    @click="deleteService(currentService.serviceId)"
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

import ServicesService from '../../services/ServicesService';
import { AUTH_LOGOUT } from '../../store/actions/auth';
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
      services: [],
      currentService: {
        serviceCode: '',
        name: '',
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
      ServicesService.get(id)
        .then((response) => {
          this.currentService = response.data;
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
      console.log(params);
      ServicesService.getAll(params)
        .then((response) => {
          const { services, totalItems } = response.data;
          this.services = services;
          if (Object.entries(this.services).length === 0) {
            alert('Session time out!!!');
            this.$store
              .dispatch(AUTH_LOGOUT)
              .then(() => this.$router.push('/login'));
          }
          this.count = totalItems;
          console.log(response.data);
          if (this.services.status === true) {
            this.messageTooltip = 'Disable this service';
          } else {
            this.messageTooltip = 'Enable this service';
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

    showAll() {
      this.searchName = '';
      this.retrieveAll();
    },

    updateService(id) {
      var data = {
        serviceCode: this.currentService.serviceCode,
        name: this.currentService.name,
        price: this.currentService.price,
        description: this.currentService.description,
      };

      ServicesService.update(id, data)
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
    deleteService(id) {
      console.log(id);
      ServicesService.delete(id)
        .then(() => {
          ServicesService.getAll().then((response) => {
            const { services, totalItems } = response.data;
            this.services = services;
            this.count = totalItems;
            console.log(response.data);
            if (this.services.status === true) {
              this.messageTooltip = 'Disable this service';
            } else {
              this.messageTooltip = 'Enable this service';
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
      if (text === true) {
        return 'Disable this service';
      } else {
        return 'Enable this service';
      }
    },
    ShowModalDisable(id) {
      console.log(id);
      this.modalDelete = true;
      ServicesService.get(id)
        .then((response) => {
          this.currentService = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },

  mounted() {
    this.retrieveAll();
  },
};
</script>
<style lang="scss">
.action {
  display: flex;
  justify-content: space-evenly;
}
.tooltip {
  display: block !important;
  z-index: 10000;

  .tooltip-inner {
    background: black;
    color: white;
    border-radius: 16px;
    padding: 5px 10px 4px;
  }

  .tooltip-arrow {
    width: 0;
    height: 0;
    border-style: solid;
    position: absolute;
    margin: 5px;
    border-color: black;
    z-index: 1;
  }

  &[x-placement^='top'] {
    margin-bottom: 5px;

    .tooltip-arrow {
      border-width: 5px 5px 0 5px;
      border-left-color: transparent !important;
      border-right-color: transparent !important;
      border-bottom-color: transparent !important;
      bottom: -5px;
      left: calc(50% - 5px);
      margin-top: 0;
      margin-bottom: 0;
    }
  }

  &[x-placement^='bottom'] {
    margin-top: 5px;

    .tooltip-arrow {
      border-width: 0 5px 5px 5px;
      border-left-color: transparent !important;
      border-right-color: transparent !important;
      border-top-color: transparent !important;
      top: -5px;
      left: calc(50% - 5px);
      margin-top: 0;
      margin-bottom: 0;
    }
  }

  &[x-placement^='right'] {
    margin-left: 5px;

    .tooltip-arrow {
      border-width: 5px 5px 5px 0;
      border-left-color: transparent !important;
      border-top-color: transparent !important;
      border-bottom-color: transparent !important;
      left: -5px;
      top: calc(50% - 5px);
      margin-left: 0;
      margin-right: 0;
    }
  }

  &[x-placement^='left'] {
    margin-right: 5px;

    .tooltip-arrow {
      border-width: 5px 0 5px 5px;
      border-top-color: transparent !important;
      border-right-color: transparent !important;
      border-bottom-color: transparent !important;
      right: -5px;
      top: calc(50% - 5px);
      margin-left: 0;
      margin-right: 0;
    }
  }

  &[aria-hidden='true'] {
    visibility: hidden;
    opacity: 0;
    transition: opacity 0.15s, visibility 0.15s;
  }

  &[aria-hidden='false'] {
    visibility: visible;
    opacity: 1;
    transition: opacity 0.15s;
  }
}
</style>
