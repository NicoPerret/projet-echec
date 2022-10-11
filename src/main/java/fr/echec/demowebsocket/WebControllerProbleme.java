package fr.echec.demowebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import fr.echec.classe.historique.NotationCoup;
import fr.echec.classe.jeu.Fen;
import fr.echec.classe.mouvements.analyse.CoupsPossibles;
import fr.echec.classe.mouvements.deplacement.Deplacement;
import fr.echec.classe.partie.JcJ;
import fr.echec.finpartie.FinPartie;
import fr.echec.service.UtilisateursService;

@Controller
public class WebControllerProbleme {

	@Autowired
	JcJ p;
	@Autowired
	protected NotationCoup nt;
	@Autowired
	protected Deplacement d;

	@Autowired
	protected CoupsPossibles coupPossible;

	@Autowired
	protected Fen fen;

	@Autowired
	protected FinPartie finPartie;

	@Autowired
	UtilisateursService srvUti;

	private int coordDepart;
	private int coordArrivee;

	@MessageMapping("/init-probleme")
	@SendTo("/topic/probleme")
	public void initPlateau() throws Exception {

	}

	@MessageMapping("/jc-pb")
	@SendTo("/topic/probleme")
	public void jouerCoup(CoordDepart coord) throws Exception {

	}

	// CHRONO ??

}
