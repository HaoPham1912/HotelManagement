import http from '../http-common';

class CancelPolicyService{
    getAll(){
        return http.get("/api/policy");
    }

    create(data){
        return http.post("/api/policy", data);
    }

    update(id, data){
        return http.put(`/api/policy/${id}`, data);
    }

    getById(id){
        return http.get(`/api/policy/${id}`);
    }
}

export default new CancelPolicyService();