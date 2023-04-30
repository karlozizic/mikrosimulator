package project.backend.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import project.backend.model.Vozilo;

@Service
@Component
public interface VoziloServis {
	
	Vozilo dohvatiVoziloPoId(Long voziloId);
	
	Vozilo updateVozila(Vozilo updatedVozilo, Long ekoRazredId, Long kategorijaId, Long drzavaId);
	
	Vozilo stvoriVozilo(Vozilo novoVozilo, Long ekoRazredId, Long kategorijaId, Long drzavaId);
	
	Vozilo obrisiVozilo(Long voziloId); 
	
	List<Vozilo> dohvatiSvaVozila(); 
	
}
