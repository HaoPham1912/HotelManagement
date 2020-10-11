<template>
  <div class="container">
    <div class="row h-100 justify-content-center align-items-center">
      <div class="col-md-6 col-sm-12">
        <div class="login-form card border border-dark">
          <div class="row mx-4 my-4">
            <div class="col-md-4 m-auto">
              <img height="150" width="150" src="\img\h3.png" />
            </div>
            <div class="col-md-8">
              <form>
                <div class="form-group">
                  <p class="h3">Username</p>
                  <input type="text" class="form-control" placeholder="User Name" />
                </div>
                <div class="form-group">
                  <p class="h3">Password</p>
                  <input type="password" class="form-control" placeholder="Password" />
                </div>
                <button @click="$router.push('/admin/room')" type="submit" class="btn btn-black">Login</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.main-head {
  height: 150px;
  background: #fff;
}

.sidenav {
  height: 100%;
  background-color: #000;
  overflow-x: hidden;
  padding-top: 20px;
}

@media screen and (max-height: 450px) {
  .sidenav {
    padding-top: 15px;
  }
}

@media screen and (max-width: 450px) {
  .login-form {
    margin-top: 10%;
  }

  .register-form {
    margin-top: 10%;
  }
}

@media screen and (min-width: 768px) {
  .main {
    margin-left: 40%;
  }

  .sidenav {
    width: 40%;
    position: fixed;
    z-index: 1;
    top: 0;
    left: 0;
  }

  .login-form {
    margin-top: 45%;
  }
}

.login-main-text {
  margin-top: 20%;
  padding: 60px;
  color: #fff;
}

.login-main-text h2 {
  font-weight: 300;
}

.btn-black {
  background-color: #000 !important;
  color: #fff;
}
</style>