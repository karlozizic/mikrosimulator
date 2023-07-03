package com.microservices.vehicles;

import com.microservices.exceptions.VehicleNotFoundException;
import com.microservices.payments.models.Vrijeme;
import com.microservices.vehicles.Utils.*;
import com.microservices.vehicles.models.Dionica;
import com.microservices.vehicles.models.Drzava;
import com.microservices.vehicles.models.Ekorazred;
import com.microservices.vehicles.models.Kategorija;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bouncycastle.asn1.cms.Time;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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

//    @RequestMapping(path = "/vehicles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(path = "/vehicles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vehicle> all() {
        logger.info("vehicles-service all() invoked: ");
        List<Vehicle> vehicles = vehicleRepository.findAll();
        logger.info("vehicles-service all() found: " + vehicles);
        return vehicles;
    }
    //https://stackoverflow.com/questions/51684550/how-to-download-an-excel-file-in-spring-restcontroller
    @GetMapping (path="/vehicles/excel")
    public ResponseEntity<ByteArrayResource> allExcel() throws Exception{
    	try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            XSSFWorkbook workbook = ExcelGenerator.generateExcel(vehicleRepository.findAll());
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "force-download"));
            header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Vehicles.xlsx");
            workbook.write(stream);
            workbook.close();
            return new ResponseEntity<>(new ByteArrayResource(stream.toByteArray()),
                    header, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

            int intenzitet;
            Timestamp pocetnoVrijeme = null;
            Timestamp zavrsnoVrijeme = null;
            boolean currentTime = false;
            Map kategorijeMap = new HashMap<Kategorija, Integer>();
            Map<String, Timestamp> dioniceTimeMap = new HashMap<String, Timestamp>();
            if (vrijeme.getPocetnoVrijeme() != null && vrijeme.getZavrsnoVrijeme() != null) {
                pocetnoVrijeme = new Timestamp(vrijeme.getPocetnoVrijeme().getTime() - (long) 2 * 60 * 60 * 1000);
                zavrsnoVrijeme = new Timestamp(vrijeme.getZavrsnoVrijeme().getTime() - (long) 2 * 60 * 60 * 1000);
                int brojSati = (int) ((zavrsnoVrijeme.getTime() - pocetnoVrijeme.getTime()) / (1000 * 60 * 60));
                if (intenzitetParam.equals("-1")) {
                    intenzitet = -1;
                } else {
                    intenzitet = Integer.parseInt(intenzitetParam);
                }
                for(Kategorija k : kategorijeArray) {
                    kategorijeMap.put(k, intenzitet * brojSati);
                }

            } else {
                intenzitet = -1;
                currentTime = true;
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
                Dionica zavrsnaDionica = dioniceList.get(new Random().nextInt(dioniceList.indexOf(pocetnaDionica), dioniceList.size())); // dioniceList.indexOf + 1 iskljucuje pocetnu dionicu

                //broj osovina - ovisno o kategoriji (vidi po kategorijama)
                int brojOsovina = BrojOsovinaUtils.generateBrojOsovina(randomKategorija);
                //registracijska oznaka - ovisno o drzavi registracije (grad?)
                String registracijskaOznaka = RegistracijaUtils.generateRegistracija(randomDrzavaRegistracije);
                float brzina = new Random().nextFloat(90, 130);
                Timestamp vrijemeGeneriranja = null;
                if (currentTime) {
                    if (dioniceTimeMap.containsKey(pocetnaDionica.getOznaka())) {
                        vrijemeGeneriranja = dioniceTimeMap.get(pocetnaDionica.getOznaka());
                        int randomInterval = new Random().nextInt(30, 60) * 1000;
                        vrijemeGeneriranja = new Timestamp(vrijemeGeneriranja.getTime() + randomInterval);
                        dioniceTimeMap.put(pocetnaDionica.getOznaka(), vrijemeGeneriranja);
                    } else {
                        vrijemeGeneriranja = new Timestamp(System.currentTimeMillis());
                        dioniceTimeMap.put(pocetnaDionica.getOznaka(), vrijemeGeneriranja);
                    }
                } else {
                    long vrijemeIzmedu = zavrsnoVrijeme.getTime() - pocetnoVrijeme.getTime();
                    vrijemeGeneriranja = new Timestamp(pocetnoVrijeme.getTime() +  new Random().nextLong(vrijemeIzmedu));
                }

                Vehicle generatedVozilo = new Vehicle(id, nacinNaplate, boja, brojOsovina, VIN, idENC, registracijskaOznaka, randomEkoRazred.getNaziv(), randomKategorija.getNaziv(), randomDrzavaRegistracije.getNaziv(), oznaka, pocetnaDionica.getOznaka(), pocetnaDionica.getDionicaId(), zavrsnaDionica.getOznaka(), zavrsnaDionica.getDionicaId(), brzina, vrijemeGeneriranja);
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
