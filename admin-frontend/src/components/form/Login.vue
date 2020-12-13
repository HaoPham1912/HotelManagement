<template>
  <!-- Default form login -->
  <div class="login-form">
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
      <!-- <div class="d-flex justify-content-around">
        <div>
          Remember me
          <div class="custom-control custom-checkbox">
            <input
              type="checkbox"
              class="custom-control-input"
              id="defaultLoginFormRemember"
            />
            <label class="custom-control-label" for="defaultLoginFormRemember"
              >Remember me</label
            >
          </div>
        </div>
        <div>
          <a href="">Forgot password?</a>
        </div>
      </div> -->

      <!-- Sign in button -->
      <button
        class="btn btn-info btn-block my-4"
        type="submit"
        :disabled="loading"
      >
        <span v-show="loading" class="spinner-border spinner-border-sm"></span>
        <span>Login</span>
      </button>

      <!-- Register -->
      <p>
        Not a member?
        <a href="/register">Register</a>
      </p>

      <!-- Social login -->
      <p>or sign in with:</p>

      <a href="#" class="mx-2" role="button"
        ><i class="fab fa-facebook-f light-blue-text"></i
      ></a>
      <a href="#" class="mx-2" role="button"
        ><i class="fab fa-twitter light-blue-text"></i
      ></a>
      <a href="#" class="mx-2" role="button"
        ><i class="fab fa-linkedin-in light-blue-text"></i
      ></a>
      <a href="#" class="mx-2" role="button"
        ><i class="fab fa-github light-blue-text"></i
      ></a>
    </form>
  </div>
  <!-- Default form login -->
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
  width: 30%;

  border: 5px solid lightskyblue;

  padding: 10px;
}
</style>
