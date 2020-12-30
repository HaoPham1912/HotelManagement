import http from '../http-common';

class BranchService{
    getAll(params){
        return http.get("/branch",{params});
    }

    getAllBranchInfor(){
        return http.get("/all-branch");
    }

    getBranchByCode(branchCode){
        return http.get(`/detail-branch/${branchCode}`);
    }

    createNewBranch(data){
        return http.post("/branch", data);
    }
}

export default new BranchService();