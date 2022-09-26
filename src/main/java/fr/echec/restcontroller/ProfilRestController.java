package fr.echec.restcontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.UtilisateurNotFoundException;
import fr.echec.service.UtilisateursService;
import fr.formation.model.Produit;

@RestController
@RequestMapping("/PageProfil")
public class ProfilRestController {

	@Autowired
	private UtilisateursService srvUtilisateurs;
	
	@GetMapping("")
	public Utilisateur findById(Integer id) throws IdNegatifException, UtilisateurNotFoundException {
	return srvUtilisateurs.findById(id);
	
}
	@PostMapping("")
	public Utilisateur sauvegarder(@Valid @RequestBody Utilisateur utilisateur, BindingResult br) throws UtilisateurNotFoundException, IdNegatifException {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		srvUtilisateurs.save(utilisateur);
		return srvUtilisateurs.findById(utilisateur.getId());
	}




}
