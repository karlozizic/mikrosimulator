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

import project.backend.model.Dionica;
import project.backend.model.ResponseDionica;
import project.backend.serviceImpl.DionicaImpl;

@Controller
@RequestMapping("/dionica")
public class DionicaController {

	DionicaImpl dionicaService;
	
	public DionicaController(@Autowired DionicaImpl dionicaService) {
		this.dionicaService = dionicaService; 
	}
	
	@GetMapping("/fetch/{id}")
	public ResponseEntity<ResponseDionica> getDionica(@PathVariable(name = "id") String id){
		
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
	
	@PutMapping("/update")
	public ResponseEntity<ResponseDionica> updateDionica(@RequestBody Dionica updatedDionica){
		
		Dionica dionicaFromDB = dionicaService.updateDionice(updatedDionica); 
		
		if(dionicaFromDB == null) {
			ResponseDionica data = new ResponseDionica(null, null, false, "Neuspjesno updateanje dionice!");
			return new ResponseEntity<ResponseDionica>(data, HttpStatus.OK);
		}else {
			ResponseDionica data = new ResponseDionica(dionicaFromDB, null, true, "Uspjesno updateanje dionice!");
			return new ResponseEntity<ResponseDionica>(data, HttpStatus.OK);
		}
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseDionica> newDionica(@RequestBody Dionica novaDionica){
		
		Dionica dionicaFromDB = dionicaService.stvoriDionicu(novaDionica);
		
		if(dionicaFromDB == null) {
			ResponseDionica data = new ResponseDionica(null, null, false, "Neuspjesno stvaranje dionice!");
			return new ResponseEntity<ResponseDionica>(data, HttpStatus.OK);
		}else {
			ResponseDionica data = new ResponseDionica(dionicaFromDB, null, true, "Uspjesno stvaranje dionice!");
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
	
}
