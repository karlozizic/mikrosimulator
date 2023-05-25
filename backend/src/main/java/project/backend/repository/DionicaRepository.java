package project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import project.backend.model.Dionica;

import java.util.List;

public interface DionicaRepository extends JpaRepository<Dionica, Long>{

    @Query("select d from Dionica d where d.oznakaAutoceste=?1")
    List<Dionica> findByOznaka(String oznaka);

}
