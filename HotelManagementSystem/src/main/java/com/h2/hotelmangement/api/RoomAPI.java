package com.h2.hotelmangement.api;


import com.h2.hotelmangement.Request.BookRoomDTO;
import com.h2.hotelmangement.entity.*;
import com.h2.hotelmangement.model.dto.HandleListDTO;
import com.h2.hotelmangement.model.dto.RoomDTO;
import com.h2.hotelmangement.model.mapper.RoomMapper;
import com.h2.hotelmangement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;

@RestController
@RequestMapping(PREFIX_API)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoomAPI {

    @Autowired
    private RoomService roomService;

    @Autowired
    private CancelPolicyService cancelPolicyService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private ServiceHotel serviceHotelService;

    @Autowired
    private BedService bedService;

    public RoomMapper roomMapper = new RoomMapper();


    @GetMapping("room")
    public ResponseEntity<Map<String, Object>> getListRoom(@RequestParam(required = false) String roomCode,
                                                           @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                           @RequestParam(value = "size", defaultValue = "3") int size) {
        try {
            List<Room> roomList = new ArrayList<>();
            Page<Room> roomPage;
            if (roomCode == null) {
                roomPage = roomService.getPageRoom(pageNo, size);
            } else {
                roomPage = roomService.getPageRoomByCode(roomCode, pageNo, size);
            }
            roomList = roomPage.getContent();
            List<RoomDTO> roomDTOList = roomMapper.convertListRoomEntityToDto(roomList);
            Map<String, Object> response = new HashMap<>();
            response.put("rooms", roomDTOList);
            response.put("currentPage", roomPage.getNumber());
            response.put("totalItems", roomPage.getTotalElements());
            response.put("totalPages", roomPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("room/{code}")
    public ResponseEntity<RoomDTO> getRoomByCode(@PathVariable(value = "code") String roomCode) {

        Room room = roomService.getRoomByRoomCode(roomCode);
        //RoomDTO roomDTO = new RoomDTO();
        RoomDTO roomDTO = roomMapper.roomEntityToDto(room);
        System.out.println(room.toString());

        return new ResponseEntity<>(roomDTO, HttpStatus.OK);
    }

    @GetMapping("room/book")
    public ResponseEntity<?> getAllRoomAvailable(@RequestBody BookRoomDTO bookRoomDTO) {
        Optional<Set<RoomDTO>> listRoom = roomService.getListAllRoomAvailable(bookRoomDTO);
        return new ResponseEntity<>(listRoom, HttpStatus.OK);
    }

    @PostMapping("/room")
    public ResponseEntity<HttpStatus> addNewRoom(@RequestBody RoomDTO roomDTO) {
        try {
            Room room = roomMapper.convertRoomDtoToEntity(roomDTO);
            CancelPolicy cancelPolicy = cancelPolicyService.getCancelPolicyByCode(roomDTO.getPolicyCode());
            room.setCancelPolicy(cancelPolicy);
            Branch branch = branchService.getBranchByBranchCode(roomDTO.getBranchCode());
            room.setRoomBranch(branch);
            room.setStatus(true);
            roomService.save(room);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/current-room/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable String id) {
        Long roomId = Long.valueOf(id);

        Room room = roomService.getRoomById(roomId);

        if (room != null) {
            RoomDTO roomDTO = roomMapper.roomEntityToDto(room);

            return new ResponseEntity<>(roomDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/room/{id}")
    public void updateRoom(@RequestBody RoomDTO roomDTO, @PathVariable String id) {
        Long roomId = Long.valueOf(id);
        Room existRoom = roomService.getRoomById(roomId);
        if (existRoom != null) {
            existRoom.setAmountPeople(Integer.parseInt(roomDTO.getAmmountPeople()));
            System.out.println(roomDTO.getPolicyCode());
            existRoom.setName(roomDTO.getName());
            existRoom.setPrice(roomDTO.getPrice());
            existRoom.setMainImage(roomDTO.getMainImage());
            existRoom.setDescription(roomDTO.getDescription());
            CancelPolicy cancelPolicy = cancelPolicyService.getCancelPolicyByCode(roomDTO.getPolicyCode());
            existRoom.setCancelPolicy(cancelPolicy);
            Branch branch = branchService.getBranchByBranchCode(roomDTO.getBranchCode());
            existRoom.setRoomBranch(branch);
            roomService.save(existRoom);
        }
    }

    @DeleteMapping("/room/{id}")
    public ResponseEntity<HttpStatus> disableRoom(@PathVariable String id) {
        Long roomId = Long.valueOf(id);
        Room room = roomService.getRoomById(roomId);
        if (room != null) {
            Boolean roomStatus = room.getStatus();
            room.setStatus(!roomStatus);
            roomService.save(room);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/room-service/{roomCode}")
    public ResponseEntity<HttpStatus> addServiceToRoom(@PathVariable String roomCode, @RequestBody HandleListDTO handleListDTO) {
        List<Long> idServiceList = handleListDTO.getLongListIdService();
        System.out.println("I''m here");
        Room room = roomService.getRoomByRoomCode(roomCode);
        Set<Services> servicesSet = new HashSet<>();
        for (Long id :
                idServiceList) {
            Services services = serviceHotelService.getServicesById(id);
            servicesSet.add(services);
            Set<Room> roomSet = services.getRooms();
            roomSet.add(room);
            services.setRooms(roomSet);
            serviceHotelService.save(services);
        }
        if (room != null) {
            room.setServices(servicesSet);
            roomService.save(room);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/room-bed/{roomCode}")
    public ResponseEntity<HttpStatus> addBedToRoom(@PathVariable String roomCode, @RequestBody HandleListDTO handleListDTO) {
        List<Long> idBedList = handleListDTO.getLongListIdBed();
        Set<Bed> bedSet = new HashSet<>();
        Room room = roomService.getRoomByRoomCode(roomCode);
        for (Long id :
                idBedList) {
            Bed bed = bedService.getBedById(id);
            bedSet.add(bed);
            Set<Room> roomSet = bed.getRooms();
            roomSet.add(room);
            bed.setRooms(roomSet);
            bedService.save(bed);
        }

        if (room != null) {
            room.setBedSet(bedSet);
            roomService.save(room);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/room-bed/{roomCode}/{bedId}")
    public ResponseEntity<HttpStatus> removeBedOutRoom(@PathVariable String roomCode, @PathVariable Long bedId) {

        Room room = roomService.getRoomByRoomCode(roomCode);
        Bed bed = bedService.getBedById(bedId);
        try {
            if (room != null) {
                Set<Bed> bedSet = room.getBedSet();
                bedSet.remove(bed);
                room.setBedSet(bedSet);
                roomService.save(room);
            } else {
                throw new Exception("Can not find room by " + roomCode);
            }
            if (bed != null) {
                Set<Room> roomSet = bed.getRooms();
                roomSet.remove(room);
                bed.setRooms(roomSet);
                bedService.save(bed);
            }else {
                throw new Exception("Can not find bed by "+ bedId);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/room-service/{roomCode}/{serviceId}")
    public ResponseEntity<HttpStatus> removeServiceOutRoom(@PathVariable String roomCode,   @PathVariable Long serviceId) {
        Room room = roomService.getRoomByRoomCode(roomCode);
        Services services = serviceHotelService.getServicesById(serviceId);
        try {
            if (room != null) {
                Set<Services> servicesSet = room.getServices();
                servicesSet.remove(services);
                room.setServices(servicesSet);
                roomService.save(room);
            } else {
                throw new Exception("Can not find room by " + roomCode);
            }
            if (services != null) {
                Set<Room> roomSet = services.getRooms();
                roomSet.remove(room);
                services.setRooms(roomSet);
                serviceHotelService.save(services);
            }else {
                throw new Exception("Can not find service by "+ serviceId);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
