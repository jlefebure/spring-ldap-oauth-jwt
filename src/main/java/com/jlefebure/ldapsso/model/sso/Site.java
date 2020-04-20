package com.jlefebure.ldapsso.model.sso;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;


@JsonDeserialize
public class Site implements Serializable {

    private String code;
    private String label;
    private String address;
    private String city;

    public Site(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public Site() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
