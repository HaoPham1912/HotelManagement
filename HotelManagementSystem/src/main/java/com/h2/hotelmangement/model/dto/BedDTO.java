package com.h2.hotelmangement.model.dto;

import java.util.HashSet;
import java.util.Set;

public class BedDTO {
    private String bedId;
    private String name;
    private String amountPeople;
    private String price;
    private String description;
    private String branchCode;
    private String roomCode;

    private Set<String> roomCodeSet = new HashSet<>();

    public Set<String> getRoomCodeSet() {
        return roomCodeSet;
    }

    public void setRoomCodeSet(Set<String> roomCodeSet) {
        this.roomCodeSet = roomCodeSet;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getBedId() {
        return bedId;
    }

    public void setBedId(String bedId) {
        this.bedId = bedId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmountPeople() {
        return amountPeople;
    }

    public void setAmountPeople(String amountPeople) {
        this.amountPeople = amountPeople;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
}
