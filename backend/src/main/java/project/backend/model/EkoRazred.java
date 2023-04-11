package project.backend.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="EKO_RAZRED")
public class EkoRazred {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String naziv;

	public EkoRazred(Long ekoRazredId, String naziv) {
		super();
		this.id = ekoRazredId;
		this.naziv = naziv;
	}
	
	public EkoRazred() {
		
	}

	public Long getEkoRazredId() {
		return id;
	}

	public void setEkoRazredId(Long ekoRazredId) {
		this.id = ekoRazredId;
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
		EkoRazred other = (EkoRazred) obj;
		return Objects.equals(id, other.id) && Objects.equals(naziv, other.naziv);
	} 
	
	
}
