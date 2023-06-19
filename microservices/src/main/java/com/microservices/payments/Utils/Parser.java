package com.microservices.payments.Utils;
import com.microservices.payments.models.Dionica;
import com.microservices.payments.models.NaplatnaTocka;
import com.microservices.payments.models.Uredaj;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser {

    public static ArrayList<NaplatnaTocka> parseNaplatneTocke (JSONArray naplatneTocke) {
        ArrayList<NaplatnaTocka> naplatneTockeList = new ArrayList<>();
        for (int i = 0; i < naplatneTocke.length(); i++) {
            JSONObject naplatnaTocka = naplatneTocke.getJSONObject(i);
            JSONObject dionicaJSON = naplatnaTocka.getJSONObject("dionica");
            Long dionicaId = dionicaJSON.getLong("dionicaId");
            String dionicaOznaka = dionicaJSON.getString("smjer");
            String[] stacionazaArr = dionicaJSON.getString("pocetnaStacionaza").split("\\+");
            Double stacionaza = Double.parseDouble(stacionazaArr[0]) + Double.parseDouble(stacionazaArr[1]) / 1000 ;
            naplatneTockeList.add(new NaplatnaTocka(naplatnaTocka.getLong("naplatnaTockaId"), naplatnaTocka.getString("oznaka"), naplatnaTocka.getString("naziv"), stacionaza, naplatnaTocka.getInt("geografskaDuzina"), naplatnaTocka.getInt("geografskaSirina"), naplatnaTocka.getString("usmjerenje"), dionicaId, dionicaOznaka));
        }
        return naplatneTockeList;
    }

    public static ArrayList<Uredaj> parseUredaji (JSONArray uredaji) {
        ArrayList<Uredaj> uredajiList = new ArrayList<>();
        for (int i = 0; i < uredaji.length(); i++) {
            JSONObject uredaj = uredaji.getJSONObject(i);
            uredajiList.add(new Uredaj(uredaj.getLong("id"), uredaj.getInt("uredajtype"), uredaj.getString("name"), uredaj.getLong("naplatnaTockaId"), uredaj.getInt("kvar"), uredaj.getString("razinaPouzdanosti")));
        }
        return uredajiList;
    }

    public static ArrayList<com.microservices.payments.models.Dionica> parseDionice (JSONArray dionice) {
        ArrayList<com.microservices.payments.models.Dionica> dioniceList = new ArrayList<>();
        Long prethodiId = null;
        Long slijediId = null;
        for (int i = 0; i < dionice.length(); i++) {
            JSONObject dionica = dionice.getJSONObject(i);
            if (!dionica.get("prethodi").equals(null)) {
                JSONObject dionicaPrethodi = dionica.getJSONObject("prethodi");
                prethodiId = dionicaPrethodi.getLong("dionicaId");
            }
            if (!dionica.get("slijedi").equals(null)) {
                JSONObject dionicaSlijedi = dionica.getJSONObject("slijedi");
                slijediId = dionicaSlijedi.getLong("dionicaId");
            }
            String[] pocetnaStacionazaArr = dionica.getString("pocetnaStacionaza").split("\\+");
            Double pocetnaStacionaza = Double.parseDouble(pocetnaStacionazaArr[0]) + Double.parseDouble(pocetnaStacionazaArr[1]) / 1000 ;
            String[] zavrsnaStacionazaArr = dionica.getString("zavrsnaStacionaza").split("\\+");
            Double zavrsnaStacionaza = Double.parseDouble(zavrsnaStacionazaArr[0]) + Double.parseDouble(zavrsnaStacionazaArr[1]) / 1000 ;
            dioniceList.add(new com.microservices.payments.models.Dionica(dionica.getLong("dionicaId"), dionica.getString("smjer"), dionica.getInt("najvecaBrzina"), dionica.getInt("brojTraka"), dionica.getString("oznaka"), pocetnaStacionaza, zavrsnaStacionaza, dionica.getString("oznakaAutoceste"), slijediId, prethodiId));
        }
        return dioniceList;
    }

    public static ArrayList<String> parseOznake (JSONArray oznake) {
        ArrayList<String> oznakeList = new ArrayList<>();
        for (int i = 0; i < oznake.length(); i++) {
            oznakeList.add(oznake.getString(i));
        }
        return oznakeList;
    }
}
