import http from '../http-common';

class BedService{
    getAll(params){
        return http.get("/api/bed",{params});
    }

    get(id){
        return http.get(`/api/bed/${id}`);
    }

    update(id, data){
        return http.put(`/api/bed/${id}`,data);
    }

    delete(id){
        return http.delete(`/api/bed/${id}`);
    }

    getAllBedNoPaging(){
        return http.get("/api/all-bed");
    }
}

export default new BedService();