package com.microservices.payments;

import com.microservices.exceptions.VehicleNotFoundException;
import com.microservices.payments.Utils.DionicaUtils;
import com.microservices.payments.Utils.ExcelGenerator;
import com.microservices.payments.Utils.Parser;
import com.microservices.payments.Utils.OcitanjeUtils;
import com.microservices.payments.models.*;
import com.microservices.vehicles.Utils.JsonReader;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.logging.Logger;

@RestController
public class PaymentController {

    @Autowired
    protected PaymentService paymentService;

    protected Logger logger = Logger.getLogger(PaymentController.class.getName());
    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentService paymentService,
                             PaymentRepository paymentRepository) {
        logger.info("PaymentController() invoked");
        this.paymentService = paymentService;
        this.paymentRepository = paymentRepository;
    }

    @RequestMapping(path = "/payments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Payment> payments(){
        logger.info("payment-service payments() invoked");
        List<Payment> payments = paymentRepository.findAll();
        logger.info("payment-service payments() found: " + payments.size() + " payments");
        return payments;
    }

    @GetMapping(path="/payments/excel")
    public ResponseEntity<ByteArrayResource> allExcel() throws Exception{
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            XSSFWorkbook workbook = ExcelGenerator.generateExcel(paymentRepository.findAll());
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "force-download"));
            header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Payments.xlsx");
            workbook.write(stream);
            workbook.close();
            return new ResponseEntity<>(new ByteArrayResource(stream.toByteArray()),
                    header, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/payments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Payment byId(@PathVariable("id") String id) {
        logger.info("payments-service byId() invoked: " + id);
        Long paymentId = Long.valueOf(id);
        Payment payment = paymentRepository.findById(paymentId);
        logger.info("payments-service byId() found: " + payment);

        if (payment == null)
            throw new VehicleNotFoundException(paymentId);
        else {
            return payment;
        }
    }

    @RequestMapping(path = "/generate", produces = MediaType.APPLICATION_JSON_VALUE)
    public void all() {
        logger.info("payment-service all() invoked");

        try {
            List<Vehicle> vehicles = paymentService.findAllVehicles();

            JSONObject oznakeAutocestaJSON = (JSONObject) JsonReader.getJson("http://localhost:8080/spring/dionica/fetchOznake");
            JSONArray oznake = oznakeAutocestaJSON.getJSONArray("oznake");
            ArrayList<String> oznakeAutocesta = Parser.parseOznake(oznake);

            JSONObject kategorijeJSON = (JSONObject) JsonReader.getJson("http://localhost:8080/spring/kategorija/all");
            JSONArray kategorije = (JSONArray) kategorijeJSON.get("listaKategorija"); //JSONArray
            ArrayList<String> kategorijeArray = OcitanjeUtils.parseKategorije(kategorije);

            Map<String, List<Dionica>> dioniceMap = new HashMap<String, List<Dionica>>();
            Map<Dionica, List<NaplatnaTocka>> naplatneTockeMap = new HashMap<Dionica, List<NaplatnaTocka>>();
            Map<NaplatnaTocka, List<Uredaj>> uredajiMap = new HashMap<NaplatnaTocka, List<Uredaj>>();

            for (int i = 0; i < oznakeAutocesta.size(); i++) {
                String oznaka = oznakeAutocesta.get(i);
                JSONObject dioniceByOznaka = (JSONObject) JsonReader.getJson("http://localhost:8080/spring/dionica/fetchByOznaka?oznaka=" + oznaka);
                JSONArray dionice = dioniceByOznaka.getJSONArray("listaDionica");
                ArrayList<Dionica> dioniceList = Parser.parseDionice(dionice);
                dioniceList.sort(Comparator.comparing(Dionica::getPocetnaStacionaza));
                dioniceMap.put(oznaka, dioniceList);

                for (int j = 0; j < dioniceList.size(); j++) {
                    JSONObject naplatneTockeJSON = (JSONObject) JsonReader.getJson("http://localhost:8080/spring/naplatnatocka/fetchByDionica?dionicaId=" + dioniceList.get(j).getDionicaId());
                    JSONArray naplatneTocke = naplatneTockeJSON.getJSONArray("listaNaplatnihTocki");
                    ArrayList<NaplatnaTocka> naplatneTockeList = Parser.parseNaplatneTocke(naplatneTocke);
                    naplatneTockeMap.put(dioniceList.get(j), naplatneTockeList);

                    for (int k = 0; k < naplatneTockeList.size(); k++ ) {
                        JSONObject uredajiJSON = (JSONObject) JsonReader.getJson("http://localhost:8080/spring/uredaj/allby?id=" + naplatneTockeList.get(k).getId());
                        if (!uredajiJSON.get("listaUredaja").equals(null)) {
                            JSONArray uredaji = uredajiJSON.getJSONArray("listaUredaja"); // tu vraca null nekad
                            ArrayList<Uredaj> uredajiList = Parser.parseUredaji(uredaji);
                            uredajiMap.put(naplatneTockeList.get(k), uredajiList);
                        }
                    }
                }
            }

            for (Vehicle v: vehicles) {
                List<Dionica> dionicaList = DionicaUtils.getDionice(dioniceMap.get(v.getOznakaAutoceste()), v);
                Timestamp pocetnoVrijeme = new Timestamp(v.getVrijeme().getTime()); //zbog vremenske zone
                for (Dionica d: dionicaList) {
                    List<NaplatnaTocka> naplatnaTockaList = naplatneTockeMap.get(d);
                    for (NaplatnaTocka n: naplatnaTockaList) {

                        if (uredajiMap.get(n) != (null)) {
                            List<Uredaj> uredajList = uredajiMap.get(n);
                            //izracunaj vrijeme ocitanja prema stacionazama i prosjecnoj brzini vozila
                            Double put = d.getZavrsnaStacionaza() - d.getPocetnaStacionaza();
                            Double vrijeme = put / v.getProsjecnaBrzina();
                            Timestamp vrijemeOcitanja = new Timestamp(pocetnoVrijeme.getTime() + (long) (vrijeme * 60 * 60 * 1000));
                            pocetnoVrijeme = vrijemeOcitanja;

                            for (Uredaj u : uredajList) {
                                Long id = paymentRepository.lastId() == null ? Long.valueOf(1) : paymentRepository.lastId() + 1;
                                int kvar = u.getKvar();
                                double razinaPouzdanosti;
                                if (u.getRazinaPouzdanosti() != "") {
                                    razinaPouzdanosti = Double.parseDouble(u.getRazinaPouzdanosti());
                                } else {
                                    razinaPouzdanosti = 1;
                                }

                                if (kvar == 0) {
                                    if (u.getUredajType() == 1) { // Kamera - registracija
                                        String registracija;
                                        if (razinaPouzdanosti < 1) {
                                            registracija = OcitanjeUtils.getOcitanjeRegistracije(v.getRegistracijskaOznaka(), razinaPouzdanosti);
                                        } else {
                                            registracija = v.getRegistracijskaOznaka();
                                        }
                                        paymentRepository.save(new Payment(id, vrijemeOcitanja, "Kamera: " + u.getName(), n.getNaziv(), null, 0, registracija));

                                    } else if (u.getUredajType() == 2) { // Primopredajnik - ID ENC (ako postoji)
                                        if (v.getIdENC() != 0) {
                                            int idENC;
                                            if (razinaPouzdanosti < 1) {
                                                idENC = OcitanjeUtils.getOcitanjeENC(v.getIdENC(), razinaPouzdanosti);
                                            } else {
                                                idENC = v.getIdENC();
                                            }
                                            paymentRepository.save(new Payment(id, vrijemeOcitanja, "Primopredajnik: " + u.getName(), n.getNaziv(), null, idENC, null));
                                        }

                                    } else { // Klasifikator - kategorija
                                        String kategorija;
                                        if (razinaPouzdanosti < 1) {
                                            kategorija = OcitanjeUtils.getOcitanjeKategorije(kategorijeArray, razinaPouzdanosti, v.getKategorija());
                                        } else {
                                            kategorija = v.getKategorija();
                                        }
                                        paymentRepository.save(new Payment(id, vrijemeOcitanja, "Klasifikator: " + u.getName(), n.getNaziv(), kategorija, 0, null));
                                    }
                                    System.out.println("Payment saved");
                                }
                            }
                        }
                    }
                }
            }

            return;
        }
        catch (IOException e) {
            System.out.println("Error");
            return;
        }

    }

}
