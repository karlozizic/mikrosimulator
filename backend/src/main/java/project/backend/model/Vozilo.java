package project.backend.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="VOZILO")
public class Vozilo { 
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nacinNaplate;
	
	private String boja;
	
	private int brojOsovina;
	
	private int VIN;
	
	private int idENC;
	
	private String registracijskaOznaka; 
	
	@ManyToOne()
	private EkoRazred ekoRazred;
	
	@ManyToOne()
	private Kategorija kategorija;
	
	@ManyToOne()
	private DrzavaRegistracije drzavaRegistracije;

	public Vozilo(Long voziloId, String nacinNaplate, String boja, int brojOsovina, int VIN, int idENC,
			String registracijskaOznaka, EkoRazred ekoRazred, Kategorija kategorija,
			DrzavaRegistracije drzavaRegistracije) {
		super();
		this.id = voziloId;
		this.nacinNaplate = nacinNaplate;
		this.boja = boja;
		this.brojOsovina = brojOsovina;
		this.VIN = VIN;
		this.idENC = idENC;
		this.registracijskaOznaka = registracijskaOznaka;
		this.ekoRazred = ekoRazred;
		this.kategorija = kategorija;
		this.drzavaRegistracije = drzavaRegistracije;
	}

	public Vozilo(String nacinNaplate, String boja, int brojOsovina, int VIN, int idENC,
				  String registracijskaOznaka, EkoRazred ekoRazred, Kategorija kategorija,
				  DrzavaRegistracije drzavaRegistracije) {
		super();
		this.nacinNaplate = nacinNaplate;
		this.boja = boja;
		this.brojOsovina = brojOsovina;
		this.VIN = VIN;
		this.idENC = idENC;
		this.registracijskaOznaka = registracijskaOznaka;
		this.ekoRazred = ekoRazred;
		this.kategorija = kategorija;
		this.drzavaRegistracije = drzavaRegistracije;
	}

	public Vozilo() {
		
	}
	
	public Long getVoziloId() {
		return id;
	}

	public void setVoziloId(Long voziloId) {
		this.id = voziloId;
	}

	public String getNacinNaplate() {
		return nacinNaplate;
	}

	public void setNacinNaplate(String nacinNaplate) {
		this.nacinNaplate = nacinNaplate;
	}

	public String getBoja() {
		return boja;
	}

	public void setBoja(String boja) {
		this.boja = boja;
	}

	public int getBrojOsovina() {
		return brojOsovina;
	}

	public void setBrojOsovina(int brojOsovina) {
		this.brojOsovina = brojOsovina;
	}

	public int getVIN() {
		return VIN;
	}

	public void setVIN(int vIN) {
		VIN = vIN;
	}

	public int getIdENC() {
		return idENC;
	}

	public void setIdENC(int idENC) {
		this.idENC = idENC;
	}

	public String getRegistracijskaOznaka() {
		return registracijskaOznaka;
	}

	public void setRegistracijskaOznaka(String registracijskaOznaka) {
		this.registracijskaOznaka = registracijskaOznaka;
	}

	public EkoRazred getEkoRazred() {
		return ekoRazred;
	}

	public void setEkoRazred(EkoRazred ekoRazred) {
		this.ekoRazred = ekoRazred;
	}

	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public DrzavaRegistracije getDrzavaRegistracije() {
		return drzavaRegistracije;
	}

	public void setDrzavaRegistracije(DrzavaRegistracije drzavaRegistracije) {
		this.drzavaRegistracije = drzavaRegistracije;
	}

	@Override
	public int hashCode() {
		return Objects.hash(VIN, boja, brojOsovina, drzavaRegistracije, ekoRazred, idENC, kategorija, nacinNaplate,
				registracijskaOznaka, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vozilo other = (Vozilo) obj;
		return VIN == other.VIN && Objects.equals(boja, other.boja) && brojOsovina == other.brojOsovina
				&& Objects.equals(drzavaRegistracije, other.drzavaRegistracije)
				&& Objects.equals(ekoRazred, other.ekoRazred) && idENC == other.idENC
				&& Objects.equals(kategorija, other.kategorija) && Objects.equals(nacinNaplate, other.nacinNaplate)
				&& Objects.equals(registracijskaOznaka, other.registracijskaOznaka)
				&& Objects.equals(id, other.id);
	}
	
	
}
