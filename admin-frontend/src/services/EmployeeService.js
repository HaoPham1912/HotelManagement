import http from '../http-common';

class EmployeeService{
    getAll(){
        return http.get("/employee");
    }

    create(data){
        return http.post("/employee", data);
    }
}

export default new EmployeeService();