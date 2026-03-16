package com.example.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.services.KorisnikService;

import jakarta.servlet.http.HttpServletRequest;
import model.Korisnik;

@Controller
public class LoginController {
	
	@Autowired
	KorisnikService korisnikServ;
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}
	
	@GetMapping("/")
	public String getHomePage(HttpServletRequest req) {
		req.getSession().removeAttribute("msgUNFE");
		return "index";
	}
	
	@GetMapping("/registracija")
	public String getRegistracija() {
		return "registracija";
	}
	
	@GetMapping("/unosMenadzera")
	public String unosMenadzera() {
		return "registracija";
	}
	
	@PostMapping("/saveKorisnik")
	public String saveKorisnik(String ime, String prezime, String username, String password, int idRole, Model m) {
		String msg = "";
		try {
			Korisnik k = new Korisnik();
			k.setIme(ime);
			k.setPrezime(prezime);
			k.setUsername(username);
			BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
			k.setPassword(enc.encode(password));
			k.setRole(korisnikServ.findRoleById(idRole));
			int saved = korisnikServ.saveKorisnik(k);
			if (saved == -1) {
				msg += "Polja su prazna";
			}
			else if (saved == -2) {
				msg += "Korisničko ime je zauzeto";
			}
			else {
				msg += "Korisnik je sačuvan";
			}
		} catch (Exception e) {
			msg += "Greška prilikom čuvanja korisnika";
		}
		m.addAttribute("msgSaved", msg);
		return "registracija";
	}
}
