import http from '../http-common';


class CustomerService{
    getAll(){
        return http.get("/customer");
    }
}

export default new CustomerService();