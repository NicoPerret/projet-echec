package fr.echec.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.parametres.PenalitePiece;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.UtilisateurNotFoundException;
import fr.echec.service.PenalitePieceService;
import fr.echec.service.UtilisateursService;

@RestController
@RequestMapping("/PageAccueil")
public class AccueilRestController {
	
	@Autowired
	private UtilisateursService srvUtilisateurs;
	@Autowired
	private PenalitePieceService srvPenalitePiece;
	@Autowired
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
	
	@GetMapping("")
	public List<PenalitePiece> findAll() {
		return srvPenalitePiece.findAll();
	}
	

}
