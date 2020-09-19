package com.h2.hotelmangement.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "taikhoan")
public class AccountEntity extends  BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "mataikhoan")
    @GeneratedValue
    private String maTaiKhoan;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "rolename", nullable = false)
    private String roleName;

    @Column(name = "trangthai", nullable = false)
    private String trangthai;

    @OneToOne(mappedBy = "taiKhoanNhanVien")
    private EmployeeEntity accountNhanVien;

    @OneToOne(mappedBy = "taiKhoanKhachHang")
    private CustomerEntity accountKhachHang;

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmployeeEntity getAccountNhanVien() {
        return accountNhanVien;
    }

    public void setAccountNhanVien(EmployeeEntity accountNhanVien) {
        this.accountNhanVien = accountNhanVien;
    }

    public CustomerEntity getAccountKhachHang() {
        return accountKhachHang;
    }

    public void setAccountKhachHang(CustomerEntity accountKhachHang) {
        this.accountKhachHang = accountKhachHang;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}

