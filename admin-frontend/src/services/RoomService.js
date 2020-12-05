import http from '../http-common';

class RoomService{
    getAll(){
        return http.get("/room");
    }
}

export default new RoomService();