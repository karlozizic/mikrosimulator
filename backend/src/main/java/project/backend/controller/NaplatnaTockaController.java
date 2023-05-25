package project.backend.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import project.backend.model.Dionica;
import project.backend.model.NaplatnaTocka;
import project.backend.model.NovaNaplatnaTocka;
import project.backend.model.ResponseNaplatnaTocka;
import project.backend.serviceImpl.DionicaImpl;
import project.backend.serviceImpl.NaplatnaTockaImpl;

@Controller
@RequestMapping("/naplatnatocka")
public class NaplatnaTockaController {

	NaplatnaTockaImpl naplatnaTockaService;
	DionicaImpl dionicaService;
	
	public NaplatnaTockaController(@Autowired NaplatnaTockaImpl naplatnaTockaService, @Autowired DionicaImpl dionicaService) {
		this.naplatnaTockaService = naplatnaTockaService;
		this.dionicaService = dionicaService;
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<ResponseNaplatnaTocka> getNaplatnaTocka(@RequestParam(name = "id") String id){
		
		Long longId = Long.parseLong(id);
		NaplatnaTocka naplatnaTockaFromDB = naplatnaTockaService.dohvatiNaplatnuTockuPoId(longId);
		
		if(naplatnaTockaFromDB == null) {
			ResponseNaplatnaTocka data = new ResponseNaplatnaTocka(null, null, false, "Neuspjesno dohvacanje naplatne tocke!");
			return new ResponseEntity<ResponseNaplatnaTocka>(data, HttpStatus.OK);
		}else {
			ResponseNaplatnaTocka data = new ResponseNaplatnaTocka(naplatnaTockaFromDB, null, true, "Uspjesno dohvacanje naplatne tocke!");
			return new ResponseEntity<ResponseNaplatnaTocka>(data, HttpStatus.OK);
		}

	}

	
	@PutMapping("/update")
	public ResponseEntity<ResponseNaplatnaTocka> updateNaplatnaTocka(@RequestBody NaplatnaTocka updatedNaplatnaTocka, @RequestParam(name="id") String id){

		Long longId = Long.parseLong(id);
		updatedNaplatnaTocka.setNaplatnaTockaId(longId);
		NaplatnaTocka naplatnaTocka = naplatnaTockaService.dohvatiNaplatnuTockuPoId(longId);
		updatedNaplatnaTocka.setDionica(naplatnaTocka.getDionica());
		NaplatnaTocka naplatnaTockaFromDB = naplatnaTockaService.updateNaplatneTocke(updatedNaplatnaTocka);
		
		if(naplatnaTockaFromDB == null) {
			ResponseNaplatnaTocka data = new ResponseNaplatnaTocka(null, null, false, "Neuspjesno updateanje naplatne tocke!");
			return new ResponseEntity<ResponseNaplatnaTocka>(data, HttpStatus.OK);
		}else {
			ResponseNaplatnaTocka data = new ResponseNaplatnaTocka(naplatnaTockaFromDB, null, true, "Uspjesno updateanje naplatne tocke!");
			return new ResponseEntity<ResponseNaplatnaTocka>(data, HttpStatus.OK);
		}
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseNaplatnaTocka> newNaplatnaTocka(@RequestBody NovaNaplatnaTocka novaNaplatnaTocka){

		Dionica dionica = dionicaService.dohvatiDionicuPoId(novaNaplatnaTocka.getDionicaId());
		NaplatnaTocka naplatnaTocka = new NaplatnaTocka(novaNaplatnaTocka.getOznaka(), novaNaplatnaTocka.getNaziv(), novaNaplatnaTocka.getStacionaza(), novaNaplatnaTocka.getGeografskaDuzina(), novaNaplatnaTocka.getGeografskaSirina(), novaNaplatnaTocka.getUsmjerenje(), dionica);
		NaplatnaTocka naplatnaTockaFromDB = naplatnaTockaService.stvoriNaplatnuTocku(naplatnaTocka);
		
		if(naplatnaTockaFromDB == null) {
			ResponseNaplatnaTocka data = new ResponseNaplatnaTocka(null, null, false, "Neuspjesno stvaranje naplatne tocke!");
			return new ResponseEntity<ResponseNaplatnaTocka>(data, HttpStatus.OK);
		}else {
			ResponseNaplatnaTocka data = new ResponseNaplatnaTocka(naplatnaTockaFromDB, null, true, "Uspjesno stvaranje naplatne tocke!");
			return new ResponseEntity<ResponseNaplatnaTocka>(data, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseNaplatnaTocka> deleteNaplatnaTocka(@PathVariable(name = "id") String idParam){
		Long naplatnaTockaId = Long.parseLong(idParam);
		NaplatnaTocka naplatnaTocka = naplatnaTockaService.obrisiNaplatnuTocku(naplatnaTockaId);
		if(naplatnaTocka == null) {
			ResponseNaplatnaTocka data = new ResponseNaplatnaTocka(null, null, false, "Neuspjesno brisanje naplatne tocke!");
			return new ResponseEntity<ResponseNaplatnaTocka>(data, HttpStatus.OK);
		}else {
			ResponseNaplatnaTocka data = new ResponseNaplatnaTocka(naplatnaTocka, null, true, "Uspjesno brisanje naplatne tocke!");
			return new ResponseEntity<ResponseNaplatnaTocka>(data, HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<ResponseNaplatnaTocka> getAllNaplatneTocke(){
		
		List<NaplatnaTocka> listaNaplatnihTocaka = naplatnaTockaService.dohvatiSveNaplatneTocke();
		
		if(listaNaplatnihTocaka.isEmpty()) {
			ResponseNaplatnaTocka data = new ResponseNaplatnaTocka(null, listaNaplatnihTocaka, false, null);
			return new ResponseEntity<ResponseNaplatnaTocka>(data, HttpStatus.OK);
		}else {
			ResponseNaplatnaTocka data = new ResponseNaplatnaTocka(null, listaNaplatnihTocaka, true, "Dohvacanje uspjesno!");
			return new ResponseEntity<ResponseNaplatnaTocka>(data, HttpStatus.OK);
		}
	}

	@GetMapping("/fetchByDionica")
	public ResponseEntity<ResponseNaplatnaTocka> getByDionica(@RequestParam(name = "dionicaId") String id){

		Long longId = Long.parseLong(id);
		Dionica dionica = dionicaService.dohvatiDionicuPoId(longId);
		List<NaplatnaTocka> listaNaplatnihTocaka = naplatnaTockaService.dohvatiSveNaplatneTockePoDionici(dionica);

		if(listaNaplatnihTocaka.isEmpty()) {
			ResponseNaplatnaTocka data = new ResponseNaplatnaTocka(null, listaNaplatnihTocaka, false, null);
			return new ResponseEntity<ResponseNaplatnaTocka>(data, HttpStatus.OK);
		}else {
			ResponseNaplatnaTocka data = new ResponseNaplatnaTocka(null, listaNaplatnihTocaka, true, "Dohvacanje uspjesno!");
			return new ResponseEntity<ResponseNaplatnaTocka>(data, HttpStatus.OK);
		}

	}
	
}
