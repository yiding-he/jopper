package com.hyd.jopper.meta;

import java.util.HashMap;
import java.util.Map;

public class Resp {

    public static Resp success() {
        Resp resp = new Resp();
        resp.success = true;
        return resp;
    }

    public static Resp fail(String message) {
        Resp resp = new Resp();
        resp.success = false;
        resp.message = message;
        return resp;
    }

    private boolean success;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Resp append(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
