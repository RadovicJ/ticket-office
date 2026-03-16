package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Venue;

public interface VenueRepository extends JpaRepository<Venue, Integer> {

	@Query("select v from Venue v where v.korisnik.idKorisnik = :idK")
	public List<Venue> getVenuesMenadzera(@Param("idK") int idK);
}
