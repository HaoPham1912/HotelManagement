<template>
  <form action="" class="form-add">
    <h3>Update Room information</h3>
    <!-- Message input -->
    <div class="form-outline mb-4">
      <label class="form-label" for="branchCode">Choose Branch Code</label>
      <b-form-select
        v-model="rooms.branchCode"
        :options="branchCodes"
        disabled
      ></b-form-select>
    </div>
    <div class="form-outline mb-4">
      <label for="roomCode">Room Code</label>
      <input
        type="text"
        id="roomCode"
        class="form-control"
        v-model="rooms.roomCode"
        disabled
        required
      />
    </div>
    <div class="form-outline mb-4">
      <label class="form-label" for="policyCode">Choose Cancel Policy</label>
      <b-form-select
        v-model="rooms.policyCode"
        :options="policyCodes"
      ></b-form-select>
    </div>

    <div class="form-outline mb-4">
      <label for="name">Room Name</label>
      <input
        type="text"
        id="name"
        class="form-control"
        v-model="rooms.name"
        required
      />
    </div>
    <div class="form-outline mb-4">
      <label for="policy">Choose Ammount People</label>
      <select
        class="browser-default custom-select"
        v-model="rooms.ammountPeople"
      >
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
      </select>
    </div>
    <div class="form-outline mb-4">
      <label for="price">Price</label>
      <input
        type="text"
        id="price"
        class="form-control"
        v-model="rooms.price"
        required
      />
    </div>
    <div class="form-outline mb-4">
      <label for="description">Description</label>
      <textarea
        id="description"
        rows="4"
        cols="50"
        class="form-control"
        v-model="rooms.description"
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
    <button
      type="button"
      class="btn btn-primary btn-block mb-4"
      @click="updateRoom(rooms.roomId)"
    >
      Update Room
    </button>
  </form>
</template>

<script>
import firebase from 'firebase';

import BranchService from '../../services/BranchService';
import CancelPolicyService from '../../services/CancelPolicyService';
import RoomService from '../../services/RoomService';
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
        mainImage: '',
      },
      branchs: [],
      branchCodes: [],
      policies: [],
      policyCodes: [],
    };
  },
  methods: {
    getCurrentRoom() {
      let roomId = this.$route.params.id;
      console.log(roomId);
      RoomService.getRoomById(roomId).then((response) => {
        this.rooms = response.data;
        this.imageUrl = response.data.mainImage;
        this.rooms.mainImage = response.data.mainImage;
      });
    },

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
      this.image = event.target.files[0];
      this.onUpload();
    },
    onUpload() {
      return new Promise((resolve, reject) => {
        const storageRef = firebase
          .storage()
          .ref(`${this.image.name}`)
          .put(this.image);
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
                this.rooms.mainImage = url;

                console.log('dasdasdasd');

                console.log(this.rooms.mainImage);
              }
            });
            resolve();
          }
        );
      });
    },

    async updateRoom(id) {
      if (this.imageUrl !== this.rooms.mainImage) {
        await this.onUpload();
      }
      var data = {
        roomCode: this.rooms.roomCode,
        policyCode: this.rooms.policyCode,
        branchCode: this.rooms.branchCode,
        name: this.rooms.name,
        ammountPeople: this.rooms.ammountPeople,
        price: this.rooms.price,
        description: this.rooms.description,
        mainImage: this.rooms.mainImage,
      };

      RoomService.updateRoom(id, data).then((response) => {
        console.log(response.data);
        alert('Room information have been updated');
        this.$router.push('/reloadRoom');
      });
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
    this.getCurrentRoom();
  },
};
</script>

<style scoped>
h3 {
  text-align: center;
}
</style>
