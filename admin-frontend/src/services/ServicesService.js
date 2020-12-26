import http from '../http-common';

class ServicesService{
    getAll(params){
        return http.get("/services",{params});
    }

    create(data){
        return http.post("/services", data);
    }

    get(code){
        return http.get(`/services/${code}`);
    }

    delete(id){
        return http.delete(`services/${id}`);
    }
}

export default new ServicesService();