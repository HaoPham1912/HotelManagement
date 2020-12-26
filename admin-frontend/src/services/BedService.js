import http from '../http-common';

class BedService{
    getAll(params){
        return http.get("/bed",{params});
    }
}

export default new BedService();