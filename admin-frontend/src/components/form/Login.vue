<template>
  <!-- Default form login -->
  <div>
    <div style="margin-top: 10px">
      <h1 class="text-center">
        WELCOME TO H2 HOTEL MANAGEMENT SYSTEM
      </h1>
      <img
        class="rounded mx-auto d-block"
        src="../../assets/h2logo.png"
        alt=""
      />
    </div>
    <div class="login-form">
      <!-- /<div class="img-login"><img src="../../assets/h2logo.png" alt="" /></div> -->
      <form
        class="text-center border border-light p-5"
        @submit.prevent="handleLogin"
      >
        <p class="h4 mb-4">Sign in</p>

        <!-- Email -->
        <input
          v-model="userName"
          v-validate="'required'"
          type="text"
          id="username"
          class="form-control mb-4"
          placeholder="User Name"
        />

        <!-- Password -->
        <input
          v-model="pass"
          v-validate="'required'"
          type="password"
          id="password"
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
//import AuthService from '../../services/AuthService';
import { AUTH_REQUEST } from '../../store/actions/auth';
export default {
  name: 'Login',
  data() {
    return {
      userName: '',
      pass: '',
      loading: false,
      message: '',
      loginError: false,
    };
  },
  methods: {
    handleLogin() {
      const { userName, pass } = this;
      this.$store
        .dispatch(AUTH_REQUEST, { userName, pass })
        .then(() => {
          alert('Login success!!!');
          this.$router.push('/redirectHome');
        })
        .catch((error) => {
          if (error.response.status === 401) {
            alert('Username or password is incorrect');
          }
        });
    },
  },
};
</script>
<style scoped>
.login-form {
  margin: auto;

  margin-top: 40px;
  width: 25%;

  border: 5px solid lightskyblue;

  padding: 10px;
}
</style>
