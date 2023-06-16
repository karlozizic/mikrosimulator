package project.backend.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Kamera extends Uredaj{

	private String name;
	public Kamera(String name, NaplatnaTocka naplatnaTocka, boolean kvar, String razinaPouzdanosti) {
		super(naplatnaTocka, kvar, razinaPouzdanosti);
		this.name = name;
	}

	public Kamera() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
