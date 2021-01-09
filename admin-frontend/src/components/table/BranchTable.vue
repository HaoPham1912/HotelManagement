<template>
  <section id="dashboard">
    <mdb-row>
      <mdb-col md="2"></mdb-col>
      <mdb-col md="10">
        <mdb-card class="mb-4">
          <div class="row">
            <div class="col-md-6">
              <a href="/admin/add-branch" type="button" class="btn btn-success">
                Add new Branch
              </a>
            </div>
            <div class="col-md-3">
              <mdb-btn class="btn-showall" color="info" @click="showAll"
                >Show All</mdb-btn
              >
            </div>
            <div class="col-md-3">
              <div class="input-group md-form form-sm form-2 pl-0">
                <input
                  class="form-control my-0 py-1 lime-border"
                  type="text"
                  placeholder="Search by Branch Code"
                  aria-label="Search"
                  name="branchCode"
                  v-model="branchCode"
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
                  <th>BranchID</th>
                  <th>Branch Code</th>
                  <th>Address</th>
                  <th>Description</th>
                  <th>Branch Name</th>
                  <th>Branch Image</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(data, index) in branchs" :key="index">
                  <td>{{ data.branchId }}</td>
                  <td>
                    <a :href="'detail-branch/' + data.branchCode">{{
                      data.branchCode
                    }}</a>
                  </td>
                  <td>
                    <p class="address">{{ data.address }}</p>
                  </td>
                  <td class="description">
                    <p>{{ data.description }}</p>
                  </td>
                  <td>{{ data.branchName }}</td>
                  <td>
                    <dir class="img-table">
                      <img :src="data.mainImage" alt="" />
                    </dir>
                  </td>
                  <td class="action">
                    <div>
                      <button
                        class="btn-sm btn-warning"
                        @click="editBranch(data.branchId)"
                      >
                        <a class="btn-link-edit action-button">
                          <i class="fas fa-pencil-alt"></i>
                        </a>
                        EDIT BRANCH
                      </button>
                    </div>
                    <!-- <div>
                      <button
                        class="btn-sm btn-danger"
                        @click="disableBranch(data.branchId)"
                      >
                        <a class="btn-link-delete action-button">
                          <i class="fas fa-trash"></i>
                        </a>
                      </button>
                    </div> -->
                  </td>
                </tr>
              </tbody>
            </mdb-tbl>
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
  mdbBtn,
} from 'mdbvue';

import BranchService from '../../services/BranchService';
import { AUTH_LOGOUT } from '../../store/actions/auth';
export default {
  data() {
    return {
      branchs: [],
      currentBranch: null,
      currentIndex: -1,
      branchCode: '',

      page: 1,
      count: 0,
      pageSize: 3,

      pageSizes: [3, 6, 9],
    };
  },
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbCardBody,
    mdbTbl,
    mdbIcon,
    mdbBtn,
  },
  methods: {
    getRequestParams(branchCode, page, pageSize) {
      let params = {};

      if (branchCode) {
        params['branchCode'] = branchCode;
      }
      if (page) {
        params['pageNo'] = page - 1;
      }
      if (pageSize) {
        params['size'] = pageSize;
      }

      return params;
    },
    retrieveBranch() {
      const params = this.getRequestParams(
        this.branchCode,
        this.page,
        this.pageSize
      );
      BranchService.getAll(params)
        .then((response) => {
          const { branchs, totalItems } = response.data;
          this.branchs = branchs;
          this.count = totalItems;
          console.log(response.data);
          if (Object.entries(this.branchs).length === 0) {
            alert('Session time out!!!');
            this.$store
              .dispatch(AUTH_LOGOUT)
              .then(() => this.$router.push('/login'));
          }
        })
        .catch((e) => {
          console.log(e);
          alert('Session time out!!!');
          this.$store
            .dispatch(AUTH_LOGOUT)
            .then(() => this.$router.push('/login'));
        });
    },

    showAll() {
      this.branchCode = '';
      this.retrieveBranch();
    },

    disableBranch(id) {
      console.log(id);

      BranchService.disableBranch(id).then((response) => {
        console.log(response.data);
      });
    },
    handlePageChange(value) {
      this.page = value;
      this.retrieveBranch();
    },

    handlePageSizeChange(event) {
      this.pageSize = event.target.value;
      this.page = 1;
      this.retrieveBranch();
    },

    editBranch(id) {
      console.log(`id is ${id}`);
      this.$router.push(`branch/${id}`);
    },

    handelSearch() {
      this.page = 1;
      this.retrieveBranch();
    },
  },

  mounted() {
    this.retrieveBranch();
  },
};
</script>
<style scoped>
a {
  right: 0;
  width: 200px;
}
.link-add {
  margin-right: auto;
}
.action {
  display: flex;
  justify-content: space-around;
}

.description {
  overflow-wrap: break-word;
  width: 300px;
}

.address {
  word-wrap: break-word;
  width: 100px;
}

img {
  width: 200px;
  height: 120px;
  text-align: center;
}
</style>
