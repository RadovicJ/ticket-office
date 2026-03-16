package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {

	@Query("select k from Korisnik k where k.role.idRole = 2 and k.idKorisnik not in (select v.korisnik.idKorisnik from Venue v)")
	public List<Korisnik> getSlobodniMenadzeri();
	
	public Korisnik findByUsername(String username);
	
	@Modifying
	@Transactional
	@Query("update Korisnik k set k.password = :password where k.idKorisnik = :idK")
	public void changePassword(@Param("password") String password, @Param("idK") int idK);
}
