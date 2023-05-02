package project.backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.backend.model.DrzavaRegistracije;
import project.backend.model.EkoRazred;
import project.backend.model.Kategorija;
import project.backend.model.Vozilo;
import project.backend.repository.DrzavaRegistracijeRepository;
import project.backend.repository.EkoRazredRepository;
import project.backend.repository.KategorijaRepository;
import project.backend.repository.VoziloRepository;
import project.backend.service.GenerirajVozilaServis;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenerirajVozilaImpl implements GenerirajVozilaServis {

    EkoRazredRepository ekoRazredRepository;
    KategorijaRepository kategorijaRepository;
    DrzavaRegistracijeRepository drzavaRegistracijeRepository;

    VoziloRepository voziloRepository;

    public GenerirajVozilaImpl(@Autowired EkoRazredRepository ekoRazredRepository, @Autowired KategorijaRepository kategorijaRepository, @Autowired DrzavaRegistracijeRepository drzavaRegistracijeRepository) {
        this.ekoRazredRepository = ekoRazredRepository;
        this.kategorijaRepository = kategorijaRepository;
        this.drzavaRegistracijeRepository = drzavaRegistracijeRepository;
    }

    @Override
    public List<Vozilo> generirajVozila(int intenzitet) {

        List<EkoRazred> ekoRazredList = ekoRazredRepository.findAll();
        List<Kategorija> kategorijaList = kategorijaRepository.findAll();
        List<DrzavaRegistracije> drzavaRegistracijeList = drzavaRegistracijeRepository.findAll();
        List<Vozilo> voziloList = new ArrayList<Vozilo>();

        for (int i = 0; i < intenzitet; i++) {
            EkoRazred randomEkoRazred =  ekoRazredList.get((int)Math.random()*ekoRazredList.size());
            Kategorija randomKategorija = kategorijaList.get((int)Math.random()*kategorijaList.size());
            DrzavaRegistracije randomDrzavaRegistracije = drzavaRegistracijeList.get((int)Math.random()*drzavaRegistracijeList.size());
            Vozilo generiranoVozilo = new Vozilo(null, null, 0, 0, 0, null, randomEkoRazred, randomKategorija, randomDrzavaRegistracije);
            voziloList.add(generiranoVozilo);
        }

        return voziloList;
    }

}
