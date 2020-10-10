package com.h2.hotelmangement.entity;


import javax.persistence.*;

@Entity
@Table(name = "beds")
public class Bed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bedid")
    private Long bedid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ammountpeople", nullable = false)
    private int ammountpeople;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "roomid")
    private Room room;

    public Long getBedid() {
        return bedid;
    }

    public void setBedid(Long bedid) {
        this.bedid = bedid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmmountpeople() {
        return ammountpeople;
    }

    public void setAmmountpeople(int ammountpeople) {
        this.ammountpeople = ammountpeople;
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
