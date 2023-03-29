package project.backend.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.backend.model.Vozilo;
import project.backend.repository.VoziloRepository;
import project.backend.service.VoziloServis;

@Service
public class VoziloImpl implements VoziloServis {
	
	VoziloRepository voziloRepository;
	
	public VoziloImpl(@Autowired VoziloRepository voziloRepository) {
		this.voziloRepository = voziloRepository; 
	}

	@Override
	public Vozilo dohvatiVoziloPoId(Long voziloId) {
		return voziloRepository.findById(voziloId).get();
	}

	@Override
	public Vozilo updateVozila(Vozilo updatedVozilo) {
		Optional<Vozilo> voziloOptional = voziloRepository.findById(updatedVozilo.getVoziloId());
		if(voziloOptional.isPresent()) {
			Vozilo vozilo = voziloOptional.get(); 
			return voziloRepository.saveAndFlush(vozilo); 
		}
		return null;
	}

	@Override
	public Vozilo stvoriVozilo(Vozilo novoVozilo) {
		return voziloRepository.save(novoVozilo);
	}

	@Override
	public Vozilo obrisiVozilo(Long voziloId) {
		Optional<Vozilo> dohvatiVozilo = voziloRepository.findById(voziloId);
		if(dohvatiVozilo.isPresent()) {
			voziloRepository.deleteById(voziloId);
			return dohvatiVozilo.get();
		}
		return null;
	}
	
	@Override
	public List<Vozilo> dohvatiSvaVozila() {
		List<Vozilo> listaVozila = voziloRepository.findAll();
		if(listaVozila != null) {
			return listaVozila; 
		}
		return null;
	}
	
}
