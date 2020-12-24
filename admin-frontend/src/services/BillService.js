import http from '../http-common';

class BillService{
    getAll(){
        return http.get("/bill");
    }
}
export default new BillService();