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
                  placeholder="Search by Customer Code"
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
                  <th>Bill Id</th>
                  <th>Customer Code</th>
                  <th>Customer Name</th>
                  <th>Create Date</th>
                  <th>Promo Code</th>
                  <th>Status</th>
                  <!-- <th>Total Price</th> -->
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(data, index) in bills" :key="index">
                  <td>{{ data.billId }}</td>
                  <td>{{ data.customerCode }}</td>
                  <td>{{ data.customerName }}</td>
                  <td>{{ data.createDate }}</td>
                  <td v-if="data.promoCode">{{ data.promoCode }}</td>
                  <td v-else>No Promotion Code</td>
                  <td v-if="data.status === 'true'">
                    <p style="color: red">Paid</p>
                  </td>
                  <td v-if="data.status === 'false'">
                    <p style="color: green">Un Paid</p>
                  </td>
                  <!-- <td>{{ data.totalPrice }}</td> -->
                  <td class="action">
                    <div>
                      <mdb-btn
                        class="btn-sm btn-primary"
                        v-tooltip.top-center="{
                          content: 'Detail Bill',
                        }"
                        color="primary"
                        @click="gotoDetailBill(data.billId)"
                      >
                        <mdb-icon icon="file-export" />
                      </mdb-btn>
                    </div>
                    <div>
                      <mdb-btn
                        v-tooltip.top-center="{
                          content: setTextTooltip(data.status),
                        }"
                        :class="{
                          'btn-sm btn-danger': data.status === 'true',
                          'btn-sm btn-success': data.status === 'false',
                        }"
                        color="data.status : danger ? success"
                        @click="showModalConfirm(data.billId)"
                      >
                        <mdb-icon far icon="money-bill-alt" />
                      </mdb-btn>
                    </div>
                  </td>
                </tr>
              </tbody>
            </mdb-tbl>
            <div>
              <mdb-modal
                centered
                :show="modalConfirm"
                @close="modalConfirm = false"
              >
                <mdb-modal-header>
                  <mdb-modal-title>ARE YOU SURE?</mdb-modal-title>
                </mdb-modal-header>
                <mdb-modal-body>
                  <strong>Please confirm your action</strong>
                </mdb-modal-body>
                <mdb-modal-footer>
                  <mdb-btn color="danger" @click.native="modalConfirm = false"
                    >Close</mdb-btn
                  >
                  <mdb-btn color="success" @click="updateBill(currentBillId)"
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
  mdbBtn,
  mdbModal,
  mdbModalHeader,
  mdbModalTitle,
  mdbModalBody,
  mdbModalFooter,
} from 'mdbvue';

import BillService from '../../services/BillService';
import { AUTH_LOGOUT } from '../../store/actions/auth';
export default {
  data() {
    return {
      bills: [],
      currentBill: null,
      currentIndex: -1,
      searchName: '',
      isDisabled: false,

      page: 1,
      count: 0,
      pageSize: 5,

      pageSizes: [5, 10, 15],

      currentURL: '',

      currentBillId: '',

      modalConfirm: false,

      prefix: '',
    };
  },
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbCardBody,
    mdbTbl,
    mdbIcon,
    mdbBtn,
    mdbModal,
    mdbModalHeader,
    mdbModalTitle,
    mdbModalBody,
    mdbModalFooter,
  },
  methods: {
    getRequestParams(searchName, page, pageSize) {
      let params = {};

      if (searchName) {
        params['customerCode'] = searchName;
      }
      if (page) {
        params['pageNo'] = page - 1;
      }
      if (pageSize) {
        params['size'] = pageSize;
      }

      return params;
    },
    retrieveBill() {
      const params = this.getRequestParams(
        this.searchName,
        this.page,
        this.pageSize
      );
      BillService.getAll(params)
        .then((response) => {
          const { bills, totalItems } = response.data;
          this.bills = bills;
          this.count = totalItems;
          console.log(response.data);
          if (Object.entries(this.bills).length === 0) {
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
    showAll() {
      this.searchName = '';
      this.retrieveBill();
    },
    showModalConfirm(id) {
      this.modalConfirm = true;
      this.currentBillId = id;
    },

    updateBill(id) {
      BillService.updateBill(id).then((response) => {
        console.log(response.data);
        alert('Bill have been updated!');
        this.modalConfirm = false;
        this.retrieveBill();
      });
    },

    gotoDetailBill(id) {
      this.currentUrl = this.$router.currentRoute.path;

      let url = this.$router.currentRoute.path;
      let index = url.lastIndexOf('/');
      let subString = url.slice(0, index);

      this.prefix = subString;

      const newPath = this.prefix + '/detailBill/' + id;

      this.$router.push(newPath);
    },
    handlePageChange(value) {
      this.page = value;
      this.retrieveBill();
    },

    handlePageSizeChange(event) {
      this.pageSize = event.target.value;
      this.page = 1;
      this.retrieveBill();
    },

    handelSearch() {
      this.page = 1;
      this.retrieveBill();
    },
    setTextTooltip(text) {
      if (text === 'true') {
        return 'Refund this bill';
      } else {
        return 'Cash this bill';
      }
    },
  },

  mounted() {
    this.retrieveBill();
  },
};
</script>
<style scoped>
.action {
  display: flex;
}
</style>
