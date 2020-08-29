package com.entity;

import lombok.Getter;

@Getter
public class RestResp {
    public static final int STATUS_OK = 0;
    public static final int STATUS_ERROR = 1;
    public static final int STATUS_INVALID_TOKEN = -1;
    public static final int STATUS_INVALID_REQUEST = -2;

    private final int status;
    private final Object data;
    private Object data2;
    private final String message;

    private RestResp(int status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static RestResp ok(Object data) {
        return new RestResp(STATUS_OK, data, null);
    }

    public static RestResp ok(Object data, Object data2) {
        RestResp resp = new RestResp(STATUS_OK, data, null);
        resp.data2 = data2;
        return resp;
    }

    public static RestResp ok() {
        return new RestResp(STATUS_OK, null, null);
    }

    public static RestResp error() {
        return new RestResp(STATUS_ERROR, null, null);
    }

    public static RestResp error(String message) {
        return new RestResp(STATUS_ERROR, null, message);
    }

    public static RestResp invalidToken() {
        return new RestResp(STATUS_INVALID_TOKEN, null, "invalid token");
    }

    public static RestResp invalidRequest() {
        return new RestResp(STATUS_INVALID_REQUEST, null, "invalid request");
    }
}
