package project.backend.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.backend.model.EkoRazred;
import project.backend.repository.EkoRazredRepository;
import project.backend.service.EkoRazredServis;

@Service
public class EkoRazredImpl implements EkoRazredServis {
	
	EkoRazredRepository ekoRazredRepository;

	public EkoRazredImpl(@Autowired EkoRazredRepository ekoRazredRepository) {
		this.ekoRazredRepository = ekoRazredRepository; 
	}
	
	@Override
	public EkoRazred dohvatiEkoRazredPoId(Long ekoRazredId) {
		return ekoRazredRepository.findById(ekoRazredId).get(); 
	}

	@Override
	public EkoRazred updateEkoRazreda(EkoRazred updatedEkoRazred) {
		Optional<EkoRazred> ekoRazredOptional = ekoRazredRepository.findById(updatedEkoRazred.getEkoRazredId());
		if(ekoRazredOptional.isPresent()) {
			return ekoRazredRepository.saveAndFlush(updatedEkoRazred); 
		}
		return null;
	}

	@Override
	public EkoRazred stvoriEkoRazred(EkoRazred noviEkoRazred) {
		return ekoRazredRepository.save(noviEkoRazred);
	}

	@Override
	public EkoRazred obrisiEkoRazred(Long ekoRazredId) {
		Optional<EkoRazred> dohvatiEkoRazred = ekoRazredRepository.findById(ekoRazredId);
		if(dohvatiEkoRazred.isPresent()) {
			ekoRazredRepository.deleteById(ekoRazredId);
			return dohvatiEkoRazred.get();
		}
		return null;
	}
	
	@Override
	public List<EkoRazred> dohvatiSveEkoRazrede() {
		List<EkoRazred> listaEkoRazreda = ekoRazredRepository.findAll();
		if(listaEkoRazreda != null) {
			return listaEkoRazreda;
		}
		return null; 
	}

}
