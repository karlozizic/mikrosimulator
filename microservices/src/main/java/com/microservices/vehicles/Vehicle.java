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
    private Long id;

    private String nacinNaplate;

    private String boja;

    private int brojOsovina;

    private String VIN;

    private int idENC;

    private String registracijskaOznaka;

    private String ekoRazred;
    private String kategorija;

    private String drzavaRegistracije;

    public Vehicle(Long id, String nacinNaplate, String boja, int brojOsovina, String VIN, int idENC,
                   String registracijskaOznaka, String ekoRazred, String kategorija,
                   String drzavaRegistracije) {
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

    public Vehicle() {

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

    @Override
    public int hashCode() {
        return Objects.hash(VIN, boja, brojOsovina, drzavaRegistracije, ekoRazred, idENC, kategorija, nacinNaplate,
                registracijskaOznaka, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return brojOsovina == vehicle.brojOsovina && idENC == vehicle.idENC && Objects.equals(id, vehicle.id) && Objects.equals(nacinNaplate, vehicle.nacinNaplate) && Objects.equals(boja, vehicle.boja) && Objects.equals(VIN, vehicle.VIN) && Objects.equals(registracijskaOznaka, vehicle.registracijskaOznaka) && Objects.equals(ekoRazred, vehicle.ekoRazred) && Objects.equals(kategorija, vehicle.kategorija) && Objects.equals(drzavaRegistracije, vehicle.drzavaRegistracije);
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
