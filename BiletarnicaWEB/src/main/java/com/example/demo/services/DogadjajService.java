package com.example.demo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.DogadjajRepository;

import model.Dogadjaj;

@Service
public class DogadjajService {

	@Autowired
	DogadjajRepository dr;
	
	public List<Dogadjaj> getSviDogadjaji() {
		return dr.findAll();
	}
	
	public Dogadjaj findById(int id) {
		return dr.findById(id).get();
	}
	
	public List<Dogadjaj> getDogadjajiMenadzera(int idM) {
		return dr.getDogadjajiMenadzera(idM);
	}
	
	public List<Dogadjaj> getFilter(Integer tip, Date datum, Integer lok) {
		return dr.getRezultatPretrage(tip, datum, lok);
	}
	
	public int saveDogadjaj(Dogadjaj d) {
		if (d.getNaziv() == null || d.getNaziv().equals("") || d.getDatum() == null || d.getVenue() == null || d.getTipdogadjaja() == null) {
			return -1;
		}
		Dogadjaj saved = dr.save(d);
		return saved.getIdDogadjaj();
	}
}
