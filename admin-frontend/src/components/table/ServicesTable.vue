<template>
  <section id="dashboard">
    <mdb-row>
      <mdb-col md="2"></mdb-col>
      <mdb-col md="10">
        <mdb-card class="mb-4">
          <div class="row">
            <div class="col-md-9">
              <a href="/service/add" type="button" class="btn btn-success">
                Add new Service
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
                      <button
                        class="btn-sm btn-warning"
                        @click="getServiceCode(data.serviceCode)"
                        v-tooltip.top-center="{
                          content: 'Edit this service',
                        }"
                      >
                        <i class="fas fa-pencil-alt"></i>
                        <a
                          class="btn-link-edit action-button"
                          :href="'services/' + data.serviceCode"
                        >
                        </a>
                      </button>
                    </div>
                    <div>
                      <button
                        :class="{
                          'btn-sm btn-danger': data.status === true,
                          'btn-sm btn-success': data.status === false,
                        }"
                        @click="deleteService(data.serviceId)"
                        v-tooltip.top-center="{
                          content: setTextTooltip(data.status),
                        }"
                      >
                        <a :href="'services/' + data.serviceId"> </a>
                        <i
                          :class="{
                            'fas fa-ban': data.status === true,
                            'fas fa-plus': data.status === false,
                          }"
                        ></i>
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

import ServicesService from '../../services/ServicesService';
export default {
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbCardBody,
    mdbTbl,
    mdbIcon,
  },
  data() {
    return {
      services: [],
      currentService: {},
      currentIndex: -1,
      searchName: '',

      page: 1,
      count: 0,
      pageSize: 3,

      pageSizes: [3, 6, 9],
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
    retrieveAll() {
      const params = this.getRequestParams(
        this.searchName,
        this.page,
        this.pageSize
      );
      ServicesService.getAll(params)
        .then((response) => {
          const { services, totalItems } = response.data;
          this.services = services;
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
        });
    },
    getServiceCode(code) {
      console.log(code);
      ServicesService.get(code)
        .then((response) => {
          console.log(response.data);
          this.currentService = response.data;
          this.$router.push(`/services/${code}`);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    deleteService(id) {
      console.log(id);
      ServicesService.delete(id)
        .then((response) => {
          console.log(response);
          ServicesService.getAll().then((response) => {
            this.services = response.data;
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
