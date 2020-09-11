package com.managementhotel.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dichvu")
public class ServicesEntity extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "madichvu")
	private String maDichVu;

	@Column(name = "tendichvu", nullable = false, length = 30)
	private String tenDichVu;

	@Column(name = "giadichvu", nullable = false)
	private double giaDichVu;

	@ManyToOne
	@JoinColumn(name = "makhachsan")
	private HotelEntity dichVuKhachSan;

	@ManyToOne
	@JoinColumn(name = "maphong")
	private RoomEntity dichVuPhong;

	@ManyToOne
	@JoinColumn(name = "makhuyenmai")
	private SaleEntity dichVuKhuyenMai;

	public String getMaDichVu() {
		return maDichVu;
	}

	public void setMaDichVu(String maDichVu) {
		this.maDichVu = maDichVu;
	}

	public String getTenDichVu() {
		return tenDichVu;
	}

	public void setTenDichVu(String tenDichVu) {
		this.tenDichVu = tenDichVu;
	}

	public double getGiaDichVu() {
		return giaDichVu;
	}

	public void setGiaDichVu(double giaDichVu) {
		this.giaDichVu = giaDichVu;
	}

	public HotelEntity getDichVuKhachSan() {
		return dichVuKhachSan;
	}

	public void setDichVuKhachSan(HotelEntity dichVuKhachSan) {
		this.dichVuKhachSan = dichVuKhachSan;
	}

	public RoomEntity getDichVuPhong() {
		return dichVuPhong;
	}

	public void setDichVuPhong(RoomEntity dichVuPhong) {
		this.dichVuPhong = dichVuPhong;
	}

	public SaleEntity getDichVuKhuyenMai() {
		return dichVuKhuyenMai;
	}

	public void setDichVuKhuyenMai(SaleEntity dichVuKhuyenMai) {
		this.dichVuKhuyenMai = dichVuKhuyenMai;
	}

}
