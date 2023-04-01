package project.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="NAPLATNA_TOCKA")
public class NaplatnaTocka {

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String oznaka;
	
	private String naziv;
	
	private int stacionaza;
	
	private int geografskaDuzina;
	
	private int geografskaSirina;
	
	private String usmjerenje; 
	
	@ManyToOne
	private Uredaj uredajZaPodatke;

	public NaplatnaTocka(Long naplatnaTockaId, String oznaka, String naziv, int stacionaza, int geografskaDuzina,
			int geografskaSirina, String usmjerenje, Uredaj uredajZaPodatke) {
		super();
		this.id = naplatnaTockaId;
		this.oznaka = oznaka;
		this.naziv = naziv;
		this.stacionaza = stacionaza;
		this.geografskaDuzina = geografskaDuzina;
		this.geografskaSirina = geografskaSirina;
		this.usmjerenje = usmjerenje;
		this.uredajZaPodatke = uredajZaPodatke;
	} 
	
	public NaplatnaTocka() {
		
	}

	public Long getNaplatnaTockaId() {
		return id;
	}

	public void setNaplatnaTockaId(Long naplatnaTockaId) {
		this.id = naplatnaTockaId;
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

	public int getStacionaza() {
		return stacionaza;
	}

	public void setStacionaza(int stacionaza) {
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

	public Uredaj getUredajZaPodatke() {
		return uredajZaPodatke;
	}

	public void setUredajZaPodatke(Uredaj uredajZaPodatke) {
		this.uredajZaPodatke = uredajZaPodatke;
	}
	
	
	
}
