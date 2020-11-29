import http from '../http-common';

class BranchService{
    getAll(){
        return http.get("/branch");
    }
}

export default new BranchService();