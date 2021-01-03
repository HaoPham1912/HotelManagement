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

    getBranchById(id){
        return http.get(`/current-branch/${id}`);
    }

    createNewBranch(data){
        return http.post("/branch", data);
    }

    updateBranchInfo(id, data){
        return http.put(`/branch/${id}`, data);
    }

    disableBranch(id){
        return http.delete(`/branch/${id}`);
    }
}

export default new BranchService();