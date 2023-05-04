package com.microservices.vehicles.Utils;

import com.microservices.vehicles.models.Kategorija;

public class BrojOsovinaUtils {

    public static int generateBrojOsovina(Kategorija kategorija) {
        String k = kategorija.getNaziv();
        if (k == "CE") {
            return 4;
        } else {
            return 2;
        }
    }
}
