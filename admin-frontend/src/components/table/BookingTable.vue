<template>
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
                      <button class="btn-sm btn-warning">
                        <a
                          class="btn-link-edit action-button"
                          @click="edit(scope.row)"
                        >
                          <i class="fas fa-pencil-alt"></i>
                        </a>
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
import { mdbRow, mdbCol, mdbCard, mdbCardBody, mdbTbl, mdbIcon } from 'mdbvue';

import BookingService from '../../services/BookingService';
export default {
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbCardBody,
    mdbTbl,
    mdbIcon,
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
}
</style>
