import http from '../http-common';
class CustomerService{
    getAll(params){
        return http.get("/api/customer",{params});
    }

    getCustomerById(id){
        return http.get(`/api/customer/${id}`);
    }
    disableCustomer(id){
        return http.delete(`/api/customer/${id}`);
    }

    upgradeCustomer(id, data){
        return http.put(`/api/customer/${id}`, data);
    }
}

export default new CustomerService();