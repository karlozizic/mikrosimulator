package project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import project.backend.model.NaplatnaTocka;
import project.backend.model.NoviUredaj;
import project.backend.model.Uredaj;

import java.util.List;

public interface UredajRepository extends JpaRepository<Uredaj, Long>{

    @Query("select uredajType from uredaj where id=?1")
    int dohvatiTipUredaja(long uredajId);

    @Query("select u from uredaj u where naplatnaTocka=?1")
    List<Uredaj> dohvatiUredajeNaplatneTocke(NaplatnaTocka naplatnaTocka);
}
