package project.backend.model;

public class NoviUredaj {

    private Long id = null;
    private int uredajtype;
    private String name;

    private Long naplatnaTockaId;

    private boolean kvar;

    private float razinaPouzdanosti;

    public NoviUredaj(int uredajtype, String name, Long naplatnaTockaId, boolean kvar, float razinaPouzdanosti) {
        this.uredajtype = uredajtype;
        this.name = name;
        this.naplatnaTockaId = naplatnaTockaId;
        this.kvar = kvar;
        this.razinaPouzdanosti = razinaPouzdanosti;
    }

    public NoviUredaj(int uredajtype, String name, Long id, Long naplatnaTockaId, boolean kvar, float razinaPouzdanosti) {
        this.uredajtype = uredajtype;
        this.name = name;
        this.id = id;
        this.naplatnaTockaId = naplatnaTockaId;
        this.kvar = kvar;
        this.razinaPouzdanosti = razinaPouzdanosti;
    }

    public NoviUredaj() {

    }

    public int getUredajtype() {
        return uredajtype;
    }

    public void setUredajtype(int uredajtype) {
        this.uredajtype = uredajtype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNaplatnaTockaId() {
        return naplatnaTockaId;
    }

    public void setNaplatnaTockaId(Long naplatnaTockaId) {
        this.naplatnaTockaId = naplatnaTockaId;
    }

    public boolean isKvar() {
        return kvar;
    }

    public void setKvar(boolean kvar) {
        this.kvar = kvar;
    }

    public float getRazinaPouzdanosti() {
        return razinaPouzdanosti;
    }

    public void setRazinaPouzdanosti(float razinaPouzdanosti) {
        this.razinaPouzdanosti = razinaPouzdanosti;
    }
}

