package project.backend.model;

public class NovaDionica
{
    private Long dionicaId;

    private String smjer;

    private int najvecaBrzina;

    private int brojTraka;

    private String oznaka;

    private int pocetnaStacionaza;

    private int zavrsnaStacionaza;

    public Long slijediId;

    public Long prethodiId;

    public NovaDionica(Long dionicaId, String smjer, int najvecaBrzina, int brojTraka, String oznaka, int pocetnaStacionaza, int zavrsnaStacionaza, Long slijediId, Long prethodiId) {
        this.dionicaId = dionicaId;
        this.smjer = smjer;
        this.najvecaBrzina = najvecaBrzina;
        this.brojTraka = brojTraka;
        this.oznaka = oznaka;
        this.pocetnaStacionaza = pocetnaStacionaza;
        this.zavrsnaStacionaza = zavrsnaStacionaza;
        this.slijediId = slijediId;
        this.prethodiId = prethodiId;
    }

    public NovaDionica(String smjer, int najvecaBrzina, int brojTraka, String oznaka, int pocetnaStacionaza, int zavrsnaStacionaza, Long slijediId, Long prethodiId) {
        this.smjer = smjer;
        this.najvecaBrzina = najvecaBrzina;
        this.brojTraka = brojTraka;
        this.oznaka = oznaka;
        this.pocetnaStacionaza = pocetnaStacionaza;
        this.zavrsnaStacionaza = zavrsnaStacionaza;
        this.slijediId = slijediId;
        this.prethodiId = prethodiId;
    }

    public NovaDionica() {}

    public Long getDionicaId() {
        return dionicaId;
    }

    public void setDionicaIdId(Long id) {
        this.dionicaId = id;
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

    public int getPocetnaStacionaza() {
        return pocetnaStacionaza;
    }

    public void setPocetnaStacionaza(int pocetnaStacionaza) {
        this.pocetnaStacionaza = pocetnaStacionaza;
    }

    public int getZavrsnaStacionaza() {
        return zavrsnaStacionaza;
    }

    public void setZavrsnaStacionaza(int zavrsnaStacionaza) {
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
