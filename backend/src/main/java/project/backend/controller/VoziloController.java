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

import project.backend.model.ResponseVozilo;
import project.backend.model.Vozilo;
import project.backend.serviceImpl.VoziloImpl;

@Controller
@RequestMapping("/vozilo")
public class VoziloController {

	VoziloImpl voziloService;
	
	public VoziloController(@Autowired VoziloImpl voziloService) {
		this.voziloService = voziloService; 
	}
	
	@GetMapping("/fetch/{id}")
	public ResponseEntity<ResponseVozilo> getVozilo(@PathVariable(name = "id") String id){
		
		Long longId = Long.parseLong(id);
		Vozilo voziloFromDB = voziloService.dohvatiVoziloPoId(longId);
		
		if(voziloFromDB == null) {
			ResponseVozilo data = new ResponseVozilo(null, null, false, "Neuspjesno dohvacanje vozila!");
			return new ResponseEntity<ResponseVozilo>(data, HttpStatus.OK);
		}else {
			ResponseVozilo data = new ResponseVozilo(voziloFromDB, null, true, "Uspjesno dohvacanje vozila!");
			return new ResponseEntity<ResponseVozilo>(data, HttpStatus.OK);
		}
		
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseVozilo> updateVozilo(@RequestBody Vozilo updatedVozilo){
		
		Vozilo voziloFromDB = voziloService.updateVozila(updatedVozilo); 
		
		if(voziloFromDB == null) {
			ResponseVozilo data = new ResponseVozilo(null, null, false, "Neuspjesno updateanje vozila!");
			return new ResponseEntity<ResponseVozilo>(data, HttpStatus.OK);
		}else {
			ResponseVozilo data = new ResponseVozilo(voziloFromDB, null, true, "Uspjesno updateanje vozila!");
			return new ResponseEntity<ResponseVozilo>(data, HttpStatus.OK);
		}
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseVozilo> newVozilo(@RequestBody Vozilo novoVozilo){
		
		Vozilo voziloFromDB = voziloService.stvoriVozilo(novoVozilo);
		
		if(voziloFromDB == null) {
			ResponseVozilo data = new ResponseVozilo(null, null, false, "Neuspjesno stvaranje vozila!");
			return new ResponseEntity<ResponseVozilo>(data, HttpStatus.OK);
		}else {
			ResponseVozilo data = new ResponseVozilo(voziloFromDB, null, true, "Uspjesno stvaranje vozila!");
			return new ResponseEntity<ResponseVozilo>(data, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseVozilo> deleteVozilo(@PathVariable(name = "id") String idParam){
		Long voziloId = Long.parseLong(idParam);
		Vozilo vozilo = voziloService.obrisiVozilo(voziloId);
		if(vozilo == null) {
			ResponseVozilo data = new ResponseVozilo(null, null, false, "Neuspješno brisanje vozila");
			return new ResponseEntity<ResponseVozilo>(data, HttpStatus.OK);
		}else{
			ResponseVozilo data = new ResponseVozilo(vozilo, null, true, "Uspješno brisanje vozila");
			return new ResponseEntity<ResponseVozilo>(data, HttpStatus.OK);
		}
	
	}
	
	@GetMapping("/all")
	public ResponseEntity<ResponseVozilo> getAllVozila(){
		
		List<Vozilo> listaVozila = voziloService.dohvatiSvaVozila();
		
		if(listaVozila.isEmpty()) {
			ResponseVozilo data = new ResponseVozilo(null, listaVozila, false, null);
			return new ResponseEntity<ResponseVozilo>(data, HttpStatus.OK);
		}else {
			ResponseVozilo data = new ResponseVozilo(null, listaVozila, true, "Dohvacanje uspjesno!");
			return new ResponseEntity<ResponseVozilo>(data, HttpStatus.OK);
		}
	}
	
}
