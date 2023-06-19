package com.microservices.payments.models;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("Uredaj")
public class Uredaj {

    protected Long id;

    protected int kvar;

    protected String razinaPouzdanosti;

    protected int uredajType;

    protected String name;

    protected Long naplatnaTockaId;

    public Uredaj(Long id, int uredajType, String name, Long naplatnaTockaId, int kvar, String razinaPouzdanosti) {
        this.id = id;
        this.uredajType = uredajType;
        this.name = name;
        this.naplatnaTockaId = naplatnaTockaId;
        this.kvar = kvar;
        this.razinaPouzdanosti = razinaPouzdanosti;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUredajType() {
        return uredajType;
    }

    public void setUredajType(int uredajType) {
        this.uredajType = uredajType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNaplatnaTockaId() {
        return naplatnaTockaId;
    }

    public void setNaplatnaTockaId(Long naplatnaTockaId) {
        this.naplatnaTockaId = naplatnaTockaId;
    }

    public int getKvar() {
        return kvar;
    }

    public void setKvar(int kvar) {
        this.kvar = kvar;
    }

    public String getRazinaPouzdanosti() {
        return razinaPouzdanosti;
    }

    public void setRazinaPouzdanosti(String razinaPouzdanosti) {
        this.razinaPouzdanosti = razinaPouzdanosti;
    }
}
