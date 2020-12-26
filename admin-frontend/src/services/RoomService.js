import http from '../http-common';

class RoomService{
    getAll(params){
        return http.get("/room",{params});
    }
}

export default new RoomService();