package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.Request.ResponseHistoryBookDTO;
import com.h2.hotelmangement.common.util.ModelMapperUtil;
import com.h2.hotelmangement.entity.Booking;
import com.h2.hotelmangement.model.dto.BillDTO;
import com.h2.hotelmangement.model.dto.BookingDTO;
import com.h2.hotelmangement.model.dto.RoomDTO;
import com.h2.hotelmangement.repository.BillRepository;
import com.h2.hotelmangement.repository.BookingRepository;
import com.h2.hotelmangement.service.BillService;
import com.h2.hotelmangement.service.BookingService;
import com.h2.hotelmangement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BillService billService;

    @Autowired
    private RoomService roomService;

    @Override
    public void saveOrUpdate(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public List<Booking> findAllBooking() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> getBookingByBookingDate(String bookingDate) {
        return bookingRepository.findBookingByBookingKey_BookDate(bookingDate);
    }

    @Override
    public Optional<Set<ResponseHistoryBookDTO>> getHistoryBooking(Long customerId) {
        Set<ResponseHistoryBookDTO> responseHistoryBookDTOS = new HashSet<>();
        Optional<Set<BillDTO>> listBill = billService.findBillByCustomer(customerId);
        if(listBill.isPresent()){
            listBill.get().stream().forEach(b ->{
                Optional<BookingDTO> booking = Optional.ofNullable(ModelMapperUtil.map(bookingRepository.findByBills_Billid(b.getBillId()),BookingDTO.class));
                if(booking.isPresent()){
                    Optional<RoomDTO> room = Optional.ofNullable(ModelMapperUtil.map(roomService.getRoomById(booking.get().getRoomId()),RoomDTO.class));
                    if(room.isPresent()){
                        ResponseHistoryBookDTO responseHistoryBookDTO =  new ResponseHistoryBookDTO();
                        responseHistoryBookDTO.setBillDTO(b);
                        responseHistoryBookDTO.setBookingDTO(booking.get());
                        responseHistoryBookDTO.setRoomDTO(room.get());
                        responseHistoryBookDTOS.add(responseHistoryBookDTO);
                    }
                }

            });
        }
        return Optional.ofNullable(responseHistoryBookDTOS);
    }

    @Override
    public Page<Booking> getPageBookingPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return bookingRepository.findAll(pageable);
    }

}
