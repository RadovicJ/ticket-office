package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import model.Tipsedista;

public interface TipSedistaRepository extends JpaRepository<Tipsedista, Integer> {

	@Query("select ts from Tipsedista ts where ts.dogadjaj.idDogadjaj = :idD")
	public List<Tipsedista> getTipoviSedistaForDogadjaj(@Param("idD") int idD);
	
	@Query("select sum(ts.raspolozivost) from Tipsedista ts where ts.dogadjaj.idDogadjaj = :idD")
	public int getDostupnost(@Param("idD") int idD);
	
	@Query("select ts.raspolozivost from Tipsedista ts where ts.idTipSedista = :idK")
	public int getDostupnostTipa(@Param("idK") int idK);
	
	@Query("select ts.kategorija from Tipsedista ts where ts.dogadjaj.idDogadjaj = :idD and ts.kategorija = :kat")
	public String getKategorija(@RequestParam("idD") int idD, @RequestParam("kat") String kat);
	
	@Modifying
	@Transactional
	@Query("update Tipsedista ts set ts.raspolozivost = :value where ts.idTipSedista = :idK")
	public void decreaseRaspolozivost(@Param("value") int value, @Param("idK") int idK);
}
