package com.h2.hotelmangement.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "promotions")
public class Promotion {

    //khuyen mai nay t nghi la minh tao ra xong roi se send code cho khach hang than thiet hay VIP gi do quan email hay sdt roi khi nguoi ta book hoac thanh toan
    //thi nhap vo xong khuyen mai cho ho de giam do phuc tap
    //lam them chuc nang mail

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promoid")
    private Long promoid;

    @Column(name = "code", nullable = false, unique = true, length = 20)
    private String promotionCode;

    //phan tram khuyen mai
    @Column(name = "percent", nullable = false)
    private float percent;

    @Column(name = "custype", nullable =  false)
    private String custype;

    @Column(name = "startdate", nullable = false)
    private String startdate;

    @Column(name = "enddate", nullable = false)
    private String enddate;

    public Long getPromoid() {
        return promoid;
    }

    public void setPromoid(Long promoid) {
        this.promoid = promoid;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public String getCustype() {
        return custype;
    }

    public void setCustype(String custype) {
        this.custype = custype;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}