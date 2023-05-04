package com.microservices.vehicles.models;

public class Ekorazred {

    public Long id;

    public String naziv;

    public Ekorazred(Long id, String naziv) {
        super();
        this.id = id;
        this.naziv = naziv;
    }

    public Ekorazred() {

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
    public String toString() {
        return "Ekorazred{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                '}';
    }
}
