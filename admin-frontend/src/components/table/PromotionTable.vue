<template>
  <section id="dashboard">
    <mdb-row>
      <mdb-col md="2"></mdb-col>
      <mdb-col md="10">
        <mdb-card class="mb-4">
          <div class="row">
            <div class="col-md-9">
              <div class="link-add">
                <a
                  href="/admin/promo/add"
                  type="button"
                  class="btn btn-success"
                >
                  Add new Promotion
                </a>
              </div>
            </div>
            <div class="col-md-3">
              <div class="input-group md-form form-sm form-2 pl-0">
                <input
                  class="form-control my-0 py-1 lime-border"
                  type="text"
                  placeholder="Search by Customer Type"
                  aria-label="Search"
                  name="customerType"
                  v-model="customerType"
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
                  <th>PromotionId</th>
                  <th>Promotion Code</th>
                  <th>Percent Discount</th>
                  <th>Customer Type</th>
                  <th>Start Date</th>
                  <th>End Date</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(data, index) in promos" :key="index">
                  <td>{{ data.promoId }}</td>
                  <td>{{ data.promotionCode }}</td>
                  <td>{{ data.percent }}</td>
                  <td>{{ data.cusType }}</td>
                  <td>{{ data.startDate }}</td>
                  <td>{{ data.endDate }}</td>
                  <td class="action">
                    <div>
                      <mdb-btn
                        color="warning"
                        class="btn-sm btn-warning"
                        v-tooltip.top-center="{
                          content: 'Edit this promotion',
                        }"
                        @click="bindingDataToModal(data.promoId)"
                      >
                        <i class="fas fa-pencil-alt"></i>
                        <a class="btn-link-edit action-button"> </a>
                      </mdb-btn>
                    </div>
                    <div>
                      <mdb-btn
                        :class="'btn-sm btn-danger'"
                        color="danger"
                        @click="ShowModalDisable(data.promoId)"
                        v-tooltip.top-center="{
                          content: setTextTooltip(data.status),
                        }"
                      >
                        <a :href="'promo/' + data.promoId"> </a>
                        <i :class="'fas fa-ban'"></i>
                      </mdb-btn>
                    </div>
                  </td>
                </tr>
              </tbody>
            </mdb-tbl>
            <div>
              <mdb-modal centered :show="modal" @close="modal = false">
                <mdb-modal-header>
                  <mdb-modal-title
                    >Update Promotion Information</mdb-modal-title
                  >
                </mdb-modal-header>
                <mdb-modal-body>
                  <div class="form-outline mb-4">
                    <label for="promotionCode">Promotion Code</label>
                    <input
                      type="text"
                      id="promotionCode"
                      class="form-control"
                      required
                      v-model="currentPromo.promoCode"
                    />
                  </div>
                  <div class="form-outline mb-4">
                    <label for="percent">Percent Discount</label>
                    <input
                      type="text"
                      id="percent"
                      class="form-control"
                      required
                      v-model="currentPromo.percent"
                    />
                  </div>
                  <!-- Message input -->
                  <div class="form-outline mb-4">
                    <label class="form-label" for="branchCode"
                      >Choose Customer Type</label
                    >
                    <b-form-select
                      v-model="currentPromo.customerType"
                      :options="customerTypeNames"
                    ></b-form-select>
                  </div>
                  <div class="form-outline mb-4">
                    <label for="startDate">Choose Start Date</label>
                    <input
                      type="date"
                      id="percent"
                      class="form-control"
                      required
                      v-model="currentPromo.startDate"
                    />
                  </div>
                  <div class="form-outline mb-4">
                    <label for="endDate">Choose End Date</label>
                    <input
                      type="date"
                      id="percent"
                      class="form-control"
                      required
                      v-model="currentPromo.endDate"
                    />
                  </div>
                </mdb-modal-body>
                <mdb-modal-footer>
                  <mdb-btn color="danger" @click.native="modal = false"
                    >Close</mdb-btn
                  >
                  <mdb-btn
                    color="primary"
                    @click="updatePromo(currentPromo.promoId)"
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
                    @click="deletePromo(currentPromo.promoId)"
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

