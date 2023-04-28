package project.backend.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.backend.model.EkoRazred;
import project.backend.model.Vozilo;
import project.backend.repository.EkoRazredRepository;
import project.backend.repository.VoziloRepository;
import project.backend.service.VoziloServis;

@Service
public class VoziloImpl implements VoziloServis {
	
	VoziloRepository voziloRepository;
	EkoRazredRepository ekoRazredRepository;
	
	public VoziloImpl(@Autowired VoziloRepository voziloRepository, @Autowired EkoRazredRepository ekoRazredRepository) {
		this.voziloRepository = voziloRepository;
		this.ekoRazredRepository = ekoRazredRepository;
	}

	@Override
	public Vozilo dohvatiVoziloPoId(Long voziloId) {
		return voziloRepository.findById(voziloId).get();
	}

	@Override
	public Vozilo updateVozila(Vozilo updatedVozilo) {

		Optional<Vozilo> voziloOptional = voziloRepository.findById(updatedVozilo.getVoziloId());
		if(voziloOptional.isPresent()) {
			return voziloRepository.saveAndFlush(updatedVozilo);
		}
		return null;
	}

	@Override
	public Vozilo stvoriVozilo(Vozilo novoVozilo, Long ekoRazredId) {
		Optional<EkoRazred> ekoRazredOptional = ekoRazredRepository.findById(ekoRazredId);
		if(ekoRazredOptional.isPresent()) {
			EkoRazred ekoRazred = ekoRazredOptional.get();
			novoVozilo.setEkoRazred(ekoRazred);
		}
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
