<template>
  <form action="" class="form-add" v-on:submit.prevent="createNewBranch">
    <h3>Add New Branch</h3>
    <div class="form-outline mb-4">
      <label for="location">Choose Location</label>
      <select v-model="branch.location" class="browser-default custom-select">
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
    <div class="form-outline mb-4">
      <label for="image">Choose Main Image</label>
      <div>
        <v-btn @click="click1">choose photo</v-btn>
        <input
          type="file"
          ref="input1"
          style="display: none"
          @change="previewImage"
          accept="image/*"
          required
        />
      </div>
      <br />
      <div v-if="imageData != null">
        <img class="preview" height="268" width="356" :src="imageShow" />
        <br />
      </div>
      <br />
      <label for="image">Choose Sub Image</label>
      <div>
        <v-btn @click="click2">choose photo</v-btn>
        <input
          type="file"
          ref="input2"
          style="display: none"
          @change="previewImage2"
          accept="image/*"
        />
      </div>
      <br />
      <div v-if="imageData != null">
        <img class="preview" height="268" width="356" :src="imageShow2" />
        <br />
      </div>
      <br />
      <div>
        <v-btn @click="click3">choose photo</v-btn>
        <input
          type="file"
          ref="input3"
          style="display: none"
          @change="previewImage3"
          accept="image/*"
        />
      </div>
      <br />
      <div v-if="imageData != null">
        <img class="preview" height="268" width="356" :src="imageShow3" />
        <br />
      </div>
      <br />
      <div>
        <v-btn @click="click4">choose photo</v-btn>
        <input
          type="file"
          ref="input4"
          style="display: none"
          @change="previewImage4"
          accept="image/*"
        />
      </div>
      <br />
      <div v-if="imageData != null">
        <img class="preview" height="268" width="356" :src="imageShow4" />
        <br />
      </div>
    </div>
    <button type="submit" class="btn btn-primary btn-block mb-4">
      Add New Branch
    </button>
  </form>
</template>

<script>
import firebase from 'firebase';
import BranchService from '../../services/BranchService';
export default {
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
      imageShow2: '',
      imageShow3: '',
      imageShow4: '',
    };
  },
  methods: {
    click1() {
      this.$refs.input1.click();
    },
    click2() {
      this.$refs.input2.click();
    },
    click3() {
      this.$refs.input3.click();
    },
    click4() {
      this.$refs.input4.click();
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
        await this.onUpload();
      });
      fileReader.readAsDataURL(files[0]);
      this.imageData = event.target.files[0];
    },
    previewImage2(event) {
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
        this.imageShow2 = fileReader.result;
        await this.onUpload();
      });
      fileReader.readAsDataURL(files[0]);
      this.imageData = event.target.files[0];
    },
    previewImage3(event) {
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
        this.imageShow3 = fileReader.result;
        await this.onUpload();
      });
      fileReader.readAsDataURL(files[0]);
      this.imageData = event.target.files[0];
    },
    previewImage4(event) {
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
        this.imageShow4 = fileReader.result;
        await this.onUpload();
      });
      fileReader.readAsDataURL(files[0]);
      this.imageData = event.target.files[0];
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
            this.branch.thumbnailsBranchSet.push(url);
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
    //     'eyJhbGciOiJIUzUxMiJ9.eyJBVVRIT1JJVElFU19LRVkiOiJBRE1JTixDVVNUT01FUixFTVBMT1lFRSIsInN1YiI6ImFkbWluIiwiaWF0IjoxNjA4NjQzNzMxLCJleHAiOjE2MDg2NDQwMzF9.qj71A_tY64DvHSr94aZoCp9ubSsrmAzGJcvjPYagASoG1uUSdibJgjdqd_bN1tfAtJMEwXBgrU24WeP4iH0ULA';

    //   let jwtData = jwt.split('.')[1];
    //   let decodedJwtJsonData = window.atob(jwtData);
    //   let decodedJwtData = JSON.parse(decodedJwtJsonData);

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
</style>
