<template>
  <form action="" class="form-add">
    <h3>Add New Room</h3>
    <!-- Message input -->
    <div class="form-outline mb-4">
      <b-form-select
        v-model="rooms.branchCode"
        :options="branchCodes"
      ></b-form-select>
      <label class="form-label" for="branchCode">Choose Branch Code</label>
    </div>
    <div class="form-outline mb-4">
      <b-form-select
        v-model="rooms.policyCode"
        :options="policyCodes"
      ></b-form-select>
      <label class="form-label" for="policyCode">Choose Branch Code</label>
    </div>
    <div class="form-outline mb-4">
      <label for="roomCode">Room Code</label>
      <input type="text" id="roomCode" class="form-control" required />
    </div>

    <div class="form-outline mb-4">
      <label for="name">Room Name</label>
      <input type="text" id="name" class="form-control" required />
    </div>
    <div class="form-outline mb-4">
      <label for="policy">Choose Ammount People</label>
      <select class="browser-default custom-select">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
      </select>
    </div>
    <div class="form-outline mb-4">
      <label for="price">Price</label>
      <input type="text" id="price" class="form-control" required />
    </div>
    <div class="form-outline mb-4">
      <label for="description">Description</label>
      <textarea
        id="description"
        name="description"
        rows="4"
        cols="50"
        class="form-control"
        required
      />
    </div>
    <div class="form-outline mb-4">
      <label for="image">Choose Image Of Room</label>
      <div class="input-group">
        <div>
          <b-button variant="success" @click="onPickFile">
            Upload Image
          </b-button>
        </div>
        <div class="custom-file">
          <input
            type="file"
            class="custom-file-input"
            style="display:none"
            ref="fileInput"
            accept="image/*"
            @change="onFilePicked"
          />
        </div>
      </div>
      <div>
        <img :src="imageUrl" alt="" height="150" />
      </div>
    </div>
    <button type="submit" class="btn btn-primary btn-block mb-4">
      Add New Room
    </button>
  </form>
</template>

<script>
import BranchService from '../../services/BranchService';
import CancelPolicyService from '../../services/CancelPolicyService';
export default {
  data() {
    return {
      imageUrl: '',
      image: null,
      rooms: {
        branchCode: '',
        roomCode: '',
        policyCode: '',
        name: '',
        ammountPeople: '',
        price: '',
        description: '',
      },
      branchs: [],
      branchCodes: [],
      policies: [],
      policyCodes: [],
    };
  },
  methods: {
    onPickFile() {
      this.$refs.fileInput.click();
    },

    onFilePicked(event) {
      const files = event.target.files;
      let fileName = files[0].name;
      if (fileName.lastIndexOf('.') <= 0) {
        return alert('Please add a valid file');
      }
      const fileReader = new FileReader();
      fileReader.addEventListener('load', () => {
        console.log(fileReader.result);
        this.imageUrl = fileReader.result;
      });
      fileReader.readAsDataURL(files[0]);
      this.image = files[0];
    },
    setBracnhCodes() {
      BranchService.getAllBranchInfor()
        .then((response) => {
          this.branchs = response.data;

          console.log(this.branchs);

          for (var i = 0; i < this.branchs.length; i++) {
            var options = [];
            for (var key in this.branchs[i]) {
              if (key == 'branchCode') {
                options['value'] = this.branchs[i][key];
                options['text'] = this.branchs[i][key];
              }
            }
            this.branchCodes.push(Object.assign({}, options));
          }
          console.log(this.branchCodes);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    setPolicyCode() {
      CancelPolicyService.getAll()
        .then((response) => {
          this.policies = response.data;

          console.log(this.policies);

          for (var i = 0; i < this.policies.length; i++) {
            var options = [];
            for (var key in this.policies[i]) {
              if (key == 'policyCode') {
                options['value'] = this.policies[i][key];
                options['text'] = this.policies[i][key];
              }
            }
            this.policyCodes.push(Object.assign({}, options));
          }
          console.log(this.policyCodes);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },

  mounted() {
    this.setBracnhCodes();
    this.setPolicyCode();
  },
};
</script>

<style scoped>
h3 {
  text-align: center;
}
</style>
