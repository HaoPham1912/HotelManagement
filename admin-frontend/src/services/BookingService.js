import http from '../http-common';

class BookingService{
    getAll(){
        return http.get("/booking");
    }
}

export default new BookingService();