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
	public Long ekoRazredId;
	
	public String naziv;

	public EkoRazred(Long ekoRazredId, String naziv) {
		super();
		this.ekoRazredId = ekoRazredId;
		this.naziv = naziv;
	}
	
	public EkoRazred() {
		
	}

	public Long getEkoRazredId() {
		return ekoRazredId;
	}

	public void setEkoRazredId(Long ekoRazredId) {
		this.ekoRazredId = ekoRazredId;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ekoRazredId, naziv);
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
		return Objects.equals(ekoRazredId, other.ekoRazredId) && Objects.equals(naziv, other.naziv);
	} 
	
	
}
