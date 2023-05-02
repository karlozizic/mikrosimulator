package project.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.backend.model.ResponseVozilo;
import project.backend.model.Vozilo;
import project.backend.serviceImpl.GenerirajVozilaImpl;

import java.util.List;

@Controller
@RequestMapping("/generate")
public class GenerirajVozilaController {
    GenerirajVozilaImpl generateVozilaService;

    public GenerirajVozilaController(GenerirajVozilaImpl generateVozilaService) {
        this.generateVozilaService = generateVozilaService;
    }

    @GetMapping
    public ResponseEntity<ResponseVozilo> generateVozila(@RequestParam(name = "intenzitet") String intenzitet) {

        int intenzitetInt = Integer.parseInt(intenzitet);
        List<Vozilo> listaVozila = generateVozilaService.generirajVozila(intenzitetInt);

        ResponseVozilo data = new ResponseVozilo(null, listaVozila, false, "Generiranje vozila!");
        return new ResponseEntity<ResponseVozilo>(data, HttpStatus.OK);
    }

}
