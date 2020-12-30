import http from '../http-common';

class ServicesService{
    getAll(params){
        return http.get("/services",{params});
    }

    create(data){
        return http.post("/services", data);
    }

    get(id){
        return http.get(`/services/${id}`);
    }

    delete(id){
        return http.delete(`services/${id}`);
    }
    update(id, data){
        return http.put(`/services/${id}`,data);
    }
}

export default new ServicesService();