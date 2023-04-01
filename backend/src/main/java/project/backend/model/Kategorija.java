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
	public Long id;
	
	public String naziv;

	public Kategorija(Long kategorijaId, String naziv) {
		super();
		this.id = kategorijaId;
		this.naziv = naziv;
	}
	
	public Kategorija() {
		
	}

	public Long getKategorijaId() {
		return id;
	}

	public void setKategorijaId(Long kategorijaId) {
		this.id = kategorijaId;
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
		Kategorija other = (Kategorija) obj;
		return Objects.equals(id, other.id) && Objects.equals(naziv, other.naziv);
	}
	
	
	
}