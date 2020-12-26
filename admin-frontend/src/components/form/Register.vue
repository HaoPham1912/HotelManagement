<template>
  <div class="register-form">
    <!-- Default form register -->
    <form class="text-center border border-light p-5" action="#!">
      <p class="h4 mb-4">Sign up</p>

      <div class="form-row mb-4">
        <!-- Last name -->
        <input
          type="text"
          id="defaultRegisterFormLastName"
          class="form-control"
          placeholder="Username"
        />
      </div>

      <!-- E-mail -->
      <input
        type="email"
        id="defaultRegisterFormEmail"
        class="form-control mb-4"
        placeholder="E-mail"
      />

      <!-- Password -->
      <input
        type="password"
        id="defaultRegisterFormPassword"
        class="form-control"
        placeholder="Password"
        aria-describedby="defaultRegisterFormPasswordHelpBlock"
      />
      <small
        id="defaultRegisterFormPasswordHelpBlock"
        class="form-text text-muted mb-4"
      >
        At least 8 characters and 1 digit
      </small>

      <!-- Phone number -->
      <input
        type="text"
        id="defaultRegisterPhonePassword"
        class="form-control"
        placeholder="Confirm Password"
        aria-describedby="defaultRegisterFormPhoneHelpBlock"
      />

      <!-- Sign up button -->
      <button class="btn btn-info my-4 btn-block" type="submit">Sign Up</button>
      <a href="/login">Login</a>
      <!-- Social register -->
    </form>

    <!-- Default form register -->
  </div>
</template>

<style>
.register-form {
  margin: auto;

  margin-top: 100px;
  width: 30%;

  border: 5px solid lightskyblue;

  padding: 10px;
}
</style>
