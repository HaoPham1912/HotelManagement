import http from '../http-common';

class BranchService{
    getAll(params){
        return http.get("/api/branch",{params});
    }

    getAllBranchInfor(){
        return http.get("/api/all-branch");
    }

    getBranchByCode(branchCode){
        return http.get(`/api/detail-branch/${branchCode}`);
    }

    getBranchById(id){
        return http.get(`/api/current-branch/${id}`);
    }

    createNewBranch(data){
        return http.post("/api/branch", data);
    }

    updateBranchInfo(id, data){
        return http.put(`/api/branch/${id}`, data);
    }

    disableBranch(id){
        return http.delete(`/api/branch/${id}`);
    }
}

export default new BranchService();