package com.h2.hotelmangement.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerid")
    private Long customerId;

    @Column(name = "cuscode", nullable = false, unique = true, length = 20)
    private String cusCode;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "phone", length = 11, nullable = false)
    private String phone;

    @Column(name = "email", length =100, nullable = false, unique = true)
    private String email;

    @Column(name = "idcard", length = 12)
    private String idCard;


    @Column(name = "status",  columnDefinition = "boolean default true")
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "accountid")
    private Account accountCus;

    @ManyToOne
    @JoinColumn(name = "typecustomerid")
    private CustomerType customerType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<Bill> cusBillSet;


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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Account getAccountCus() {
        return accountCus;
    }

    public void setAccountCus(Account accountCus) {
        this.accountCus = accountCus;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public Set<Bill> getCusBillSet() {
        return cusBillSet;
    }

    public void setCusBillSet(Set<Bill> cusBillSet) {
        this.cusBillSet = cusBillSet;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
