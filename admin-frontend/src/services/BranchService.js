import http from '../http-common';

class BranchService{
    getAll(){
        return http.get("/branch");
    }

    getBranchByCode(branchCode){
        return http.get(`/branch/${branchCode}`);
    }

    createNewBranch(data){
        return http.post("/branch", data);
    }
}

export default new BranchService();