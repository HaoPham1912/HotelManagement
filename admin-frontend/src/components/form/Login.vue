<template>
  <!-- Default form login -->
  <div>
    <div class="login-form">
      <!-- /<div class="img-login"><img src="../../assets/h2logo.png" alt="" /></div> -->
      <form
        class="text-center border border-light p-5"
        @submit.prevent="handleLogin"
      >
        <p class="h4 mb-4">Sign in</p>

        <!-- Email -->
        <input
          v-model="user.username"
          v-validate="'required'"
          type="text"
          id="username"
          name="username"
          class="form-control mb-4"
          placeholder="User Name"
        />

        <!-- Password -->
        <input
          v-model="user.password"
          v-validate="'required'"
          type="password"
          id="password"
          name="password"
          class="form-control mb-4"
          placeholder="Password"
        />

        <!-- Sign in button -->
        <button
          class="btn btn-info btn-block my-4"
          type="submit"
          :disabled="loading"
        >
          <span
            v-show="loading"
            class="spinner-border spinner-border-sm"
          ></span>
          <span>Login</span>
        </button>

        <!-- Register -->
      </form>
    </div>
    <!-- Default form login -->
  </div>
</template>
<script>
import User from '../../model/user';
export default {
  name: 'Login',
  data() {
    return {
      user: new User('', ''),
      loading: false,
      message: '',
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  created() {
    if (this.loggedIn) {
      this.$router.push('/');
    }
  },
  methods: {
    handleLogin() {
      this.loading = true;
      this.$validation.validateAll().then((isValid) => {
        if (!isValid) {
          this.loading = false;
          return;
        }
        if (this.user.username && this.user.password) {
          this.$store.dispatch('login', this.user).then(
            () => {
              this.$router.push('/');
            },
            (error) => {
              this.loading = false;
              this.message =
                (error.response && error.response.data) ||
                error.message ||
                error.toString();
            }
          );
        }
      });
    },
  },
};
</script>
<style scoped>
.login-form {
  margin: auto;

  margin-top: 200px;
  width: 25%;

  border: 5px solid lightskyblue;

  padding: 10px;
}
</style>
