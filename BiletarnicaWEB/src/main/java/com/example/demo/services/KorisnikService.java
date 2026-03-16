package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.KorisnikRepository;
import com.example.demo.repositories.RoleRepository;

import model.Korisnik;
import model.Role;

@Service
public class KorisnikService {

	@Autowired
	KorisnikRepository kr;
	
	@Autowired
	RoleRepository rr;
	
	public List<Korisnik> getSlobodniMenadzeri() {
		return kr.getSlobodniMenadzeri();
	}
	
	public Korisnik findById(int idK) {
		return kr.findById(idK).get();
	}
	
	public int saveKorisnik(Korisnik k) {
		if (k.getIme() == null || k.getPrezime() == null || k.getUsername() == null || k.getPassword() == null ||
				k.getIme().equals("") || k.getPrezime().equals("") || k.getUsername().equals("") || k.getPassword().equals("")) {
			return -1;
		}
		if (kr.findByUsername(k.getUsername()) != null) {
			return -2;
		}
		Korisnik saved = kr.save(k);
		return saved.getIdKorisnik();
	}
	
	public Role findRoleById(int id) {
		return rr.findById(id).get();
	}
	
	public void changePassword(String password, int idK) {
		kr.changePassword(password, idK);
	}
}
