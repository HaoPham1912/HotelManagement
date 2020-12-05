package com.h2.hotelmangement.model.dto;

import java.util.HashSet;
import java.util.Set;

public class BranchDTO {
    private String branchId;
    private String address;
    private String branchCode;
    private String description;
    private String branchName;
    private String rating;
    private String status;
    private String location;
    private Set<String> empCodeSet = new HashSet<>();

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<String> getEmpCodeSet() {
        return empCodeSet;
    }

    public void setEmpCodeSet(Set<String> empCodeSet) {
        this.empCodeSet = empCodeSet;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
