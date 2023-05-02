package project.backend.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import project.backend.model.Vozilo;

import java.util.List;

@Service
@Component
public interface GenerirajVozilaServis {
    List<Vozilo> generirajVozila(int intenzitet);
}
