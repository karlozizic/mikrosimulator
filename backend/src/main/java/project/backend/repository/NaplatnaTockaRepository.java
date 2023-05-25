package project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import project.backend.model.Dionica;
import project.backend.model.NaplatnaTocka;

import java.util.List;

public interface NaplatnaTockaRepository extends JpaRepository<NaplatnaTocka, Long>{

    @Query("select n from NaplatnaTocka n where dionica=?1")
    List<NaplatnaTocka> dohvatSveNaplatneTockePoDionici(Dionica dionica);

}
