package project.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import project.backend.model.Kategorija;
import project.backend.model.ResponseKategorija;
import project.backend.serviceImpl.KategorijaImpl;


@Controller
@RequestMapping("/kategorija")
public class KategorijaController {

	KategorijaImpl kategorijaService;
	
	public KategorijaController(@Autowired KategorijaImpl kategorijaService) {
		this.kategorijaService = kategorijaService; 
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<ResponseKategorija> getKategorija(@RequestParam(name = "id") String id){
		
		Long longId = Long.parseLong(id);
		Kategorija kategorijaFromDB = kategorijaService.dohvatiKategorijuPoId(longId);
		
		if(kategorijaFromDB == null) {
			ResponseKategorija data = new ResponseKategorija(null, null, false, "Neuspjesno dohvacanje kategorije!");
			return new ResponseEntity<ResponseKategorija>(data, HttpStatus.OK);
		}else {
			ResponseKategorija data = new ResponseKategorija(kategorijaFromDB, null, true, "Uspjesno dohvacanje kategorije!");
			return new ResponseEntity<ResponseKategorija>(data, HttpStatus.OK);
		}
		
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseKategorija> updateKategorija(@RequestBody Kategorija updatedKategorija){
		
		Kategorija kategorijaFromDB = kategorijaService.updateKategorija(updatedKategorija); 
		
		if(kategorijaFromDB == null) {
			ResponseKategorija data = new ResponseKategorija(null, null, false, "Neuspjesno updateanje kategorije!");
			return new ResponseEntity<ResponseKategorija>(data, HttpStatus.OK);
		}else {
			ResponseKategorija data = new ResponseKategorija(kategorijaFromDB, null, true, "Uspjesno updateanje kategorije!");
			return new ResponseEntity<ResponseKategorija>(data, HttpStatus.OK);
		}
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseKategorija> newKategorija(@RequestBody Kategorija novaKategorija){
		
		Kategorija kategorijaFromDB = kategorijaService.stvoriKategoriju(novaKategorija);
		
		if(kategorijaFromDB == null) {
			ResponseKategorija data = new ResponseKategorija(null, null, false, "Neuspjesno stvaranje kategorije!");
			return new ResponseEntity<ResponseKategorija>(data, HttpStatus.OK);
		}else {
			ResponseKategorija data = new ResponseKategorija(kategorijaFromDB, null, true, "Uspjesno stvaranje kategorije!");
			return new ResponseEntity<ResponseKategorija>(data, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseKategorija> deleteKategorija(@PathVariable(name = "id") String idParam){
		Long kategorijaId = Long.parseLong(idParam);
		Kategorija kategorija = kategorijaService.obrisiKategoriju(kategorijaId);
		if(kategorija == null) {
			ResponseKategorija data = new ResponseKategorija(null, null, false, "Neuspjesno brisanje kategorije!");
			return new ResponseEntity<ResponseKategorija>(data, HttpStatus.OK);
		}else {
			ResponseKategorija data = new ResponseKategorija(kategorija, null, true, "Uspjesno brisanje kategorije!");
			return new ResponseEntity<ResponseKategorija>(data, HttpStatus.OK);
		}
	
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<ResponseKategorija> getAllKategorije(){
		
		List<Kategorija> listaKategorija = kategorijaService.dohvatiSveKategorije();
		
		if(listaKategorija.isEmpty()) {
			ResponseKategorija data = new ResponseKategorija(null, listaKategorija, false, null);
			return new ResponseEntity<ResponseKategorija>(data, HttpStatus.OK);
		}else {
			ResponseKategorija data = new ResponseKategorija(null, listaKategorija, true, "Dohvacanje uspjesno!");
			return new ResponseEntity<ResponseKategorija>(data, HttpStatus.OK);
		}
	}
	
}