package com.h2.hotelmangement.common.bean;

public class ErrorBean {
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{code:").append(code).append(", ");
        sb.append("message:").append(message).append('}');
        return sb.toString();
    }
}
