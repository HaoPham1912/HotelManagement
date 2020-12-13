import http from '../http-common';

class BranchService{
    getAll(){
        return http.get("/branch");
    }

    getBranchByCode(branchCode){
        return http.get(`/branch/${branchCode}`);
    }
}

export default new BranchService();