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
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(data, index) in accounts" :key="index">
                  <td>{{ data.accountId }}</td>
                  <td>{{ data.username }}</td>
                  <td>{{ data.password }}</td>
                  <td>{{ data.status }}</td>
                  <td>{{ data.roleName }}</td>
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
                      <button class="btn-danger" @click="disableAccount">
                        <a class="btn-link-delete action-button">
                          <i class="fas fa-trash"></i>
                        </a>
                        {{ data.status ? 'DISABLE' : 'ENABLE' }}
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
          console.log('error' + e);
        });
    },
    disableAccount() {
      AccountService.disableAccount(this.accounts.accountId, this.accounts)
        .then((response) => {
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
<style scoped>
.action {
  display: flex;
  justify-content: space-evenly;
}
</style>
