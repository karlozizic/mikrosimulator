package com.microservices.services.web;


import com.fasterxml.jackson.annotation.JsonRootName;

//interacts with WebVehiclesService
@JsonRootName("Vehicle")
public class Vehicle {

    protected Long id;
    protected String nacinNaplate;
    protected String boja;
    protected int brojOsovina;
    protected int VIN;
    protected int idENC;
    protected String registracijskaOznaka;
    protected String ekoRazred;
    protected String kategorija;
    protected String drzavaRegistracije;

    protected Vehicle() {
    }

    protected Vehicle(Long id, String nacinNaplate, String boja, int brojOsovina, int VIN, int idENC, String registracijskaOznaka,
                      String ekoRazred, String kategorija, String drzavaRegistracije) {
        super();
        this.id = id;
        this.nacinNaplate = nacinNaplate;
        this.boja = boja;
        this.brojOsovina = brojOsovina;
        this.VIN = VIN;
        this.idENC = idENC;
        this.registracijskaOznaka = registracijskaOznaka;
        this.ekoRazred = ekoRazred;
        this.kategorija = kategorija;
        this.drzavaRegistracije = drzavaRegistracije;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNacinNaplate() {
        return nacinNaplate;
    }

    public void setNacinNaplate(String nacinNaplate) {
        this.nacinNaplate = nacinNaplate;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    public int getBrojOsovina() {
        return brojOsovina;
    }

    public void setBrojOsovina(int brojOsovina) {
        this.brojOsovina = brojOsovina;
    }

    public int getVIN() {
        return VIN;
    }

    public void setVIN(int VIN) {
        this.VIN = VIN;
    }

    public int getIdENC() {
        return idENC;
    }

    public void setIdENC(int idENC) {
        this.idENC = idENC;
    }

    public String getRegistracijskaOznaka() {
        return registracijskaOznaka;
    }

    public void setRegistracijskaOznaka(String registracijskaOznaka) {
        this.registracijskaOznaka = registracijskaOznaka;
    }

    public String getEkoRazred() {
        return ekoRazred;
    }

    public void setEkoRazred(String ekoRazred) {
        this.ekoRazred = ekoRazred;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public String getDrzavaRegistracije() {
        return drzavaRegistracije;
    }

    public void setDrzavaRegistracije(String drzavaRegistracije) {
        this.drzavaRegistracije = drzavaRegistracije;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", nacinNaplate='" + nacinNaplate + '\'' +
                ", boja='" + boja + '\'' +
                ", brojOsovina=" + brojOsovina +
                ", VIN=" + VIN +
                ", idENC=" + idENC +
                ", registracijskaOznaka='" + registracijskaOznaka + '\'' +
                ", ekoRazred='" + ekoRazred + '\'' +
                ", kategorija='" + kategorija + '\'' +
                ", drzavaRegistracije='" + drzavaRegistracije + '\'' +
                '}';
    }
}
