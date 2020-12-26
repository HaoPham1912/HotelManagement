package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.Request.BookRoomDTO;
import com.h2.hotelmangement.common.util.ModelMapperUtil;
import com.h2.hotelmangement.entity.Room;
import com.h2.hotelmangement.model.dto.RoomDTO;
import com.h2.hotelmangement.repository.BookingRepository;
import com.h2.hotelmangement.repository.RoomRepository;
import com.h2.hotelmangement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Override
    public List<Room> findAllRoom() {
        return roomRepository.findAll();
    }

    @Override
    public void save(Room room) {
        roomRepository.save(room);
    }

    @Override
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.getOne(id);
    }

    @Override
    public Room getRoomByRoomCode(String roomCode) {
        return roomRepository.findRoomByRoomCode(roomCode);
    }

    @Override
    public Optional<Set<RoomDTO>> getListAllRoomAvailable(BookRoomDTO bookRoomDTO) {
        Optional<Set<Room>> listRoomCheck = bookingRepository.findRoomAvailableTime(bookRoomDTO.getCheckIn(),bookRoomDTO.getCheckOut());
        if(listRoomCheck.isPresent()){
            Set<Long> listRoomId = listRoomCheck.get().stream().map(r -> r.getRoomId()).collect(Collectors.toSet());
            Optional<Set<Room>> listRoomAvailable = roomRepository.findRoomAvailable(listRoomId,bookRoomDTO.getBranch().getBranchCode());
            return Optional.ofNullable(ModelMapperUtil.mapAllToSet(listRoomAvailable.get(),RoomDTO.class));
        }
        Optional<Set<Room>> listRoomInBranch = roomRepository.findRoomByRoomBranch(bookRoomDTO.getBranch().getBranchCode());
        return Optional.ofNullable(ModelMapperUtil.mapAllToSet(listRoomInBranch.get(),RoomDTO.class));
    }

}
