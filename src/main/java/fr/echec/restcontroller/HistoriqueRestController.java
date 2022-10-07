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
@RequestMapping("/api/historique")
public class HistoriqueRestController {
	@Autowired
	private UtilisateursService srvUtilisateurs;

	private Utilisateur utilisateur;

	@GetMapping("/{id}")
	public Utilisateur findById(@PathVariable("id")Integer id) throws IdNegatifException, UtilisateurNotFoundException {
		utilisateur = srvUtilisateurs.findById(id);
		return utilisateur;
	}

	@GetMapping("/nom")
	public String getNom(Integer id, String nom) throws IdNegatifException, UtilisateurNotFoundException {
		utilisateur = srvUtilisateurs.findById(id);
		nom = utilisateur.getNom();
		return nom;

	}

	@GetMapping("/elo")
	public Integer getElo(Integer elo) {
		elo = utilisateur.getElo();
		return elo;
	}

	@GetMapping("/historique")
	public Utilisateur getHistoriqueJoueur(Integer id) throws UtilisateurNotFoundException {
		return srvUtilisateurs.findByIdFetchHistorique(id);
	}

}
