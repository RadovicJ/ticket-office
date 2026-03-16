package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.security.CustomUserDetail;
import com.example.demo.services.DogadjajService;
import com.example.demo.services.KartaService;
import com.example.demo.services.KorisnikService;
import com.example.demo.services.VenueService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/korisnik/")
public class KorisnikController {

	@Autowired
	KorisnikService korisinikServ;
	
	@Autowired
	KartaService kartaserv;
	
	@Autowired
	VenueService venueServ;
	
	@Autowired
	DogadjajService dogadjajServ;
	
	@GetMapping("pregled")
	public String pregled(HttpServletRequest req) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        int idK = userDetails.getId();
            
		req.getSession().setAttribute("karteKorisnika", kartaserv.getKarteKorisnika(idK));
		req.getSession().setAttribute("venues", venueServ.getVenuesMenadzera(idK));
		req.getSession().setAttribute("dogadjajiMenadzera", dogadjajServ.getDogadjajiMenadzera(idK));
		req.getSession().setAttribute("sveLokacije", venueServ.getVenues());
		req.getSession().setAttribute("slobMenadžeri", korisinikServ.getSlobodniMenadzeri());
		return "profil";
	}
	
	@PostMapping("changePassword")
	public String changePassword(String password, int idK, Model m) {
		String msg = "";
		if (!(password == null || password.equals(""))) {
			try {
				BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
				korisinikServ.changePassword(enc.encode(password), idK);
				msg += "Lozinka je ažurirana";
			} catch (Exception e) {
				msg += "Greška prilikom ažuriranja lozinke";
				e.printStackTrace();
			}
		} else {
			msg += "Polje je obavezno";
		}
		m.addAttribute("msgSaved", msg);
		return "profil";
	}
	
}
