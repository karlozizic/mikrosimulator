package project.backend.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.backend.model.DrzavaRegistracije;
import project.backend.repository.DrzavaRegistracijeRepository;
import project.backend.service.DrzavaRegistracijeServis;

@Service
public class DrzavaRegistracijeImpl implements DrzavaRegistracijeServis{

	DrzavaRegistracijeRepository drzavaRegistracijeRepository;
	
	public DrzavaRegistracijeImpl(@Autowired DrzavaRegistracijeRepository drzavaRegistracijeRepository) {
		this.drzavaRegistracijeRepository = drzavaRegistracijeRepository; 
	}

	@Override
	public DrzavaRegistracije dohvatiDrzavuRegistracijePoId(Long drzavaRegistracijeId) {
		return drzavaRegistracijeRepository.findById(drzavaRegistracijeId).get();
	}

	@Override
	public DrzavaRegistracije updateDrzaveRegistracije(DrzavaRegistracije updatedDrzavaRegistracije) {
		Optional<DrzavaRegistracije> drzavaRegistracijeOptional = drzavaRegistracijeRepository.findById(updatedDrzavaRegistracije.getDrzavaRegistracijeId());
		if(drzavaRegistracijeOptional.isPresent()) {
			DrzavaRegistracije drzavaRegistracije = drzavaRegistracijeOptional.get(); 
			return drzavaRegistracijeRepository.saveAndFlush(drzavaRegistracije); 
		}
		return null;
	}

	@Override
	public DrzavaRegistracije stvoriDrzavuRegistracije(DrzavaRegistracije novaDrzavaRegistracije) {
		return drzavaRegistracijeRepository.save(novaDrzavaRegistracije);
	}

	@Override
	public List<DrzavaRegistracije> dohvatiSveDrzaveRegistracije() {
		List<DrzavaRegistracije> listaDrzavaRegistracije = drzavaRegistracijeRepository.findAll();
		if(listaDrzavaRegistracije != null) {
			return listaDrzavaRegistracije; 
		}
		return null;
	}
	
}
