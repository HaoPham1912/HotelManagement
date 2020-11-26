<template>
  <section id="tables">
    <mdb-row>
      <mdb-col md="12">
        <mdb-card cascade narrow class="mt-5">
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
                <tr v-for="(data, index) in results" :key="index">
                  <td>{{ data.customerId }}</td>
                  <td>{{ data.customerCode }}</td>
                  <td>{{ data.email }}</td>
                  <td>{{ data.idCard }}</td>
                  <td>{{ data.name }}</td>
                  <td>{{ data.phone }}</td>
                  <td>{{ data.customerType }}</td>
                  <td>{{ data.userName }}</td>
                  <td>
                    <div
                      class="btn-link-edit action-button"
                      @click="edit(scope.row)"
                    >
                      <i class="fas fa-pencil-alt"></i>
                    </div>
                  </td>
                  <td>
                    <div
                      class="btn-link-delete action-button"
                      @click="remove(scope.row)"
                    >
                      <i class="fas fa-trash"></i>
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

import CustomerServices from '../../services/CustomerServices';
export default {
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbCardBody,
    mdbTbl,
  },
  methods: {
    retrieveCustomer() {
      CustomerServices.getAll()
        .then((response) => {
          this.results = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
  data() {
    return {
      results: [],
    };
  },
  mounted() {
    this.retrieveCustomer();
  },
};
</script>
<style scoped></style>
