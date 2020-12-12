<template>
  <section id="tables">
    <mdb-row>
      <mdb-col md="12">
        <mdb-card cascade narrow class="mt-5">
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
                  <th colspan="2">Action</th>
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
                      <button class="btn-warning">
                        <a
                          class="btn-link-edit action-button"
                          @click="edit(scope.row)"
                        >
                          <i class="fas fa-pencil-alt"></i> </a
                        >EDIT
                      </button>
                    </div>
                    <div>
                      <button class="btn-danger">
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
        </mdb-card>
      </mdb-col>
    </mdb-row>
  </section>
</template>
<script>
import { mdbRow, mdbCol, mdbCard, mdbCardBody, mdbTbl } from 'mdbvue';

import BookingService from '../../services/BookingService';
export default {
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbCardBody,
    mdbTbl,
  },
  methods: {
    retrieveBookings() {
      BookingService.getAll()
        .then((response) => {
          this.bookings = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
  data() {
    return {
      bookings: [],
    };
  },
  mounted() {
    this.retrieveBookings();
  },
};
</script>
<style scoped>
.action {
  display: flex;
  justify-content: space-evenly;
}
</style>
