package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Karta;

public interface KartaRepository extends JpaRepository<Karta, Integer> {

	@Query("select k from Karta k where k.korisnik.idKorisnik = :idK")
	public List<Karta> getKarteKorisnika(@Param("idK") int idK);
}
