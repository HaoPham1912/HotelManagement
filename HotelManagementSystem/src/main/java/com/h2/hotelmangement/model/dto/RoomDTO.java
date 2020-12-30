package com.h2.hotelmangement.model.dto;

import java.util.List;
import java.util.Set;

public class RoomDTO {
    private Long roomId;
    private String description;
    private String name;
    private double price;
    private String roomCode;
    private Long branchId;
    private Set<String> thumbnailList;
    private String status;
    private String policyCode;
    private String branchCode;
    private String ammountPeople;


    public String getAmmountPeople() {
        return ammountPeople;
    }

    public void setAmmountPeople(String ammountPeople) {
        this.ammountPeople = ammountPeople;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getPolicyCode() {
        return policyCode;
    }

    public void setPolicyCode(String policyCode) {
        this.policyCode = policyCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Set<String> getThumbnailList() {
        return thumbnailList;
    }

    public void setThumbnailList(Set<String> thumbnailList) {
        this.thumbnailList = thumbnailList;
    }
}
