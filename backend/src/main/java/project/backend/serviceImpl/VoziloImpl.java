package project.backend.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.backend.model.DrzavaRegistracije;
import project.backend.model.EkoRazred;
import project.backend.model.Kategorija;
import project.backend.model.Vozilo;
import project.backend.repository.DrzavaRegistracijeRepository;
import project.backend.repository.EkoRazredRepository;
import project.backend.repository.KategorijaRepository;
import project.backend.repository.VoziloRepository;
import project.backend.service.VoziloServis;

@Service
public class VoziloImpl implements VoziloServis {
	
	VoziloRepository voziloRepository;
	EkoRazredRepository ekoRazredRepository;
	KategorijaRepository kategorijaRepository;
	DrzavaRegistracijeRepository drzavaRegistracijeRepository;
	
	public VoziloImpl(@Autowired VoziloRepository voziloRepository, @Autowired EkoRazredRepository ekoRazredRepository, @Autowired KategorijaRepository kategorijaRepository, @Autowired DrzavaRegistracijeRepository drzavaRegistracijeRepository) {
		this.voziloRepository = voziloRepository;
		this.ekoRazredRepository = ekoRazredRepository;
		this.kategorijaRepository = kategorijaRepository;
		this.drzavaRegistracijeRepository = drzavaRegistracijeRepository;
	}

	@Override
	public Vozilo dohvatiVoziloPoId(Long voziloId) {
		return voziloRepository.findById(voziloId).get();
	}

	@Override
	public Vozilo updateVozila(Vozilo updatedVozilo, Long ekoRazredId, Long kategorijaId, Long drzavaId) {
		Optional<Vozilo> voziloOptional = voziloRepository.findById(updatedVozilo.getVoziloId());
		if(voziloOptional.isPresent()) {
			Optional<EkoRazred> ekoRazredOptional = ekoRazredRepository.findById(ekoRazredId);
			if (ekoRazredOptional.isPresent()) {
				EkoRazred ekoRazred = ekoRazredOptional.get();
				updatedVozilo.setEkoRazred(ekoRazred);
			}
			Optional<Kategorija> kategorijaOptional = kategorijaRepository.findById(kategorijaId);
			if (kategorijaOptional.isPresent()) {
				Kategorija kategorija = kategorijaOptional.get();
				updatedVozilo.setKategorija(kategorija);
			}
			Optional<DrzavaRegistracije> drzavaRegistracijeOptional = drzavaRegistracijeRepository.findById(drzavaId);
			if (drzavaRegistracijeOptional.isPresent()) {
				DrzavaRegistracije drzavaRegistracije = drzavaRegistracijeOptional.get();
				updatedVozilo.setDrzavaRegistracije(drzavaRegistracije);
			}
			return voziloRepository.saveAndFlush(updatedVozilo);
		}
		return null;
	}

	@Override
	public Vozilo stvoriVozilo(Vozilo novoVozilo, Long ekoRazredId, Long kategorijaId, Long drzavaId) {
		Optional<EkoRazred> ekoRazredOptional = ekoRazredRepository.findById(ekoRazredId);
		if(ekoRazredOptional.isPresent()) {
			EkoRazred ekoRazred = ekoRazredOptional.get();
			novoVozilo.setEkoRazred(ekoRazred);
		}
		Optional<Kategorija> kategorijaOptional = kategorijaRepository.findById(kategorijaId);
		if(kategorijaOptional.isPresent()) {
			Kategorija kategorija = kategorijaOptional.get();
			novoVozilo.setKategorija(kategorija);
		}
		Optional<DrzavaRegistracije> drzavaRegistracijeOptional = drzavaRegistracijeRepository.findById(drzavaId);
		if(drzavaRegistracijeOptional.isPresent()) {
			DrzavaRegistracije drzavaRegistracije = drzavaRegistracijeOptional.get();
			novoVozilo.setDrzavaRegistracije(drzavaRegistracije);
		}
		return voziloRepository.save(novoVozilo);
	}

	@Override
	public Vozilo obrisiVozilo(Long voziloId) {
		Optional<Vozilo> dohvatiVozilo = voziloRepository.findById(voziloId);
		if(dohvatiVozilo.isPresent()) {
			voziloRepository.deleteById(voziloId);
			return dohvatiVozilo.get();
		}
		return null;
	}
	
	@Override
	public List<Vozilo> dohvatiSvaVozila() {
		List<Vozilo> listaVozila = voziloRepository.findAll();
		if(listaVozila != null) {
			return listaVozila; 
		}
		return null;
	}
	
}
