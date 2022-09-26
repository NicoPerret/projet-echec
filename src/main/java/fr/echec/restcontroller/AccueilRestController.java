package fr.echec.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.echec.classe.historique.HistoriquePartie;
import fr.echec.classe.joueur.Utilisateur;
import fr.echec.exception.HistoriquePartieNotFoundException;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.UtilisateurNotFoundException;
import fr.echec.service.HistoriquePartieService;
import fr.echec.service.UtilisateursService;

@RestController
@RequestMapping("/PageAccueil")
public class AccueilRestController {
	
	private UtilisateursService srvUtilisateurs;
	private HistoriquePartieService srvHistoriquePartie;
	private Utilisateur utilisateur;

	
	@GetMapping("")
	public Utilisateur findById(Integer id) throws IdNegatifException, UtilisateurNotFoundException {
		utilisateur = srvUtilisateurs.findById(id);
		return utilisateur;
	
}
	@GetMapping("")
	public Integer getElo(Integer id, Integer elo) throws IdNegatifException, UtilisateurNotFoundException {
		utilisateur = srvUtilisateurs.findById(id);
		elo = utilisateur.getElo();
		return elo;
	}
	
	@GetMapping("")
	public Utilisateur getHistoriqueJoueur(Integer id) throws UtilisateurNotFoundException {
		return srvUtilisateurs.findByIdFetchHistorique(id);
	}
	
	



}
