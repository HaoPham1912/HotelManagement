<template>
  <section id="dashboard">
    <mdb-row>
      <mdb-col md="2"></mdb-col>
      <mdb-col md="10">
        <mdb-card class="mb-4">
          <div class="link-add">
            <a href="/promo/add" type="button" class="btn btn-success">
              Add new Promotion
            </a>
          </div>
          <mdb-card-body>
            <mdb-tbl>
              <thead class="blue-grey lighten-4">
                <tr>
                  <th>PromotionId</th>
                  <th>Promotion Code</th>
                  <th>Percent Discount</th>
                  <th>Customer Type</th>
                  <th>Start Date</th>
                  <th>End Date</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(data, index) in promos" :key="index">
                  <td>{{ data.promoId }}</td>
                  <td>{{ data.promotionCode }}</td>
                  <td>{{ data.percent }}</td>
                  <td>{{ data.cusType }}</td>
                  <td>{{ data.startDate }}</td>
                  <td>{{ data.endDate }}</td>
                  <td class="action">
                    <div>
                      <button class="btn-sm btn-warning">
                        <a class="btn-link-edit action-button">
                          <i class="fas fa-pencil-alt"></i>
                        </a>
                      </button>
                    </div>
                    <div>
                      <button class="btn-sm btn-danger">
                        <a class="btn-link-delete action-button">
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

import PromotionService from '../../services/PromotionService';
export default {
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbCardBody,
    mdbTbl,
  },
  methods: {
    retrievePromo() {
      PromotionService.getAll()
        .then((response) => {
          this.promos = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log('error' + e);
        });
    },
  },
  data() {
    return {
      promos: [],
    };
  },
  mounted() {
    this.retrievePromo();
  },
};
</script>
<style scoped>
.action {
  display: flex;
  justify-content: space-evenly;
}
</style>
