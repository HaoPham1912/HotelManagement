import http from '../http-common';

class AccountService{
    getAll(){
        return http.get("/account");
    }
    disableAccount(id,data){
        return http.put(`/account/${id}`, data);
    }
}

export default new AccountService();