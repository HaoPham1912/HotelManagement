<template>
  <div>
    <section id="tables">
      <mdb-row>
        <mdb-col md="12">
          <mdb-card cascade narrow class="mt-5">
            <div class="link-add">
              <a href="/employee/add" type="button" class="btn btn-success">
                Add new Employee
              </a>
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
                          class="btn-warning"
                          @click="getIdEmp(data.employeeId)"
                          data-mdb-toggle="modal"
                          data-mdb-target="#exampleModal"
                        >
                          <i class="fas fa-pencil-alt"></i>
                          EDIT
                        </button>
                      </div>
                      <div>
                        <button class="btn-danger" @click="remove(scope.row)">
                          <i class="fas fa-trash"></i>
                          DELETE
                        </button>
                      </div>
                    </td>
                  </tr>
                  <!-- Modal -->
                  <div
                    class="modal fade"
                    id="exampleModal"
                    tabindex="-1"
                    aria-labelledby="exampleModalLabel"
                    aria-hidden="true"
                  >
                    <div class="modal-dialog">
                      <div class="modal-content">
                        <div class="modal-header">
                          <h5 class="modal-title" id="exampleModalLabel">
                            Modal title
                          </h5>
                          <button
                            type="button"
                            class="btn-close"
                            data-mdb-dismiss="modal"
                            aria-label="Close"
                          ></button>
                        </div>
                        <div class="modal-body">...</div>
                        <div class="modal-footer">
                          <button
                            type="button"
                            class="btn btn-secondary"
                            data-mdb-dismiss="modal"
                          >
                            Close
                          </button>
                          <button type="button" class="btn btn-primary">
                            Save changes
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>
                </tbody>
              </mdb-tbl>
            </mdb-card-body>
          </mdb-card>
        </mdb-col>
      </mdb-row>
    </section>
  </div>
</template>
<script>
import { mdbRow, mdbCol, mdbCard, mdbCardBody, mdbTbl } from 'mdbvue';

import EmployeeService from '../../services/EmployeeService';

export default {
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbCardBody,
    mdbTbl,
  },
  methods: {
    retrieveEmployee() {
      EmployeeService.getAll()
        .then((response) => {
          this.employees = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getIdEmp(id) {
      console.log(`id is ${id}`);
    },
  },
  data() {
    return {
      employees: [],
    };
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
