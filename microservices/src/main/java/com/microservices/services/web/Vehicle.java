package com.microservices.services.web;


import com.fasterxml.jackson.annotation.JsonRootName;

import java.sql.Timestamp;

//interacts with WebVehiclesService
@JsonRootName("Vehicle")
public class Vehicle {

    protected Long id;
    protected String nacinNaplate;
    protected String boja;
    protected int brojOsovina;
    protected String VIN;
    protected int idENC;
    protected String registracijskaOznaka;
    protected String ekoRazred;
    protected String kategorija;
    protected String drzavaRegistracije;

    private String pocetnaDionicaOznaka;

    private Long pocetnaDionicaId;
    private String zavrsnaDionicaOznaka;

    private Long zavrsnaDionicaId;

    private float prosjecnaBrzina;

    private Timestamp vrijeme;

    protected Vehicle() {
    }

    protected Vehicle(Long id, String nacinNaplate, String boja, int brojOsovina, String VIN, int idENC, String registracijskaOznaka,
                      String ekoRazred, String kategorija, String drzavaRegistracije,
                      String pocetnaDionicaOznaka, Long pocetnaDionicaId, String zavrsnaDionicaOznaka, Long zavrsnaDionicaId, float prosjecnaBrzina,
                      Timestamp vrijeme) {
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
        this.pocetnaDionicaOznaka = pocetnaDionicaOznaka;
        this.pocetnaDionicaId = pocetnaDionicaId;
        this.zavrsnaDionicaOznaka = zavrsnaDionicaOznaka;
        this.zavrsnaDionicaId = zavrsnaDionicaId;
        this.prosjecnaBrzina = prosjecnaBrzina;
        this.vrijeme = vrijeme;
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

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
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

    public String getPocetnaDionicaOznaka() {
        return pocetnaDionicaOznaka;
    }

    public void setPocetnaDionicaOznaka(String pocetnaDionicaOznaka) {
        this.pocetnaDionicaOznaka = pocetnaDionicaOznaka;
    }

    public Long getPocetnaDionicaId() {
        return pocetnaDionicaId;
    }

    public void setPocetnaDionicaId(Long pocetnaDionicaId) {
        this.pocetnaDionicaId = pocetnaDionicaId;
    }

    public String getZavrsnaDionicaOznaka() {
        return zavrsnaDionicaOznaka;
    }

    public void setZavrsnaDionicaOznaka(String zavrsnaDionicaOznaka) {
        this.zavrsnaDionicaOznaka = zavrsnaDionicaOznaka;
    }

    public Long getZavrsnaDionicaId() {
        return zavrsnaDionicaId;
    }

    public void setZavrsnaDionicaId(Long zavrsnaDionicaId) {
        this.zavrsnaDionicaId = zavrsnaDionicaId;
    }

    public float getProsjecnaBrzina() {
        return prosjecnaBrzina;
    }

    public void setProsjecnaBrzina(float prosjecnaBrzina) {
        this.prosjecnaBrzina = prosjecnaBrzina;
    }

    public Timestamp getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(Timestamp vrijeme) {
        this.vrijeme = vrijeme;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", nacinNaplate='" + nacinNaplate + '\'' +
                ", boja='" + boja + '\'' +
                ", brojOsovina=" + brojOsovina +
                ", VIN='" + VIN + '\'' +
                ", idENC=" + idENC +
                ", registracijskaOznaka='" + registracijskaOznaka + '\'' +
                ", ekoRazred='" + ekoRazred + '\'' +
                ", kategorija='" + kategorija + '\'' +
                ", drzavaRegistracije='" + drzavaRegistracije + '\'' +
                ", pocetnaDionicaOznaka='" + pocetnaDionicaOznaka + '\'' +
                ", pocetnaDionicaId=" + pocetnaDionicaId +
                ", zavrsnaDionicaOznaka='" + zavrsnaDionicaOznaka + '\'' +
                ", zavrsnaDionicaId=" + zavrsnaDionicaId +
                '}';
    }
}
