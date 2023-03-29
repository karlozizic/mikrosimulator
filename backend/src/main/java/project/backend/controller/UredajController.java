package project.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import project.backend.model.ResponseUredaj;
import project.backend.model.Uredaj;
import project.backend.serviceImpl.UredajImpl;


@Controller
@RequestMapping("/uredaj")
public class UredajController{
	
	UredajImpl uredajService;
	
	public UredajController(@Autowired UredajImpl uredajService) {
		this.uredajService = uredajService; 
	}
	
	@GetMapping("/fetch/{id}")
	public ResponseEntity<ResponseUredaj> getUredaj(@PathVariable(name = "id") String id){
		
		Long longId = Long.parseLong(id);
		Uredaj uredajFromDB = uredajService.dohvatiUredajPoId(longId);
		
		if(uredajFromDB == null) {
			ResponseUredaj data = new ResponseUredaj(null, null, false, "Neuspjesno dohvacanje uredaja!");
			return new ResponseEntity<ResponseUredaj>(data, HttpStatus.OK);
		}else {
			ResponseUredaj data = new ResponseUredaj(uredajFromDB, null, true, "Uspjesno dohvacanje uredaja!");
			return new ResponseEntity<ResponseUredaj>(data, HttpStatus.OK);
		}
		
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseUredaj> updateUredaj(@RequestBody Uredaj updatedUredaj){
		
		Uredaj uredajFromDB = uredajService.updateUredaja(updatedUredaj); 
		
		if(uredajFromDB == null) {
			ResponseUredaj data = new ResponseUredaj(null, null, false, "Neuspjesno updateanje uredaja!");
			return new ResponseEntity<ResponseUredaj>(data, HttpStatus.OK);
		}else {
			ResponseUredaj data = new ResponseUredaj(uredajFromDB, null, true, "Uspjesno updateanje uredaja!");
			return new ResponseEntity<ResponseUredaj>(data, HttpStatus.OK);
		}
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseUredaj> newUredaj(@RequestBody Uredaj noviUredaj){
		
		Uredaj uredajFromDB = uredajService.stvoriUredaj(noviUredaj);
		
		if(uredajFromDB == null) {
			ResponseUredaj data = new ResponseUredaj(null, null, false, "Neuspjesno stvaranje uredaja!");
			return new ResponseEntity<ResponseUredaj>(data, HttpStatus.OK);
		}else {
			ResponseUredaj data = new ResponseUredaj(uredajFromDB, null, true, "Uspjesno stvaranje uredaja!");
			return new ResponseEntity<ResponseUredaj>(data, HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<ResponseUredaj> getAllUredaji(){
		
		List<Uredaj> listaUredaja = uredajService.dohvatiSveUredaje();
		
		if(listaUredaja.isEmpty()) {
			ResponseUredaj data = new ResponseUredaj(null, listaUredaja, false, null);
			return new ResponseEntity<ResponseUredaj>(data, HttpStatus.OK);
		}else {
			ResponseUredaj data = new ResponseUredaj(null, listaUredaja, true, "Dohvacanje uspjesno!");
			return new ResponseEntity<ResponseUredaj>(data, HttpStatus.OK);
		}
	}
}
