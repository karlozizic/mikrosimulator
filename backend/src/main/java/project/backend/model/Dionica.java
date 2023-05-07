package project.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="DIONICA")
public class Dionica {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String smjer;
	
	private int najvecaBrzina;
	
	private int brojTraka;
	
	private String oznaka;
	
	private int pocetnaStacionaza;
	
	private int zavrsnaStacionaza;
	
	@OneToOne(optional=true)
	public Dionica slijedi;
	
	@OneToOne(optional=true)
	public Dionica prethodi; 

	public Dionica(Long dionicaId, String smjer, int najvecaBrzina, int brojTraka, String oznaka, int pocetnaStacionaza,
			int zavrsnaStacionaza) {
		super();
		this.id = dionicaId;
		this.smjer = smjer;
		this.najvecaBrzina = najvecaBrzina;
		this.brojTraka = brojTraka;
		this.oznaka = oznaka;
		this.pocetnaStacionaza = pocetnaStacionaza;
		this.zavrsnaStacionaza = zavrsnaStacionaza;
	}
	
	public Dionica() {
		
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

	
	
	
}
