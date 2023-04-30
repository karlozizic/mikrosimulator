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
			return kategorijaRepository.saveAndFlush(updatedKategorija);
		}
		return null;
	}

	@Override
	public Kategorija stvoriKategoriju(Kategorija novaKategorija) {
		return kategorijaRepository.save(novaKategorija);
	}

	@Override
	public Kategorija obrisiKategoriju(Long kategorijaId) {
		Optional<Kategorija> dohvatiKategoriju = kategorijaRepository.findById(kategorijaId);
		if(dohvatiKategoriju.isPresent()) {
			kategorijaRepository.deleteById(kategorijaId);
			return dohvatiKategoriju.get();
		}
		return null;
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
