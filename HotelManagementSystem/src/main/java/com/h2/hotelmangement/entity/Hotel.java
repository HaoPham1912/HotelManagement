package com.h2.hotelmangement.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotelid")
    private Long hotelid;

    @Column(name = "hotelcode", nullable = false, unique = true, length = 20)
    private String hotelCode;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "rating")
    private float rating;

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Branch branchs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "empHotel")
    private Set<Employee> employeeSet;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roomHotel")
    private Set<Room> roomSet;

    @Column(name = "thumbnail")
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "hotelimages", joinColumns = @JoinColumn(name = "hotelid"))
    private Set<String> thumbnailsHotelList;

    public Long getHotelid() {
        return hotelid;
    }

    public void setHotelid(Long hotelid) {
        this.hotelid = hotelid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Branch getBranchs() {
        return branchs;
    }

    public void setBranchs(Branch branchs) {
        this.branchs = branchs;
    }

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }

    public Set<Room> getRoomSet() {
        return roomSet;
    }

    public void setRoomSet(Set<Room> roomSet) {
        this.roomSet = roomSet;
    }

    public Set<String> getThumbnailsHotelList() {
        return thumbnailsHotelList;
    }

    public void setThumbnailsHotelList(Set<String> thumbnailsHotelList) {
        this.thumbnailsHotelList = thumbnailsHotelList;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }
}