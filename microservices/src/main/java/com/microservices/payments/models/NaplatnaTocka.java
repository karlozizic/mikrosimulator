package com.microservices.payments.models;

import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@JsonRootName("NaplatnaTocka")
public class NaplatnaTocka {

    protected Long id;

    protected String oznaka;

    protected String naziv;

    protected Double stacionaza;

    protected int geografskaDuzina;

    protected int geografskaSirina;

    protected String usmjerenje;

    protected Long dionicaId;

    protected String oznakaDionice;

    public NaplatnaTocka(Long id, String oznaka, String naziv, Double stacionaza, int geografskaDuzina, int geografskaSirina, String usmjerenje, Long dionicaId, String oznakaDionice) {
        this.id = id;
        this.oznaka = oznaka;
        this.naziv = naziv;
        this.stacionaza = stacionaza;
        this.geografskaDuzina = geografskaDuzina;
        this.geografskaSirina = geografskaSirina;
        this.usmjerenje = usmjerenje;
        this.dionicaId = dionicaId;
        this.oznakaDionice = oznakaDionice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Double getStacionaza() {
        return stacionaza;
    }

    public void setStacionaza(Double stacionaza) {
        this.stacionaza = stacionaza;
    }

    public int getGeografskaDuzina() {
        return geografskaDuzina;
    }

    public void setGeografskaDuzina(int geografskaDuzina) {
        this.geografskaDuzina = geografskaDuzina;
    }

    public int getGeografskaSirina() {
        return geografskaSirina;
    }

    public void setGeografskaSirina(int geografskaSirina) {
        this.geografskaSirina = geografskaSirina;
    }

    public String getUsmjerenje() {
        return usmjerenje;
    }

    public void setUsmjerenje(String usmjerenje) {
        this.usmjerenje = usmjerenje;
    }

    public Long getDionicaId() {
        return dionicaId;
    }

    public void setDionicaId(Long dionicaId) {
        this.dionicaId = dionicaId;
    }

    public String getOznakaDionice() {
        return oznakaDionice;
    }

    public void setOznakaDionice(String oznakaDionice) {
        this.oznakaDionice = oznakaDionice;
    }
}
