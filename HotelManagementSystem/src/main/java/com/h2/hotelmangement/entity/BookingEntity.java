package com.h2.hotelmangement.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    @Column(name = "mabooking", nullable = false, unique = true)
    private String maBooking;

    @ManyToOne
    @JoinColumn(name = "makhachhang")
    private CustomerEntity khachHangbooking;

    @ManyToOne
    @JoinColumn(name = "maphong")
    private RoomEntity phongBooking;

    @Column(name = "ngaydat", nullable = false)
    private Date ngayDat;

    @Column(name = "ngaytra", nullable = false)
    private Date ngayTra;

    @Column(name = "ngaynhan", nullable = false)
    private Date ngayNhan;

    @Column(name = "tientratruoc", nullable = false)
    private double tienTraTruoc;

    @Column(name = "trangthai", nullable = false)
    private String trangThai;

    public String getMaBooking() {
        return maBooking;
    }

    public void setMaBooking(String maBooking) {
        this.maBooking = maBooking;
    }

    public CustomerEntity getKhachHangbooking() {
        return khachHangbooking;
    }

    public void setKhachHangbooking(CustomerEntity khachHangbooking) {
        this.khachHangbooking = khachHangbooking;
    }

    public RoomEntity getPhongBooking() {
        return phongBooking;
    }

    public void setPhongBooking(RoomEntity phongBooking) {
        this.phongBooking = phongBooking;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public Date getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(Date ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public double getTienTraTruoc() {
        return tienTraTruoc;
    }

    public void setTienTraTruoc(double tienTraTruoc) {
        this.tienTraTruoc = tienTraTruoc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

}
