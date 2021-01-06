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
                        @click="viewBookingDetail(data.billId, data.roomId)"
                      >
                        <b-icon icon="exclamation-circle"></b-icon>
                        <a class="btn-link-edit action-button"> </a>
                      </mdb-btn>
                    </div>
                    <div>
                      <mdb-btn
                        :class="'btn-sm btn-danger'"
                        color="data.status : danger ? success"
                        @click="showConfirmation(data.billId, data.roomId)"
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
              <mdb-modal
                centered
                :show="modalDelete"
                @close="modalDelete = false"
              >
                <mdb-modal-header>
                  <mdb-modal-title>ARE YOU SURE?</mdb-modal-title>
                </mdb-modal-header>
                <mdb-modal-body>
                  <strong
                    >Are you sure delete this booking information? <br />
                    Data Deleted can not restore!!!!</strong
                  >
                </mdb-modal-body>
                <mdb-modal-footer>
                  <mdb-btn color="danger" @click.native="modalDelete = false"
                    >Cancel</mdb-btn
                  >
                  <mdb-btn
                    color="success"
                    @click="deleteBooking(billId, roomId)"
                    >OK</mdb-btn
                  >
                </mdb-modal-footer>
              </mdb-modal>
            </div>
            <div>
              <mdb-modal
                centered
                :show="modalDetail"
                @close="modalDetail = false"
              >
                <mdb-modal-header>
                  <mdb-modal-title>BOOKING DETAIL</mdb-modal-title>
                </mdb-modal-header>
                <mdb-modal-body>
                  <div style="display: flex; justify-content:space-around">
                    <strong>Customer Code: </strong>
                    <p>{{ detailBooking.customerCode }}</p>
                  </div>
                  <div style="display: flex; justify-content:space-around">
                    <strong>Customer Name: </strong>
                    <p>{{ detailBooking.customerName }}</p>
                  </div>
                  <div style="display: flex; justify-content:space-around">
                    <strong>Customer Phone: </strong>
                    <p>{{ detailBooking.customerPhone }}</p>
                  </div>
                  <div style="display: flex; justify-content:space-around">
                    <strong>Customer Email: </strong>
                    <p>{{ detailBooking.customerEmail }}</p>
                  </div>
                  <div style="display: flex; justify-content:space-around">
                    <strong>Room Code: </strong>
                    <p>{{ detailBooking.roomCode }}</p>
                  </div>
                  <div style="display: flex; justify-content:space-around">
                    <strong>Room Name: </strong>
                    <p>{{ detailBooking.roomName }}</p>
                  </div>
                </mdb-modal-body>
                <mdb-modal-footer>
                  <mdb-btn color="primary" @click.native="modalDetail = false"
                    >Ok</mdb-btn
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
      modalDelete: false,
      modalDetail: false,
      currentIndex: -1,
      page: 1,
      count: 0,
      pageSize: 5,

      detailBooking: {},

      billId: '',
      roomId: '',

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

    showConfirmation(billId, roomId) {
      this.billId = billId;
      this.roomId = roomId;
      this.modalDelete = true;
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

    viewBookingDetail(billId, roomId) {
      BookingService.getDetailBooking(billId, roomId).then((response) => {
        console.log(response.data);
        this.detailBooking = response.data;
        this.modalDetail = true;
      });
    },

    deleteBooking(billId, roomId) {
      console.log(billId);
      console.log(roomId);

      BookingService.deleteBooking(billId, roomId)
        .then((response) => {
          console.log(response.data);
          alert('This booking information have been deleted!!!');
          this.retrieveBookings();
          this.modalDelete = false;
        })
        .catch((e) => {
          console.log(e);
          alert('This booking have been paid can not delete');
          this.modalDelete = false;
        });
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
