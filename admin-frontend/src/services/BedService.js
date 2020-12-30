import http from '../http-common';

class BedService{
    getAll(params){
        return http.get("/bed",{params});
    }

    get(id){
        return http.get(`bed/${id}`);
    }

    update(id, data){
        return http.put(`bed/${id}`,data);
    }

    delete(id){
        return http.delete(`bed/${id}`);
    }
}

export default new BedService();