package com.example.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Dogadjaj;

public interface DogadjajRepository extends JpaRepository<Dogadjaj, Integer> {

	@Query("select d from Dogadjaj d where (:tip IS NULL OR d.tipdogadjaja.idTipDogadjaja = :tip) "
			+ "AND (:datum IS NULL OR d.datum >= :datum) "
			+ "AND (:lok IS NULL OR d.venue.idVenue = :lok) "
			+ "order by d.datum")
	public List<Dogadjaj> getRezultatPretrage(@Param("tip") Integer tip, @Param("datum") Date datum, @Param("lok") Integer lok);
	
	@Query("select d from Dogadjaj d where d.venue.korisnik.idKorisnik = :idM")
	public List<Dogadjaj> getDogadjajiMenadzera(@Param("idM") int idM);
	
	@Query("select d from Dogadjaj d order by d.datum")
	public List<Dogadjaj> findAll();
}
