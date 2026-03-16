package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.SedisteRepository;

import model.Sediste;

@Service
public class SedisteService {

	@Autowired
	SedisteRepository sr;
	
	public Sediste findById(int id) {
		return sr.findById(id).get();
	}
	
	public int saveSediste(Sediste s) {
		Sediste saved = sr.save(s);
		return saved.getIdSediste();
	}
}
