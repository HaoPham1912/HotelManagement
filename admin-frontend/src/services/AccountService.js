import http from '../http-common';

class AccountService{
    getAll(params){
        return http.get("/account", {params});
    }
    disableAccount(id){
        return http.delete(`/account/${id}`);
    }

    getById(id){
        return http.get(`/account/${id}`);
    }

    updateAccount(id, data){
        return http.put(`/account/${id}`, data);
    }
}

export default new AccountService();