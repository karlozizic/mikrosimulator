package com.microservices.payments;

import com.microservices.payments.Utils.DionicaUtils;
import com.microservices.payments.Utils.Parser;
import com.microservices.payments.models.Dionica;
import com.microservices.payments.models.NaplatnaTocka;
import com.microservices.payments.models.Uredaj;
import com.microservices.payments.models.Vehicle;
import com.microservices.vehicles.Utils.JsonReader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(path = "/generate", produces = MediaType.APPLICATION_JSON_VALUE)
    public void all() {
        logger.info("payment-service all() invoked");

        try {
            List<Vehicle> vehicles = paymentService.findAllVehicles();

            JSONObject oznakeAutocestaJSON = (JSONObject) JsonReader.getJson("http://localhost:8080/spring/dionica/fetchOznake");
            JSONArray oznake = oznakeAutocestaJSON.getJSONArray("oznake");
            ArrayList<String> oznakeAutocesta = Parser.parseOznake(oznake);

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
                Date pocetnoVrijeme = new Date();
                for (Dionica d: dionicaList) {
                    List<NaplatnaTocka> naplatnaTockaList = naplatneTockeMap.get(d);
                    for (NaplatnaTocka n: naplatnaTockaList) {

                        if (uredajiMap.get(n) != (null)) {
                            List<Uredaj> uredajList = uredajiMap.get(n);
                            //izracunaj vrijeme ocitanja prema stacionazama i prosjecnoj brzini vozila
                            Double put = d.getZavrsnaStacionaza() - d.getPocetnaStacionaza();
                            Double vrijeme = put / v.getProsjecnaBrzina();
                            pocetnoVrijeme = new java.sql.Date(pocetnoVrijeme.getTime() + (long) (vrijeme * 60 * 60 * 1000));
                            java.sql.Date vrijemeOcitanja = (java.sql.Date) pocetnoVrijeme;

                            for (Uredaj u : uredajList) {
                                Long id = paymentRepository.lastId() == null ? Long.valueOf(1) : paymentRepository.lastId() + 1;
                                Timestamp t = new Timestamp(vrijemeOcitanja.getTime() + 2 * 60 * 60 * 1000); //dodajemo 2 sata zbog vremenske zone
                                if (u.getUredajType() == 1) { // Kamera - registracija
                                    paymentRepository.save(new Payment(id, t, "Kamera: " + u.getName(), n.getNaziv(), null, 0, v.getRegistracijskaOznaka()));
                                } else if (u.getUredajType() == 2) { // Primopredajnik - ID ENC (ako postoji)
                                    if (v.getIdENC() != 0) {
                                        paymentRepository.save(new Payment(id, t, "Primopredajnik: " + u.getName(), n.getNaziv(), null, v.getIdENC(), null));
                                    }
                                } else { // Klasifikator - kategorija
                                    paymentRepository.save(new Payment(id, t, "Klasifikator: " + u.getName(), n.getNaziv(), v.getKategorija(), 0, null));
                                }
                                System.out.println("Payment saved");
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
