import http from '../http-common';

class AccountService{
    getAll(params){
        return http.get("/api/account", {params});
    }
    disableAccount(id){
        return http.delete(`/api/account/${id}`);
    }

    getById(id){
        return http.get(`/api/account/${id}`);
    }

    updateAccount(id, data){
        return http.put(`/api/account/${id}`, data);
    }

    getCurrentAccount(username){
        return http.get(`/api/currentAccount/${username}`);
    }
}

export default new AccountService();