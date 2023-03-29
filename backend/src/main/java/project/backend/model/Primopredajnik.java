package project.backend.model;


import jakarta.persistence.Entity;

@Entity
public class Primopredajnik extends Uredaj{

	public Primopredajnik(Long uredajId) {
		super(uredajId);
		// TODO Auto-generated constructor stub
	}
	
}
