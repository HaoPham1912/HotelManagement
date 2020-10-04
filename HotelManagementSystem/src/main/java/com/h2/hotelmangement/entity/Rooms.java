package com.h2.hotelmangement.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Rooms {

    @Id
    @Column(name = "roomid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomid;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotelid")
    private Hotels roomHotels;


    @OneToMany(mappedBy = "rooms")
    private List<Beds> bedInRoomList;



    @ManyToMany(mappedBy = "rooms" )
    private Set<Services> services;

    //nay la thay vi tao 1 entity hinh la 1 class thi minh tao tu day luon
    //minh tao ra cai table ra roomimages tu day
    //khi save thi minh se save xuong no 1 list String name cua imange
    //Tai ten imange co the trung nen t de list
    //tuong tu voi imangehotel luon nha

    @Column(name = "thumbnail")
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "roomimages", joinColumns = @JoinColumn(name = "roomid"))
    private List<String> thumbnailsRoomList;

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Hotels getRoomHotels() {
        return roomHotels;
    }

    public void setRoomHotels(Hotels roomHotels) {
        this.roomHotels = roomHotels;
    }

    public Set<Services> getServices() {
        return services;
    }

    public void setServices(Set<Services> services) {
        this.services = services;
    }

    public List<String> getThumbnailsRoomList() {
        return thumbnailsRoomList;
    }

    public void setThumbnailsRoomList(List<String> thumbnailsRoomList) {
        this.thumbnailsRoomList = thumbnailsRoomList;
    }

    public List<Beds> getBedInRoomList() {
        return bedInRoomList;
    }

    public void setBedInRoomList(List<Beds> bedInRoomList) {
        this.bedInRoomList = bedInRoomList;
    }
}