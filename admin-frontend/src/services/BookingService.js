import http from '../http-common';

class BookingService{
    getAll(params){
        return http.get("/booking", {params});
    }
}

export default new BookingService();