<template>
  <section id="dashboard">
    <mdb-row>
      <mdb-col md="2"></mdb-col>
      <mdb-col md="10">
        <mdb-card class="mb-4">
          <div class="row">
            <div class="col-md-9"></div>
            <div class="col-md-3"></div>
          </div>
          <mdb-card-body>
            <mdb-tbl>
              <thead class="blue-grey lighten-4">
                <tr>
                  <th>Room Id</th>
                  <th>Bill Id</th>
                  <th>Book Date</th>
                  <th>Checkin Date</th>
                  <th>Check out Date</th>
                  <th>Paid Price</th>
                  <th>Status</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(data, index) in bookings" :key="index">
                  <td>{{ data.roomId }}</td>
                  <td>{{ data.billId }}</td>
                  <td>{{ data.bookDate }}</td>
                  <td>{{ data.checkinDate }}</td>
                  <td>{{ data.checkoutDate }}</td>
                  <td>{{ data.paidPrice }}</td>
                  <td>{{ data.status }}</td>
                  <td class="action">
                    <div>
                      <mdb-btn
                        color="success"
                        class="btn-sm btn-success"
                        v-tooltip.top-center="{
                          content: 'View booking detail',
                        }"
                        @click="bindingDataToModal(data.bedId)"
                      >
                        <b-icon icon="exclamation-circle"></b-icon>
                        <a class="btn-link-edit action-button"> </a>
                      </mdb-btn>
                    </div>
                    <div>
                      <mdb-btn
                        :class="'btn-sm btn-danger'"
                        color="data.status : danger ? success"
                        @click="ShowModalDisable(data.bedId)"
                        v-tooltip.top-center="{
                          content: setTextTooltip(data.status),
                        }"
                      >
                        <a :href="'bed/' + data.bedId"> </a>
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
                  <mdb-modal-title>Update Bed Information</mdb-modal-title>
                </mdb-modal-header>
                <mdb-modal-body> </mdb-modal-body>
                <mdb-modal-footer>
                  <mdb-btn color="danger" @click.native="modal = false"
                    >Close</mdb-btn
                  >
                  <mdb-btn color="primary" @click="updateBed(currentBed.bedId)"
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
                  <mdb-btn color="primary" @click="deleteBed(currentBed.bedId)"
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
  mdbModal,
  mdbModalHeader,
  mdbModalTitle,
  mdbModalBody,
  mdbModalFooter,
  mdbBtn,
} from 'mdbvue';

import BookingService from '../../services/BookingService';
export default {
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbCardBody,
    mdbTbl,

    mdbModal,
    mdbModalHeader,
    mdbModalTitle,
    mdbModalBody,
    mdbModalFooter,
    mdbBtn,
  },
  data() {
    return {
      bookings: [],
      modal: false,
      modalDelete: false,
      currentIndex: -1,
      page: 1,
      count: 0,
      pageSize: 5,

      pageSizes: [5, 10, 15],
    };
  },
  methods: {
    getRequestParams(page, pageSize) {
      let params = {};

      if (page) {
        params['pageNo'] = page - 1;
      }
      if (pageSize) {
        params['size'] = pageSize;
      }

      return params;
    },

    retrieveBookings() {
      const params = this.getRequestParams(this.page, this.pageSize);
      BookingService.getAll(params)
        .then((response) => {
          const { bookings, totalItems } = response.data;
          this.bookings = bookings;
          this.count = totalItems;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    setTextTooltip() {
      return 'Cancel this booking';
    },
    handlePageChange(value) {
      this.page = value;
      this.retrieveBookings();
    },
    handlePageSizeChange(event) {
      this.pageSize = event.target.value;
      this.page = 1;
      this.retrieveBookings();
    },
  },

  mounted() {
    this.retrieveBookings();
  },
};
</script>
<style scoped>
.action {
  display: flex;
}
</style>
