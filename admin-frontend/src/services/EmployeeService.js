import http from '../http-common';

class EmployeeService{
    getAll(params){
        return http.get("/employee", {params});
    }

    getEmployeeById(id){
        return http.get(`/employee/${id}`);
    }

    create(data){
        return http.post("/employee", data);
    }

    updateInfo(id, data){
        return http.put(`/employee/${id}`, data)
    }

    disableEmployee(id){
        return http.delete(`/employee/${id}`);
    }

    getAllNoPaging(){
        return http.get("all-emp");
    }
}

export default new EmployeeService();