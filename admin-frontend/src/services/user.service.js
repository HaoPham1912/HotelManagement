import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:9090/api/';

class UserService{
    getAdminPage(){
        return axios.get(API_URL + 'admin',
        {
            headers: authHeader()
        });
    }

    getEmployeePage(){
        return axios.get(API_URL +'emp',{
            headers: authHeader()
        });
    }
}

export default new UserService();