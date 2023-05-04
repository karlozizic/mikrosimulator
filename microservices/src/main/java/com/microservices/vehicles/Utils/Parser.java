package com.microservices.vehicles.Utils;

import com.microservices.vehicles.models.Drzava;
import com.microservices.vehicles.models.Ekorazred;
import com.microservices.vehicles.models.Kategorija;
import org.json.JSONArray;

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

}
