package com.h2.hotelmangement.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @Column(name = "roomid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(name = "roomcode",nullable = false, unique = true, length = 20)
    private String roomCode;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotelid")
    private Hotel roomHotel;


    @OneToMany(mappedBy = "room")
    private List<Bed> bedInRoomList;

    @ManyToMany(mappedBy = "rooms" )
    private Set<Service> services;

    //nay la thay vi tao 1 entity hinh la 1 class thi minh tao tu day luon
    //minh tao ra cai table ra roomimages tu day
    //khi save thi minh se save xuong no 1 list String name cua imange
    //tuong tu voi imangehotel luon nha

    @Column(name = "thumbnail")
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "roomimages", joinColumns = @JoinColumn(name = "roomid"))
    private Set<String> thumbnailsRoomList;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
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

    public Hotel getRoomHotel() {
        return roomHotel;
    }

    public void setRoomHotel(Hotel roomHotel) {
        this.roomHotel = roomHotel;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public Set<String> getThumbnailsRoomList() {
        return thumbnailsRoomList;
    }

    public void setThumbnailsRoomList(Set<String> thumbnailsRoomList) {
        this.thumbnailsRoomList = thumbnailsRoomList;
    }

    public List<Bed> getBedInRoomList() {
        return bedInRoomList;
    }

    public void setBedInRoomList(List<Bed> bedInRoomList) {
        this.bedInRoomList = bedInRoomList;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }
}
