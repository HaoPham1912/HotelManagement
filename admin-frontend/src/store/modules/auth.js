import {
    AUTH_REQUEST,
    AUTH_ERROR,
    AUTH_SUCCESS,
    AUTH_LOGOUT
  } from "../actions/auth";

  import AuthService from '../../services/AuthService';
  import AccountService from "../../services/AccountService";
  const state = {
    token: localStorage.getItem("user-token") || "",
    status: "",
    hasLoadedOnce: false,
    currentRole:localStorage.getItem("currentRole") || "",
    username: localStorage.getItem("username") || "",
  };

  const getters = {
    isAuthenticated: state => !!state.token,
    authStatus: state => state.status,
    currentRole: state => state.currentRole,
    username: state=>state.username
  };

  const actions = {
    [AUTH_REQUEST]: ({ commit}, user) => {
      return new Promise((resolve, reject) => {
        commit(AUTH_REQUEST);
        AuthService.login(user)
          .then(resp => {
            //console.log(resp.data);
            localStorage.setItem("user-token", resp.data.data.accessToken);
            
            let username = resp.data.data.user;
            localStorage.setItem("username", username);

            AccountService.getCurrentAccount(username).then((response) =>{
              //console.log(response.data.roleName);
              localStorage.setItem("currentRole",response.data.roleName.toString());
            })
            // Here set the header of your ajax library to the token value.
            // example with axios
            //axios.defaults.headers.common['Authorization'] = 'Bearer '+ resp.data.data.accessToken;
            commit(AUTH_SUCCESS, resp);
            // dispatch(USER_REQUEST);
            resolve(resp);
          })
          .catch(err => {
            commit(AUTH_ERROR, err);
            localStorage.removeItem("user-token");
            reject(err);
          });
      });
    },
    [AUTH_LOGOUT]: ({ commit }) => {
      return new Promise(resolve => {
        commit(AUTH_LOGOUT);
        localStorage.removeItem("user-token");
        localStorage.removeItem("username");
        localStorage.removeItem("currentRole");
      
        //delete axios.defaults.headers.common['Authorization'];
        resolve();
      });
    }
  };
  
  const mutations = {
    [AUTH_REQUEST]: state => {
      state.status = "loading";
    },
    [AUTH_SUCCESS]: (state, resp) => {
      state.status = "success";
      state.token = resp.token;
      state.hasLoadedOnce = true;
    },
    [AUTH_ERROR]: state => {
      state.status = "error";
      state.hasLoadedOnce = true;
    },
    [AUTH_LOGOUT]: state => {
      state.token = "";
    }
  };

  export default {
    state,
    getters,
    actions,
    mutations
  };