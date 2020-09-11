package com.managementhotel.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "hoadon")
public class BillEntity {
	
	@Id
	@Column(name = "mahoadon")
	private String maHoaDon;
	
	@ManyToOne
	@JoinColumn(name = "mabooking")
	private BookingEntity thongTinBooking;
	
	@ManyToOne
	@JoinColumn(name = "makhuyenmai")
	private SaleEntity thongTinKhuyenMaiHoaDon;
	
	@OneToMany(mappedBy = "hoaDonKhachHang")
	private Set<CustomerEntity> khachHangThanhToans = new HashSet<CustomerEntity>();
	
	@Column
	private Date ngayTao;
	
	@Column
	private double giaHoaDon;

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public BookingEntity getThongTinBooking() {
		return thongTinBooking;
	}

	public void setThongTinBooking(BookingEntity thongTinBooking) {
		this.thongTinBooking = thongTinBooking;
	}

	public SaleEntity getThongTinKhuyenMaiHoaDon() {
		return thongTinKhuyenMaiHoaDon;
	}

	public void setThongTinKhuyenMaiHoaDon(SaleEntity thongTinKhuyenMaiHoaDon) {
		this.thongTinKhuyenMaiHoaDon = thongTinKhuyenMaiHoaDon;
	}

	public Set<CustomerEntity> getKhachHangThanhToans() {
		return khachHangThanhToans;
	}

	public void setKhachHangThanhToans(Set<CustomerEntity> khachHangThanhToans) {
		this.khachHangThanhToans = khachHangThanhToans;
	}

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}

	public double getGiaHoaDon() {
		return giaHoaDon;
	}

	public void setGiaHoaDon(double giaHoaDon) {
		this.giaHoaDon = giaHoaDon;
	}
	
	
}
