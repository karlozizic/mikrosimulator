package com.microservices.vehicles;

import com.microservices.exceptions.VehicleNotFoundException;
import com.microservices.payments.models.Vrijeme;
import com.microservices.vehicles.Utils.BrojOsovinaUtils;
import com.microservices.vehicles.Utils.JsonReader;
import com.microservices.vehicles.Utils.Parser;
import com.microservices.vehicles.Utils.RegistracijaUtils;
import com.microservices.vehicles.models.Dionica;
import com.microservices.vehicles.models.Drzava;
import com.microservices.vehicles.models.Ekorazred;
import com.microservices.vehicles.models.Kategorija;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
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

    @PostMapping(path = "/vehicles/generate/{number}")//, produces = MediaType.APPLICATION_JSON_VALUE)
    public void generateVehicles(@PathVariable("number") String number, @RequestBody Vrijeme vrijeme, @RequestParam(name="intenzitet") String intenzitetParam) {

        int num = Integer.parseInt(number);

        try {
            Long id = vehicleRepository.lastId() == null ? Long.valueOf(1) : vehicleRepository.lastId() + 1;

            ArrayList<String> naciniNaplate = new ArrayList<String>(Arrays.asList("Bežično", "Registracijska oznaka"));

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

            JSONObject oznakeAutocestaJSON = (JSONObject) JsonReader.getJson("http://localhost:8080/spring/dionica/fetchOznake");
            JSONArray oznake = (JSONArray) oznakeAutocestaJSON.get("oznake"); //JSONArray
            ArrayList<String> oznakeAutocesta = Parser.parseOznake(oznake);

            Timestamp pocetnoVrijeme;
            Timestamp zavrsnoVrijeme;
            int intenzitet;
            Map kategorijeMap = new HashMap<Kategorija, Integer>();
            if (vrijeme.getPocetnoVrijeme() != null && vrijeme.getZavrsnoVrijeme() != null) {
                pocetnoVrijeme = vrijeme.getPocetnoVrijeme();
                zavrsnoVrijeme = vrijeme.getZavrsnoVrijeme();
                int brojSati = (int) ((zavrsnoVrijeme.getTime() - pocetnoVrijeme.getTime()) / (1000 * 60 * 60));
                intenzitet = Integer.parseInt(intenzitetParam);
                for(Kategorija k : kategorijeArray) {
                    kategorijeMap.put(k, intenzitet * brojSati);
                }

            } else {
                pocetnoVrijeme = new Timestamp(System.currentTimeMillis() + 2 * 60 * 60 * 1000);
                zavrsnoVrijeme = new Timestamp(System.currentTimeMillis() + 2 * 60 * 60 * 1000 + 1); // + 1 da ne bi bilo isto vrijeme
                intenzitet = -1;
            }

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
                if (nacinNaplate == "Bežično") {
                    idENC = (Integer) new Random().nextInt(1000000);
                } else {
                    idENC = 0;
                }
                Ekorazred randomEkoRazred =  ekorazrediArray.get(new Random().nextInt(ekorazrediArray.size()));
                Kategorija randomKategorija = null;
                if (intenzitet == -1) {
                    randomKategorija = kategorijeArray.get(new Random().nextInt(kategorijeArray.size()));
                } else {
                    boolean finished = false;
                    while (!finished) {
                        randomKategorija = kategorijeArray.get(new Random().nextInt(kategorijeArray.size()));
                        if ((Integer) kategorijeMap.get(randomKategorija) > 0) {
                            kategorijeMap.put(randomKategorija, (Integer) kategorijeMap.get(randomKategorija) - 1);
                            finished = true;
                        } 
                    }
                }
                Drzava randomDrzavaRegistracije = drzaveArray.get(new Random().nextInt(drzaveArray.size()));

                String oznaka = oznakeAutocesta.get(new Random().nextInt(oznakeAutocesta.size()));
                JSONObject dionice = (JSONObject) JsonReader.getJson("http://localhost:8080/spring/dionica/fetchByOznaka?oznaka=" + oznaka);
                JSONArray dioniceArray = (JSONArray) dionice.get("listaDionica");
                List<Dionica> dioniceList = Parser.parseDionice(dioniceArray);
                dioniceList.sort(Comparator.comparing(Dionica::getPocetnaStacionaza)); //sortirane dionice po pocetnoj stacionazi
                Dionica pocetnaDionica = dioniceList.get(new Random().nextInt(dioniceList.size() - 1)); //ne moze biti zadnja dionica
                Dionica zavrsnaDionica = dioniceList.get(new Random().nextInt(dioniceList.indexOf(pocetnaDionica) + 1, dioniceList.size()));

                //broj osovina - ovisno o kategoriji (vidi po kategorijama)
                int brojOsovina = BrojOsovinaUtils.generateBrojOsovina(randomKategorija);
                //registracijska oznaka - ovisno o drzavi registracije (grad?)
                String registracijskaOznaka = RegistracijaUtils.generateRegistracija(randomDrzavaRegistracije);
                float brzina = new Random().nextFloat(90, 130);

                long vrijemeIzmedu = zavrsnoVrijeme.getTime() - pocetnoVrijeme.getTime();
                Timestamp randomVrijeme = new Timestamp(pocetnoVrijeme.getTime() +  new Random().nextLong(vrijemeIzmedu));

                Vehicle generatedVozilo = new Vehicle(id, nacinNaplate, boja, brojOsovina, VIN, idENC, registracijskaOznaka, randomEkoRazred.getNaziv(), randomKategorija.getNaziv(), randomDrzavaRegistracije.getNaziv(), oznaka, pocetnaDionica.getOznaka(), pocetnaDionica.getDionicaId(), zavrsnaDionica.getOznaka(), zavrsnaDionica.getDionicaId(), brzina, randomVrijeme);
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
