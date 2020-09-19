package com.h2.hotelmangement.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "khachsan")
public class HotelEntity {

    @Id
    @Column(name = "machinhanh")
    private String maChiNhanh;

    @Column(name = "tenchinhanh", nullable = false, length = 20)
    private String tenChiNhanh;

    @Column(name = "diachi", nullable = false, length = 100)
    private String diaChi;

    @Column(name = "rating", nullable = true)
    private float rating;

    @Column(name = "thumbnail")
    private String thumbnail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chiNhanh")
    private Set<EmployeeEntity> nhanViens = new HashSet<EmployeeEntity>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chiNhanh")
    private Set<RoomEntity> phongs = new HashSet<RoomEntity>();

    @OneToMany(mappedBy = "dichVuKhachSan")
    private Set<ServicesEntity> dichVuKhachSans = new HashSet<ServicesEntity>();

    public String getMaChiNhanh() {
        return maChiNhanh;
    }

    public void setMaChiNhanh(String maChiNhanh) {
        this.maChiNhanh = maChiNhanh;
    }

    public String getTenChiNhanh() {
        return tenChiNhanh;
    }

    public void setTenChiNhanh(String tenChiNhanh) {
        this.tenChiNhanh = tenChiNhanh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Set<EmployeeEntity> getNhanViens() {
        return nhanViens;
    }

    public void setNhanViens(Set<EmployeeEntity> nhanViens) {
        this.nhanViens = nhanViens;
    }

    public Set<RoomEntity> getPhongs() {
        return phongs;
    }

    public void setPhongs(Set<RoomEntity> phongs) {
        this.phongs = phongs;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Set<ServicesEntity> getDichVuKhachSans() {
        return dichVuKhachSans;
    }

    public void setDichVuKhachSans(Set<ServicesEntity> dichVuKhachSans) {
        this.dichVuKhachSans = dichVuKhachSans;
    }

}