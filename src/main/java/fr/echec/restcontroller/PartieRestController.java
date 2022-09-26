package fr.echec.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.service.HistoriquePartieService;
import fr.echec.service.UtilisateursService;

@RestController
@RequestMapping("/PageJeu")
public class PartieRestController {

	
	private UtilisateursService srvUtilisateurs;
	private HistoriquePartieService srvHistoriquePartie;
	private Utilisateur utilisateur;
}
