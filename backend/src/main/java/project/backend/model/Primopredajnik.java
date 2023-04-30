package project.backend.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class Primopredajnik extends Uredaj{

	private String name;
	public Primopredajnik(String name) {
		super();
		this.name = name;
	}

	public Primopredajnik() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
