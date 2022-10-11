package fr.echec.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import fr.echec.classe.JsonViews;
import fr.echec.classe.JsonViews.Common;
import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.probleme.Probleme;
import fr.echec.classe.probleme.ResultatProbleme;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.ProblemeNotFoundException;
import fr.echec.exception.ResultatProblemeNotFoundException;
import fr.echec.exception.UtilisateurNotFoundException;
import fr.echec.service.ResultatProblemeService;
import fr.echec.service.UtilisateursService;

@RestController
@RequestMapping("/api/resultat-probleme")
@CrossOrigin(origins = "*")
public class ResultatProblemeRestController {

	@Autowired
	private ResultatProblemeService srvResultatProbleme;
	
	@JsonView(JsonViews.ResultatProbleme.class)
	@GetMapping("")
	public List<ResultatProbleme> findAll() {
		return srvResultatProbleme.findAll();

	}
	
	@JsonView(JsonViews.ResultatProbleme.class)
	@GetMapping("/{id}")
	public ResultatProbleme findById(@PathVariable("id")Integer id) throws IdNegatifException, ResultatProblemeNotFoundException {
		return srvResultatProbleme.findById(id);	
	}
	
	@JsonView(JsonViews.ResultatProbleme.class)
	@GetMapping("/{idProbleme}&{idUtilisateur}")
	public boolean findByIdAndUser(@PathVariable("idProbleme")Integer idProbleme, @PathVariable("idUtilisateur")Integer idUtilisateur) throws IdNegatifException, ResultatProblemeNotFoundException {
		return srvResultatProbleme.findByIdAndUser(idProbleme, idUtilisateur);	
	}
	
	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(Common.class)
	public ResultatProbleme create(@Valid @RequestBody ResultatProbleme probleme,BindingResult br) throws ResultatProblemeNotFoundException, ProblemeNotFoundException {
		// pas d'id dans le fournisseur
		try {
			if(br.hasErrors()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		srvResultatProbleme.save(probleme);
		return probleme;
		
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		try {
			srvResultatProbleme.deleteById(id);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
}
