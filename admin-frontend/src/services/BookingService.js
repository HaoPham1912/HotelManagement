import http from '../http-common';

class BookingService{
    getAll(params){
        return http.get("/booking", {params});
    }

    getDetailBooking(billId, roomId){
        return http.get(`/detail-booking/${billId}/${roomId}`);
    }
    deleteBooking(billId, roomId){
        return http.delete(`/booking/${billId}/${roomId}`);
    }
}

export default new BookingService();