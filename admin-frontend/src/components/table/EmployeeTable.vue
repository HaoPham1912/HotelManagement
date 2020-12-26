<template>
  <section id="dashboard">
    <mdb-row>
      <mdb-col md="2"></mdb-col>
      <mdb-col md="10">
        <mdb-card class="mb-4">
          <div class="row">
            <div class="col-md-9">
              <a href="/employee/add" type="button" class="btn btn-success">
                ADD NEW EMPLOYEE
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
                  <th>Employee Id</th>
                  <th>Employee Code</th>
                  <th>Id Card</th>
                  <th>Name</th>
                  <th>Phone</th>
                  <th>Email</th>
                  <th>Username</th>
                  <th>Branch Code</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(data, index) in employees" :key="index">
                  <td>{{ data.employeeId }}</td>
                  <td>{{ data.employeeCode }}</td>
                  <td>{{ data.empIdCard }}</td>
                  <td>{{ data.empName }}</td>
                  <td>{{ data.empPhone }}</td>
                  <td>{{ data.email }}</td>
                  <td>{{ data.username }}</td>
                  <td>{{ data.branchCode }}</td>
                  <td class="action">
                    <div>
                      <button
                        class="btn-sm btn-warning"
                        @click="getIdEmp(data.employeeId)"
                        data-mdb-toggle="modal"
                        data-mdb-target="#exampleModal"
                      >
                        <i class="fas fa-pencil-alt"></i>
                      </button>
                    </div>
                    <div>
                      <button
                        class="btn-sm btn-danger"
                        @click="remove(scope.row)"
                      >
                        <i class="fas fa-trash"></i>
                      </button>
                    </div>
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
import { mdbRow, mdbCol, mdbCard, mdbCardBody, mdbTbl, mdbIcon } from 'mdbvue';

import EmployeeService from '../../services/EmployeeService';

export default {
  data() {
    return {
      employees: [],

      currentIndex: -1,
      searchName: '',

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
  },
  methods: {
    getRequestParams(searchName, page, pageSize) {
      let params = {};

      if (searchName) {
        params['name'] = searchName;
      }
      if (page) {
        params['pageNo'] = page - 1;
      }
      if (pageSize) {
        params['size'] = pageSize;
      }

      return params;
    },
    retrieveEmployee() {
      const params = this.getRequestParams(
        this.searchName,
        this.page,
        this.pageSize
      );
      EmployeeService.getAll(params)
        .then((response) => {
          const { employees, totalItems } = response.data;
          this.employees = employees;
          this.count = totalItems;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    handlePageChange(value) {
      this.page = value;
      this.retrieveEmployee();
    },

    handlePageSizeChange(event) {
      this.pageSize = event.target.value;
      this.page = 1;
      this.retrieveEmployee();
    },

    handelSearch() {
      this.page = 1;
      this.retrieveEmployee();
    },
    getIdEmp(id) {
      console.log(`id is ${id}`);
    },
  },

  mounted() {
    this.retrieveEmployee();
  },
};
</script>
<style>
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
</style>
