package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.TipDogadjajaRepository;

import model.Tipdogadjaja;

@Service
public class TipDogadjajaService {

	@Autowired
	TipDogadjajaRepository tdr;
	
	public List<Tipdogadjaja> getTipovi() {
		return tdr.findAll();
	}
	
	public Tipdogadjaja findById(int id) {
		return tdr.findById(id).get();
	}
}