import PromotionService from '../../services/PromotionService';
import CustomerTypeService from '../../services/CustomerTypeService';
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
      promos: [],
      customerTypes: [],
      customerTypeNames: [],
      currentPromo: {
        promoId: '',
        promotionCode: '',
        percent: '',
        cusType: '',
        startDate: '',
        endDate: '',
      },

      customerType: '',
      currentIndex: -1,
      page: 1,
      count: 0,
      pageSize: 3,
      modalDelete: false,
      modal: false,
      pageSizes: [3, 6, 9],
    };
  },
  methods: {
    getRequestParams(customerType, page, pageSize) {
      let params = {};

      if (customerType) {
        params['customerType'] = customerType;
      }
      if (page) {
        params['pageNo'] = page - 1;
      }
      if (pageSize) {
        params['size'] = pageSize;
      }

      return params;
    },

    ShowModalDisable(id) {
      console.log(id);
      this.modalDelete = true;
      PromotionService.getPromotionById(id)
        .then((response) => {
          this.currentPromo = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },

    bindingDataToModal(id) {
      console.log(id);
      this.modal = true;
      PromotionService.getPromotionById(id)
        .then((response) => {
          console.log(response.data);
          this.currentPromo = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    retrievePromo() {
      const params = this.getRequestParams(
        this.customerType,
        this.page,
        this.pageSize
      );
      PromotionService.getAll(params)
        .then((response) => {
          console.log(response.data);
          const { promos, totalItems } = response.data;
          this.promos = promos;
          this.count = totalItems;
        })
        .catch((e) => {
          console.log('error' + e);
        });
    },
    handlePageChange(value) {
      this.page = value;
      this.retrievePromo();
    },

    handlePageSizeChange(event) {
      this.pageSize = event.target.value;
      this.page = 1;
      this.retrievePromo();
    },
    handelSearch() {
      this.page = 1;
      this.retrievePromo();
    },
    setTextTooltip() {
      return 'Delete this promotion';
    },
    retrieveCustomerType() {
      CustomerTypeService.getAll().then((reponse) => {
        this.customerTypes = reponse.data;
        console.log(this.customerTypes);
        for (var i = 0; i < this.customerTypes.length; i++) {
          var options = [];
          for (var key in this.customerTypes[i]) {
            if (key == 'typeName') {
              options['value'] = this.customerTypes[i][key];
              options['text'] = this.customerTypes[i][key];
            }
          }
          this.customerTypeNames.push(Object.assign({}, options));
        }
      });
    },
    updatePromo(id) {
      var data = {
        promotionCode: this.currentPromo.promotionCode,
        percent: this.currentPromo.percent,
        cusType: this.currentPromo.customerType,
        startDate: this.currentPromo.startDate,
        endDate: this.currentPromo.endDate,
      };

      PromotionService.update(id, data)
        .then(() => {
          this.modal = false;
          alert('Update Success!');
          this.retrievePromo();
        })
        .catch((e) => {
          console.log(e);
          alert('Update Failed!');
        });
    },

    deletePromo(id) {
      PromotionService.delete(id)
        .then(() => {
          alert('Promotion have been deleted!');
          PromotionService.getAll()
            .then((response) => {
              console.log(response.data);
              const { promos, totalItems } = response.data;
              this.promos = promos;
              this.count = totalItems;
            })
            .catch((e) => {
              console.log('error' + e);
            });
          this.modalDelete = false;
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },

  mounted() {
    this.retrievePromo();
    this.retrieveCustomerType();
  },
};
</script>
<style scoped>
.action {
  display: flex;
  justify-content: space-evenly;
}
</style>
