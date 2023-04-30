package project.backend.model;

public class NoviUredaj {

    private Long id = null;
    private int uredajtype;
    private String name;

    public NoviUredaj(int uredajtype, String name) {
        this.uredajtype = uredajtype;
        this.name = name;
    }

    public NoviUredaj(int uredajtype, String name, Long id) {
        this.uredajtype = uredajtype;
        this.name = name;
        this.id = id;
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
}

