package com.h2.hotelmangement.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bills")
public class Bills {

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bills")
    private Set<Bookings> billBookingSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerid")
    private Customers customers;

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

    public Set<Bookings> getBillBookingSet() {
        return billBookingSet;
    }

    public void setBillBookingSet(Set<Bookings> billBookingSet) {
        this.billBookingSet = billBookingSet;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }
}
