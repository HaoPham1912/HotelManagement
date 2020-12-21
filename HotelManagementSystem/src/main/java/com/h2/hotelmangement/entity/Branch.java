package com.h2.hotelmangement.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "branchs")
@DynamicInsert
@DynamicUpdate
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branchid")
    private Long branchId;

    @Column(name = "branchcode", nullable = false, unique = true, length = 20)
    private String branchCode;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "address", nullable = false)
    @Type(type = "text")
    private String address;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "rating", columnDefinition = "float default 0")
    private float rating;

    @Column(name = "mainimage")
    private String mainImage;

    @Column(name = "status", nullable = false, columnDefinition = "boolean default true")
    private Boolean status;

    @Column(name = "description")
    @Type(type = "text")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "empBranch")
    private Set<Employee> employeeSet;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roomBranch")
    private Set<Room> roomSet;

    @Column(name = "thumbnail")
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "branchimages", joinColumns = @JoinColumn(name = "branchid"))
    private Set<String> thumbnailsHotelList;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }

    public Set<Room> getRoomSet() {
        return roomSet;
    }

    public void setRoomSet(Set<Room> roomSet) {
        this.roomSet = roomSet;
    }

    public Set<String> getThumbnailsHotelList() {
        return thumbnailsHotelList;
    }

    public void setThumbnailsHotelList(Set<String> thumbnailsHotelList) {
        this.thumbnailsHotelList = thumbnailsHotelList;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
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

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branchId=" + branchId +
                ", branchCode='" + branchCode + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", rating=" + rating +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}