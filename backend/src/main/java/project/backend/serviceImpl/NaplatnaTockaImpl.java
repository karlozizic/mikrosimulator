package project.backend.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.backend.model.NaplatnaTocka;
import project.backend.repository.NaplatnaTockaRepository;
import project.backend.service.NaplatnaTockaServis;

@Service
public class NaplatnaTockaImpl implements NaplatnaTockaServis{

	NaplatnaTockaRepository naplatnaTockaRepository;
	
	public NaplatnaTockaImpl(@Autowired NaplatnaTockaRepository naplatnaTockaRepository) {
		this.naplatnaTockaRepository = naplatnaTockaRepository; 
	}

	@Override
	public NaplatnaTocka dohvatiNaplatnuTockuPoId(Long naplatnaTockaId) {
		return naplatnaTockaRepository.findById(naplatnaTockaId).get();
	}

	@Override
	public NaplatnaTocka updateNaplatneTocke(NaplatnaTocka updatedNaplatnaTocka) {
		Optional<NaplatnaTocka> naplatnaTockaOptional = naplatnaTockaRepository.findById(updatedNaplatnaTocka.getNaplatnaTockaId());
		if(naplatnaTockaOptional.isPresent()) {
			NaplatnaTocka naplatnaTocka = naplatnaTockaOptional.get(); 
			return naplatnaTockaRepository.saveAndFlush(naplatnaTocka); 
		}
		return null;
	}

	@Override
	public NaplatnaTocka stvoriNaplatnuTocku(NaplatnaTocka novaNaplatnaTocka) {
		return naplatnaTockaRepository.save(novaNaplatnaTocka);
	}

	@Override
	public List<NaplatnaTocka> dohvatiSveNaplatneTocke() {
		List<NaplatnaTocka> listaNaplatnihTocaka = naplatnaTockaRepository.findAll();
		if(listaNaplatnihTocaka != null) {
			return listaNaplatnihTocaka; 
		}
		return null;
	}
	
	
}
