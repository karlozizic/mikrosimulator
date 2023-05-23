package com.microservices.vehicles.models;


public class Dionica {

    private Long id;

    private String smjer;

    private int najvecaBrzina;

    private int brojTraka;

    private String oznaka;

    private Double pocetnaStacionaza;

    private Double zavrsnaStacionaza;

    public String oznakaAutoceste;
    public Long slijediId;

    public Long prethodiId;

    public Dionica(Long dionicaId, String smjer, int najvecaBrzina, int brojTraka, String oznaka, Double pocetnaStacionaza,
                   Double zavrsnaStacionaza, String oznakaAutoceste, Long slijediId, Long prethodiId) {
        super();
        this.id = dionicaId;
        this.smjer = smjer;
        this.najvecaBrzina = najvecaBrzina;
        this.brojTraka = brojTraka;
        this.oznaka = oznaka;
        this.pocetnaStacionaza = pocetnaStacionaza;
        this.zavrsnaStacionaza = zavrsnaStacionaza;
        this.oznakaAutoceste = oznakaAutoceste;
        this.slijediId = slijediId;
        this.prethodiId = prethodiId;
    }

    public Dionica(String smjer, int najvecaBrzina, int brojTraka, String oznaka, Double pocetnaStacionaza, Double zavrsnaStacionaza, Long slijediId, Long prethodiId) {
        this.smjer = smjer;
        this.najvecaBrzina = najvecaBrzina;
        this.brojTraka = brojTraka;
        this.oznaka = oznaka;
        this.pocetnaStacionaza = pocetnaStacionaza;
        this.zavrsnaStacionaza = zavrsnaStacionaza;
        this.slijediId = slijediId;
        this.prethodiId = prethodiId;
    }
    public Long getDionicaId() {
        return id;
    }

    public void setDionicaId(Long dionicaId) {
        this.id = dionicaId;
    }

    public String getSmjer() {
        return smjer;
    }

    public void setSmjer(String smjer) {
        this.smjer = smjer;
    }

    public int getNajvecaBrzina() {
        return najvecaBrzina;
    }

    public void setNajvecaBrzina(int najvecaBrzina) {
        this.najvecaBrzina = najvecaBrzina;
    }

    public int getBrojTraka() {
        return brojTraka;
    }

    public void setBrojTraka(int brojTraka) {
        this.brojTraka = brojTraka;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public Double getPocetnaStacionaza() {
        return pocetnaStacionaza;
    }

    public void setPocetnaStacionaza(Double pocetnaStacionaza) {
        this.pocetnaStacionaza = pocetnaStacionaza;
    }

    public Double getZavrsnaStacionaza() {
        return zavrsnaStacionaza;
    }

    public void setZavrsnaStacionaza(Double zavrsnaStacionaza) {
        this.zavrsnaStacionaza = zavrsnaStacionaza;
    }

    public Long getSlijediId() {
        return slijediId;
    }

    public void setSlijediId(Long slijediId) {
        this.slijediId = slijediId;
    }

    public Long getPrethodiId() {
        return prethodiId;
    }

    public void setPrethodiId(Long prethodiId) {
        this.prethodiId = prethodiId;
    }
}