import http from '../http-common';
class CustomerService{
    getAll(){
        return http.get("/customer");
    }

    getCustomerById(id){
        return http.get(`customer/${id}`);
    }
}

export default new CustomerService();