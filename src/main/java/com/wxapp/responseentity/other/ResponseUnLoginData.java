package com.wxapp.responseentity.other;

import java.io.Serializable;
import java.util.List;

public class ResponseUnLoginData implements Serializable {
    private List<String> success;
    private List<String> error;

    public ResponseUnLoginData() {
    }

    public ResponseUnLoginData(List<String> success, List<String> error) {
        this.success = success;
        this.error = error;
    }

    public List<String> getSuccess() {
        return success;
    }

    public void setSuccess(List<String> success) {
        this.success = success;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }
}
