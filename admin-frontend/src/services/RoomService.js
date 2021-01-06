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

    addBedToRoom(roomCode,data){
        return http.put(`/api/room-bed/${roomCode}`,data);
    }
    addServiceToRoom(roomCode,data){
        return http.put(`/api/room-service/${roomCode}`,data);
    }

    deleteBedToRoom(roomCode,data){
        return http.delete(`/api/room-bed/${roomCode}`,data);
    }
    
    deleteServiceToRoom(roomCode,data){
        return http.delete(`/api/room-service/${roomCode}`,data);
    }
}

export default new RoomService();