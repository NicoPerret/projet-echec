package fr.echec.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.UtilisateurNotFoundException;
import fr.echec.service.UtilisateursService;

@RestController
@RequestMapping("/api/statistiques")
public class StatistiquesRestController {

	@Autowired
	private UtilisateursService srvUtilisateurs;
	
	@GetMapping("/{id}")
	public Utilisateur findById(@PathVariable("id")Integer id) throws IdNegatifException, UtilisateurNotFoundException {
	return srvUtilisateurs.findById(id);
	
	
}
}
