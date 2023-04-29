package project.backend.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("3")
public class Klasifikator extends Uredaj{

	private String name;
	public Klasifikator(String name) {
		super();
		this.name = name;
	}

	public Klasifikator() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
