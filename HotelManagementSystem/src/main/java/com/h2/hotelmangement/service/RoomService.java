package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Room;

import java.util.List;

public interface RoomService {

    List<Room> findAllRoom();

    void saveOrUpdate(Room room);

    void delete(Long id);

    Room getRoomById(Long id);
}
