package fr.echec.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import fr.echec.classe.probleme.Probleme;
import fr.echec.classe.probleme.ResultatProbleme;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.ProblemeNotFoundException;
import fr.echec.service.ProblemeService;
import fr.echec.service.ResultatProblemeService;
import fr.echec.classe.JsonViews;

@RestController
@RequestMapping("/api/probleme")
@CrossOrigin(origins = "*")
public class ProblemeRestController {

	@Autowired
	private ProblemeService srvProbleme;

	@Autowired
	private ResultatProblemeService srvResultatProbleme;
	
	@JsonView(JsonViews.Probleme.class)
	@GetMapping("")
	public List<Probleme> findAll() {
		return srvProbleme.findAll();

	}
	
	@JsonView(JsonViews.Probleme.class)
	@GetMapping("/{id}")
	public Probleme findById(@PathVariable("id")Integer id) throws IdNegatifException, ProblemeNotFoundException {
		return srvProbleme.findById(id);

	}

	@JsonView(JsonViews.ResultatProbleme.class)
	@GetMapping("/pbRes")
	public List<ResultatProbleme> listeProblemesResolus() {
		return srvResultatProbleme.findAll();

	}
	
	@JsonView(JsonViews.Probleme.class)
	@PostMapping("/choix")
	public Probleme choix(@RequestBody Probleme probleme, BindingResult br)
			throws IdNegatifException, ProblemeNotFoundException {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return srvProbleme.findById(probleme.getId());
	}

}
