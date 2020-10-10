package com.h2.hotelmangement.entity;

import javax.persistence.*;


@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeid")
    private Long employeeid;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "phone", length = 11, nullable = false)
    private String phone;

    //so CMND
    @Column(name = "idcard", length = 12, nullable = false)
    private String idcard;

    @OneToOne
    @JoinColumn(name = "accountid")
    private Account accountEmp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotelid")
    private Hotel empHotel;

    public Long getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Long employeeid) {
        this.employeeid = employeeid;
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

    public Account getAccountEmp() {
        return accountEmp;
    }

    public void setAccountEmp(Account accountEmp) {
        this.accountEmp = accountEmp;
    }

    public Hotel getEmpHotel() {
        return empHotel;
    }

    public void setEmpHotel(Hotel empHotel) {
        this.empHotel = empHotel;
    }
}
