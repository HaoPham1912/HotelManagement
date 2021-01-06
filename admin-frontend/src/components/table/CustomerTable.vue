<template>
  <section id="dashboard">
    <mdb-row>
      <mdb-col md="2"></mdb-col>
      <mdb-col md="10">
        <mdb-card class="mb-4">
          <div class="row">
            <div class="col-md-6"></div>
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
                  placeholder="Search by Customer Name"
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
                  <th>CustomerId</th>
                  <th>CustomerCode</th>
                  <th>Email</th>
                  <th>idCard</th>
                  <th>Name</th>
                  <th>Phone</th>
                  <th>Customer Type</th>
                  <th>Username</th>
                  <th colspan="2">Action</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(data, index) in customers" :key="index">
                  <td>{{ data.customerId }}</td>
                  <td>{{ data.customerCode }}</td>
                  <td>{{ data.email }}</td>
                  <td>{{ data.idCard }}</td>
                  <td>{{ data.name }}</td>
                  <td>{{ data.phone }}</td>
                  <td>
                    {{ data.customerType }}
                  </td>

                  <td>{{ data.userName }}</td>

                  <td class="action">
                    <div>
                      <mdb-btn
                        @click="bindingDataToModal(data.customerId)"
                        color="light-green"
                        v-tooltip.top-center="{
                          content: 'Upgrade this customer',
                        }"
                      >
                        <mdb-icon icon="angle-double-up" />
                      </mdb-btn>
                    </div>
                  </td>
                </tr>
                <tr>
                  {{
                    noDataMessage
                  }}
                </tr>
              </tbody>
            </mdb-tbl>
            <div>
              <mdb-modal
                centered
                :show="modalUpdate"
                @close="modalUpdate = false"
              >
                <mdb-modal-header>
                  <mdb-modal-title>ARE YOU SURE?</mdb-modal-title>
                </mdb-modal-header>
                <mdb-modal-body>
                  <div class="form-outline">
                    <label class="form-label" for="employeeCode"
                      >Customer Code</label
                    >
                    <input
                      type="text"
                      class="form-control"
                      v-model="currentCustomer.customerCode"
                      required
                      disabled
                    />
                  </div>
                  <br />
                  <div class="form-outline">
                    <label class="form-label" for="employeeCode"
                      >Customer Type</label
                    >
                    <b-form-select
                      v-model="currentCustomer.customerType"
                      :options="customerType"
                    ></b-form-select>
                  </div>
                </mdb-modal-body>
                <mdb-modal-footer>
                  <mdb-btn color="danger" @click.native="modalUpdate = false"
                    >Close</mdb-btn
                  >
                  <mdb-btn
                    color="primary"
                    @click="updateCustomer(currentCustomer.customerId)"
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

import CustomerServices from '../../services/CustomerServices';
import CustomerTypeService from '../../services/CustomerTypeService';
export default {
  data() {
    return {
      customers: [],

      currentIndex: -1,
      searchName: '',

      modalUpdate: false,

      currentCustomer: {},
      page: 1,
      count: 0,
      pageSize: 3,

      noDataMessage: '',

      customerTypes: [],
      customerType: [],

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
    mdbModal,
    mdbModalHeader,
    mdbModalTitle,
    mdbModalBody,
    mdbModalFooter,
    mdbBtn,
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
      CustomerServices.getCustomerById(id)
        .then((response) => {
          console.log(response.data);
          this.currentCustomer = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
      this.modalUpdate = true;
    },
    retrieveCustomer() {
      const params = this.getRequestParams(
        this.searchName,
        this.page,
        this.pageSize
      );
      CustomerServices.getAll(params)
        .then((response) => {
          const { customers, totalItems } = response.data;
          this.customers = customers;
          this.count = totalItems;
          if (this.customers.length === 0) {
            this.noDataMessage = 'No data matching';
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    ShowModalDisable(id) {
      console.log(id);
      this.modalDelete = true;
      CustomerServices.getCustomerById(id)
        .then((response) => {
          this.currentCustomer = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    deleteAccount(id) {
      console.log(id);
      CustomerServices.disableCustomer(id)
        .then(() => {
          CustomerServices.getAll().then((response) => {
            const { customers, totalItems } = response.data;
            this.customers = customers;
            this.count = totalItems;
            console.log(response.data);
          });
        })
        .catch((e) => {
          console.log(e);
        });
    },
    handlePageChange(value) {
      this.page = value;
      this.retrieveCustomer();
    },

    handlePageSizeChange(event) {
      this.pageSize = event.target.value;
      this.page = 1;
      this.retrieveCustomer();
    },

    handelSearch() {
      this.page = 1;
      this.retrieveCustomer();
    },
    getCustomerId(id) {
      console.log(id);
    },
    showAll() {
      this.searchName = '';
      this.retrieveCustomer();
    },

    getAllCustomerType() {
      CustomerTypeService.getAll()
        .then((response) => {
          this.customerTypes = response.data;

          console.log(this.customerTypes);
          for (var i = 0; i < this.customerTypes.length; i++) {
            var options = [];
            for (var key in this.customerTypes[i]) {
              if (key == 'typeName') {
                options['value'] = this.customerTypes[i][key];
                options['text'] = this.customerTypes[i][key];
              }
            }
            this.customerType.push(Object.assign({}, options));
          }
          console.log(this.customerType);
        })
        .catch((e) => {
          console.log(e);
        });
    },

    updateCustomer(id) {
      var data = {
        customerCode: this.currentCustomer.customerCode,
        customerType: this.currentCustomer.customerType,
      };

      CustomerServices.upgradeCustomer(id, data)
        .then((response) => {
          console.log(response.data);
          alert('Update Success!!!!');
          this.modalUpdate = false;
          this.retrieveCustomer();
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },

  mounted() {
    this.retrieveCustomer();
    this.getAllCustomerType();
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

.spec {
  color: red;
  font-weight: bold;
}
</style>
