<template>
  <section id="dashboard">
    <mdb-row>
      <mdb-col md="2"></mdb-col>
      <mdb-col md="10">
        <mdb-card class="mb-4">
          <div class="row">
            <div class="col-md-9"></div>
            <div class="col-md-3">
              <div class="input-group md-form form-sm form-2 pl-0">
                <input
                  class="form-control my-0 py-1 lime-border"
                  type="text"
                  placeholder="Search by Username"
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
                      <button
                        class="btn-sm btn-warning"
                        @click="getServiceCode(data.serviceCode)"
                        v-tooltip.top-center="{
                          content: 'Edit this account',
                        }"
                      >
                        <i class="fas fa-pencil-alt"></i>
                        <a
                          class="btn-link-edit action-button"
                          :href="'account/' + data.accountId"
                        >
                        </a>
                      </button>
                    </div>
                    <div>
                      <button
                        :class="{
                          'btn-sm btn-danger': data.status === 'true',
                          'btn-sm btn-success': data.status === 'false',
                        }"
                        v-tooltip.top-center="{
                          content: setTextTooltip(data.status),
                        }"
                      >
                        <a :href="'services/' + data.accountId"> </a>
                        <i
                          :class="{
                            'fas fa-ban': data.status === 'true',
                            'fas fa-plus': data.status === 'false',
                          }"
                        ></i>
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

import AccountService from '../../services/AccountService';
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
    setTextTooltip(text) {
      if (text === true) {
        return 'Disable this acoount';
      } else {
        return 'Enable this account';
      }
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
<style>
.action {
  display: flex;
}
</style>
