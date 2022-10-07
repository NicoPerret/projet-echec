package fr.echec.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.parametres.PenalitePiece;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.UtilisateurNotFoundException;
import fr.echec.service.PenalitePieceService;
import fr.echec.service.UtilisateursService;

@RestController
@RequestMapping("/api/accueil")
public class AccueilRestController {

	@Autowired
	private UtilisateursService srvUtilisateurs;
	@Autowired
	private PenalitePieceService srvPenalitePiece;

	private Utilisateur utilisateur;
	
	@GetMapping("/connexion2")
	public String connexion(){
		return "connexion";
	}

	@GetMapping("/id")
	public Utilisateur findById(Integer id) throws IdNegatifException, UtilisateurNotFoundException {
		utilisateur = srvUtilisateurs.findById(id);
		return utilisateur;
	}

	@GetMapping("/elo")
	public Integer getElo(Integer id, Integer elo) throws IdNegatifException, UtilisateurNotFoundException {
		utilisateur = srvUtilisateurs.findById(id);
		elo = utilisateur.getElo();
		return elo;
	}

	@GetMapping("/historique")
	public Utilisateur getHistoriqueJoueur(Integer id) throws UtilisateurNotFoundException {
		return srvUtilisateurs.findByIdFetchHistorique(id);
	}

	@GetMapping("/all")
	public List<PenalitePiece> findAll() {
		return srvPenalitePiece.findAll();
	}

	@PostMapping("/JeuJoueur") // bouton JcJ
	public void jouerJoueur() {
		return;
	}

	@PostMapping("/JeuIA") // bouton JcIA
	public void jouerIA() {
		return;
	}

	@PostMapping("/JeuPb") // bouton Probleme
	public void jouerProbleme() {
		return;
	}

}
