<template>
  <section id="dashboard">
    <mdb-row>
      <mdb-col md="3"></mdb-col>
      <mdb-col md="6">
        <mdb-card class="mb-4"
          ><form
            action=""
            class="form-add-branch"
            v-on:submit.prevent="createNewBranch"
          >
            <h3>Add New Branch</h3>
            <div class="form-outline mb-4">
              <label for="location">Choose Location</label>
              <select
                v-model="branch.location"
                class="browser-default custom-select"
              >
                <option selected value="Ho Chi Minh">Ho Chi Minh</option>
                <option value="Nha Trang">Nha Trang</option>
                <option value="Da Nang">Da Nang</option>
              </select>
            </div>
            <div class="form-outline mb-4">
              <label for="branchCode">Branch Code</label>
              <input
                type="text"
                id="branchCode"
                class="form-control"
                required
                v-model="branch.branchCode"
              />
            </div>
            <div class="form-outline mb-4">
              <label for="name">Branch Name</label>
              <input
                type="text"
                id="name"
                class="form-control"
                required
                v-model="branch.branchName"
              />
            </div>
            <div class="form-outline mb-4">
              <label for="address">Address</label>
              <input
                type="text"
                id="address"
                class="form-control"
                required
                v-model="branch.address"
              />
            </div>
            <div class="form-outline mb-4">
              <label for="description">Description</label>
              <textarea
                id="description"
                name="description"
                rows="4"
                cols="50"
                class="form-control"
                v-model="branch.description"
                required
              />
            </div>
            <div class="image-list">
              <div class="group-img">
                <div v-if="imageData != null">
                  <img
                    class="preview"
                    height="268"
                    width="356"
                    :src="imageShow"
                  />
                </div>

                <div class="form-outline mb-4">
                  <mdb-btn class="btn-add-img" outline="info" @click="click1">{{
                    this.buttonText
                  }}</mdb-btn>
                  <input
                    type="file"
                    ref="input1"
                    style="display: none"
                    @change="previewImage"
                    accept="image/*"
                    required
                  />
                </div>
              </div>
              <br />
            </div>
            <div class="form-group">
              <button type="submit" @submit="onUpload">
                Add New Branch
              </button>
            </div>
          </form>
        </mdb-card>
      </mdb-col>
      <mdb-col md="3"></mdb-col>
    </mdb-row>
  </section>
</template>

<script>
import firebase from 'firebase';
import BranchService from '../../services/BranchService';
import { mdbRow, mdbCol, mdbCard, mdbBtn } from 'mdbvue';
export default {
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbBtn,
  },
  data() {
    return {
      branch: {
        branchCode: '',
        branchName: '',
        location: '',
        address: '',
        description: '',
        mainImage: '',
        thumbnailsBranchSet: [],
      },
      imgSubList: [],
      img1: '',
      imageData: null,

      imageShow: '',

      buttonText: 'Choose Image',
    };
  },
  methods: {
    click1() {
      this.$refs.input1.click();
    },
    previewImage(event) {
      // this.uploadValue = 0;
      // this.img1 = null;
      // this.imageData = event.target.files[0];
      // console.log(event.target.files[0]);
      //  / this.onUpload();
      const files = event.target.files;
      let fileName = files[0].name;
      if (fileName.lastIndexOf('.') <= 0) {
        return alert('Please add a valid file');
      }
      const fileReader = new FileReader();
      fileReader.addEventListener('load', async () => {
        this.imageShow = fileReader.result;
        //await this.onUpload();
      });
      fileReader.readAsDataURL(files[0]);
      this.imageData = event.target.files[0];
      this.buttonText = 'Change Image';
    },

    onUpload() {
      const storageRef = firebase
        .storage()
        .ref(`${this.imageData.name}`)
        .put(this.imageData);
      storageRef.on(
        `state_changed`,
        (snapshot) => {
          this.uploadValue =
            (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
        },
        (error) => {
          console.log(error.message);
        },
        () => {
          this.uploadValue = 100;
          storageRef.snapshot.ref.getDownloadURL().then((url) => {
            // this.img1 = url;
            console.log('=------------------');
            console.log(url);
            this.branch.mainImage = url;
            if (this.branch.mainImage !== '') {
              this.branch.thumbnailsBranchSet.push(url);
              if (this.branch.thumbnailsBranchSet.length > 0) {
                var data = {
                  branchCode: this.branch.branchCode,
                  branchName: this.branch.branchName,
                  location: this.branch.location,
                  address: this.branch.address,
                  description: this.branch.description,
                  mainImage: this.branch.mainImage,
                  thumbnailsBranchSet: this.branch.thumbnailsBranchSet,
                };

                BranchService.createNewBranch(data)
                  .then((response) => {
                    console.log(response.data);
                  })
                  .catch((e) => {
                    console.log(e);
                  });
              }
            }
          });
        }
      );
    },
    createNewBranch() {
      var data = {
        branchCode: this.branch.branchCode,
        branchName: this.branch.branchName,
        location: this.branch.location,
        address: this.branch.address,
        description: this.branch.description,
        mainImage: this.branch.mainImage,
        thumbnailsBranchSet: this.branch.thumbnailsBranchSet,
      };

      BranchService.createNewBranch(data)
        .then((response) => {
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },

    // decode() {
    //   let jwt =
    //     'eyJhbGciOiJIUzUxMiJ9.eyJBVVRIT1JJVElFU19LRVkiOiJFTVBMT1lFRSIsInN1YiI6ImZvbyIsImlhdCI6MTYwOTI2MDEzMywiZXhwIjoxNjA5MjYwNDMzfQ.c9gixHSqKiWwDSDgsbR7dAvi5-epdhucsqb6hvyUhKakIUYgL3QP49UMSSKb4r28sAXwFslhxHHfDETq1_kZJg';

    //   let jwtData = jwt.split('.')[1];
    //   let decodedJwtJsonData = window.atob(jwtData);
    //   let decodedJwtData = JSON.stringify(decodedJwtJsonData);

    //   let isAdmin = decodedJwtData.admin;

    //   console.log('jwtData: ' + jwtData);
    //   console.log('decodedJwtJsonData: ' + decodedJwtJsonData);
    //   console.log('decodedJwtData: ' + decodedJwtData);
    //   console.log('Is admin: ' + isAdmin);
    // },
  },
  mounted() {},
};
</script>

<style scoped>
h3 {
  text-align: center;
}

.img-list {
  display: flex;
}

.group-img {
  display: flex;
  justify-content: center;
}

.btn-add-img {
  margin-left: 10px;
}
.form-add-branch {
  width: 70%;
  margin-left: 200px;
}
</style>
