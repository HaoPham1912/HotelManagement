package com.h2.hotelmangement.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
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

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    @Type(type = "text")
    private String description;

    @Column(name = "status",  columnDefinition = "boolean default true")
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branchid")
    private Branch roomBranch;

    @ManyToMany(mappedBy = "rooms", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Services> services = new HashSet<>();

    @OneToMany(mappedBy = "room" , cascade = CascadeType.ALL)
    private List<Booking> bookingList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "policyid")
    private CancelPolicy cancelPolicy;

    @Column(name = "thumbnail")
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "roomimages", joinColumns = @JoinColumn(name = "roomid"))
    private Set<String> thumbnailsRoomList;

    @ManyToMany(mappedBy = "rooms", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Bed> bedSet = new HashSet<>();

    public CancelPolicy getCancelPolicy() {
        return cancelPolicy;
    }

    public void setCancelPolicy(CancelPolicy cancelPolicy) {
        this.cancelPolicy = cancelPolicy;
    }

    public Set<Bed> getBedSet() {
        return bedSet;
    }

    public void setBedSet(Set<Bed> bedSet) {
        this.bedSet = bedSet;
    }

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

    public Branch getRoomBranch() {
        return roomBranch;
    }

    public void setRoomBranch(Branch roomBranch) {
        this.roomBranch = roomBranch;
    }

    public Set<Services> getServices() {
        return services;
    }

    public void setServices(Set<Services> services) {
        this.services = services;
    }

    public Set<String> getThumbnailsRoomList() {
        return thumbnailsRoomList;
    }

    public void setThumbnailsRoomList(Set<String> thumbnailsRoomList) {
        this.thumbnailsRoomList = thumbnailsRoomList;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomCode='" + roomCode + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
