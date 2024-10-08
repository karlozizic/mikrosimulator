package project.backend.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="DRZAVA_REGISTRACIJE")
public class DrzavaRegistracije {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String naziv;

	public DrzavaRegistracije(Long drzavaRegistracijeId, String naziv) {
		super();
		this.id = drzavaRegistracijeId;
		this.naziv = naziv;
	}
	
	public DrzavaRegistracije() {
		
	}

	public Long getDrzavaRegistracijeId() {
		return id;
	}

	public void setDrzavaRegistracijeId(Long drzavaRegistracijeId) {
		this.id = drzavaRegistracijeId;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, naziv);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DrzavaRegistracije other = (DrzavaRegistracije) obj;
		return Objects.equals(id, other.id) && Objects.equals(naziv, other.naziv);
	}
	
	
}