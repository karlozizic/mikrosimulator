package project.backend.service;



import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import project.backend.model.Dionica;
import project.backend.model.NaplatnaTocka;

@Service
@Component
public interface NaplatnaTockaServis {

	NaplatnaTocka dohvatiNaplatnuTockuPoId(Long naplatnaTockaId);
	
	NaplatnaTocka updateNaplatneTocke(NaplatnaTocka updatedNaplatnaTocka);
	
	NaplatnaTocka stvoriNaplatnuTocku(NaplatnaTocka novaNaplatnaTocka);
	
	NaplatnaTocka obrisiNaplatnuTocku(Long naplatnaTockaId); 
	
	List<NaplatnaTocka> dohvatiSveNaplatneTocke();

	List<NaplatnaTocka> dohvatiSveNaplatneTockePoDionici(Dionica dionica);
	
}
