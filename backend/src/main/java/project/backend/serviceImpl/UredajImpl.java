package project.backend.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.backend.model.Uredaj;
import project.backend.repository.UredajRepository;
import project.backend.service.UredajServis;

@Service
public class UredajImpl implements UredajServis{

	UredajRepository uredajRepository;
	
	public UredajImpl(@Autowired UredajRepository uredajRepository) {
		this.uredajRepository = uredajRepository; 
	}

	@Override
	public Uredaj dohvatiUredajPoId(Long uredajId) {
		return uredajRepository.findById(uredajId).get();
	}

	@Override
	public Uredaj updateUredaja(Uredaj updatedUredaj) {
		Optional<Uredaj> uredajOptional = uredajRepository.findById(updatedUredaj.getUredajId());
		if(uredajOptional.isPresent()) {
			return uredajRepository.saveAndFlush(updatedUredaj);
		}
		return null;
	}

	@Override
	public Uredaj stvoriUredaj(Uredaj noviUredaj) {
		return uredajRepository.save(noviUredaj);
	}
	
	@Override
	public Uredaj obrisiUredaj(Long uredajId) {
		Optional<Uredaj> dohvatiUredaj = uredajRepository.findById(uredajId);
		if(dohvatiUredaj.isPresent()) {
			uredajRepository.deleteById(uredajId);
			return dohvatiUredaj.get();
		}
		return null;
	}

	@Override
	public List<Uredaj> dohvatiSveUredaje() {
		List<Uredaj> listaUredaja = uredajRepository.findAll();
		if(listaUredaja != null) {
			return listaUredaja; 
		}
		return null;
	}

	
}
