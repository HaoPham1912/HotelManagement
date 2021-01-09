package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.Request.BookRoomDTO;
import com.h2.hotelmangement.common.util.ModelMapperUtil;
import com.h2.hotelmangement.entity.Room;
import com.h2.hotelmangement.model.dto.RoomDTO;
import com.h2.hotelmangement.repository.BookingRepository;
import com.h2.hotelmangement.repository.RoomRepository;
import com.h2.hotelmangement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public Optional<Set<RoomDTO>> getListAllRoomAvailable(BookRoomDTO bookRoomDTO){
        Date dateCheckIn = new Date(bookRoomDTO.getCheckIn());
        Date dateCheckOut = new Date(bookRoomDTO.getCheckOut());
        Set<Room> listRoomCheck = bookingRepository.findRoomAvailableTime(dateCheckIn,dateCheckOut);
        if(CollectionUtils.isEmpty(listRoomCheck)){
            Set<Long> listRoomId = listRoomCheck.stream().map(r -> r.getRoomId()).collect(Collectors.toSet());
            Set<Room> listRoomAvailable = roomRepository.findRoomAvailable(listRoomId,bookRoomDTO.getBranchCode());
            return Optional.ofNullable(ModelMapperUtil.mapAllToSet(listRoomAvailable,RoomDTO.class));
        }
        Set<Room> listRoomInBranch = roomRepository.findRoomByRoomBranch_BranchCode(bookRoomDTO.getBranchCode());
        return Optional.ofNullable(ModelMapperUtil.mapAllToSet(listRoomInBranch,RoomDTO.class));
    }


    public Page<Room> getPageRoom(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return roomRepository.findAll(pageable);
    }

    @Override
    public Page<Room> getPageRoomByCode(String roomCode, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return roomRepository.findAllByRoomCodeContains(roomCode,pageable);
    }

    @Override
    public Page<Room> getListAllRoomAvailable2(BookRoomDTO bookRoomDTO) throws ParseException {
        Date dateCheckIn = new Date(bookRoomDTO.getCheckIn());
        Date dateCheckOut = new Date(bookRoomDTO.getCheckOut());
        Pageable pageable = PageRequest.of(bookRoomDTO.getPageNo(), bookRoomDTO.getPageSize());
        Set<Room> listRoomCheck = bookingRepository.findRoomAvailableTime(dateCheckIn,dateCheckOut);
        if(CollectionUtils.isEmpty(listRoomCheck)){
            Set<Long> listRoomId = listRoomCheck.stream().map(r -> r.getRoomId()).collect(Collectors.toSet());
           return roomRepository.findRoomAvailable2(listRoomId,bookRoomDTO.getBranchCode(),pageable);
        }
        return roomRepository.findRoomByRoomBranch_BranchCode(bookRoomDTO.getBranchCode(),pageable);
    }

}
