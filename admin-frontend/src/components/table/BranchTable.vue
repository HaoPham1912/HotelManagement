<template>
  <section id="tables">
    <mdb-row>
      <mdb-col md="12">
        <mdb-card cascade narrow class="mt-5">
          <mdb-card-body>
            <mdb-tbl>
              <thead class="blue-grey lighten-4">
                <tr>
                  <th>BranchID</th>
                  <th>Address</th>
                  <th>Branch Code</th>
                  <th>Description</th>
                  <th>Branch Name</th>
                  <th>Rating</th>
                  <th>Status</th>
                  <th colspan="2">Action</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(data, index) in branchs" :key="index">
                  <td>{{ data.branchId }}</td>
                  <td>{{ data.address }}</td>
                  <td>{{ data.branchCode }}</td>
                  <td>{{ data.description }}</td>
                  <td>{{ data.branchName }}</td>
                  <td>{{ data.rating }}</td>
                  <td>{{ data.status }}</td>
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

import BranchService from '../../services/BranchService';
export default {
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbCardBody,
    mdbTbl,
  },
  methods: {
    retrieveBranch() {
      BranchService.getAll()
        .then((response) => {
          this.branchs = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
  data() {
    return {
      branchs: [],
    };
  },
  mounted() {
    this.retrieveBranch();
  },
};
</script>
<style scoped></style>
