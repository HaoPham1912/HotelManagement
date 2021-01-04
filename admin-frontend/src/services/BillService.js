import http from '../http-common';

class BillService{
    getAll(params){
        return http.get("/api/bill",{params});
    }

    getBookingByBillId(id){
        return http.get(`/api/bill/${id}`);
    }

    updateBill(id){
        return http.put(`/api/bill/${id}`);
    }
}
export default new BillService();