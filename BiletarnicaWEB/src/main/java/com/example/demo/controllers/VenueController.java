package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.GradService;
import com.example.demo.services.KorisnikService;
import com.example.demo.services.VenueService;

import jakarta.servlet.http.HttpServletRequest;
import model.Venue;

@Controller
@RequestMapping("/lokacija/")
public class VenueController {

	@Autowired
	VenueService venueServ;
	
	@Autowired
	GradService gradServ;
	
	@Autowired
	KorisnikService korisnikServ;
	
	@GetMapping("unos")
	public String unos(HttpServletRequest req) {
		req.getSession().setAttribute("mesta", gradServ.getGradovi());
		req.getSession().setAttribute("menadzeri", korisnikServ.getSlobodniMenadzeri());
		return "unosLokacije";
	}
	
	@PostMapping("saveVenue")
	public String saveVenue(String naziv, int idG, int idK, Model m) {
		String msg = "";
		try {
			Venue v = new Venue();
			v.setNaziv(naziv);
			v.setGrad(gradServ.findById(idG));
			v.setKorisnik(korisnikServ.findById(idK));
			int saved = venueServ.saveVenue(v);
			if (saved == -1) {
				msg += "Polja su prazna";
			}
			else {
				msg += "Lokacija je sačuvana";
			}
		} catch (Exception e) {
			msg += "Greška prilikom čuvanja lokacije";
		}
		m.addAttribute("msgSaved", msg);
		return "unosLokacije";
	}
}
