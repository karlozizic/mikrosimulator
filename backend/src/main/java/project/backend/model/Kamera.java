package project.backend.model;

import jakarta.persistence.Entity;

@Entity
public class Kamera extends Uredaj{
	
	public Kamera(Long uredajId, String naziv) {
		super(uredajId);
		// TODO Auto-generated constructor stub
	}
	
}
