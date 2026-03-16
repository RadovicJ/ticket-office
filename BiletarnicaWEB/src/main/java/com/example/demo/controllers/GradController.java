package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.GradService;

import model.Grad;

@Controller
@RequestMapping("/grad/")
public class GradController {

	@Autowired
	GradService serv;
	
	@GetMapping("unos")
	public String unos() {
		return "unosGrada";
	}
	
	@ModelAttribute("grad")
	public Grad noviGrad() {
		return new Grad();
	}
	
	@PostMapping("saveGrad")
	public String saveGrad(@ModelAttribute("grad") Grad grad, Model m) {
		String msg = "";
		try {
			int toSave = serv.saveGrad(grad);
			if (toSave == -1) {
				msg += "Grad " + grad.getIme() + " je već unet";
			} else {
				msg += "Sačuvano mesto: " + grad.getIme();
			}
		} catch(Exception e) {
			msg += "Greška prilikom čuvanja grada";
		}
		m.addAttribute("msgSaved", msg);
		return "unosGrada";
	}
}
