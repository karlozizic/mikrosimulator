package com.microservices.payments;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name="PAYMENT")
public class Payment implements Serializable {

    @Id
    private Long id;

    private Timestamp vrijemeOcitanja;

    private String uredaj;

    private String naplatnaTocka;

    private String kategorija;

    private int idENC;

    private String registracijskaOznaka;

    public Payment(Long id, Timestamp vrijemeOcitanja, String uredaj, String naplatnaTocka, String kategorija, int idENC, String registracijskaOznaka) {
        super();
        this.id = id;
        this.vrijemeOcitanja = vrijemeOcitanja;
        this.uredaj = uredaj;
        this.naplatnaTocka = naplatnaTocka;
        this.kategorija = kategorija;
        this.idENC = idENC;
        this.registracijskaOznaka = registracijskaOznaka;
    }

    public Payment() {
    }

    public Timestamp getVrijemeOcitanja() {
        return vrijemeOcitanja;
    }

    public void setVrijemeOcitanja(Timestamp vrijemeOcitanja) {
        this.vrijemeOcitanja = vrijemeOcitanja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUredaj() {
        return uredaj;
    }

    public void setUredaj(String uredaj) {
        this.uredaj = uredaj;
    }

    public String getNaplatnaTocka() {
        return naplatnaTocka;
    }

    public void setNaplatnaTocka(String naplatnaTocka) {
        this.naplatnaTocka = naplatnaTocka;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return idENC == payment.idENC && Objects.equals(id, payment.id) && Objects.equals(vrijemeOcitanja, payment.vrijemeOcitanja) && Objects.equals(uredaj, payment.uredaj) && Objects.equals(naplatnaTocka, payment.naplatnaTocka) && Objects.equals(kategorija, payment.kategorija) && Objects.equals(registracijskaOznaka, payment.registracijskaOznaka);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vrijemeOcitanja, uredaj, naplatnaTocka, kategorija, idENC, registracijskaOznaka);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", vrijemeOcitanja=" + vrijemeOcitanja +
                ", uredaj='" + uredaj + '\'' +
                ", naplatnaTocka='" + naplatnaTocka + '\'' +
                ", kategorija='" + kategorija + '\'' +
                ", idENC=" + idENC +
                ", registracijskaOznaka='" + registracijskaOznaka + '\'' +
                '}';
    }
}
