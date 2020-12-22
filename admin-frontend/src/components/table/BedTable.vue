<template>
  <div>
    <section id="tables">
      <mdb-row>
        <mdb-col md="12">
          <mdb-card cascade narrow class="mt-5">
            <div class="link-add">
              <a href="/bed/add" type="button" class="btn btn-success">
                Add new Bed
              </a>
            </div>
            <div class="list row">
              <div class="col-md-8">
                <div class="input-group mb-3">
                  <input
                    type="text"
                    class="form-control"
                    placeholder="Search by title"
                    name="searchName"
                    v-model="searchName"
                  />
                  <div class="input-group-append">
                    <button
                      class="btn btn-outline-secondary"
                      type="submit"
                      @click="handelSearch"
                    >
                      Search
                    </button>
                  </div>
                </div>
              </div>
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
      <br />
      <div>
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
    </section>
  </div>
</template>
<script>
import { mdbRow, mdbCol, mdbCard, mdbCardBody, mdbTbl } from 'mdbvue';

import BedService from '../../services/BedService';
export default {
  data() {
    return {
      beds: [],
      currentBed: null,
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
    retrieveAll() {
      const params = this.getRequestParams(
        this.searchName,
        this.page,
        this.pageSize
      );
      BedService.getAll(params)
        .then((response) => {
          const { beds, totalItems } = response.data;
          this.beds = beds;
          this.count = totalItems;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    handlePageChange(value) {
      this.page = value;
      this.retrieveAll();
    },

    handlePageSizeChange(event) {
      this.pageSize = event.target.value;
      this.page = 1;
      this.retrieveAll();
    },

    handelSearch() {
      this.page = 1;
      this.retrieveAll();
    },
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
