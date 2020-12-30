import http from '../http-common';
class CustomerService{
    getAll(params){
        return http.get("/customer",{params});
    }

    getCustomerById(id){
        return http.get(`customer/${id}`);
    }
    disableCustomer(id){
        return http.delete(`/customer/${id}`);
    }
}

export default new CustomerService();