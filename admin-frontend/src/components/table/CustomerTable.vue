<template>
  <section id="dashboard">
    <mdb-row>
      <mdb-col md="2"></mdb-col>
      <mdb-col md="10">
        <mdb-card class="mb-4">
          <div class="link-add">
            <a href="/customer/add" type="button" class="btn btn-success">
              Add new Customer
            </a>
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
                <tr v-for="(data, index) in results" :key="index">
                  <td>{{ data.customerId }}</td>
                  <td>{{ data.customerCode }}</td>
                  <td>{{ data.email }}</td>
                  <td>{{ data.idCard }}</td>
                  <td>{{ data.name }}</td>
                  <td>{{ data.phone }}</td>
                  <td>{{ data.customerType }}</td>
                  <td>{{ data.userName }}</td>
                  <td class="action">
                    <div>
                      <button
                        class="btn-sm btn-warning"
                        @click="getCustomerId(data.customerId)"
                      >
                        <a
                          class="btn-link-edit action-button"
                          :href="'customer/' + data.customerId"
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
    getCustomerId(id) {
      console.log(id);
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
</style>
