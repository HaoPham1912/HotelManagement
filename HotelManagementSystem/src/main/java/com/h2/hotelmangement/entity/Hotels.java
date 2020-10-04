package com.h2.hotelmangement.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "hotels")
public class Hotels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotelid")
    private Long hotelid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "rating")
    private float rating;

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Branchs branchs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "empHotels")
    private Set<Employees> employeesSet;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roomHotels")
    private Set<Rooms> roomsSet;

    @Column(name = "thumbnail")
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "hotelimages", joinColumns = @JoinColumn(name = "hotelid"))
    private List<String> thumbnailsHotelList;

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

    public Branchs getBranchs() {
        return branchs;
    }

    public void setBranchs(Branchs branchs) {
        this.branchs = branchs;
    }

    public Set<Employees> getEmployeesSet() {
        return employeesSet;
    }

    public void setEmployeesSet(Set<Employees> employeesSet) {
        this.employeesSet = employeesSet;
    }

    public Set<Rooms> getRoomsSet() {
        return roomsSet;
    }

    public void setRoomsSet(Set<Rooms> roomsSet) {
        this.roomsSet = roomsSet;
    }

    public List<String> getThumbnailsHotelList() {
        return thumbnailsHotelList;
    }

    public void setThumbnailsHotelList(List<String> thumbnailsHotelList) {
        this.thumbnailsHotelList = thumbnailsHotelList;
    }
}