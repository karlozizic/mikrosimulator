package com.microservices.vehicles.Utils;

import com.microservices.vehicles.models.Drzava;

import java.util.Random;

public class RegistracijaUtils {

    public static String generateRegistracija(Drzava drzava) {
        int upperBound = 10;
        String registracija = drzava.getNaziv();

        for (int i = 0; i < 4; i++) {
            registracija += ((Integer) new Random().nextInt(upperBound)).toString();
        }
        for (int i = 0; i < 2; i++) {
            Random r = new Random();
            registracija += (char)(r.nextInt(26) + 'a');
        }

        return registracija.toUpperCase();
    }
}
