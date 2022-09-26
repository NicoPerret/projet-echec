package fr.echec.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.UtilisateurNotFoundException;
import fr.echec.service.HistoriquePartieService;
import fr.echec.service.UtilisateursService;

@RestController
@RequestMapping("/PageHistoriqueParties")
public class HistoriqueRestController {
	private UtilisateursService srvUtilisateurs;
	private HistoriquePartieService srvHistoriquePartie;
	private Utilisateur utilisateur;
	
	
	@GetMapping("")
	public Utilisateur findById(Integer id) throws IdNegatifException, UtilisateurNotFoundException {
		utilisateur = srvUtilisateurs.findById(id);
		return utilisateur;
	
}
	
	public String getNom(Integer id, String nom) throws IdNegatifException, UtilisateurNotFoundException {
		utilisateur = srvUtilisateurs.findById(id);
		nom= utilisateur.getNom();
		return nom;
		
	}
	
	@GetMapping("")
	public Integer getElo(Integer elo) {
		elo = utilisateur.getElo();
		return elo;
	}
	
	@GetMapping("")
	public Utilisateur getHistoriqueJoueur(Integer id) throws UtilisateurNotFoundException {
		return srvUtilisateurs.findByIdFetchHistorique(id);
	}
	
	
	
}
