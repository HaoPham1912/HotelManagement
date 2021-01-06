import http from '../http-common';

class BookingService{
    getAll(params){
        return http.get("/api/booking", {params});
    }

    getDetailBooking(billId, roomId){
        return http.get(`/api/detail-booking/${billId}/${roomId}`);
    }
    deleteBooking(billId, roomId){
        return http.delete(`/api/booking/${billId}/${roomId}`);
    }
}

export default new BookingService();