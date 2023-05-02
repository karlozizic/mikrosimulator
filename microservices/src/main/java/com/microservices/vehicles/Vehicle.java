package com.microservices.vehicles;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="VEHICLE")
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nacinNaplate;

    private String boja;

    private int brojOsovina;

    private int VIN;

    private int idENC;

    private String registracijskaOznaka;

    private String ekoRazred;
    private String kategorija;

    private String drzavaRegistracije;

    public Vehicle(Long voziloId, String nacinNaplate, String boja, int brojOsovina, int vIN, int idENC,
                   String registracijskaOznaka, String ekoRazred, String kategorija,
                   String drzavaRegistracije) {
        super();
        this.id = voziloId;
        this.nacinNaplate = nacinNaplate;
        this.boja = boja;
        this.brojOsovina = brojOsovina;
        VIN = vIN;
        this.idENC = idENC;
        this.registracijskaOznaka = registracijskaOznaka;
        this.ekoRazred = ekoRazred;
        this.kategorija = kategorija;
        this.drzavaRegistracije = drzavaRegistracije;
    }

    public Vehicle() {

    }

    public Long getVoziloId() {
        return id;
    }

    public void setVoziloId(Long voziloId) {
        this.id = voziloId;
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

    public void setVIN(int vIN) {
        VIN = vIN;
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
    public int hashCode() {
        return Objects.hash(VIN, boja, brojOsovina, drzavaRegistracije, ekoRazred, idENC, kategorija, nacinNaplate,
                registracijskaOznaka, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vehicle other = (Vehicle) obj;
        return VIN == other.VIN && Objects.equals(boja, other.boja) && brojOsovina == other.brojOsovina
                && Objects.equals(drzavaRegistracije, other.drzavaRegistracije)
                && Objects.equals(ekoRazred, other.ekoRazred) && idENC == other.idENC
                && Objects.equals(kategorija, other.kategorija) && Objects.equals(nacinNaplate, other.nacinNaplate)
                && Objects.equals(registracijskaOznaka, other.registracijskaOznaka)
                && Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "nacinNaplate='" + nacinNaplate + '\'' +
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
