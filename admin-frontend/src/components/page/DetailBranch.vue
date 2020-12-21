<template>
  <div>
    <h3>Employee Of Branch</h3>
    <b-table bordered hover :items="items" :fields="fields"></b-table>
    <h3>Image Of Branch</h3>
    <div class="img-branch">
      <img
        src="https://firebasestorage.googleapis.com/v0/b/h2hotel-7b674.appspot.com/o/image-branch%2Fhinhtest.jpg?alt=media&token=ab04cbf5-315f-4aad-94b0-f5ee9e76bd28"
        alt=""
      />
    </div>
    <div class="img-branch">
      <img
        src="https://firebasestorage.googleapis.com/v0/b/h2hotel-7b674.appspot.com/o/image-branch%2Fhinhtest.jpg?alt=media&token=ab04cbf5-315f-4aad-94b0-f5ee9e76bd28"
        alt=""
      />
    </div>
    <div class="img-branch">
      <img
        src="https://firebasestorage.googleapis.com/v0/b/h2hotel-7b674.appspot.com/o/image-branch%2Fhinhtest.jpg?alt=media&token=ab04cbf5-315f-4aad-94b0-f5ee9e76bd28"
        alt=""
      />
    </div>
    <div class="img-branch">
      <img
        src="https://firebasestorage.googleapis.com/v0/b/h2hotel-7b674.appspot.com/o/image-branch%2Fhinhtest.jpg?alt=media&token=ab04cbf5-315f-4aad-94b0-f5ee9e76bd28"
        alt=""
      />
    </div>
  </div>
</template>

<script>
//import BranchService from '../../services/BranchService';
export default {
  name: 'branch',
  data() {
    return {
      branchCode: '',
      fields: [
        {
          key: 'employeeCode',
          label: 'Employee Code',
          sortable: true,
        },
        {
          key: 'empIdCard',
          label: 'Id Card',
          sortable: false,
        },
        {
          key: 'empName',
          label: 'Employee Name',
          sortable: true,
          // Variant applies to the whole column, including the header and footer
          // /variant: 'danger',
        },
        {
          key: 'empPhone',
          label: 'Employee Phone',
          sortable: false,
          // Variant applies to the whole column, including the header and footer
          // /variant: 'danger',
        },
        {
          key: 'email',
          label: 'Employee Email',
          sortable: false,
          // Variant applies to the whole column, including the header and footer
          // /variant: 'danger',
        },
      ],
      items: [
        {
          isActive: false,
          employeeCode: 'EMP1',
          empIdCard: '123123123',
          empName: 'Macdonald',
          empPhone: '090909090909',
          email: 'emp1@gmail.com',
        },
        {
          isActive: false,
          employeeCode: 'EMP2',
          empIdCard: '213123123',
          empName: 'AScdonald2',
          empPhone: '1231231231',
          email: 'emp2@gmail.com',
        },
      ],
    };
  },
  methods: {
    getBranch(branchCode) {
      this.branchCode = branchCode;
    },
  },

  mounted() {
    this.getBranch(this.$route.params.branchCode);
  },
};
</script>

<style scoped>
img {
  width: 300px;
  height: 150px;
}

.img-branch {
  margin: 10px;
}
</style>
