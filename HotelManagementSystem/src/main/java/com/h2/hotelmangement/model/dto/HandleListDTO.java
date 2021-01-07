package com.h2.hotelmangement.model.dto;

import java.util.ArrayList;
import java.util.List;

public class HandleListDTO {

    private List<Long> longListIdBed = new ArrayList<>();
    private List<Long> longListIdService = new ArrayList<>();

    private Long bedId;
    private Long serviceId;

    public Long getBedId() {
        return bedId;
    }

    public void setBedId(Long bedId) {
        this.bedId = bedId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public List<Long> getLongListIdBed() {
        return longListIdBed;
    }

    public void setLongListIdBed(List<Long> longListIdBed) {
        this.longListIdBed = longListIdBed;
    }

    public List<Long> getLongListIdService() {
        return longListIdService;
    }

    public void setLongListIdService(List<Long> longListIdService) {
        this.longListIdService = longListIdService;
    }
}
