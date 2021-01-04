import http from '../http-common';

class RoomService{
    getAll(params){
        return http.get("/api/room",{params});
    }

    addRoom(data){
        return http.post("/api/room", data);
    }

    updateRoom(id, data){
        return http.put(`/api/room/${id}`,data);
    }

    disableRoom(id){
        return http.delete(`/api/room/${id}`);
    }

    getRoomById(id){
        return http.get(`/api/current-room/${id}`);
    }
    
    getRoomByRoomCode(code){
        return http.get(`/api/room/${code}`);
    }
}

export default new RoomService();