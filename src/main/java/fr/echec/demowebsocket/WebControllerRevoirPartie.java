package fr.echec.demowebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import fr.echec.classe.historique.NotationCoup;
import fr.echec.classe.historique.RevoirPartie;
import fr.echec.classe.jeu.Fen;
import fr.echec.classe.jeu.Plateau;
import fr.echec.classe.mouvements.analyse.CoupsPossibles;
import fr.echec.classe.mouvements.deplacement.Deplacement;
import fr.echec.finpartie.FinPartie;
import fr.echec.ia.CoupOrdi;
import fr.echec.service.UtilisateursService;

public class WebControllerRevoirPartie {
	
	@Autowired
	RevoirPartie revoirPartie;
	@Autowired
	protected NotationCoup nt;
	@Autowired
	protected Deplacement d;

	@Autowired
	protected CoupsPossibles coupPossible;
	
	@Autowired
	protected CoupOrdi coupOrdi;

	@Autowired
	protected Fen fen;

	@Autowired
	protected FinPartie finPartie;

	@Autowired
	UtilisateursService srvUti;
	
	boolean stop;

	
	@MessageMapping("/init-rp")
	@SendTo("/topic/revoirPartie")
	public Plateau initPlateau(int id) throws Exception {
		revoirPartie.trouveHistoriquePartie(id);
		revoirPartie.donneInfosPartie();
		revoirPartie.generationPartie();
		
		return revoirPartie.getPlateau();
	}
	
	@MessageMapping("/suivant-rp")
	@SendTo("/topic/revoirPartie")
	public void defilementAvant() throws Exception {
		stop = revoirPartie.defilementAvant();
		if (stop) {
			revoirPartie.finPartie();
		}
	}
	
	@MessageMapping("/precedent-rp")
	@SendTo("/topic/revoirPartie")
	public void defilementArriere() throws Exception {
		revoirPartie.defilementArriere();
	}

}
