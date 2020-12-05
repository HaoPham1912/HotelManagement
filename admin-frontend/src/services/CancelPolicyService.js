import http from '../http-common';

class CancelPolicyService{
    getAll(){
        return http.get("/policy");
    }
}

export default new CancelPolicyService();