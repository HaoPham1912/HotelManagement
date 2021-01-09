<template>
  <section id="dashboard">
    <mdb-row>
      <mdb-col md="2"></mdb-col>
      <mdb-col md="10">
        <mdb-card class="mb-4">
          <div class="row">
            <div class="col-md-6">
              <a
                href="/admin/add-employee"
                type="button"
                class="btn btn-success"
              >
                ADD NEW EMPLOYEE
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
                      <mdb-btn
                        color="warning"
                        class="btn-sm btn-warning"
                        v-tooltip.top-center="{
                          content: 'Edit this account',
                        }"
                        @click="getIdEmp(data.employeeId)"
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
                        @click="ShowModalDisable(data.employeeId)"
                        v-tooltip.top-center="{
                          content: setTextTooltip(data.status),
                        }"
                      >
                        <a :href="'employee/' + data.employeeId"> </a>
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
              <mdb-modal
                centered
                :show="modalDelete"
                @close="modalDelete = false"
              >
                <mdb-modal-header>
                  <mdb-modal-title>ARE YOU SURE?</mdb-modal-title>
                </mdb-modal-header>
                <mdb-modal-body>Please check before action</mdb-modal-body>
                <mdb-modal-footer>
                  <mdb-btn color="danger" @click.native="modalDelete = false"
                    >Close</mdb-btn
                  >
                  <mdb-btn
                    color="success"
                    @click="disableEmployee(currentEmployee.employeeId)"
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

import EmployeeService from '../../services/EmployeeService';
import { AUTH_LOGOUT } from '../../store/actions/auth';
export default {
  data() {
    return {
      employees: [],
      currentEmployee: {},

      modalDelete: false,

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
    mdbModal,
    mdbModalHeader,
    mdbModalTitle,
    mdbModalBody,
    mdbModalFooter,
    mdbBtn,
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
          if (Object.entries(this.employees).length === 0) {
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
      this.searchName = '';
      this.retrieveEmployee();
    },

    getCurrentEmployee(id) {
      EmployeeService.getEmployeeById(id)
        .then((response) => {
          this.currentEmployee = response.data;
          console.log(this.currentEmployee);
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
      this.$router.push(`employee/${id}`);
    },
    setTextTooltip(text) {
      if (text === 'true') {
        return 'Disable this employee';
      } else {
        return 'Enable this employee';
      }
    },
    ShowModalDisable(id) {
      console.log(id);
      this.modalDelete = true;
      EmployeeService.getEmployeeById(id)
        .then((response) => {
          this.currentEmployee = response.data;
          console.log(this.currentEmployee);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    disableEmployee(id) {
      EmployeeService.disableEmployee(id).then(() => {
        EmployeeService.getAll().then((response) => {
          const { employees, totalItems } = response.data;
          this.employees = employees;
          this.count = totalItems;
          console.log(response.data);
          if (this.employees.status === 'true') {
            this.messageTooltip = 'Disable this employee';
          } else {
            this.messageTooltip = 'Enable this employee';
          }
          this.modalDelete = false;
        });
      });
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
