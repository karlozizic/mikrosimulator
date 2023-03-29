package project.backend.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.backend.model.Dionica;
import project.backend.repository.DionicaRepository;
import project.backend.service.DionicaServis;

@Service
public class DionicaImpl implements DionicaServis{

	DionicaRepository dionicaRepository;
	
	public DionicaImpl(@Autowired DionicaRepository dionicaRepository) {
		this.dionicaRepository = dionicaRepository; 
	}

	@Override
	public Dionica dohvatiDionicuPoId(Long dionicaId) {
		return dionicaRepository.findById(dionicaId).get();
	}

	@Override
	public Dionica updateDionice(Dionica updatedDionica) {
		Optional<Dionica> dionicaOptional = dionicaRepository.findById(updatedDionica.getDionicaId());
		if(dionicaOptional.isPresent()) {
			Dionica dionica = dionicaOptional.get(); 
			return dionicaRepository.saveAndFlush(dionica); 
		}
		return null;
	}

	@Override
	public Dionica stvoriDionicu(Dionica novaDionica) {
		return dionicaRepository.save(novaDionica);
	}
	
	@Override
	public Dionica obrisiDionicu(Long dionicaId) {
		Optional<Dionica> dohvatiDionicu = dionicaRepository.findById(dionicaId);
		if(dohvatiDionicu.isPresent()) {
			dionicaRepository.deleteById(dionicaId);
			return dohvatiDionicu.get();
		}
		return null;
	}

	@Override
	public List<Dionica> dohvatiSveDionice() {
		List<Dionica> listaDionica = dionicaRepository.findAll();
		if(listaDionica != null) {
			return listaDionica; 
		}
		return null;
	}
	
}
