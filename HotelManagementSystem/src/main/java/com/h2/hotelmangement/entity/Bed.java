package com.h2.hotelmangement.entity;


import javax.persistence.*;

@Entity
@Table(name = "beds")
public class Bed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bedid")
    private Long bedId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ammountpeople", nullable = false)
    private int ammountPeople;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "roomid")
    private Room room;

    public Long getBedId() {
        return bedId;
    }

    public void setBedId(Long bedId) {
        this.bedId = bedId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmmountPeople() {
        return ammountPeople;
    }

    public void setAmmountPeople(int ammountPeople) {
        this.ammountPeople = ammountPeople;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

}
