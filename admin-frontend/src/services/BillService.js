import http from '../http-common';

class BillService{
    getAll(params){
        return http.get("/bill",{params});
    }

    getBookingByBillId(id){
        return http.get(`/bill/${id}`);
    }
}
export default new BillService();