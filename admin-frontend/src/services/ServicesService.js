import http from '../http-common';

class ServicesService{
    getAll(params){
        return http.get("/api/services",{params});
    }

    create(data){
        return http.post("/api/services", data);
    }

    get(id){
        return http.get(`/api/services/${id}`);
    }

    delete(id){
        return http.delete(`/api/services/${id}`);
    }
    update(id, data){
        return http.put(`/api/services/${id}`,data);
    }

    getAllNoPaging(){
        return http.get("/api/all-service");
    }
}

export default new ServicesService();