package fr.echec.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.parametres.ParametresPartie;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.UtilisateurNotFoundException;
import fr.echec.service.HistoriquePartieService;
import fr.echec.service.ParametresPartieService;
import fr.echec.service.UtilisateursService;

@RestController
@RequestMapping("/PageJeu")
public class PartieRestController {

	@Autowired
	private UtilisateursService srvUtilisateurs;
	@Autowired
	private HistoriquePartieService srvHistoriquePartie;
	@Autowired
	private ParametresPartieService srvParametresPartie;
	@Autowired
	private Utilisateur utilisateur;
	
	
	@GetMapping("")
	public List<ParametresPartie> listeParametresPartie() {
		return srvParametresPartie.findAll();
	}
	@GetMapping("")
	public List<Utilisateur> listeUtilisateurs() {
		return srvUtilisateurs.findAll();
	}
	@GetMapping("")
	public Utilisateur findById(Integer id) throws IdNegatifException, UtilisateurNotFoundException {
		return srvUtilisateurs.findById(id);
	}
	
}
