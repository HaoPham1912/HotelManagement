import axios from 'axios';

class AuthService{
    login(user){
        return axios.post("http://localhost:9090/login", user);
    }
}

export default new AuthService();