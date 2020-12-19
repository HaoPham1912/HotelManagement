package com.h2.hotelmangement.common.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;

public class BaseResponse {

    static final String SUCCESS = "success";
    static final String ERROR = "error";

    private String status;

    /**
     * Error messages, if any
     */
    private String message;

    /**
     * To show or hide message popup after call API at FE client
     */
    private Boolean isShowMessage;

    /**
     *
     */
    private Instant timestamp;

    /**
     *
     */
    private String requestPath;

    public BaseResponse() {
        this.setStatus(SUCCESS);
        this.setMessage("");
        this.setShowMessage(false);
        this.setTimestamp(new Date().toInstant());
        HttpServletRequest currentHttpRequest = this.getCurrentHttpRequest();
        if (currentHttpRequest != null) {
            this.setRequestPath(currentHttpRequest.getRequestURL().toString());
        } else {
            this.setRequestPath("");
        }
    }
    public BaseResponse(boolean isSuccess) {
        if (isSuccess) {
            this.setStatus(SUCCESS);
            this.setMessage("");
            this.setShowMessage(false);
        } else {
            this.setStatus(ERROR);
            this.setMessage("");
            this.setShowMessage(false);
        }

        this.setTimestamp(new Date().toInstant());
        HttpServletRequest currentHttpRequest = this.getCurrentHttpRequest();
        if (currentHttpRequest != null) {
            this.setRequestPath(currentHttpRequest.getRequestURL().toString());
        } else {
            this.setRequestPath("");
        }

    }

    private HttpServletRequest getCurrentHttpRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
            return request;
        }
        return null;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getShowMessage() {
        return isShowMessage;
    }

    public void setShowMessage(Boolean showMessage) {
        isShowMessage = showMessage;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", isShowMessage=" + isShowMessage +
                ", timestamp=" + timestamp +
                ", requestPath='" + requestPath + '\'' +
                '}';
    }
}
