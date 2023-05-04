package com.microservices.vehicles;

import com.microservices.exceptions.VehicleNotFoundException;
import com.microservices.vehicles.Utils.BrojOsovinaUtils;
import com.microservices.vehicles.Utils.JsonReader;
import com.microservices.vehicles.Utils.Parser;
import com.microservices.vehicles.Utils.RegistracijaUtils;
import com.microservices.vehicles.models.Drzava;
import com.microservices.vehicles.models.Ekorazred;
import com.microservices.vehicles.models.Kategorija;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

@RestController
public class VehiclesController {

    protected Logger logger = Logger.getLogger(VehiclesController.class.getName());

    protected VehicleRepository vehicleRepository;

    @Autowired
    public VehiclesController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
        logger.info("VehicleRepository says system has " + vehicleRepository.countVehicles() + " vehicles");
    }

    @RequestMapping(path = "/vehicles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vehicle byId(@PathVariable("id") String id) {
        logger.info("vehicles-service byId() invoked: " + id);
        Long vehicleId = Long.valueOf(id);
        Vehicle vehicle = vehicleRepository.findById(vehicleId);
        logger.info("vehicles-service byId() found: " + vehicle);

        if (vehicle == null)
            throw new VehicleNotFoundException(vehicleId);
        else {
            return vehicle;
        }
    }

    @RequestMapping(path = "/vehicles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vehicle> all() {
        logger.info("vehicles-service all() invoked: ");
        List<Vehicle> vehicles = vehicleRepository.findAll();
        logger.info("vehicles-service all() found: " + vehicles);
        return vehicles;
    }

    @GetMapping(path = "/vehicles/generate/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void generateVehicles(@PathVariable("number") String number) {

        int num = Integer.parseInt(number);

        try {
            Long id = vehicleRepository.lastId() == null ? Long.valueOf(1) : vehicleRepository.lastId() + 1;

            ArrayList<String> naciniNaplate = new ArrayList<String>(Arrays.asList("ENC", "Na naplatnom mjestu"));

            ArrayList<String> boje = new ArrayList<String>(Arrays.asList("Crvena", "Plava", "Zelena", "Žuta", "Narančasta", "Ljubičasta", "Smeđa", "Crna", "Bijela"));

            JSONObject ekoRazrediJSON = (JSONObject) JsonReader.getJson("http://localhost:8080/spring/ekorazred/all");
            JSONArray ekoRazredi = (JSONArray) ekoRazrediJSON.get("listaEkoRazreda"); //JSONArray
            ArrayList<Ekorazred> ekorazrediArray = Parser.parseEkoRazredi(ekoRazredi);

            JSONObject kategorijeJSON = (JSONObject) JsonReader.getJson("http://localhost:8080/spring/kategorija/all");
            JSONArray kategorije = (JSONArray) kategorijeJSON.get("listaKategorija"); //JSONArray
            ArrayList<Kategorija> kategorijeArray = Parser.parseKategorije(kategorije);

            JSONObject drzaveJSON = (JSONObject) JsonReader.getJson("http://localhost:8080/spring/drzavaregistracije/all");
            JSONArray drzave = (JSONArray) drzaveJSON.get("listaDrzavaRegistracije"); //JSONArray
            ArrayList<Drzava> drzaveArray = Parser.parseDrzave(drzave);

            for (int i = 0; i < num; i++) {
                String nacinNaplate = naciniNaplate.get(new Random().nextInt(naciniNaplate.size()));
                String boja = boje.get(new Random().nextInt(boje.size()));
                //treba dodati VIN generator Utils - importani ne radi!
                String VIN = "";
                for (int k = 0; k < 17; k++) {
                    VIN += ((Integer) new Random().nextInt(10)).toString();
                }
                int idENC = 0;
                // mora uzeti najvecu vrijednost i dodat + 1 - problem kada imamo veliki skup podataka
                if (nacinNaplate == "ENC") {
                    idENC = (Integer) new Random().nextInt(1000000);
                } else {
                    idENC = 0;
                }
                Ekorazred randomEkoRazred =  ekorazrediArray.get(new Random().nextInt(ekorazrediArray.size()));
                Kategorija randomKategorija = kategorijeArray.get(new Random().nextInt(kategorijeArray.size()));
                Drzava randomDrzavaRegistracije = drzaveArray.get(new Random().nextInt(drzaveArray.size()));
                //broj osovina - ovisno o kategoriji (vidi po kategorijama)
                int brojOsovina = BrojOsovinaUtils.generateBrojOsovina(randomKategorija);
                //registracijska oznaka - ovisno o drzavi registracije (grad?)
                String registracijskaOznaka = RegistracijaUtils.generateRegistracija(randomDrzavaRegistracije);
                Vehicle generatedVozilo = new Vehicle(id, nacinNaplate, boja, brojOsovina, VIN, idENC, registracijskaOznaka, randomEkoRazred.getNaziv(), randomKategorija.getNaziv(), randomDrzavaRegistracije.getNaziv());
                id += 1;
                vehicleRepository.save(generatedVozilo);
            }

            return;
        }
        catch (IOException e) {
            System.out.println("Error");
            return;
        }
    }
}
