import http from '../http-common';

class EmployeeService{
    getAll(){
        return http.get("/employee");
    }
}

export default new EmployeeService();