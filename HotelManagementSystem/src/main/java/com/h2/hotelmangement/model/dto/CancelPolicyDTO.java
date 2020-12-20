package com.h2.hotelmangement.model.dto;

public class CancelPolicyDTO {
    private int policyId;
    private String policyCode;
    private String title;
    private String detail;
    private String rangeDate;

    public String getRangeDate() {
        return rangeDate;
    }

    public void setRangeDate(String rangeDate) {
        this.rangeDate = rangeDate;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getPolicyCode() {
        return policyCode;
    }

    public void setPolicyCode(String policyCode) {
        this.policyCode = policyCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "CancelPolicyDTO{" +
                "policyId='" + policyId + '\'' +
                ", policyCode='" + policyCode + '\'' +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", rangeDate='" + rangeDate + '\'' +
                '}';
    }
}
