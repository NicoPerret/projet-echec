package fr.echec.restcontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
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

import fr.echec.classe.JsonViews.Common;
import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.probleme.ResultatProbleme;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.ProblemeNotFoundException;
import fr.echec.exception.ResultatProblemeNotFoundException;
import fr.echec.exception.UtilisateurNotFoundException;
import fr.echec.service.ResultatProblemeService;
import fr.echec.service.UtilisateursService;

@RestController
@RequestMapping("/api/probleme")
public class ResultatProblemeRestController {

	@Autowired
	private ResultatProblemeService srvResultatProbleme;
	
	@GetMapping("/id")
	public ResultatProbleme findById( Integer id) throws IdNegatifException, ResultatProblemeNotFoundException {
		return srvResultatProbleme.findById(id);
		
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
