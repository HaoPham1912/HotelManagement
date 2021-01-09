package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.Request.BookingCustomerDTO;
import com.h2.hotelmangement.Request.ResponseHistoryBookDTO;
import com.h2.hotelmangement.common.util.ModelMapperUtil;
import com.h2.hotelmangement.entity.Bill;
import com.h2.hotelmangement.entity.Booking;
import com.h2.hotelmangement.entity.BookingKey;
import com.h2.hotelmangement.entity.Room;
import com.h2.hotelmangement.model.dto.BillDTO;
import com.h2.hotelmangement.model.dto.BookingDTO;
import com.h2.hotelmangement.model.dto.RoomDTO;
import com.h2.hotelmangement.model.mapper.RoomMapper;
import com.h2.hotelmangement.repository.BillRepository;
import com.h2.hotelmangement.repository.BookingRepository;
import com.h2.hotelmangement.repository.RoomRepository;
import com.h2.hotelmangement.service.BillService;
import com.h2.hotelmangement.service.BookingService;
import com.h2.hotelmangement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BillService billService;

    @Autowired
    private RoomService roomService;

    private RoomMapper roomMapper = new RoomMapper();

    @Autowired
    private RoomRepository roomRepository;

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

    @Override
    public Set<Booking> getBookingByBillId(Long id) {
        return bookingRepository.findBookingByBookingKey_BillId(id);
    }

    @Override
    public Long deleteBooking(Long billId, Long roomId) throws Exception {
        Booking booking = bookingRepository.findBookingByBills_BillidAndRoom_RoomId(billId, roomId);
        if(booking.getStatus()){
            return bookingRepository.deleteBookingByBills_BillidAndRoom_RoomId(billId,roomId);
        }else {
            throw new Exception("Can not delete this booking!!!");
        }
    }

    @Override
    public Booking getBookingInfor(Long billId, Long roomId) {
        return bookingRepository.findBookingByBills_BillidAndRoom_RoomId(billId, roomId);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Optional<List<BookingDTO>> createBookingCustomer(BookingCustomerDTO bookingCustomerDTO) throws Exception {
        Date dateCheckIn = new Date(bookingCustomerDTO.getCheckIn());
        Date dateCheckOut = new Date(bookingCustomerDTO.getCheckOut());
        Optional<Bill> bill = billService.createBill(bookingCustomerDTO);
        List<Booking> bookingList = new ArrayList<>();
        if(bill.isPresent()){
            for(RoomDTO r : bookingCustomerDTO.getListRoom()){
                Room room = roomRepository.findRoomByRoomCode(r.getRoomCode());
                BookingKey bookingKey = new BookingKey();
                bookingKey.setBillId(bill.get().getBillid());
                bookingKey.setBookDate(bill.get().getCreatedate());
                bookingKey.setCheckinDate(dateCheckIn);
                bookingKey.setRoomId(r.getRoomId());
                Booking booking = new Booking();
                booking.setBills(bill.get());
                booking.setBookingKey(bookingKey);
                booking.setCheckoutDate(dateCheckOut);
                booking.setPaidPrice(r.getPrice());
                booking.setRoom(room);
                booking.setStatus(false);
               Booking booking1 = bookingRepository.save(booking);
                bookingList.add(booking1);
            }
            if(bookingList.size() == bookingCustomerDTO.getListRoom().size())
            return Optional.ofNullable(ModelMapperUtil.mapAll(bookingList,BookingDTO.class));
            else throw new Exception();
        }
        return Optional.empty();
    }

}
