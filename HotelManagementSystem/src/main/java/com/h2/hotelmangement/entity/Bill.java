package com.h2.hotelmangement.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "bills")
@DynamicInsert
@DynamicUpdate
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billid")
    private Long billid;

    @Column(name = "createdate", nullable = false)
    private Date createdate;

    @Column(name = "promocode")
    private String promocode;

    @Column(name = "totalprice", nullable = false)
    private double totalprice;

    @Column(name = "status", columnDefinition = "boolean default true")
    private Boolean status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bills")
    private Set<Booking> billBookingSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerid")
    private Customer customer;

    @OneToMany(mappedBy = "bills", cascade = CascadeType.ALL)
    private List<Booking> bookingList = new ArrayList<>();

    public Long getBillid() {
        return billid;
    }

    public void setBillid(Long billid) {
        this.billid = billid;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getPromocode() {
        return promocode;
    }

    public void setPromocode(String promocode) {
        this.promocode = promocode;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public Set<Booking> getBillBookingSet() {
        return billBookingSet;
    }

    public void setBillBookingSet(Set<Booking> billBookingSet) {
        this.billBookingSet = billBookingSet;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billid=" + billid +
                ", createdate=" + createdate +
                ", promocode='" + promocode + '\'' +
                ", totalprice=" + totalprice +
                ", customer=" + customer +
                +'}';
    }
}
