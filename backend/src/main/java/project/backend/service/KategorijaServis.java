package project.backend.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import project.backend.model.Kategorija;

@Service
@Component
public interface KategorijaServis {

	Kategorija dohvatiKategorijuPoId(Long kategorijaId);
	
	Kategorija updateKategorija(Kategorija updatedKategorija);
	
	Kategorija stvoriKategoriju(Kategorija novaKategorija);
	
	List<Kategorija> dohvatiSveKategorije(); 
	
}
