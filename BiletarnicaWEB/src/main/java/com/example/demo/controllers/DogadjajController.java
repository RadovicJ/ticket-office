package com.example.demo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.security.CustomUserDetail;
import com.example.demo.services.DogadjajService;
import com.example.demo.services.KartaService;
import com.example.demo.services.KorisnikService;
import com.example.demo.services.SedisteService;
import com.example.demo.services.TipDogadjajaService;
import com.example.demo.services.TipSedistaService;
import com.example.demo.services.VenueService;

import jakarta.servlet.http.HttpServletRequest;
import model.Dogadjaj;
import model.Karta;
import model.Sediste;
import model.Tipsedista;

@Controller
@RequestMapping("/dogadjaji/")
public class DogadjajController {

	@Autowired
	DogadjajService dogadjajServ;
	
	@Autowired
	VenueService venueServ;
	
	@Autowired
	TipDogadjajaService tipDogadjajaServ;
	
	@Autowired
	TipSedistaService tipSedistaServ;
	
	@Autowired
	SedisteService sedisteServ;
	
	@Autowired
	KartaService kartaServ;
	
	@Autowired
	KorisnikService korisnikServ;
	
	@GetMapping("svi")
	public String getSviDogadjaji(HttpServletRequest req) {
		req.getSession().removeAttribute("dogadjaj");
		req.getSession().removeAttribute("kategorije");
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String danas = sdf.format(c.getTime());
		req.getSession().setAttribute("danas", danas);
		req.getSession().setAttribute("dogadjaji", dogadjajServ.getSviDogadjaji());
		req.getSession().setAttribute("venues", venueServ.getVenues());
		req.getSession().setAttribute("tipoviDog", tipDogadjajaServ.getTipovi());
		return "pregledDogadjaja";
	}
	
	@GetMapping("filter")
	public String filter(HttpServletRequest req, @RequestParam(name = "idT", required = false) int idT, 
			@RequestParam(name = "datum", required = false) Date datum, 
			@RequestParam(name = "idL", required = false) int idL) throws ParseException {
		Integer tip, lok;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		tip = (idT == 0) ? null : idT;
		lok = (idL == 0) ? null : idL;
		
		datum = (datum == null) ? sdf.parse((String) req.getSession().getAttribute("danas")) : datum;
		req.getSession().removeAttribute("dogadjaji");
		req.getSession().setAttribute("dogadjaji", dogadjajServ.getFilter(tip, datum, lok));
		return "pregledDogadjaja";
	}
	
	@GetMapping("unos")
	public String unos(HttpServletRequest req) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        int idM = userDetails.getId();
        
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String danas = sdf.format(c.getTime());
		req.getSession().setAttribute("danas", danas);
		req.getSession().setAttribute("tipoviDog", tipDogadjajaServ.getTipovi());
		req.getSession().setAttribute("venuesMenadzera", venueServ.getVenuesMenadzera(idM));
		return "unosDogadjaja";
	}
	
	@GetMapping("pregled")
	public String pregled(HttpServletRequest req, @RequestParam("idD") int idD) {
		req.getSession().setAttribute("dogadjaj", dogadjajServ.findById(idD));
		return "prikazDogadjaja";
	}
	
	@GetMapping("ulaznice")
	public String ulaznice(HttpServletRequest req, @RequestParam("idD") int idD) {
		req.getSession().setAttribute("dogadjaj", dogadjajServ.findById(idD));
		req.getSession().setAttribute("kategorije", tipSedistaServ.getTipoviSedistaForDogadjaj(idD));
		return "kupovinaKarata";
	}
	
	@GetMapping("unosSedista")
	public String unosSedista(HttpServletRequest req) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        int idM = userDetails.getId();
        
		req.getSession().setAttribute("dogadjajiMenadzera", dogadjajServ.getDogadjajiMenadzera(idM));
		return "unosSedista";
	}
	
	@PostMapping("saveDogadjaj")
	public String saveDogadjaj(String naziv, Date datum, String opis, int idL, int idT, Model m) {
		String msg = "";
		try {
			Dogadjaj d = new Dogadjaj();
			d.setNaziv(naziv);
			d.setDatum(datum);
			if (!(opis == null || opis.equals(""))) {
				d.setOpis(opis);
			}
			d.setVenue(venueServ.findbyId(idL));
			d.setTipdogadjaja(tipDogadjajaServ.findById(idT));
			int saved = dogadjajServ.saveDogadjaj(d);
			if (saved == -1) {
				msg += "Polja su prazna";
			}
			else {
				msg += "Događaj je sačuvan";
			}
		} catch (Exception e) {
			msg += "Greska prilikom čuvanja događaja";
		}
		m.addAttribute("msgSaved", msg);
		return "unosDogadjaja";
	}
	
	@PostMapping("saveSedista")
	public String saveSedista(String kat, int cena, int rasp, int idD, Model m) {
		String msg = "";
		try {
			Tipsedista ts = new Tipsedista();
			ts.setKategorija(kat);
			ts.setCena(cena);
			ts.setRaspolozivost(rasp);
			ts.setDogadjaj(dogadjajServ.findById(idD));
			int saved = tipSedistaServ.saveTipSedista(ts);
			if (saved == -1) {
				msg += "Polja su prazna";
			}
			else {
				msg += "Tip sedišta je sačuvan";
			}
		} catch (Exception e) {
			msg += "Greska prilikom čuvanja tipa sedišta";
		}
		m.addAttribute("msgSaved", msg);
		return "unosSedista";
	}
	
	@GetMapping("potvrda")
	public String potvrda(HttpServletRequest req, @RequestParam("idD") int idD, @RequestParam("idK") int idK) {
		if (tipSedistaServ.getDostupnostTipa(idK) == 0) {
			return "kupovinaKarata";
		}
		req.getSession().setAttribute("dogadjaj", dogadjajServ.findById(idD));
		req.getSession().setAttribute("tipsedista", tipSedistaServ.findById(idK));
		return "potvrdaKupovine";
	}
	
	@PostMapping("saveKarta")
	public String saveKarta(int korisnik, Date datum, int dogadjaj, int tipSed, Model m) {
		if (tipSedistaServ.getDostupnostTipa(tipSed) == 0) {
			return "kupovinaKarata";
		}
		String msg = "";
		try {
			Tipsedista ts = tipSedistaServ.findById(tipSed);
			Sediste s = new Sediste();
			s.setTipsedista(ts);
			int sedisteId = sedisteServ.saveSediste(s);
			Karta k = new Karta();
			k.setDatumProdaje(datum);
			k.setDogadjaj(dogadjajServ.findById(dogadjaj));
			k.setKorisnik(korisnikServ.findById(korisnik));
			k.setSediste(sedisteServ.findById(sedisteId));
			kartaServ.saveKarta(k);
			msg += "Karta je sačuvana";
			
			tipSedistaServ.decreaseRaspolozivost(ts);
		} catch (Exception e) {
			msg += "Greska prilikom čuvanja karte";
			e.printStackTrace();
		}
		m.addAttribute("msgSaved", msg);
		return "potvrdaKupovine";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true); 
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
}
