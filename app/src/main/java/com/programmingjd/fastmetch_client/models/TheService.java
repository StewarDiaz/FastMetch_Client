package com.programmingjd.fastmetch_client.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class TheService implements Serializable {

    @Expose
    String IdTheService;
    @Expose
    String NameTheService;
    @Expose
    String DescriptionTheService;
    @Expose
    int IdServiceType;

    public String getIdTheService() {
        return IdTheService;
    }

    public void setIdTheService(String idTheService) {
        IdTheService = idTheService;
    }

    public String getNameTheService() {
        return NameTheService;
    }

    public void setNameTheService(String nameTheService) {
        NameTheService = nameTheService;
    }

    public String getDescriptionTheService() {
        return DescriptionTheService;
    }

    public void setDescriptionTheService(String descriptionTheService) {
        DescriptionTheService = descriptionTheService;
    }

    public int getIdServiceType() {
        return IdServiceType;
    }

    public void setIdServiceType(int idServiceType) {
        IdServiceType = idServiceType;
    }

    public TheService(String idTheService, String nameTheService, String descriptionTheService, int idServiceType) {
        IdTheService = idTheService;
        NameTheService = nameTheService;
        DescriptionTheService = descriptionTheService;
        IdServiceType = idServiceType;
    }

    public TheService() {
    }

    @Override
    public String toString() {
        return  NameTheService;
    }
}
