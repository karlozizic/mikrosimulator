package project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.backend.model.Kategorija;

public interface KategorijaRepository extends JpaRepository<Kategorija, Long>{
	
}
