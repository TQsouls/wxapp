package com.wxapp.responseentity.other;

import com.wxapp.requestentity.other.Account;

import java.io.Serializable;
import java.util.List;

public class ResponseAddAccountData implements Serializable {
    private List<Account> success;
    private List<Account> error;

    public ResponseAddAccountData() {
    }

    public ResponseAddAccountData(List<Account> success, List<Account> error) {
        this.success = success;
        this.error = error;
    }

    public List<Account> getSuccess() {
        return success;
    }

    public void setSuccess(List<Account> success) {
        this.success = success;
    }

    public List<Account> getError() {
        return error;
    }

    public void setError(List<Account> error) {
        this.error = error;
    }
}
