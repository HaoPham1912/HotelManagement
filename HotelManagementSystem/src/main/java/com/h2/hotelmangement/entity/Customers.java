package com.h2.hotelmangement.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerid")
    private Long customerid;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "phone", length = 11, nullable = false)
    private String phone;

    @Column(name = "idcard", length = 12, nullable = false)
    private String idcard;

    @OneToOne
    @JoinColumn(name = "accountid")
    private Accounts accountCus;

    @ManyToOne
    @JoinColumn(name = "typecustomerid")
    private CustomerTypes customerTypes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customers")
    private Set<Bills> cusBillsSet;

    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Accounts getAccountCus() {
        return accountCus;
    }

    public void setAccountCus(Accounts accountCus) {
        this.accountCus = accountCus;
    }

    public CustomerTypes getCustomerTypes() {
        return customerTypes;
    }

    public void setCustomerTypes(CustomerTypes customerTypes) {
        this.customerTypes = customerTypes;
    }

    public Set<Bills> getCusBillsSet() {
        return cusBillsSet;
    }

    public void setCusBillsSet(Set<Bills> cusBillsSet) {
        this.cusBillsSet = cusBillsSet;
    }
}
