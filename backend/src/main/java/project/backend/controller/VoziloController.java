package project.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	
	@GetMapping("/fetch")
	public ResponseEntity<ResponseVozilo> getVozilo(@RequestParam(name = "id") String id){
		
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
	public ResponseEntity<ResponseVozilo> newVozilo(@RequestBody Vozilo novoVozilo, @RequestParam(name="ekoRazredId") String idEkoRazred, @RequestParam(name="kategorijaId") String idKategorija, @RequestParam(name="drzavaId") String idDrzava){

		Long ekoRazredId = Long.parseLong(idEkoRazred);
		Long kategorijaId = Long.parseLong(idKategorija);
		Long drzavaId = Long.parseLong(idDrzava);

		Vozilo voziloFromDB = voziloService.stvoriVozilo(novoVozilo, ekoRazredId, kategorijaId, drzavaId);
		
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
