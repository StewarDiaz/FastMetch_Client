package com.programmingjd.fastmetch_client.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class Client implements Serializable {
    @Expose
    String IdClient;   //para el post no se usa
    @Expose
    String NameClient;
    @Expose
    String SurnameClient;
    @Expose
    String IdentificationNumberClient;
    @Expose
    String GenderClient;
    @Expose
    String PhoneClient;
    @Expose
    String emailAddressClient;
    @Expose
    String VehicleClient;
    @Expose
    int IdCity;
    @Expose
    int IdTypeDocument;

    public String getIdClient() {
        return IdClient;
    }

    public void setIdClient(String idClient) {
        IdClient = idClient;
    }

    public String getNameClient() {
        return NameClient;
    }

    public void setNameClient(String nameClient) {
        NameClient = nameClient;
    }

    public String getSurnameClient() {
        return SurnameClient;
    }

    public void setSurnameClient(String surnameClient) {
        SurnameClient = surnameClient;
    }

    public String getIdentificationNumberClient() {
        return IdentificationNumberClient;
    }

    public void setIdentificationNumberClient(String identificationNumberClient) {
        IdentificationNumberClient = identificationNumberClient;
    }

    public String getGenderClient() {
        return GenderClient;
    }

    public void setGenderClient(String genderClient) {
        GenderClient = genderClient;
    }

    public String getPhoneClient() {
        return PhoneClient;
    }

    public void setPhoneClient(String phoneClient) {
        PhoneClient = phoneClient;
    }

    public String getVehicleClient() {
        return VehicleClient;
    }

    public void setVehicleClient(String vehicleClient) {
        VehicleClient = vehicleClient;
    }

    public int getIdCity() {
        return IdCity;
    }

    public void setIdCity(int idCity) {
        IdCity = idCity;
    }

    public int getIdTypeDocument() {
        return IdTypeDocument;
    }

    public void setIdTypeDocument(int idTypeDocument) {
        IdTypeDocument = idTypeDocument;
    }

    public String getEmailAddressClient() {
        return emailAddressClient;
    }

    public void setEmailAddressClient(String emailAddressClient) {
        this.emailAddressClient = emailAddressClient;
    }

    @Override
    public String toString() {
        return "Client{" +
                "IdClient='" + IdClient + '\'' +
                ", NameClient='" + NameClient + '\'' +
                ", SurnameClient='" + SurnameClient + '\'' +
                ", IdentificationNumberClient='" + IdentificationNumberClient + '\'' +
                ", GenderClient='" + GenderClient + '\'' +
                ", PhoneClient='" + PhoneClient + '\'' +
                ", emailAddressClient='" + emailAddressClient + '\'' +
                ", VehicleClient='" + VehicleClient + '\'' +
                ", IdCity=" + IdCity +
                ", IdTypeDocument=" + IdTypeDocument +
                ", clientListModel=" + clientListModel +
                '}';
    }

    List<Client> clientListModel;
}
