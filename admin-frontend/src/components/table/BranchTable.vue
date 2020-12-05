<template>
  <section id="tables">
    <mdb-row>
      <mdb-col md="12">
        <mdb-card cascade narrow class="mt-5">
          <div class="link-add">
            <a href="/branch/add" type="button" class="btn btn-success">
              Add new Branch
            </a>
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
                  <th>Status</th>
                  <th colspan="2"></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(data, index) in branchs" :key="index">
                  <td>{{ data.branchId }}</td>
                  <td>
                    <a href="">{{ data.branchCode }}</a>
                  </td>
                  <td>{{ data.address }}</td>
                  <td class="description">{{ data.description }}</td>
                  <td>{{ data.branchName }}</td>
                  <td>{{ data.status }}</td>
                  <td class="action">
                    <div>
                      <button class="btn-warning">
                        <a
                          class="btn-link-edit action-button"
                          @click="edit(scope.row)"
                        >
                          <i class="fas fa-pencil-alt"></i>
                        </a>
                      </button>
                    </div>
                    <div>
                      <button class="btn-danger">
                        <a
                          class="btn-link-delete action-button"
                          @click="remove(scope.row)"
                        >
                          <i class="fas fa-trash"></i>
                        </a>
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
}
</style>
