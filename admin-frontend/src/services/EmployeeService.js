import http from '../http-common';

class EmployeeService{
    getAll(params){
        return http.get("/api/employee", {params});
    }

    getEmployeeById(id){
        return http.get(`/api/employee/${id}`);
    }

    create(data){
        return http.post("/api/employee", data);
    }

    updateInfo(id, data){
        return http.put(`/api/employee/${id}`, data)
    }

    disableEmployee(id){
        return http.delete(`/api/employee/${id}`);
    }

    getAllNoPaging(){
        return http.get("/api/all-emp");
    }
}

export default new EmployeeService();