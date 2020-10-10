package com.h2.hotelmangement.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * nay la bang the hien cho chi nhanh de co gi khi ma cho nguoi dung search thi lay len cho de
 */
@Entity
@Table(name = "branchs")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branchid")
    private Long branchId;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "location",nullable = false)
    private String location;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "branchs")
    private Set<Hotel> hotels;

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(Set<Hotel> hotels) {
        this.hotels = hotels;
    }
}
