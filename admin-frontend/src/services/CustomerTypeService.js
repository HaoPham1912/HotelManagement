import http from '../http-common';

class CustomerTypeService{
    getAll(){
        return http.get("/api/custype");
    }
}

export default new CustomerTypeService();