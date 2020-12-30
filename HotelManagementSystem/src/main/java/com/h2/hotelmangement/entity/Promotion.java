package com.h2.hotelmangement.entity;

import com.h2.hotelmangement.model.dto.BaseDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "promotions")
public class Promotion extends BaseEntity implements Serializable {

    //khuyen mai nay t nghi la minh tao ra xong roi se send code cho khach hang than thiet hay VIP gi do quan email hay sdt roi khi nguoi ta book hoac thanh toan
    //thi nhap vo xong khuyen mai cho ho de giam do phuc tap
    //lam them chuc nang mail

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promoid")
    private Long promoId;

    @Column(name = "code", nullable = false, unique = true, length = 20)
    private String promotionCode;

    //phan tram khuyen mai
    @Column(name = "percent", nullable = false)
    private float percent;

    @Column(name = "custype", nullable = false)
    private String cusType;

    @Column(name = "startdate", nullable = false)
    private String startDate;

    @Column(name = "enddate", nullable = false)
    private String endDate;

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

    public Long getPromoId() {
        return promoId;
    }

    public void setPromoId(Long promoId) {
        this.promoId = promoId;
    }

    public String getCusType() {
        return cusType;
    }

    public void setCusType(String cusType) {
        this.cusType = cusType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "promoId=" + promoId +
                ", promotionCode='" + promotionCode + '\'' +
                ", percent=" + percent +
                ", cusType='" + cusType + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}