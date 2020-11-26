import http from '../http-common';

class AccountService{
    getAll(){
        return http.get("/account");
    }
}

export default new AccountService();