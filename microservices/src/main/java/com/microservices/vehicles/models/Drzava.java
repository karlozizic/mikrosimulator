package com.microservices.vehicles.models;

import java.util.Objects;

public class Drzava {

    public Long id;

    public String naziv;

    public Drzava(Long id, String naziv) {
        super();
        this.id = id;
        this.naziv = naziv;
    }

    public Drzava() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Drzava other = (Drzava) obj;
        return Objects.equals(id, other.id) && Objects.equals(naziv, other.naziv);
    }


}