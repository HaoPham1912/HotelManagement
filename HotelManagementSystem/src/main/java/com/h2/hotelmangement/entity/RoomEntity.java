package com.h2.hotelmangement.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "phong")
public class RoomEntity {

    @Id
    @Column(name = "maphong")
    private String maPhong;

    @ManyToOne
    @JoinColumn(name = "machinhanh")
    private HotelEntity chiNhanh;

    @Column(name = "tenphong", nullable = false, length = 30)
    private String tenPhong;

    @Column(name = "giaphong", nullable = false)
    private double giaPhong;

    @Column(name = "sogiuong", nullable = false)
    private int soGiuong;

    @OneToMany(mappedBy = "dichVuPhong")
    private Set<ServicesEntity> dichVuPhongs = new HashSet<ServicesEntity>();

    @OneToMany(mappedBy = "phongBooking")
    private Set<BookingEntity> phongDaDats = new HashSet<BookingEntity>();

    @OneToMany(mappedBy = "phongKhuyenMai")
    private Set<SaleEntity> phongKhuyenMais = new HashSet<SaleEntity>();

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public HotelEntity getChiNhanh() {
        return chiNhanh;
    }

    public void setChiNhanh(HotelEntity chiNhanh) {
        this.chiNhanh = chiNhanh;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public double getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(double giaPhong) {
        this.giaPhong = giaPhong;
    }

    public int getSoGiuong() {
        return soGiuong;
    }

    public void setSoGiuong(int soGiuong) {
        this.soGiuong = soGiuong;
    }

    public Set<ServicesEntity> getDichVuPhongs() {
        return dichVuPhongs;
    }

    public void setDichVuPhongs(Set<ServicesEntity> dichVuPhongs) {
        this.dichVuPhongs = dichVuPhongs;
    }

    public Set<BookingEntity> getPhongDaDats() {
        return phongDaDats;
    }

    public void setPhongDaDats(Set<BookingEntity> phongDaDats) {
        this.phongDaDats = phongDaDats;
    }


    public Set<SaleEntity> getPhongKhuyenMais() {
        return phongKhuyenMais;
    }

    public void setPhongKhuyenMais(Set<SaleEntity> phongKhuyenMais) {
        this.phongKhuyenMais = phongKhuyenMais;
    }

}