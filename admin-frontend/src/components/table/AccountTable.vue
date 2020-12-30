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
                  name="usernameSearch"
                  v-model="usernameSearch"
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
                      <mdb-btn
                        color="warning"
                        class="btn-sm btn-warning"
                        v-tooltip.top-center="{
                          content: 'Edit this account',
                        }"
                        @click="bindingDataToModal(data.accountId)"
                      >
                        <i class="fas fa-pencil-alt"></i>
                        <a class="btn-link-edit action-button"> </a>
                      </mdb-btn>
                    </div>
                    <div>
                      <mdb-btn
                        :class="{
                          'btn-sm btn-danger': data.status === 'true',
                          'btn-sm btn-success': data.status === 'false',
                        }"
                        color="data.status : danger ? success"
                        @click="ShowModalDisable(data.accountId)"
                        v-tooltip.top-center="{
                          content: setTextTooltip(data.status),
                        }"
                      >
                        <a :href="'account/' + data.accountId"> </a>
                        <i
                          :class="{
                            'fas fa-ban': data.status === 'true',
                            'fas fa-plus': data.status === 'false',
                          }"
                        ></i>
                      </mdb-btn>
                    </div>
                  </td>
                </tr>
              </tbody>
            </mdb-tbl>
            <div>
              <mdb-modal centered :show="modal" @close="modal = false">
                <mdb-modal-header>
                  <mdb-modal-title>Update Account Information</mdb-modal-title>
                </mdb-modal-header>
                <mdb-modal-body>
                  <div>
                    <div class="form-group">
                      <input
                        type="text"
                        id="accountId"
                        class="form-control form-control-md"
                        v-model="currentAccount.accountId"
                        hidden
                      />
                    </div>
                    <div class="form-group">
                      <label for="username">Username</label>
                      <input
                        type="text"
                        id="username"
                        class="form-control form-control-md"
                        v-model="currentAccount.username"
                      />
                    </div>
                    <div class="form-group">
                      <label for="password">Password</label>
                      <input
                        type="text"
                        id="password"
                        class="form-control form-control-md"
                        v-model="currentAccount.password"
                      />
                    </div></div
                ></mdb-modal-body>
                <mdb-modal-footer>
                  <mdb-btn color="danger" @click.native="modal = false"
                    >Close</mdb-btn
                  >
                  <mdb-btn
                    color="primary"
                    @click="updateAcount(currentAccount.accountId)"
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
                  <mdb-btn
                    color="primary"
                    @click="deleteAccount(currentAccount.accountId)"
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
  mdbModal,
  mdbModalHeader,
  mdbModalTitle,
  mdbModalBody,
  mdbModalFooter,
  mdbBtn,
} from 'mdbvue';
import AccountService from '../../services/AccountService';

export default {
  data() {
    return {
      accounts: [],
      currentAccount: {
        accountId: '',
        username: '',
        password: '',
      },
      currentIndex: -1,
      usernameSearch: '',

      page: 1,
      count: 0,
      pageSize: 3,

      pageSizes: [3, 6, 9],
      modal: false,
      modalDelete: false,
    };
  },
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbCardBody,
    mdbTbl,
    mdbIcon,
    mdbModal,
    mdbModalHeader,
    mdbModalTitle,
    mdbModalBody,
    mdbModalFooter,
    mdbBtn,
  },
  methods: {
    getRequestParams(usernameSearch, page, pageSize) {
      let params = {};

      if (usernameSearch) {
        params['username'] = usernameSearch;
      }
      if (page) {
        params['pageNo'] = page - 1;
      }
      if (pageSize) {
        params['size'] = pageSize;
      }

      return params;
    },

    ShowModalDisable(id) {
      console.log(id);
      this.modalDelete = true;
      AccountService.getById(id)
        .then((response) => {
          this.currentAccount = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },

    bindingDataToModal(id) {
      console.log(id);
      this.modal = true;
      AccountService.getById(id)
        .then((response) => {
          this.currentAccount = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },

    retrieveAccount() {
      const params = this.getRequestParams(
        this.usernameSearch,
        this.page,
        this.pageSize
      );
      AccountService.getAll(params)
        .then((response) => {
          const { accounts, totalItems } = response.data;
          this.accounts = accounts;
          this.count = totalItems;
          console.log(response.data);
          if (this.accounts.status === 'true') {
            this.messageTooltip = 'Disable this account';
          } else {
            this.messageTooltip = 'Enable this account';
          }
        })
        .catch((e) => {
          console.log('error' + e);
        });
    },

    retrieveAvailAccount() {
      AccountService.getAll()
        .then((response) => {
          for (var i = 0, l = response.data.length; i < l; i++) {
            var obj = response.data[i];
            if (obj.status === 'true') {
              this.accounts.push(obj);
            }
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },

    deleteAccount(id) {
      console.log(id);
      AccountService.disableAccount(id)
        .then(() => {
          AccountService.getAll().then((response) => {
            const { accounts, totalItems } = response.data;
            this.accounts = accounts;
            this.count = totalItems;
            console.log(response.data);
            if (this.accounts.status === 'true') {
              this.messageTooltip = 'Disable this account';
            } else {
              this.messageTooltip = 'Enable this account';
            }
            this.modalDelete = false;
          });
        })
        .catch((e) => {
          console.log(e);
        });
    },

    updateAcount(id) {
      var data = {
        accountId: this.currentAccount.accountId,
        username: this.currentAccount.username,
        password: this.currentAccount.password,
      };

      AccountService.updateAccount(id, data)
        .then(() => {
          this.modal = false;
          alert('Update Success!');
          this.retrieveAccount();
        })
        .catch((e) => {
          console.log(e);
          alert('Update Failed!');
        });
    },

    handlePageChange(value) {
      this.page = value;
      this.retrieveAccount();
    },

    handlePageSizeChange(event) {
      this.pageSize = event.target.value;
      this.page = 1;
      this.retrieveAccount();
    },
    handelSearch() {
      this.page = 1;
      this.retrieveAccount();
    },
    setTextTooltip(text) {
      if (text === 'true') {
        return 'Disable this acoount';
      } else {
        return 'Enable this account';
      }
    },
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
