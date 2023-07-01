package com.microservices.payments.Utils;

import com.microservices.payments.models.Dionica;
import com.microservices.payments.models.Vehicle;

import java.util.List;
import java.util.Map;

public class DionicaUtils {

    public static List<Dionica> getDionice(List<Dionica> dionice, Vehicle v) {
        int pocInd = 0;
        int zavInd = 0;
        for (Dionica d: dionice) {
            if (v.getPocetnaDionicaId() == d.getDionicaId()) {
                pocInd = dionice.indexOf(d);
            }
        }
        for (Dionica d: dionice) {
            if (v.getZavrsnaDionicaId() == d.getDionicaId()) {
                zavInd = dionice.indexOf(d);
            }
        }

        List<Dionica> dionicaList = dionice.subList(pocInd, zavInd + 1); // bez +1 ne ukljucuje zavrsnu dionicu
        return dionicaList;
    }
}
