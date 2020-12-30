import http from '../http-common';

class CancelPolicyService{
    getAll(){
        return http.get("/policy");
    }

    create(data){
        return http.post("/policy", data);
    }

    update(id, data){
        return http.put(`/policy/${id}`, data);
    }

    getById(id){
        return http.get(`/policy/${id}`);
    }
}

export default new CancelPolicyService();