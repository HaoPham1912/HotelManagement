import http from '../http-common';

class BedService{
    getAll(){
        return http.get("/bed");
    }
}

export default new BedService();