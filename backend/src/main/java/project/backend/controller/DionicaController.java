package project.backend.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import project.backend.model.*;
import project.backend.serviceImpl.DionicaImpl;

@Controller
@RequestMapping("/dionica")
public class DionicaController {

	DionicaImpl dionicaService;
	
	public DionicaController(@Autowired DionicaImpl dionicaService) {
		this.dionicaService = dionicaService; 
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<ResponseDionica> getDionica(@RequestParam(name = "id") String id){
		
		Long longId = Long.parseLong(id);
		Dionica dionicaFromDB = dionicaService.dohvatiDionicuPoId(longId);
		
		if(dionicaFromDB == null) {
			ResponseDionica data = new ResponseDionica(null, null, false, "Neuspjesno dohvacanje dionice!");
			return new ResponseEntity<ResponseDionica>(data, HttpStatus.OK);
		}else {
			ResponseDionica data = new ResponseDionica(dionicaFromDB, null, true, "Uspjesno dohvacanje dionice!");
			return new ResponseEntity<ResponseDionica>(data, HttpStatus.OK);
		}
		

	}
	@GetMapping("/fetchByOznaka")
	public ResponseEntity<ResponseDionica> getDionicaByOznaka(@RequestParam(name = "oznaka") String oznaka){

		List<Dionica> listaDionica = dionicaService.dohvatiDionicePoOznaci(oznaka);

		if(listaDionica == null) {
			ResponseDionica data = new ResponseDionica(null, null, false, "Neuspjesno dohvacanje dionica po oznaci!");
			return new ResponseEntity<ResponseDionica>(data, HttpStatus.OK);
		}else {
			ResponseDionica data = new ResponseDionica(null, listaDionica, true, "Uspjesno dohvacanje dionica po oznaci!");
			return new ResponseEntity<ResponseDionica>(data, HttpStatus.OK);
		}


	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseDionica> updateDionica(@RequestBody NovaDionica updatedDionica){

		Dionica oldDionica = dionicaService.dohvatiDionicuPoId(updatedDionica.getDionicaId());
		Dionica slijedi = oldDionica.getSlijedi();
		Dionica prethodi = oldDionica.getPrethodi();

		Dionica dionica = new Dionica(updatedDionica.getDionicaId(), updatedDionica.getSmjer(), updatedDionica.getNajvecaBrzina(), updatedDionica.getBrojTraka(), updatedDionica.getOznaka(), updatedDionica.getPocetnaStacionaza(), updatedDionica.getZavrsnaStacionaza(), updatedDionica.getOznakaAutoceste(), slijedi, prethodi);
		Dionica dionicaFromDB = dionicaService.updateDionice(dionica);
		
		if(dionicaFromDB == null) {
			ResponseDionica data = new ResponseDionica(null, null, false, "Neuspjesno updateanje dionice!");
			return new ResponseEntity<ResponseDionica>(data, HttpStatus.OK);
		}else {
			ResponseDionica data = new ResponseDionica(dionicaFromDB, null, true, "Uspjesno updateanje dionice!");
			return new ResponseEntity<ResponseDionica>(data, HttpStatus.OK);
		}
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseDionica> newDionica(@RequestBody NovaDionica novaDionica){

		Dionica slijedi = null;
		Dionica prethodi = null;

		if (novaDionica.getSlijediId() != null) {
			slijedi = dionicaService.dohvatiDionicuPoId(novaDionica.getSlijediId());
		}
		if (novaDionica.getPrethodiId() != null) {
			prethodi = dionicaService.dohvatiDionicuPoId(novaDionica.getPrethodiId());
		}

		Dionica dionica = new Dionica(novaDionica.getSmjer(), novaDionica.getNajvecaBrzina(), novaDionica.getBrojTraka(), novaDionica.getOznaka(), novaDionica.getPocetnaStacionaza(), novaDionica.getZavrsnaStacionaza(), novaDionica.getOznakaAutoceste(), slijedi, prethodi);
		Dionica dionicaFromDB = dionicaService.stvoriDionicu(dionica);
		
		if(dionicaFromDB == null) {
			ResponseDionica data = new ResponseDionica(null, null, false, "Neuspjesno stvaranje dionice!");
			return new ResponseEntity<ResponseDionica>(data, HttpStatus.OK);
		}else {
			ResponseDionica data = new ResponseDionica(dionicaFromDB, null, true, "Uspjesno stvaranje dionice!");
			return new ResponseEntity<ResponseDionica>(data, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDionica> deleteDionicu(@PathVariable(name = "id") String idParam){
		Long dionicaId = Long.parseLong(idParam);
		Dionica dionica = dionicaService.obrisiDionicu(dionicaId);
		if(dionica == null) {
			ResponseDionica data = new ResponseDionica(null, null, false, "Neuspjesno brisanje dionice!");
			return new ResponseEntity<ResponseDionica>(data, HttpStatus.OK);
		}else {
			ResponseDionica data = new ResponseDionica(dionica, null, true, "Uspjesno brisanje dionice!");
			return new ResponseEntity<ResponseDionica>(data, HttpStatus.OK);
		}
	
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<ResponseDionica> getAllDionice(){
		
		List<Dionica> listaDionica = dionicaService.dohvatiSveDionice();
		
		if(listaDionica.isEmpty()) {
			ResponseDionica data = new ResponseDionica(null, listaDionica, false, null);
			return new ResponseEntity<ResponseDionica>(data, HttpStatus.OK);
		}else {
			ResponseDionica data = new ResponseDionica(null, listaDionica, true, "Dohvacanje uspjesno!");
			return new ResponseEntity<ResponseDionica>(data, HttpStatus.OK);
		}
	}

	@GetMapping("/fetchOznake")
	public ResponseEntity<ResponseOznaka> getAllOznake(){
		List<Dionica> listaDionica = dionicaService.dohvatiSveDionice();
		List<String> listaOznaka = new ArrayList<>();

		for (Dionica dionica : listaDionica) {
			if(!listaOznaka.contains(dionica.getOznakaAutoceste())){
				listaOznaka.add(dionica.getOznakaAutoceste());
			}
		}
		if(listaOznaka.isEmpty()) {
			ResponseOznaka data = new ResponseOznaka(listaOznaka, false);
			return new ResponseEntity<ResponseOznaka>(data, HttpStatus.OK);
		}else {
			ResponseOznaka data = new ResponseOznaka(listaOznaka, true);
			return new ResponseEntity<ResponseOznaka>(data, HttpStatus.OK);
		}
	}
}
