<template>
  <div>
    <b-table bordered hover :items="items" :fields="fields"></b-table>
    <img src="http://localhost:9090/login/sid/?imgId=a(1)" alt="" />
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
          key: 'last_name',
          sortable: true,
        },
        {
          key: 'first_name',
          sortable: false,
        },
        {
          key: 'age',
          label: 'Person age',
          sortable: true,
          // Variant applies to the whole column, including the header and footer
          variant: 'danger',
        },
      ],
      items: [
        {
          isActive: false,
          age: 40,
          first_name: 'Dickerson',
          last_name: 'Macdonald',
        },
        { isActive: false, age: 21, first_name: 'Larsen', last_name: 'Shaw' },
        { isActive: false, age: 89, first_name: 'Geneva', last_name: 'Wilson' },
        { isActive: true, age: 38, first_name: 'Jami', last_name: 'Carney' },
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
