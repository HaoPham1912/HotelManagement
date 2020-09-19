package com.h2.hotelmangement.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "taikhoan")
public class AccountEntity extends BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "mataikhoan")
    private String maTaiKhoan;

    @Column(name = "username", nullable = false, length = 30)
    private String username;

    @Column(name = "password", nullable = false, length = 30)
    private String password;

    @OneToOne
    @JoinColumn(name = "manhanvien")
    private EmployeeEntity accountNhanVien;

    @OneToOne
    @JoinColumn(name = "makhachhang")
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

}

