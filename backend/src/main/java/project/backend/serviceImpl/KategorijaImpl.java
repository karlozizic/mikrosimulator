package project.backend.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.backend.model.Kategorija;
import project.backend.repository.KategorijaRepository;
import project.backend.service.KategorijaServis;

@Service
public class KategorijaImpl implements KategorijaServis {

	KategorijaRepository kategorijaRepository;
	
	public KategorijaImpl(@Autowired KategorijaRepository kategorijaRepository) {
		this.kategorijaRepository = kategorijaRepository; 
	}

	@Override
	public Kategorija dohvatiKategorijuPoId(Long kategorijaId) {
		return kategorijaRepository.findById(kategorijaId).get();
	}

	@Override
	public Kategorija updateKategorija(Kategorija updatedKategorija) {
		Optional<Kategorija> kategorijaOptional = kategorijaRepository.findById(updatedKategorija.getKategorijaId());
		if(kategorijaOptional.isPresent()) {
			Kategorija kategorija = kategorijaOptional.get(); 
			return kategorijaRepository.saveAndFlush(kategorija); 
		}
		return null;
	}

	@Override
	public Kategorija stvoriKategoriju(Kategorija novaKategorija) {
		return kategorijaRepository.save(novaKategorija);
	}

	@Override
	public List<Kategorija> dohvatiSveKategorije() {
		List<Kategorija> listaKategorija = kategorijaRepository.findAll();
		if(listaKategorija != null) {
			return listaKategorija; 
		}
		return null;
	}

}
