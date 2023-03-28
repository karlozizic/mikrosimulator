package project.backend.service;

import java.util.List;

import project.backend.model.EkoRazred;

public interface EkoRazredServis {

	EkoRazred dohvatiEkoRazredPoId(Long ekoRazredId);
	
	EkoRazred updateEkoRazreda(EkoRazred updatedEkoRazred);
	
	EkoRazred stvoriEkoRazred(EkoRazred noviEkoRazred);
	
	List<EkoRazred> dohvatiSveEkoRazrede(); 
	
}
