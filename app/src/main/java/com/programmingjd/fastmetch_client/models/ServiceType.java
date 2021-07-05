package com.programmingjd.fastmetch_client.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class ServiceType implements Serializable {

    @Expose
    String IdServiceType;
    @Expose
    String NameServiceType;
    @Expose
    String DescriptionServiceType;

    public String getIdServiceType() {
        return IdServiceType;
    }

    public void setIdServiceType(String idServiceType) {
        IdServiceType = idServiceType;
    }

    public String getNameServiceType() {
        return NameServiceType;
    }

    public void setNameServiceType(String nameServiceType) {
        NameServiceType = nameServiceType;
    }

    public String getDescriptionServiceType() {
        return DescriptionServiceType;
    }

    public void setDescriptionServiceType(String descriptionServiceType) {
        DescriptionServiceType = descriptionServiceType;
    }

    @Override
    public String toString() {
        return "ServiceType{" +
                "IdServiceType='" + IdServiceType + '\'' +
                ", NameServiceType='" + NameServiceType + '\'' +
                ", DescriptionServiceType='" + DescriptionServiceType + '\'' +
                '}';
    }
}
