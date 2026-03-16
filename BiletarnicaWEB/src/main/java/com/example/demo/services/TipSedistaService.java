package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.TipSedistaRepository;

import model.Tipsedista;

@Service
public class TipSedistaService {

	@Autowired
	TipSedistaRepository tsr;
	
	public List<Tipsedista> getTipoviSedistaForDogadjaj(int idD) {
		return tsr.getTipoviSedistaForDogadjaj(idD);
	}
	
	public Tipsedista findById(int id) {
		return tsr.findById(id).get();
	}
	
	public String getKategorija(int idD, String kat) {
		return tsr.getKategorija(idD, kat);
	}
	
	public int getDostupnostTipa(int idK) {
		return tsr.getDostupnostTipa(idK);
	}
	
	public void decreaseRaspolozivost(Tipsedista ts) {
		int value = ts.getRaspolozivost() - 1;
		tsr.decreaseRaspolozivost(value, ts.getIdTipSedista());
	}
	
	public int saveTipSedista(Tipsedista ts) {
		if (getKategorija(ts.getDogadjaj().getIdDogadjaj(), ts.getKategorija()) != null || ts.getCena() <= 0 || ts.getRaspolozivost() <= 0) {
			return -1;
		}
		Tipsedista saved = tsr.save(ts);
		return saved.getIdTipSedista();
	}
}
