package com.microservices.vehicles.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.vehicles.models.Dionica;
import com.microservices.vehicles.models.Drzava;
import com.microservices.vehicles.models.Ekorazred;
import com.microservices.vehicles.models.Kategorija;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser {

    public static ArrayList<Ekorazred> parseEkoRazredi (JSONArray ekoRazredi) {
        ArrayList<Ekorazred> ekoRazrediList = new ArrayList<>();
        for (int i = 0; i < ekoRazredi.length(); i++) {
            ekoRazrediList.add(new Ekorazred(ekoRazredi.getJSONObject(i).getLong("id"), ekoRazredi.getJSONObject(i).getString("naziv")));
        }
        return ekoRazrediList;
    }

    public static ArrayList<Kategorija> parseKategorije (JSONArray kategorije) {
        ArrayList<Kategorija> kategorijeList = new ArrayList<>();
        for (int i = 0; i < kategorije.length(); i++) {
            kategorijeList.add(new Kategorija(kategorije.getJSONObject(i).getLong("id"), kategorije.getJSONObject(i).getString("naziv")));
        }
        return kategorijeList;
    }

    public static ArrayList<Drzava> parseDrzave (JSONArray drzave) {
        ArrayList<Drzava> drzaveList = new ArrayList<>();
        for (int i = 0; i < drzave.length(); i++) {
            drzaveList.add(new Drzava(drzave.getJSONObject(i).getLong("id"), drzave.getJSONObject(i).getString("naziv")));
        }
        return drzaveList;
    }

    public static ArrayList<Dionica> parseDionice (JSONArray dionice) {
        ArrayList<Dionica> dioniceList = new ArrayList<>();
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
            dioniceList.add(new Dionica(dionica.getLong("dionicaId"), dionica.getString("smjer"), dionica.getInt("najvecaBrzina"), dionica.getInt("brojTraka"), dionica.getString("oznaka"), pocetnaStacionaza, zavrsnaStacionaza, dionica.getString("oznakaAutoceste"), slijediId, prethodiId));
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
