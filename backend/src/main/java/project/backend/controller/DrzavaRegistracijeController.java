package project.backend.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import project.backend.model.DrzavaRegistracije;
import project.backend.model.ResponseDrzavaRegistracije;
import project.backend.serviceImpl.DrzavaRegistracijeImpl;

@Controller
@RequestMapping("/drzavaregistracije")
public class DrzavaRegistracijeController {

	DrzavaRegistracijeImpl drzavaRegistracijeService;
	
	public DrzavaRegistracijeController(@Autowired DrzavaRegistracijeImpl drzavaRegistracijeService) {
		this.drzavaRegistracijeService = drzavaRegistracijeService; 
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<ResponseDrzavaRegistracije> getDrzavaRegistracije(@RequestParam(name = "id") String id){
		
		Long longId = Long.parseLong(id);
		DrzavaRegistracije drzavaRegistracijeFromDB = drzavaRegistracijeService.dohvatiDrzavuRegistracijePoId(longId);
		
		if(drzavaRegistracijeFromDB == null) {
			ResponseDrzavaRegistracije data = new ResponseDrzavaRegistracije(null, null, false, "Neuspjesno dohvacanje drzave registracije!");
			return new ResponseEntity<ResponseDrzavaRegistracije>(data, HttpStatus.OK);
		}else {
			ResponseDrzavaRegistracije data = new ResponseDrzavaRegistracije(drzavaRegistracijeFromDB, null, true, "Uspjesno dohvacanje drzave registracije!");
			return new ResponseEntity<ResponseDrzavaRegistracije>(data, HttpStatus.OK);
		}
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseDrzavaRegistracije> updateDrzavaRegistracije(@RequestBody DrzavaRegistracije updatedDrzavaRegistracije){
		
		DrzavaRegistracije drzavaRegistracijeFromDB = drzavaRegistracijeService.updateDrzaveRegistracije(updatedDrzavaRegistracije); 
		
		if(drzavaRegistracijeFromDB == null) {
			ResponseDrzavaRegistracije data = new ResponseDrzavaRegistracije(null, null, false, "Neuspjesno updateanje drzave registracije!");
			return new ResponseEntity<ResponseDrzavaRegistracije>(data, HttpStatus.OK);
		}else {
			ResponseDrzavaRegistracije data = new ResponseDrzavaRegistracije(drzavaRegistracijeFromDB, null, true, "Uspjesno updateanje drzave registracije!");
			return new ResponseEntity<ResponseDrzavaRegistracije>(data, HttpStatus.OK);
		}
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseDrzavaRegistracije> newDrzavaRegistracije(@RequestBody DrzavaRegistracije novaDrzavaRegistracije){
		
		DrzavaRegistracije drzavaRegistracijeFromDB = drzavaRegistracijeService.stvoriDrzavuRegistracije(novaDrzavaRegistracije);
		
		if(drzavaRegistracijeFromDB == null) {
			ResponseDrzavaRegistracije data = new ResponseDrzavaRegistracije(null, null, false, "Neuspjesno stvaranje drzave registracije!");
			return new ResponseEntity<ResponseDrzavaRegistracije>(data, HttpStatus.OK);
		}else {
			ResponseDrzavaRegistracije data = new ResponseDrzavaRegistracije(drzavaRegistracijeFromDB, null, true, "Uspjesno stvaranje drzave registracije!");
			return new ResponseEntity<ResponseDrzavaRegistracije>(data, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDrzavaRegistracije> deleteDrzavaRegistracije(@PathVariable(name = "id") String idParam){
		Long drzavaRegistracijeId = Long.parseLong(idParam);
		DrzavaRegistracije drzava = drzavaRegistracijeService.obrisiDrzavuRegistracije(drzavaRegistracijeId);
		if(drzava == null) {
			ResponseDrzavaRegistracije data = new ResponseDrzavaRegistracije(null, null, false, "Neuspjesno brisanje drzave registracije!");
			return new ResponseEntity<ResponseDrzavaRegistracije>(data, HttpStatus.OK);
		}else {
			ResponseDrzavaRegistracije data = new ResponseDrzavaRegistracije(drzava, null, true, "Uspjesno brisanje drzave registracije!");
			return new ResponseEntity<ResponseDrzavaRegistracije>(data, HttpStatus.OK);
		}
	
	}
	
	@GetMapping("/all")
	public ResponseEntity<ResponseDrzavaRegistracije> getAllDrzaveRegistracije(){
		
		List<DrzavaRegistracije> listaDrzavaRegistracije = drzavaRegistracijeService.dohvatiSveDrzaveRegistracije();
		
		if(listaDrzavaRegistracije.isEmpty()) {
			ResponseDrzavaRegistracije data = new ResponseDrzavaRegistracije(null, listaDrzavaRegistracije, false, null);
			return new ResponseEntity<ResponseDrzavaRegistracije>(data, HttpStatus.OK);
		}else {
			ResponseDrzavaRegistracije data = new ResponseDrzavaRegistracije(null, listaDrzavaRegistracije, true, "Dohvacanje uspjesno!");
			return new ResponseEntity<ResponseDrzavaRegistracije>(data, HttpStatus.OK);
		}
	}
	
}