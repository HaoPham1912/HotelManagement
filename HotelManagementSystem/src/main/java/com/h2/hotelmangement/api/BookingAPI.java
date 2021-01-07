package com.h2.hotelmangement.api;

import com.h2.hotelmangement.Request.ResponseHistoryBookDTO;
import com.h2.hotelmangement.entity.Bill;
import com.h2.hotelmangement.entity.Booking;
import com.h2.hotelmangement.entity.Customer;
import com.h2.hotelmangement.entity.Room;
import com.h2.hotelmangement.model.dto.BookingDTO;
import com.h2.hotelmangement.model.dto.DetailsBillDTO;
import com.h2.hotelmangement.model.mapper.BookingMapper;
import com.h2.hotelmangement.repository.BookingRepository;
import com.h2.hotelmangement.service.BillService;
import com.h2.hotelmangement.service.BookingService;
import com.h2.hotelmangement.service.RoomService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;

@RestController
@RequestMapping(PREFIX_API)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookingAPI {

    private BookingMapper bookingMapper = new BookingMapper();

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BillService billService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/booking")
    public ResponseEntity<Map<String, Object>> getAllBooking(
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        try {
            Page<Booking> bookingPage = bookingService.getPageBookingPagination(pageNo,size);
            List<Booking> bookingList = bookingPage.getContent();
            List<BookingDTO> bookingDTOList = bookingMapper.convertBookingListEntityToDto(bookingList);
            Map<String, Object> response = new HashMap<>();
            response.put("bookings", bookingDTOList);
            response.put("currentPage", bookingPage.getNumber());
            response.put("totalItems", bookingPage.getTotalElements());
            response.put("totalPages", bookingPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/booking/history/{customerId}")
    public ResponseEntity<?> getHistoryBooking(@PathVariable(value = "customerId") String customerId) {
        if (StringUtils.isNumeric(customerId)) {
            Long custId = Long.valueOf(customerId);
            Optional<Set<ResponseHistoryBookDTO>> listResponseHistoryBookDTO = bookingService.getHistoryBooking(custId);
            return new ResponseEntity<>(listResponseHistoryBookDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/detail-booking/{billId}/{roomId}")
    public ResponseEntity<DetailsBillDTO> getDetailBill(@PathVariable String billId, @PathVariable String roomId){
        Long id = Long.valueOf(billId);
        Long idRoom = Long.valueOf(roomId);
        DetailsBillDTO detailsBillDTO = new DetailsBillDTO();
        try {
            Customer customer = billService.getCustomerByBillId(id);
            Room room = roomService.getRoomById(idRoom);
            detailsBillDTO.setCustomerCode(customer.getCusCode());
            detailsBillDTO.setCustomerName(customer.getName());
            detailsBillDTO.setCustomerPhone(customer.getPhone());
            detailsBillDTO.setCustomerEmail(customer.getEmail());
            detailsBillDTO.setRoomCode(room.getRoomCode());
            detailsBillDTO.setRoomName(room.getName());
            return new ResponseEntity<>(detailsBillDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/booking/{billId}/{roomId}")
    public ResponseEntity<HttpStatus> deleteBooking(@PathVariable String billId, @PathVariable String roomId){
        Long idBill = Long.valueOf(billId);
        Long idRoom = Long.valueOf(roomId);
        try {
            bookingService.deleteBooking(idBill,idRoom);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Set<Booking> bookingSet = bookingService.getBookingByBillId(idBill);
        if(bookingSet.isEmpty()){
            Bill bill = billService.getBillByBillId(idBill);
            if(!bill.getStatus()){
                billService.deleteBillById(idBill);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
