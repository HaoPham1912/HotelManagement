package com.managementhotel.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "nhanvien")
public class EmployeeEntity {

	@Id
	@Column(name = "manhanvien")
	private String maNhanVien;

	@OneToOne(mappedBy = "accountNhanVien")
	private AccountEntity taiKhoanNhanVien;

	@Column(name = "tennhanvien", nullable = false, length = 100)
	private String tenNhanVien;

	@Column(name = "soDT", nullable = false, length = 10)
	private String soDienThoai;

	@Column(name = "luong", nullable = false)
	private double luong;

	@Column(name = "chucvu", nullable = false, length = 20)
	private String chucVu;

	@ManyToOne
	@JoinColumn(name = "machinhanh")
	private HotelEntity chiNhanh;

	@Column(name = "trangthai", nullable = false)
	private String trangThai;

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public AccountEntity getTaiKhoanNhanVien() {
		return taiKhoanNhanVien;
	}

	public void setTaiKhoanNhanVien(AccountEntity taiKhoanNhanVien) {
		this.taiKhoanNhanVien = taiKhoanNhanVien;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getTenNhanVien() {
		return tenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public HotelEntity getChiNhanh() {
		return chiNhanh;
	}

	public void setChiNhanh(HotelEntity chiNhanh) {
		this.chiNhanh = chiNhanh;
	}

	public String getStatus() {
		return trangThai;
	}

	public void setStatus(String trangThai) {
		this.trangThai = trangThai;
	}
}
