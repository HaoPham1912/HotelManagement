import http from '../http-common';

class ServicesService{
    getAll(){
        return http.get("/services");
    }
}

export default new ServicesService();