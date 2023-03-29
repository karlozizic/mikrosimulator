package project.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import project.backend.model.EkoRazred;
import project.backend.model.ResponseEkoRazred;
import project.backend.serviceImpl.EkoRazredImpl;

@Controller
@RequestMapping("/ekorazred")
public class EkoRazredController {

	EkoRazredImpl ekoRazredService;
	
	public EkoRazredController(@Autowired EkoRazredImpl ekoRazredService) {
		this.ekoRazredService = ekoRazredService;
	}
	
	@GetMapping("/fetch/{id}")
	public ResponseEntity<ResponseEkoRazred> getEkoRazred(@PathVariable(name = "id") String id){
		
		Long longId = Long.parseLong(id);
		EkoRazred ekoRazredFromDB = ekoRazredService.dohvatiEkoRazredPoId(longId);
		
		if(ekoRazredFromDB == null) {
			ResponseEkoRazred data = new ResponseEkoRazred(null, null, false, "Neuspjesno dohvacanje eko razreda!");
			return new ResponseEntity<ResponseEkoRazred>(data, HttpStatus.OK);
		}else {
			ResponseEkoRazred data = new ResponseEkoRazred(ekoRazredFromDB, null, true, "Uspjesno dohvacanje eko razreda!");
			return new ResponseEntity<ResponseEkoRazred>(data, HttpStatus.OK);
		}
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseEkoRazred> updateEkoRazred(@RequestBody EkoRazred updatedEkoRazred){
		
		EkoRazred ekoRazredFromDB = ekoRazredService.updateEkoRazreda(updatedEkoRazred); 
		
		if(ekoRazredFromDB == null) {
			ResponseEkoRazred data = new ResponseEkoRazred(null, null, false, "Neuspjesno updateanje eko razreda!");
			return new ResponseEntity<ResponseEkoRazred>(data, HttpStatus.OK);
		}else {
			ResponseEkoRazred data = new ResponseEkoRazred(ekoRazredFromDB, null, true, "Uspjesno updateanje eko razreda!");
			return new ResponseEntity<ResponseEkoRazred>(data, HttpStatus.OK);
		}
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseEkoRazred> newEkoRazred(@RequestBody EkoRazred noviEkoRazred){
		
		EkoRazred ekoRazredFromDB = ekoRazredService.stvoriEkoRazred(noviEkoRazred);
		
		if(ekoRazredFromDB == null) {
			ResponseEkoRazred data = new ResponseEkoRazred(null, null, false, "Neuspjesno stvaranje eko razreda!");
			return new ResponseEntity<ResponseEkoRazred>(data, HttpStatus.OK);
		}else {
			ResponseEkoRazred data = new ResponseEkoRazred(ekoRazredFromDB, null, true, "Uspjesno stvaranje eko razreda!");
			return new ResponseEntity<ResponseEkoRazred>(data, HttpStatus.OK);
		}
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseEkoRazred> deleteEkoRazred(@PathVariable(name = "id") String idParam){
		Long ekoRazredId = Long.parseLong(idParam);
		EkoRazred ekoRazred = ekoRazredService.obrisiEkoRazred(ekoRazredId);
		if(ekoRazred == null) {
			ResponseEkoRazred data = new ResponseEkoRazred(null, null, false, "Neuspjesno brisanje eko razreda!");
			return new ResponseEntity<ResponseEkoRazred>(data, HttpStatus.OK);
		}else {
			ResponseEkoRazred data = new ResponseEkoRazred(ekoRazred, null, true, "Uspjesno brisanje eko razreda!");
			return new ResponseEntity<ResponseEkoRazred>(data, HttpStatus.OK);
		}
	
	}
	
	@GetMapping("/all")
	public ResponseEntity<ResponseEkoRazred> getAllEkoRazredi(){
		
		List<EkoRazred> listaEkoRazreda = ekoRazredService.dohvatiSveEkoRazrede();
		
		if(listaEkoRazreda.isEmpty()) {
			ResponseEkoRazred data = new ResponseEkoRazred(null, listaEkoRazreda, false, null);
			return new ResponseEntity<ResponseEkoRazred>(data, HttpStatus.OK);
		}else {
			ResponseEkoRazred data = new ResponseEkoRazred(null, listaEkoRazreda, true, "Dohvacanje uspjesno!");
			return new ResponseEntity<ResponseEkoRazred>(data, HttpStatus.OK);
		}
	}
	
}