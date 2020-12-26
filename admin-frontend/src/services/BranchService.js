import http from '../http-common';

class BranchService{
    getAll(params){
        return http.get("/branch",{params});
    }

    getBranchByCode(branchCode){
        return http.get(`/branch/${branchCode}`);
    }

    createNewBranch(data){
        return http.post("/branch", data);
    }
}

export default new BranchService();