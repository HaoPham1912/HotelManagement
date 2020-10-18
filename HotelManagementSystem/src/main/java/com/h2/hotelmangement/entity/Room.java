package com.h2.hotelmangement.entity;

import javax.persistence.*;
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
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branchid")
    private Branch roomBranch;

    @OneToMany(mappedBy = "room")
    private List<Bed> bedInRoomList;

    @OneToMany(mappedBy = "room")
    private List<ComboType> comboTypeList;

    @ManyToMany(mappedBy = "rooms", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Services> services = new HashSet<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
