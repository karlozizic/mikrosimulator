package project.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import project.backend.model.*;
import project.backend.serviceImpl.NaplatnaTockaImpl;
import project.backend.serviceImpl.UredajImpl;


@Controller
@RequestMapping("/uredaj")
public class UredajController{
	
	UredajImpl uredajService;

	NaplatnaTockaImpl naplatnaTockaService;
	
	public UredajController(@Autowired UredajImpl uredajService, @Autowired NaplatnaTockaImpl naplatnaTockaService) {
		this.uredajService = uredajService;
		this.naplatnaTockaService = naplatnaTockaService;
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<ResponseUredaj> getUredaj(@RequestParam(name = "id") String id){

		Long longId = Long.parseLong(id);
		Uredaj uredajFromDB = uredajService.dohvatiUredajPoId(longId);
		int tipUredaja = uredajService.dohvatiTipUredaja(longId);
		String name = "";

		if(tipUredaja == 1) {
			name = ((Kamera) uredajFromDB).getName();
		} else if(tipUredaja == 2) {
			name = ((Primopredajnik) uredajFromDB).getName();
		} else {
			name = ((Klasifikator) uredajFromDB).getName();
		}
		NoviUredaj noviUredaj = new NoviUredaj(tipUredaja, name, uredajFromDB.getUredajId(), uredajFromDB.getNaplatnaTocka().getNaplatnaTockaId());

		if(uredajFromDB == null) {
			ResponseUredaj data = new ResponseUredaj(null, null, false, "Neuspjesno dohvacanje uredaja!");
			return new ResponseEntity<ResponseUredaj>(data, HttpStatus.OK);
		}else {
			ResponseUredaj data = new ResponseUredaj(noviUredaj, null, true, "Uspjesno dohvacanje uredaja!");
			return new ResponseEntity<ResponseUredaj>(data, HttpStatus.OK);
		}
	}


	@PutMapping("/update")
	public ResponseEntity<ResponseUredaj> updateUredaj(@RequestBody NoviUredaj updatedUredaj){

		Uredaj uredajFromDB = uredajService.dohvatiUredajPoId(updatedUredaj.getId());

		if(uredajFromDB == null) {
			ResponseUredaj data = new ResponseUredaj(null, null, false, "Neuspjesno updateanje uredaja!");
			return new ResponseEntity<ResponseUredaj>(data, HttpStatus.OK);
		}

		int tipUredaja = uredajService.dohvatiTipUredaja(uredajFromDB.getUredajId());

		if(tipUredaja == 1) {
			uredajFromDB = (Kamera) uredajFromDB;
			((Kamera) uredajFromDB).setName(updatedUredaj.getName());
		} else if (tipUredaja == 2) {
			uredajFromDB = (Primopredajnik) uredajFromDB;
			((Primopredajnik) uredajFromDB).setName(updatedUredaj.getName());
		} else if (tipUredaja == 3) {
			uredajFromDB = (Klasifikator) uredajFromDB;
			((Klasifikator) uredajFromDB).setName(updatedUredaj.getName());
		}

		Uredaj uredaj = uredajService.updateUredaja(uredajFromDB);
		NoviUredaj noviUredaj = new NoviUredaj(tipUredaja, updatedUredaj.getName(), uredajFromDB.getUredajId(), uredajFromDB.getNaplatnaTocka().getNaplatnaTockaId()	);

		ResponseUredaj data = new ResponseUredaj(noviUredaj, null, true, "Uspjesno updateanje uredaja!");
		return new ResponseEntity<ResponseUredaj>(data, HttpStatus.OK);


	}

	@PostMapping("/register")
	public ResponseEntity<ResponseUredaj> newUredaj(@RequestBody NoviUredaj noviUredaj) {

		Uredaj uredajFromDB = null;
		String name = "";
		NaplatnaTocka naplatnaTocka = naplatnaTockaService.dohvatiNaplatnuTockuPoId(noviUredaj.getNaplatnaTockaId());
		if (noviUredaj.getUredajtype() == 1) {
			Kamera uredaj = new Kamera(noviUredaj.getName(), naplatnaTocka);
			uredajFromDB =  uredajService.stvoriUredaj(uredaj);
			name = ((Kamera) uredajFromDB).getName();
		}
		else if (noviUredaj.getUredajtype() == 2) {
			Primopredajnik uredaj = new Primopredajnik(noviUredaj.getName(), naplatnaTocka);
			uredajFromDB = (Primopredajnik) uredajService.stvoriUredaj(uredaj);
			name = ((Primopredajnik) uredajFromDB).getName();
		} else if (noviUredaj.getUredajtype() == 3) {
			Klasifikator uredaj = new Klasifikator(noviUredaj.getName(), naplatnaTocka);
			uredajFromDB = (Klasifikator) uredajService.stvoriUredaj(uredaj);
			name = ((Klasifikator) uredajFromDB).getName();
		}
		else {
			ResponseUredaj data = new ResponseUredaj(null, null, false, "Neuspjesno stvaranje uredaja - treba navesti ispravan uredaj type!");
			return new ResponseEntity<ResponseUredaj>(data, HttpStatus.OK);
		}

		int tipUredaja = uredajService.dohvatiTipUredaja(uredajFromDB.getUredajId());
		noviUredaj = new NoviUredaj(tipUredaja, name, uredajFromDB.getUredajId(), naplatnaTocka.getNaplatnaTockaId());

		ResponseUredaj data = new ResponseUredaj(noviUredaj, null, true, "Uspjesno stvaranje uredaja!");
		return new ResponseEntity<ResponseUredaj>(data, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseUredaj> deleteUredaj(@PathVariable(name = "id") String idParam){

		Long uredajId = Long.parseLong(idParam);
		Uredaj uredajFromDB = uredajService.dohvatiUredajPoId(uredajId);
		int tipUredaja = uredajService.dohvatiTipUredaja(uredajFromDB.getUredajId());
		String name = "";
		if(tipUredaja == 1) {
			name = ((Kamera) uredajFromDB).getName();
		} else if(tipUredaja == 2) {
			name = ((Primopredajnik) uredajFromDB).getName();
		} else {
			name = ((Klasifikator) uredajFromDB).getName();
		}
		Uredaj uredaj = uredajService.obrisiUredaj(uredajId);

		NoviUredaj noviUredaj = new NoviUredaj(tipUredaja, name, uredaj.getUredajId(), uredaj.getNaplatnaTocka().getNaplatnaTockaId());

		if(uredaj == null) {
			ResponseUredaj data = new ResponseUredaj(null, null, false, "Neuspjesno brisanje uredaja!");
			return new ResponseEntity<ResponseUredaj>(data, HttpStatus.OK);
		}else {
			ResponseUredaj data = new ResponseUredaj(noviUredaj, null, true, "Uspjesno brisanje uredaja!");
			return new ResponseEntity<ResponseUredaj>(data, HttpStatus.OK);
		}

	}


	@GetMapping("/all")
	public ResponseEntity<ResponseUredaj> getAllUredaji(){

		List<Uredaj> listaUredaja = uredajService.dohvatiSveUredaje();

		if(listaUredaja.isEmpty()) {
			ResponseUredaj data = new ResponseUredaj(null, null, false, null);
			return new ResponseEntity<ResponseUredaj>(data, HttpStatus.OK);
		} else {
			List<NoviUredaj> listaNovihUredaja= new ArrayList<NoviUredaj>();
			for (Uredaj uredaj : listaUredaja) {
				int tipUredaja = uredajService.dohvatiTipUredaja(uredaj.getUredajId());
				String name = "";
				if(tipUredaja == 1) {
					name = ((Kamera) uredaj).getName();
				} else if(tipUredaja == 2) {
					name = ((Primopredajnik) uredaj).getName();
				} else {
					name = ((Klasifikator) uredaj).getName();
				}
				NoviUredaj noviUredaj = new NoviUredaj(tipUredaja, name, uredaj.getUredajId(), uredaj.getNaplatnaTocka().getNaplatnaTockaId());
				listaNovihUredaja.add(noviUredaj);
			}

			ResponseUredaj data = new ResponseUredaj(null, listaNovihUredaja, true, "Dohvacanje uspjesno!");
			return new ResponseEntity<ResponseUredaj>(data, HttpStatus.OK);
		}
	}

	@GetMapping("/allby")
	public ResponseEntity<ResponseUredaj> dohvatiUredajeNaplatneTocke(@RequestParam(name = "id") String idNaplatneTocke){
		Long id = Long.parseLong(idNaplatneTocke);
		NaplatnaTocka naplatnaTocka = naplatnaTockaService.dohvatiNaplatnuTockuPoId(id);
		List<Uredaj> listaUredaja = uredajService.dohvatiUredajeNaplatneTocke(naplatnaTocka);

		if(listaUredaja.isEmpty()) {
			ResponseUredaj data = new ResponseUredaj(null, null, false, null);
			return new ResponseEntity<ResponseUredaj>(data, HttpStatus.OK);
		} else {
			List<NoviUredaj> listaNovihUredaja= new ArrayList<NoviUredaj>();
			for (Uredaj uredaj : listaUredaja) {
				int tipUredaja = uredajService.dohvatiTipUredaja(uredaj.getUredajId());
				String name = "";
				if(tipUredaja == 1) {
					name = ((Kamera) uredaj).getName();
				} else if(tipUredaja == 2) {
					name = ((Primopredajnik) uredaj).getName();
				} else {
					name = ((Klasifikator) uredaj).getName();
				}
				NoviUredaj noviUredaj = new NoviUredaj(tipUredaja, name, uredaj.getUredajId(), uredaj.getNaplatnaTocka().getNaplatnaTockaId());
				listaNovihUredaja.add(noviUredaj);
			}

			ResponseUredaj data = new ResponseUredaj(null, listaNovihUredaja, true, "Dohvacanje uspjesno!");
			return new ResponseEntity<ResponseUredaj>(data, HttpStatus.OK);
		}
	}
}
