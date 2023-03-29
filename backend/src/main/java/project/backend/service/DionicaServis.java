package project.backend.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import project.backend.model.Dionica;

@Service
@Component
public interface DionicaServis {
	
	Dionica dohvatiDionicuPoId(Long id);
	
	Dionica updateDionice(Dionica updatedDionica);
	
	Dionica stvoriDionicu(Dionica novaDionica);
	
	Dionica obrisiDionicu(Long dionicaId); 
	
	List<Dionica> dohvatiSveDionice(); 
	
}
