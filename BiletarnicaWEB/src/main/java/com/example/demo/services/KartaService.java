package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.KartaRepository;

import model.Karta;

@Service
public class KartaService {

	@Autowired
	KartaRepository kr;
	
	public int saveKarta(Karta k) {
		Karta saved = kr.save(k);
		return saved.getIdKarta();
	}
	
	public List<Karta> getKarteKorisnika(int id) {
		return kr.getKarteKorisnika(id);
	}
}
