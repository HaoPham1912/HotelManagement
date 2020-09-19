package com.h2.hotelmangement.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "khachhang")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Column(name = "makhachhang", nullable = false, unique = true)
    private String maKhachHang;

    @OneToOne
    @JoinColumn(name = "account_ii")
    private AccountEntity taiKhoanKhachHang;

    @Column(name = "soCMND", nullable = false, length = 20)
    private String soCMND;

    @Column(name = "tenkhachang", nullable = false, length = 50)
    private String tenKhachHang;

    @Column(name = "soDT", nullable = false, length = 10)
    private String soDienThoai;

    @Column(name = "loaikhachhang", nullable = false, length = 30)
    private String loaiKhachHang;

    @ManyToOne
    @JoinColumn(name = "mahoadon")
    private BillEntity hoaDonKhachHang;

    @OneToMany(mappedBy = "khachHangbooking")
    private Set<BookingEntity> danhsachBooking = new HashSet<BookingEntity>();

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public AccountEntity getTaiKhoanKhachHang() {
        return taiKhoanKhachHang;
    }

    public void setTaiKhoanKhachHang(AccountEntity taiKhoanKhachHang) {
        this.taiKhoanKhachHang = taiKhoanKhachHang;
    }

    public String getSoCMND() {
        return soCMND;
    }

    public void setSoCMND(String soCMND) {
        this.soCMND = soCMND;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getLoaiKhachHang() {
        return loaiKhachHang;
    }

    public void setLoaiKhachHang(String loaiKhachHang) {
        this.loaiKhachHang = loaiKhachHang;
    }

    public BillEntity getHoaDonKhachHang() {
        return hoaDonKhachHang;
    }

    public void setHoaDonKhachHang(BillEntity hoaDonKhachHang) {
        this.hoaDonKhachHang = hoaDonKhachHang;
    }

    public Set<BookingEntity> getDanhsachBooking() {
        return danhsachBooking;
    }

    public void setDanhsachBooking(Set<BookingEntity> danhsachBooking) {
        this.danhsachBooking = danhsachBooking;
    }

}
