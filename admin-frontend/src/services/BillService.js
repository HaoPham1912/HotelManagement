import http from '../http-common';

class BillService{
    getAll(params){
        return http.get("/bill",{params});
    }
}
export default new BillService();