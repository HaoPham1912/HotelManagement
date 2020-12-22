package com.h2.hotelmangement.model.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private String mainImage;

    private Set<String> empCodeSet = new HashSet<>();
    private List<String> thumbnailsBranchSet = new ArrayList<>();
    private List<EmployeeDTO> employeeDTOSet = new ArrayList<>();

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public List<EmployeeDTO> getEmployeeDTOSet() {
        return employeeDTOSet;
    }

    public void setEmployeeDTOSet(List<EmployeeDTO> employeeDTOSet) {
        this.employeeDTOSet = employeeDTOSet;
    }

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

    public List<String> getThumbnailsBranchSet() {
        return thumbnailsBranchSet;
    }

    public void setThumbnailsBranchSet(List<String> thumbnailsBranchSet) {
        this.thumbnailsBranchSet = thumbnailsBranchSet;
    }

    @Override
    public String toString() {
        return "BranchDTO{" +
                "branchId='" + branchId + '\'' +
                ", address='" + address + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", description='" + description + '\'' +
                ", branchName='" + branchName + '\'' +
                ", rating='" + rating + '\'' +
                ", status='" + status + '\'' +
                ", location='" + location + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", empCodeSet=" + empCodeSet +
                ", thumbnailsBranchSet=" + thumbnailsBranchSet.toString() +
                ", employeeDTOSet=" + employeeDTOSet +
                '}';
    }
}
