<template>
  <section id="tables">
    <mdb-row>
      <mdb-col md="12">
        <mdb-card cascade narrow class="mt-5">
          <mdb-card-body>
            <mdb-tbl>
              <thead class="blue-grey lighten-4">
                <tr>
                  <th>Employee Id</th>
                  <th>Employee Code</th>
                  <th>Id Card</th>
                  <th>Name</th>
                  <th>Phone</th>
                  <th>Username</th>
                  <th>Branch Code</th>
                  <th colspan="2">Action</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(data, index) in employees" :key="index">
                  <td>{{ data.employeeId }}</td>
                  <td>{{ data.employeeCode }}</td>
                  <td>{{ data.empIdCard }}</td>
                  <td>{{ data.empName }}</td>
                  <td>{{ data.empPhone }}</td>
                  <td>{{ data.username }}</td>
                  <td>{{ data.brandCode }}</td>
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
<style scoped></style>
