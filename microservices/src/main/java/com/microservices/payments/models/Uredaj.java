package com.microservices.payments.models;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("Uredaj")
public class Uredaj {

    protected Long id;

    protected int uredajType;

    protected String name;

    protected Long naplatnaTockaId;

    public Uredaj(Long id, int uredajType, String name, Long naplatnaTockaId) {
        this.id = id;
        this.uredajType = uredajType;
        this.name = name;
        this.naplatnaTockaId = naplatnaTockaId;
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

}
