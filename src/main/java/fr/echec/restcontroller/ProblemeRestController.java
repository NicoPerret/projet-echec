package fr.echec.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

	@GetMapping("/pb")
	public List<Probleme> listeProblemes() {
		return srvProbleme.findAll();

	}

	@GetMapping("/id")
	public Probleme findById(Integer id) throws IdNegatifException, ProblemeNotFoundException {
		return srvProbleme.findById(id);

	}

	@GetMapping("/pbRes")
	public List<ResultatProbleme> listeProblemesResolus() {
		return srvResultatProbleme.findAll();

	}

	@PostMapping("/choix")
	public Probleme choix(@RequestBody Probleme probleme, BindingResult br)
			throws IdNegatifException, ProblemeNotFoundException {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return srvProbleme.findById(probleme.getId());
	}

}
