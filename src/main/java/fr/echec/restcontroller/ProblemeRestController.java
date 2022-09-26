package fr.echec.restcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.probleme.Probleme;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.ProblemeNotFoundException;
import fr.echec.service.ProblemeService;

@RestController
@RequestMapping("/PageProbleme")
public class ProblemeRestController {

	private ProblemeService srvProbleme;
	
	@GetMapping("")
	public List<Probleme> findAll() {
		return srvProbleme.findAll();

	}

	@GetMapping("")
	public Probleme findById(Integer id) throws IdNegatifException, ProblemeNotFoundException {
		return srvProbleme.findById(id);

	}
	
	
}
