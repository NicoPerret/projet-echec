package fr.echec.demowebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import fr.echec.classe.historique.NotationCoup;
import fr.echec.classe.historique.Statistiques;
import fr.echec.classe.jeu.Fen;
import fr.echec.classe.jeu.Plateau;
import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.mouvements.analyse.CoupsPossibles;
import fr.echec.classe.mouvements.deplacement.Deplacement;
import fr.echec.classe.parametres.ParametresPartie;
import fr.echec.classe.partie.JcJ;
import fr.echec.finpartie.FinPartie;
import fr.echec.service.UtilisateursService;

@Controller
public class WebController {

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
	@Autowired
	Statistiques stats;

	private int coordDepart64;
	private int coordArrivee64;

	@MessageMapping("/initialisation")
	@SendTo("/topic/hi")
	public Plateau initPlateau() throws Exception {
		ParametresPartie param = new ParametresPartie();
		p.setParam(param);

		Utilisateur j1 = srvUti.findById(3);
		Utilisateur j2 = srvUti.findById(2);
		p.setCompteurCoups(1);
		p.setCompteurTours(1);
		p.setJ1(j1);
		p.setJ2(j2);
		return p.getPlateau();
	}

	@MessageMapping("/jouer-coup")
	@SendTo("/topic/hi")
	public Plateau jouerCoup(Coords coord) throws Exception {
		System.out.println("Coucou " + coord);
		coordArrivee64 = NotationCoup.conversionLettreTo64(coord.getCoupArrivee());
		coordDepart64 = NotationCoup.conversionLettreTo64(coord.getCoupDepart());
		d.deplacement(p.getPlateau().getPieceCase(coordDepart64), coordArrivee64, p.getPlateau());
		p.finTour();
		if (p.isPartieFinie()) {

			p.savePartieEtHistorique();
			Utilisateur J1 = srvUti.findByIdFetchHistorique(3);
			Utilisateur J2 = srvUti.findByIdFetchHistorique(2);

			stats.calculStats(J1);
			stats.calculStats(J2);
			initPlateau();

		}
		return p.getPlateau();
	}

	// CHRONO ??

}
