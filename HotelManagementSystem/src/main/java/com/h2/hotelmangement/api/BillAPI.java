package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Bill;
import com.h2.hotelmangement.entity.Booking;
import com.h2.hotelmangement.entity.Services;
import com.h2.hotelmangement.model.dto.BillDTO;
import com.h2.hotelmangement.model.dto.BookingDTO;
import com.h2.hotelmangement.model.dto.ServicesDTO;
import com.h2.hotelmangement.model.mapper.BillMapper;
import com.h2.hotelmangement.model.mapper.BookingMapper;
import com.h2.hotelmangement.service.BillService;
import com.h2.hotelmangement.service.BookingService;
import org.jboss.weld.context.http.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(PREFIX_API)
@RestController
public class BillAPI {

    @Autowired
    private BillService billService;

    @Autowired
    private BookingService bookingService;

    private BillMapper billMapper = new BillMapper();

    private BookingMapper bookingMapper = new BookingMapper();

    @GetMapping("/bill")
    public ResponseEntity<Map<String, Object>> getAllBill(@RequestParam(required = false) String customerCode,
                                                          @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                          @RequestParam(value = "size", defaultValue = "5") int size){
        try{
            List<Bill> billList = new ArrayList<>();
            Page<Bill> billPage;
            if(customerCode == null){
                billPage = billService.findPageBill(pageNo, size);
            }else{
                billPage = billService.findPageBillByCusCode(customerCode,pageNo,size);
            }
            billList = billPage.getContent();
            List<BillDTO> billDTOList = billMapper.convertListBillEntityToDto(billList);
            Map<String, Object> response = new HashMap<>();
            response.put("bills", billDTOList);
            response.put("currentPage", billPage.getNumber());
            response.put("totalItems", billPage.getTotalElements());
            response.put("totalPages", billPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/bill/{id}")
    public ResponseEntity<List<BookingDTO>> getBookingByBillId(@PathVariable String id){
        Long billId = Long.valueOf(id);

        try {
            Set<Booking> bookingSet = bookingService.getBookingByBillId(billId);

            List<Booking> bookingList = new ArrayList<>(bookingSet);

            List<BookingDTO> bookingDTOList = bookingMapper.convertBookingListEntityToDto(bookingList);

            return new ResponseEntity<>(bookingDTOList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @PostMapping("/bill")
    public Bill addBill(@RequestBody Bill bill){
        return billService.save(bill);
    }

    @PutMapping("/bill/{id}")
    public ResponseEntity<HttpStatus> updateStatusBill(@PathVariable String id){
        Long billId = Long.valueOf(id);

        Bill bill = billService.getBillByBillId(billId);
        if(bill!= null){
            Boolean billStatus = bill.getStatus();
            bill.setStatus(!billStatus);
            billService.save(bill);
            Set<Booking> bookingSet = bookingService.getBookingByBillId(billId);
            for (Booking booking:
                 bookingSet) {
                booking.setStatus(bill.getStatus());
                bookingService.saveOrUpdate(booking);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
