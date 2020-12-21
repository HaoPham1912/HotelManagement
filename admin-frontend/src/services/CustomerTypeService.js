import http from '../http-common';

class CustomerTypeService{
    getAll(){
        return http.get("/custype");
    }
}

export default new CustomerTypeService();