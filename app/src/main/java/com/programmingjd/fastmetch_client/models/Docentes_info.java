package com.programmingjd.fastmetch_client.models;

import java.io.Serializable;

public class Docentes_info implements Serializable {

    String Id;
    String Cedula;
    String NombreCompleto;
    String Direccion;
    String FechaContrato;
    String Salario;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        NombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getFechaContrato() {
        return FechaContrato;
    }

    public void setFechaContrato(String fechaContrato) {
        FechaContrato = fechaContrato;
    }

    public String getSalario() {
        return Salario;
    }

    public void setSalario(String salario) {
        Salario = salario;
    }
}
