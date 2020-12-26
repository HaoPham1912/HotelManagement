package com.h2.hotelmangement.model.dto;

import java.util.HashSet;
import java.util.Set;

public class ServicesDTO extends BaseDTO {
    private Long serviceId;
    private String serviceCode;
    private String name;
    private String price;
    private String description;
    private Set<Long> roomIdSet = new HashSet<>();
    private Boolean status;

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Long> getRoomIdSet() {
        return roomIdSet;
    }

    public void setRoomIdSet(Set<Long> roomIdSet) {
        this.roomIdSet = roomIdSet;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ServicesDTO{" +
                "serviceId=" + serviceId +
                ", serviceCode='" + serviceCode + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", roomIdSet=" + roomIdSet +
                '}';
    }
}
