<template>
  <section id="dashboard">
    <mdb-row>
      <mdb-col md="3"></mdb-col>
      <mdb-col md="6">
        <mdb-card class="mb-4"
          ><form class="form-add-branch">
            <h3>Update Branch</h3>
            <div class="form-outline mb-4">
              <label for="location">Choose Location</label>
              <select
                v-model="branch.location"
                class="browser-default custom-select"
                disabled
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
                disabled
                v-model="branch.branchCode"
              />
            </div>
            <div class="form-outline mb-4">
              <label for="branchName">Branch Name</label>
              <input
                type="text"
                id="branchName"
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
                <div v-if="imageData == null">
                  <img
                    class="preview"
                    height="268"
                    width="356"
                    :src="branch.mainImage"
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
            <div class="btn-submit">
              <button
                class="btn btn-outline-success btn-rounded"
                data-mdb-ripple-color="dark"
                type="button"
                @click="updateBranch(branch.branchId)"
              >
                Update Branch
              </button>
            </div>
            <mdb-container>
              <!-- cart modal -->
              <mdb-modal
                :show="push"
                @close="push = false"
                class="text-center"
                info
              >
                <mdb-modal-header center :close="false">
                  <p class="heading">Notification</p>
                </mdb-modal-header>
                <mdb-modal-body>
                  <mdb-icon
                    icon="bell"
                    size="4x"
                    class="animated rotateIn mb-4"
                  />
                  <p>
                    Do you want to add new branch to system?
                  </p>
                </mdb-modal-body>
                <mdb-modal-footer center>
                  <mdb-btn outline="primary">Yes</mdb-btn>
                  <mdb-btn color="primary" @click="push = false">No</mdb-btn>
                </mdb-modal-footer>
              </mdb-modal>
            </mdb-container>
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
import {
  mdbRow,
  mdbCol,
  mdbCard,
  mdbBtn,
  mdbContainer,
  mdbIcon,
  mdbModal,
  mdbModalHeader,
  mdbModalBody,
  mdbModalFooter,
} from 'mdbvue';
export default {
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbContainer,
    mdbBtn,
    mdbIcon,
    mdbModal,
    mdbModalHeader,
    mdbModalBody,
    mdbModalFooter,
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

      idUpdate: '',

      push: false,
    };
  },
  methods: {
    getCurrentBranch() {
      let branchId = this.$route.params.id;
      console.log(branchId);
      BranchService.getBranchById(branchId).then((response) => {
        console.log(response.data);
        this.branch = response.data;
        this.idUpdate = this.branch.branchId;
        this.branch.mainImage = response.data.mainImage;
      });
    },

    click1() {
      this.$refs.input1.click();
    },
    previewImage(event) {
      const files = event.target.files;
      let fileName = files[0].name;
      if (fileName.lastIndexOf('.') <= 0) {
        return alert('Please add a valid file');
      }
      const fileReader = new FileReader();
      fileReader.addEventListener('load', async () => {
        this.imageShow = fileReader.result;
      });
      fileReader.readAsDataURL(files[0]);
      this.imageData = event.target.files[0];
      this.buttonText = 'Change Image';
      this.onUpload();
    },

    onUpload() {
      return new Promise((resolve, reject) => {
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
            reject();
          },
          () => {
            this.uploadValue = 100;
            storageRef.snapshot.ref.getDownloadURL().then((url) => {
              console.log('=------------------');
              console.log(url);
              if (url !== '') {
                this.branch.mainImage = url;

                this.branch.thumbnailsBranchSet.push(url);
                console.log('dasdasdasd');

                console.log(this.branch.thumbnailsBranchSet);
              }
            });
            resolve();
          }
        );
      });
    },
    async updateBranch(id) {
      if (this.imageData !== this.branch.mainImage) {
        await this.onUpload();
      }
      var data = {
        branchCode: this.branch.branchCode,
        branchName: this.branch.branchName,
        location: this.branch.location,
        address: this.branch.address,
        description: this.branch.description,
        mainImage: this.branch.mainImage,
      };
      console.log(id);
      BranchService.updateBranchInfo(id, data)
        .then((response) => {
          console.log(response.data);
          alert('Branch information have been updated');
          this.$router.push('/reloadBranch');
        })
        .catch((e) => {
          console.log(e);
        });
    },

    confirmAddBracnh() {
      this.push = true;
    },

    // decode() {
    //   let jwt =
    //     'eyJhbGciOiJIUzUxMiJ9.eyJBVVRIT1JJVElFU19LRVkiOiJFTVBMT1lFRSIsInN1YiI6ImZvbyIsImlhdCI6MTYwOTI2MDEzMywiZXhwIjoxNjA5MjYwNDMzfQ.c9gixHSqKiWwDSDgsbR7dAvi5-epdhucsqb6hvyUhKakIUYgL3QP49UMSSKb4r28sAXwFslhxHHfDETq1_kZJg';

    //   let jwtData = jwt.split('.')[1];
    //   let decodedJwtJsonData = window.atob(jwtData);
    //   let decodedJwtData = JSON.stringify(decodedJwtJsonData);

    //   //let isAdmin = decodedJwtData.admin;

    //   console.log('jwtData: ' + jwtData);
    //   console.log('decodedJwtJsonData: ' + decodedJwtJsonData);
    //   console.log('decodedJwtData: ' + decodedJwtData);
    //   console.log(decodedJwtJsonData.AUTHORITIES_KEY);
    // },
  },
  mounted() {
    this.getCurrentBranch();
  },
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
  width: 60%;
  margin-left: 160px;
}

.btn-submit {
  position: relative;
  left: 110px;
  top: 10%;
  height: 100px;
}

.btn-submit button {
  height: 50px;
  width: 300px;

  font-size: 20px;
}
</style>
