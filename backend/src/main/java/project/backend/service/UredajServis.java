package project.backend.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import project.backend.model.NoviUredaj;
import project.backend.model.Uredaj;

@Service
@Component
public interface UredajServis {

	Uredaj dohvatiUredajPoId(Long uredajId);

	int dohvatiTipUredaja(long uredajId);

	Uredaj updateUredaja(Uredaj updatedUredaj);

	Uredaj stvoriUredaj(Uredaj noviUredaj);

	Uredaj obrisiUredaj(Long uredajId);

	List<Uredaj> dohvatiSveUredaje();
	
}
