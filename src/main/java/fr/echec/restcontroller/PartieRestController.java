package fr.echec.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.parametres.ParametresPartie;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.UtilisateurNotFoundException;
import fr.echec.service.ParametresPartieService;
import fr.echec.service.UtilisateursService;

@RestController
@RequestMapping("/api/jeu")
public class PartieRestController {

	@Autowired
	private UtilisateursService srvUtilisateurs;
	@Autowired
	private ParametresPartieService srvParametresPartie;

	@GetMapping("/parametre")
	public List<ParametresPartie> listeParametresPartie() {
		return srvParametresPartie.findAll();
	}
	
	@GetMapping("/utilisateur")
	public List<Utilisateur> listeUtilisateurs() {
		return srvUtilisateurs.findAll();
	}
	
	@GetMapping("/{id}")
	public Utilisateur findById(@PathVariable("id")Integer id) throws IdNegatifException, UtilisateurNotFoundException {
		return srvUtilisateurs.findById(id);
	}
	
}
