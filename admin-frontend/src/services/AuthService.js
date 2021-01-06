import http from '../http-common';

class AuthService{
    login(user){
        return http.post("/login", user);
    }
}

export default new AuthService();