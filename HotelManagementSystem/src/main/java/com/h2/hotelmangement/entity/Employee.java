package com.h2.hotelmangement.entity;

import javax.persistence.*;


@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeid")
    private Long employeeId;

    @Column(name = "empcode", nullable = false, unique = true, length = 20)
    private String empCode;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;


    @Column(name = "phone", length = 11, nullable = false)
    private String phone;

    //so CMND
    @Column(name = "idcard", length = 12, nullable = false)
    private String idCard;

    @OneToOne
    @JoinColumn(name = "accountid")
    private Account accountEmp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branchid")
    private Branch empBranch;


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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Account getAccountEmp() {
        return accountEmp;
    }

    public void setAccountEmp(Account accountEmp) {
        this.accountEmp = accountEmp;
    }

    public Branch getEmpBranch() {
        return empBranch;
    }

    public void setEmpBranch(Branch empBranch) {
        this.empBranch = empBranch;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
