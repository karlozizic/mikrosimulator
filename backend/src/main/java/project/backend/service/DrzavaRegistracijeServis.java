package project.backend.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import project.backend.model.DrzavaRegistracije;

@Service
@Component
public interface DrzavaRegistracijeServis {

	DrzavaRegistracije dohvatiDrzavuRegistracijePoId(Long drzavaRegistracijeId);
	
	DrzavaRegistracije updateDrzaveRegistracije(DrzavaRegistracije updatedDrzavaRegistracije);
	
	DrzavaRegistracije stvoriDrzavuRegistracije(DrzavaRegistracije novaDrzavaRegistracije);
	
	DrzavaRegistracije obrisiDrzavuRegistracije(Long drzavaRegistracijeId); 
	
	List<DrzavaRegistracije> dohvatiSveDrzaveRegistracije();
	
}
