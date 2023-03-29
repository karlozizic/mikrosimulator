package project.backend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

//Za UredajZaPrikupljanjePodataka, Kameru, Primopredajnik, Klasifikator sam koristio strategiju 
//Single Table - dakle svi Entiteti ce se nalaziti u jednoj tablici unutar baze
//vidi jos je li to dobro? - medutim strategije Table Per Class, MappedSupperClass nisam uspio implementirati
//https:www.baeldung.com/hibernate-inheritance
//https://thorben-janssen.com/complete-guide-inheritance-strategies-jpa-hibernate/

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="UREDAJ")
public class Uredaj {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long uredajId;

	public Uredaj(Long uredajId) {
		super();
		this.uredajId = uredajId;
	}

	public Long getUredajId() {
		return uredajId;
	}

	public void setUredajId(Long uredajId) {
		this.uredajId = uredajId;
	} 
	
}
