package com.microservices.payments.models;

import java.sql.Timestamp;

public class Vrijeme {

    protected Timestamp pocetnoVrijeme;
    protected Timestamp zavrsnoVrijeme;

    public Vrijeme(Timestamp pocetnoVrijeme, Timestamp zavrsnoVrijeme) {
        this.pocetnoVrijeme = pocetnoVrijeme;
        this.zavrsnoVrijeme = zavrsnoVrijeme;
    }

    public Timestamp getPocetnoVrijeme() {
        return pocetnoVrijeme;
    }

    public void setPocetnoVrijeme(Timestamp pocetnoVrijeme) {
        this.pocetnoVrijeme = pocetnoVrijeme;
    }

    public Timestamp getZavrsnoVrijeme() {
        return zavrsnoVrijeme;
    }

    public void setZavrsnoVrijeme(Timestamp zavrsnoVrijeme) {
        this.zavrsnoVrijeme = zavrsnoVrijeme;
    }
}
