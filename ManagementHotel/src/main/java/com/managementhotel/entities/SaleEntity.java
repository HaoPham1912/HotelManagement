package com.managementhotel.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "khuyenmai")
public class SaleEntity {

	@Id
	@Column(name = "makhuyenmai")
	private String maKhuyenMai;

	@Column(name = "madichvu", nullable = false, length = 30)
	private String maDichVu;

	@Column(name = "ngaybatdau", nullable = false)
	private Date ngayBatDau;

	@Column(name = "ngayketthuc", nullable = false)
	private Date ngayKetThuc;

	@OneToMany(mappedBy = "thongTinKhuyenMaiHoaDon")
	private Set<BillEntity> khuyenMaiHoaDons = new HashSet<BillEntity>();

	@OneToMany(mappedBy = "dichVuKhuyenMai")
	private Set<ServicesEntity> khuyemais = new HashSet<ServicesEntity>();

	@ManyToOne
	@JoinColumn(name = "maphong")
	private RoomEntity phongKhuyenMai;

	public String getMaKhuyenMai() {
		return maKhuyenMai;
	}

	public void setMaKhuyenMai(String maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}

	public String getMaDichVu() {
		return maDichVu;
	}

	public void setMaDichVu(String maDichVu) {
		this.maDichVu = maDichVu;
	}

	public Date getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public Set<BillEntity> getKhuyenMaiHoaDons() {
		return khuyenMaiHoaDons;
	}

	public void setKhuyenMaiHoaDons(Set<BillEntity> khuyenMaiHoaDons) {
		this.khuyenMaiHoaDons = khuyenMaiHoaDons;
	}

	public Set<ServicesEntity> getKhuyemais() {
		return khuyemais;
	}

	public void setKhuyemais(Set<ServicesEntity> khuyemais) {
		this.khuyemais = khuyemais;
	}

	public RoomEntity getPhongKhuyenMai() {
		return phongKhuyenMai;
	}

	public void setPhongKhuyenMai(RoomEntity phongKhuyenMai) {
		this.phongKhuyenMai = phongKhuyenMai;
	}

}
