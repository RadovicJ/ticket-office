package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.VenueRepository;

import model.Venue;

@Service
public class VenueService {

	@Autowired
	VenueRepository vr;
	
	public List<Venue> getVenues() {
		return vr.findAll();
	}
	
	public Venue findbyId(int id) {
		return vr.findById(id).get();
	}
	
	public int saveVenue(Venue v) {
		if (v.getNaziv().equals("")) {
			return -1;
		}
		Venue saved = vr.save(v);
		return saved.getIdVenue();
	}

	public List<Venue> getVenuesMenadzera(int id) {
		return vr.getVenuesMenadzera(id);
	}
}
