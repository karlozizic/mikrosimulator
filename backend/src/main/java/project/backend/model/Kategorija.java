package project.backend.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="KATEGORIJA")
public class Kategorija {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long kategorijaId;
	
	public String naziv;

	public Kategorija(Long kategorijaId, String naziv) {
		super();
		this.kategorijaId = kategorijaId;
		this.naziv = naziv;
	}
	
	public Kategorija() {
		
	}

	public Long getKategorijaId() {
		return kategorijaId;
	}

	public void setKategorijaId(Long kategorijaId) {
		this.kategorijaId = kategorijaId;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	@Override
	public int hashCode() {
		return Objects.hash(kategorijaId, naziv);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kategorija other = (Kategorija) obj;
		return Objects.equals(kategorijaId, other.kategorijaId) && Objects.equals(naziv, other.naziv);
	}
	
	
	
}