<template>
  <section id="dashboard">
    <mdb-row>
      <mdb-col md="2"></mdb-col>
      <mdb-col md="10">
        <mdb-card class="mb-4">
          <div class="link-add">
            <a href="/policy/add" type="button" class="btn btn-success">
              Add new Policy
            </a>
          </div>
          <mdb-card-body>
            <mdb-tbl>
              <thead class="blue-grey lighten-4">
                <tr>
                  <th>Policy ID</th>
                  <th>Policy Code</th>
                  <th>Title</th>
                  <th>Day Long</th>
                  <th>Detail</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(data, index) in policies" :key="index">
                  <td>{{ data.policyId }}</td>
                  <td>{{ data.policyCode }}</td>

                  <td class="description">
                    <p>{{ data.title }}</p>
                  </td>
                  <td>{{ data.rangeDate }}</td>
                  <td class="description">
                    <p>{{ data.detail }}</p>
                  </td>
                  <td class="action">
                    <div>
                      <mdb-btn
                        color="warning"
                        class="btn-sm btn-warning"
                        v-tooltip.top-center="{
                          content: 'Edit this account',
                        }"
                        @click="bindingDataToModal(data.policyId)"
                      >
                        <i class="fas fa-pencil-alt"></i>
                        <a class="btn-link-edit action-button"> </a>
                      </mdb-btn>
                    </div>
                    <div>
                      <mdb-btn
                        :class="'btn-sm btn-danger'"
                        color="data.status : danger ? success"
                        @click="ShowModalDisable(data.policyId)"
                        v-tooltip.top-center="{
                          content: setTextTooltip(data.status),
                        }"
                        :disabled="isDisabled"
                      >
                        <a :href="'policy/' + data.policyId"> </a>
                        <i :class="'fas fa-ban'"></i>
                      </mdb-btn>
                    </div>
                  </td>
                </tr>
              </tbody>
            </mdb-tbl>
            <div>
              <mdb-modal centered :show="modal" @close="modal = false">
                <mdb-modal-header>
                  <mdb-modal-title>Update Account Information</mdb-modal-title>
                </mdb-modal-header>
                <mdb-modal-body>
                  <div>
                    <div class="form-outline mb-4">
                      <label for="policyCode">Policy Code</label>
                      <input
                        type="text"
                        id="policyCode"
                        class="form-control"
                        required
                        v-model="currentPolicy.policyCode"
                      />
                    </div>
                    <div class="form-outline mb-4">
                      <label for="rangeDate">Day Long</label>
                      <input
                        type="text"
                        id="rangeDate"
                        class="form-control"
                        required
                        v-model="currentPolicy.rangeDate"
                      />
                    </div>
                    <div class="form-outline mb-4">
                      <label for="title">Title</label>
                      <input
                        type="text"
                        id="title"
                        class="form-control"
                        required
                        v-model="currentPolicy.title"
                      />
                    </div>
                    <div class="form-outline mb-4">
                      <label for="description">Description</label>
                      <textarea
                        id="description"
                        rows="4"
                        cols="50"
                        class="form-control"
                        required
                        v-model="currentPolicy.detail"
                      />
                    </div></div
                ></mdb-modal-body>
                <mdb-modal-footer>
                  <mdb-btn color="danger" @click.native="modal = false"
                    >Close</mdb-btn
                  >
                  <mdb-btn
                    color="primary"
                    @click="updatePolicy(currentPolicy.policyId)"
                    >Save changes</mdb-btn
                  >
                </mdb-modal-footer>
              </mdb-modal>
            </div>
            <div>
              <mdb-modal
                centered
                :show="modalDelete"
                @close="modalDelete = false"
              >
                <mdb-modal-header>
                  <mdb-modal-title>ARE YOU SURE?</mdb-modal-title>
                </mdb-modal-header>
                <mdb-modal-body>PLEASE CHECK BEFORE ACTION</mdb-modal-body>
                <mdb-modal-footer>
                  <mdb-btn color="danger" @click.native="modalDelete = false"
                    >Close</mdb-btn
                  >
                  <mdb-btn
                    color="primary"
                    @click="deletePolicy(currentPolicy.policyId)"
                    >OK</mdb-btn
                  >
                </mdb-modal-footer>
              </mdb-modal>
            </div>
          </mdb-card-body>
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
  mdbModal,
  mdbModalHeader,
  mdbModalTitle,
  mdbModalBody,
  mdbModalFooter,
  mdbBtn,
} from 'mdbvue';

import CancelPolicyService from '../../services/CancelPolicyService';
export default {
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbCardBody,
    mdbTbl,
    mdbModal,
    mdbModalHeader,
    mdbModalTitle,
    mdbModalBody,
    mdbModalFooter,
    mdbBtn,
  },
  data() {
    return {
      policies: [],
      currentPolicy: {
        policyId: '',
        policyCode: '',
        title: '',
        detail: '',
        rangeDate: '',
      },
      modal: false,
      modalDelete: false,
      isDisabled: true,
    };
  },
  methods: {
    ShowModalDisable(id) {
      console.log(id);
      this.modalDelete = true;
      CancelPolicyService.getById(id)
        .then((response) => {
          this.currentPolicy = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    bindingDataToModal(id) {
      console.log(id);
      this.modal = true;
      CancelPolicyService.getById(id)
        .then((response) => {
          this.currentPolicy = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    retrieveAll() {
      CancelPolicyService.getAll()
        .then((response) => {
          this.policies = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    updatePolicy(id) {
      var data = {
        policyId: this.currentPolicy.policyId,
        policyCode: this.currentPolicy.policyCode,
        title: this.currentPolicy.title,
        detail: this.currentPolicy.detail,
        rangeDate: this.currentPolicy.rangeDate,
      };

      CancelPolicyService.update(id, data)
        .then(() => {
          this.modal = false;
          alert('Update Success!');
          this.retrieveAll();
        })
        .catch((e) => {
          console.log(e);
          alert('Update Failed!');
        });
    },
    setTextTooltip(text) {
      if (text === 'true') {
        return 'Disable this acoount';
      } else {
        return 'Enable this account';
      }
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
.description {
  word-wrap: break-word;
  width: 300px;
}
</style>
