package com.programmingjd.fastmetch_client.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class Mechanic implements Serializable {

    @Expose
    String IdMechanic;
    @Expose
    String NameMechanic;
    @Expose
    String SurnameMechanic;
    @Expose
    String GenderMechanic;
    @Expose
    String PhoneMechanic;
    @Expose
    String LatitudeMechanic;
    @Expose
    String LongitudeMechanic;

    public String getIdMechanic() {
        return IdMechanic;
    }

    public void setIdMechanic(String idMechanic) {
        IdMechanic = idMechanic;
    }

    public String getNameMechanic() {
        return NameMechanic;
    }

    public void setNameMechanic(String nameMechanic) {
        NameMechanic = nameMechanic;
    }

    public String getSurnameMechanic() {
        return SurnameMechanic;
    }

    public void setSurnameMechanic(String surnameMechanic) {
        SurnameMechanic = surnameMechanic;
    }

    public String getGenderMechanic() {
        return GenderMechanic;
    }

    public void setGenderMechanic(String genderMechanic) {
        GenderMechanic = genderMechanic;
    }

    public String getPhoneMechanic() {
        return PhoneMechanic;
    }

    public void setPhoneMechanic(String phoneMechanic) {
        PhoneMechanic = phoneMechanic;
    }

    public String getLatitudeMechanic() {
        return LatitudeMechanic;
    }

    public void setLatitudeMechanic(String latitudeMechanic) {
        LatitudeMechanic = latitudeMechanic;
    }

    public String getLongitudeMechanic() {
        return LongitudeMechanic;
    }

    public void setLongitudeMechanic(String longitudeMechanic) {
        LongitudeMechanic = longitudeMechanic;
    }

    @Override
    public String toString() {
        return "Mechanic{" +
                "IdMechanic='" + IdMechanic + '\'' +
                ", NameMechanic='" + NameMechanic + '\'' +
                ", SurnameMechanic='" + SurnameMechanic + '\'' +
                ", GenderMechanic='" + GenderMechanic + '\'' +
                ", PhoneMechanic='" + PhoneMechanic + '\'' +
                ", LatitudeMechanic='" + LatitudeMechanic + '\'' +
                ", LongitudeMechanic='" + LongitudeMechanic + '\'' +
                '}';
    }

    List<Mechanic> mechanicListModel;
}
