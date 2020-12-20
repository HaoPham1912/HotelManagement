import http from '../http-common';

class CancelPolicyService{
    getAll(){
        return http.get("/policy");
    }

    create(data){
        return http.post("/policy", data);
    }
}

export default new CancelPolicyService();