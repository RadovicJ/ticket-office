package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.GradRepository;

import model.Grad;

@Service
public class GradService {

	@Autowired
	GradRepository gr;
	
	public List<Grad> getGradovi() {
		return gr.findAll();
	}
	
	public Grad findById(int idG) {
		return gr.findById(idG).get();
	}
	
	public Grad getGrad(String ime) {
		return gr.findByIme(ime);
	}
	
	public int saveGrad(Grad g) {
		if (getGrad(g.getIme()) != null) {
			return -1;
		}
		Grad saved = gr.save(g);
		return saved.getIdGrad();
	}
}
