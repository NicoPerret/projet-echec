package fr.echec.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.echec.classe.probleme.Probleme;
import fr.echec.classe.probleme.ResultatProbleme;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.ProblemeNotFoundException;
import fr.echec.service.ProblemeService;
import fr.echec.service.ResultatProblemeService;

@RestController
@RequestMapping("/PageProbleme")
public class ProblemeRestController {
	
	@Autowired
	private ProblemeService srvProbleme;
	
	@Autowired
	private ResultatProblemeService srvResultatProbleme;
	
	@GetMapping("")
	public List<Probleme> listeProblemes() {
		return srvProbleme.findAll();

	}

	@GetMapping("")
	public Probleme findById(Integer id) throws IdNegatifException, ProblemeNotFoundException {
		return srvProbleme.findById(id);

	}
	@GetMapping("")
	public List<ResultatProbleme> listeProblemesResolus() {
		return srvResultatProbleme.findAll();
	
	
	
	
}}
