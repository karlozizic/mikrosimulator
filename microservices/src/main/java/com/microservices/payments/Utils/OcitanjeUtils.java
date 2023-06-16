package com.microservices.payments.Utils;

import com.microservices.vehicles.models.Kategorija;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OcitanjeUtils {

    public static String getOcitanjeRegistracije(String registracija, double razinaPouzdanosti) {
        String novaRegistracija = "";
        //prvi i zadnji indeks broja
        int prviIndeksBroja = 0;
        int zadnjiIndeksBroja = 0;

        for (int i = 0; i < registracija.length(); i++) {
            if (Character.isDigit(registracija.charAt(i))) {
                prviIndeksBroja = i;
                break;
            }
        }

        for (int i = 0; i < registracija.length(); i++) {
            if (Character.isDigit(registracija.charAt(i))) {
                zadnjiIndeksBroja = i;
            }
        }

        for (int i = 0; i < registracija.length(); i++) {
            double random = Math.random();
            //ovisno o pouzdanosti svaki znak moze biti random ili ne
            if (random > razinaPouzdanosti) {
                if (i < prviIndeksBroja || i > zadnjiIndeksBroja) {
                    char randomChar = (char) ((int) 'A' + new Random().nextInt(26));    //
                    novaRegistracija += randomChar;
                } else {
                    novaRegistracija += (new Random().nextInt(10));
                }
            } else {
                novaRegistracija += registracija.charAt(i);
            }
        }

        return novaRegistracija;
    }

    public static int getOcitanjeENC(int enc, double razinaPouzdanosti) {
        String noviENC = "";
        String encString = String.valueOf(enc);
        for (int i = 0; i < encString.length(); i++) {
            double random = Math.random();
            // ovisno o pouzdanosti svaka znamenka moze biti random
            if (random > razinaPouzdanosti) {
                noviENC += (new Random().nextInt(10));
            } else {
                noviENC += encString.charAt(i);
            }
        }
        return Integer.parseInt(noviENC);
    }

    public static String getOcitanjeKategorije(List<String> kategorije, double razinaPouzdanosti, String kategorija) {
        double random = Math.random();
        String newKategorija;
        // ovisno o pouzdanosti ocitava krivu kategoriju
        if (random > razinaPouzdanosti) {
            newKategorija = kategorije.get(new Random().nextInt(kategorije.size()));
        } else {
            newKategorija = kategorija;
        }
        return newKategorija;
    }

    public static ArrayList<String> parseKategorije (JSONArray kategorije) {
        ArrayList<String> kategorijeList = new ArrayList<>();
        for (int i = 0; i < kategorije.length(); i++) {
            kategorijeList.add(kategorije.getJSONObject(i).getString("naziv"));
        }
        return kategorijeList;
    }

}
