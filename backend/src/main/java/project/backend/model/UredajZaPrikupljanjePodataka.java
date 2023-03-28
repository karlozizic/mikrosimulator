package project.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="UREDAJ_ZA_PRIKUPLJANJE_PODATAKA")
public class UredajZaPrikupljanjePodataka {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long uredajId; 
	
}
