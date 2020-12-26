<template>
  <div>
    <section id="dashboard">
      <mdb-row>
        <mdb-col md="2"></mdb-col>
        <mdb-col md="10">
          <mdb-card class="mb-4">
            <div class="row">
              <div class="col-md-9">
                <a href="/bed/add" type="button" class="btn btn-success">
                  Add new Bed
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
                        <button class="btn-sm btn-warning">
                          <a
                            class="btn-link-edit action-button"
                            @click="edit(scope.row)"
                          >
                            <i class="fas fa-pencil-alt"></i>
                          </a>
                        </button>
                      </div>
                      <div>
                        <button class="btn-sm btn-danger">
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
  </div>
</template>
<script>
import { mdbRow, mdbCol, mdbCard, mdbCardBody, mdbTbl, mdbIcon } from 'mdbvue';
import BedService from '../../services/BedService';
export default {
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbCardBody,
    mdbTbl,
    mdbIcon,
  },
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
<style>
.action {
  display: flex;
  justify-content: space-evenly;
}
.input-group.md-form.form-sm.form-1 input {
  border: 1px solid #bdbdbd;
  border-top-right-radius: 0.25rem;
  border-bottom-right-radius: 0.25rem;
}
.input-group.md-form.form-sm.form-2 input {
  border: 1px solid #bdbdbd;
  border-top-left-radius: 0.25rem;
  border-bottom-left-radius: 0.25rem;
}
.input-group.md-form.form-sm.form-2 input.red-border {
  border: 1px solid #ef9a9a;
}
.input-group.md-form.form-sm.form-2 input.lime-border {
  border: 1px solid #cddc39;
}
.input-group.md-form.form-sm.form-2 input.amber-border {
  border: 1px solid #ffca28;
}

#paging {
  position: absolute;
  bottom: 0;
  right: 80px;
}
</style>
