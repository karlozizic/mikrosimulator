package project.backend.model;

public class NovaNaplatnaTocka {

    private Long id;

    private String oznaka;

    private String naziv;

    private String stacionaza;

    private int geografskaDuzina;

    private int geografskaSirina;

    private String usmjerenje;

    private Long dionicaId;

    public NovaNaplatnaTocka(Long naplatnaTockaId, String oznaka, String naziv, String stacionaza, int geografskaDuzina,
                         int geografskaSirina, String usmjerenje, Long dionicaId) {
        super();
        this.id = naplatnaTockaId;
        this.oznaka = oznaka;
        this.naziv = naziv;
        this.stacionaza = stacionaza;
        this.geografskaDuzina = geografskaDuzina;
        this.geografskaSirina = geografskaSirina;
        this.usmjerenje = usmjerenje;
        this.dionicaId = dionicaId;
    }

    public NovaNaplatnaTocka() {

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

    public String getStacionaza() {
        return stacionaza;
    }

    public void setStacionaza(String stacionaza) {
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
}
