package fr.echec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccueilController {
	
	@GetMapping("/connexion")
	public String connexion(){
		return "connexion";
	}
}
