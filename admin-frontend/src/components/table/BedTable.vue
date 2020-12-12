<template>
  <section id="tables">
    <mdb-row>
      <mdb-col md="12">
        <mdb-card cascade narrow class="mt-5">
          <div class="link-add">
            <a href="/bed/add" type="button" class="btn btn-success">
              Add new Bed
            </a>
          </div>
          <mdb-card-body>
            <mdb-tbl>
              <thead class="blue-grey lighten-4">
                <tr>
                  <th>Bed ID</th>
                  <th>Name</th>
                  <th>Amount People</th>
                  <th>Price</th>
                  <th>In Room</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(data, index) in beds" :key="index">
                  <td>{{ data.bedId }}</td>
                  <td>{{ data.name }}</td>
                  <td>{{ data.amountPeople }}</td>
                  <td>{{ data.price }}</td>
                  <td>{{ data.roomCodeSet }}</td>
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
                      <button class="btn-danger">
                        <a
                          class="btn-link-delete action-button"
                          @click="remove(scope.row)"
                        >
                          <i class="fas fa-trash"></i>
                        </a>
                        DELETE
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

import BedService from '../../services/BedService';
export default {
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbCardBody,
    mdbTbl,
  },
  methods: {
    retrieveAll() {
      BedService.getAll()
        .then((response) => {
          this.beds = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
  data() {
    return {
      beds: [],
    };
  },
  mounted() {
    this.retrieveAll();
  },
};
</script>
<style scoped>
.action {
  display: flex;
  justify-content: space-evenly;
}
</style>
