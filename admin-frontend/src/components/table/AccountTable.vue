<template>
  <section id="tables">
    <mdb-row>
      <mdb-col md="12">
        <mdb-card cascade narrow class="mt-5">
          <mdb-card-body>
            <mdb-tbl>
              <thead class="blue-grey lighten-4">
                <tr>
                  <th>AccountId</th>
                  <th>Username</th>
                  <th>Password</th>
                  <th>Status</th>
                  <th>RoleName</th>
                  <th colspan="2">Action</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(data, index) in accounts" :key="index">
                  <td>{{ data.accountId }}</td>
                  <td>{{ data.username }}</td>
                  <td>{{ data.password }}</td>
                  <td>{{ data.status }}</td>
                  <td>{{ data.roleName }}</td>
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

import AccountService from '../../services/AccountService';
export default {
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbCardBody,
    mdbTbl,
  },
  methods: {
    retrieveAccount() {
      AccountService.getAll()
        .then((response) => {
          this.accounts = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
  data() {
    return {
      accounts: [],
    };
  },
  mounted() {
    this.retrieveAccount();
  },
};
</script>
<style scoped></style>
