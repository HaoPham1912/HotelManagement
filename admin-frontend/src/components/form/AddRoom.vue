<template>
  <form action="" class="form-add">
    <h3>Add New Room</h3>
    <div class="form-outline mb-4">
      <label for="location">Choose Branch</label>
      <select class="browser-default custom-select">
        <option selected value="HCM1">HCM1</option>
        <option value="NT1">NT1</option>
        <option value="DN1">DN1</option>
      </select>
    </div>
    <div class="form-outline mb-4">
      <label for="policy">Choose Cancel Policy</label>
      <select class="browser-default custom-select">
        <option selected value="PL1">PL1</option>
        <option value="PL2">PL2</option>
      </select>
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
      <label for="amount">Amount People</label>
      <input type="text" id="address" class="form-control" required />
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
      <br />
      <div class="input-group">
        <div class="input-group-prepend">
          <span class="input-group-text" id="inputGroupFileAddon01"
            >Upload</span
          >
        </div>
        <div class="custom-file">
          <input
            type="file"
            class="custom-file-input"
            id="inputGroupFile01"
            aria-describedby="inputGroupFileAddon01"
          />
          <label class="custom-file-label" for="inputGroupFile01"
            >Choose file</label
          >
        </div>
      </div>
      <br />
      <div class="input-group">
        <div class="input-group-prepend">
          <span class="input-group-text" id="inputGroupFileAddon01"
            >Upload</span
          >
        </div>
        <div class="custom-file">
          <input
            type="file"
            class="custom-file-input"
            id="inputGroupFile01"
            aria-describedby="inputGroupFileAddon01"
          />
          <label class="custom-file-label" for="inputGroupFile01"
            >Choose file</label
          >
        </div>
      </div>
      <br />
      <div class="input-group">
        <div class="input-group-prepend">
          <span class="input-group-text" id="inputGroupFileAddon01"
            >Upload</span
          >
        </div>
        <div class="custom-file">
          <input
            type="file"
            class="custom-file-input"
            id="inputGroupFile01"
            aria-describedby="inputGroupFileAddon01"
          />
          <label class="custom-file-label" for="inputGroupFile01"
            >Choose file</label
          >
        </div>
      </div>
    </div>
    <button type="submit" class="btn btn-primary btn-block mb-4">
      Add New Room
    </button>
  </form>
</template>

<script>
export default {
  data() {
    return {
      imageUrl: '',
      image: null,
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
  },
};
</script>

<style scoped>
h3 {
  text-align: center;
}
</style>
