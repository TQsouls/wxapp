package com.wxapp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * {
 *   "currentPhoneNo": "string",
 *   "phoneNos": [
 *     "string"
 *   ],
 *   "wxId": "string"
 * }
 */
//通讯录对象
public class Contrat implements Serializable {
    private String currentPhoneNo;
    private List<String> phoneNos;
    private String wxId;

    public Contrat( ) {
    }

    public Contrat(String currentPhoneNo, List<String> phoneNos, String wxId) {
        this.currentPhoneNo = currentPhoneNo;
        this.phoneNos = phoneNos;
        this.wxId = wxId;
    }

    public String getCurrentPhoneNo() {
        return currentPhoneNo;
    }

    public void setCurrentPhoneNo(String currentPhoneNo) {
        this.currentPhoneNo = currentPhoneNo;
    }

    public List<String> getPhoneNos() {
        return phoneNos;
    }

    public void setPhoneNos(List<String> phoneNos) {
        this.phoneNos = phoneNos;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }
}
