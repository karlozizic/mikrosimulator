package com.microservices.vehicles;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
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

    private String oznakaAutoceste;
    private String pocetnaDionicaOznaka;

    private Long pocetnaDionicaId;
    private String zavrsnaDionicaOznaka;

    private Long zavrsnaDionicaId;

    private float prosjecnaBrzina;

    public Vehicle(Long id, String nacinNaplate, String boja, int brojOsovina, String VIN, int idENC,
                   String registracijskaOznaka, String ekoRazred, String kategorija,
                   String drzavaRegistracije, String oznakaAutoceste, String pocetnaDionicaOznaka,
                   Long pocetnaDionicaId, String zavrsnaDionicaOznaka, Long zavrsnaDionicaId, float prosjecnaBrzina) {
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
        this.oznakaAutoceste = oznakaAutoceste;
        this.pocetnaDionicaOznaka = pocetnaDionicaOznaka;
        this.pocetnaDionicaId = pocetnaDionicaId;
        this.zavrsnaDionicaOznaka = zavrsnaDionicaOznaka;
        this.zavrsnaDionicaId = zavrsnaDionicaId;
        this.prosjecnaBrzina = prosjecnaBrzina;
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

    public String getOznakaAutoceste() {
        return oznakaAutoceste;
    }

    public void setOznakaAutoceste(String oznakaAutoceste) {
        this.oznakaAutoceste = oznakaAutoceste;
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
