package com.h2.hotelmangement.entity;

import javax.persistence.*;

@Entity
@Table(name = "combotypes")
public class ComboType {

    @Column(name = "comboid")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comboId;

    @Column(name = "name", nullable = false)
    private String comboName;

    @Column(name = "cancelpolicy")
    private String cancelPolicy;

    @Column(name = "price", nullable = false)
    private Double comboPrice;

    @Column(name = "shortdescription")
    private String shortDescription;

    @Column(name = "roomdetail", nullable = false)
    private String roomDetail;

    @ManyToOne
    @JoinColumn(name = "roomid")
    private Room room;

    public Long getComboId() {
        return comboId;
    }

    public void setComboId(Long comboId) {
        this.comboId = comboId;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public String getCancelPolicy() {
        return cancelPolicy;
    }

    public void setCancelPolicy(String cancelPolicy) {
        this.cancelPolicy = cancelPolicy;
    }

    public Double getComboPrice() {
        return comboPrice;
    }

    public void setComboPrice(Double comboPrice) {
        this.comboPrice = comboPrice;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getRoomDetail() {
        return roomDetail;
    }

    public void setRoomDetail(String roomDetail) {
        this.roomDetail = roomDetail;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
