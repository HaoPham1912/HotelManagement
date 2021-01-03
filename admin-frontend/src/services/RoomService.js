import http from '../http-common';

class RoomService{
    getAll(params){
        return http.get("/room",{params});
    }

    addRoom(data){
        return http.post("/room", data);
    }

    updateRoom(id, data){
        return http.put(`/room/${id}`,data);
    }

    disableRoom(id){
        return http.delete(`/room/${id}`);
    }

    getRoomById(id){
        return http.get(`/current-room/${id}`);
    }
}

export default new RoomService();