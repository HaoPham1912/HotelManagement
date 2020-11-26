<template>
  <main>
    <div class="mt-5 p-5">
      <router-view></router-view>
    </div>
    <!-- <ftr color="primary-color-dark" class="text-center font-small darken-2">
      <hr class="my4" />
      <div class="pb-4">
        <a href="#"><mdb-icon fab icon="facebook-square" class="mr-3"/></a>
        <a href="#"><mdb-icon fab icon="youtube" class="mr-3"/></a>
        <a href="#"><mdb-icon fab icon="google" class="mr-3"/></a>
      </div>
      <p class="footer-copyright mb-0 py-3 text-center">
        &copy; {{ new Date().getFullYear() }} Copyright:
        <a href=""> H2 Hotel</a>
      </p>
    </ftr> -->
  </main>
</template>
<script>
//import { mdbIcon, mdbFooter } from 'mdbvue';
export default {
  components: {
    // /mdbIcon,
    //ftr: mdbFooter,
  },
};
</script>
<style scoped>
main {
  background-color: whitesmoke;
}
</style>
