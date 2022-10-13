package fr.echec.demowebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import fr.echec.classe.historique.NotationCoup;
import fr.echec.classe.jeu.Fen;
import fr.echec.classe.jeu.Plateau;
import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.mouvements.analyse.CoupsPossibles;
import fr.echec.classe.mouvements.deplacement.Deplacement;
import fr.echec.classe.parametres.ParametresPartie;
import fr.echec.classe.partie.JcIA;
import fr.echec.enumerateur.CouleursPiece;
import fr.echec.finpartie.FinPartie;
import fr.echec.service.UtilisateursService;

@Controller
public class WebControllerJcIA {

	@Autowired
	JcIA pIA;
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

	private int coordDepart64;
	private int coordArrivee64;

	@MessageMapping("/init-IA_facile")
	@SendTo("/topic/JCIA-facile")
	public Plateau initPlateau() throws Exception {
		ParametresPartie param = new ParametresPartie();
		pIA.setParam(param);

		Utilisateur j1 = srvUti.findById(3);
		pIA.setCouleurJoueurActif(CouleursPiece.BLANC);
		pIA.setCompteurCoups(1);
		pIA.setCompteurTours(1);

		return pIA.getPlateau();
	}

	@MessageMapping("/jc-IA-facile")
	@SendTo("/topic/JCIA-facile")
	public Plateau jouerCoup(Coords coord) throws Exception {

		coordArrivee64 = NotationCoup.conversionLettreTo64(coord.getCoupArrivee());
		coordDepart64 = NotationCoup.conversionLettreTo64(coord.getCoupDepart());
		d.deplacement(pIA.getPlateau().getPieceCase(coordDepart64), coordArrivee64, pIA.getPlateau());
		pIA.setCompteurTours(pIA.getCompteurTours() + 1);
		pIA.setCompteurCoups(pIA.getCompteurCoups() + 1);
		if (pIA.isPartieFinieIA()) {
			initPlateau();

		}

		pIA.jouerContreIaFacile();
		if (pIA.isPartieFinieIA()) {
			System.out.println("Test");
			initPlateau();

		}
		return pIA.getPlateau();
	}

	@MessageMapping("/jc-stockfish")
	@SendTo("/topic/JCIA-facile")
	public Plateau jouerCoupSF(Coords coord) throws Exception {
		coordArrivee64 = NotationCoup.conversionLettreTo64(coord.getCoupArrivee());
		coordDepart64 = NotationCoup.conversionLettreTo64(coord.getCoupDepart());
		d.deplacement(pIA.getPlateau().getPieceCase(coordDepart64), coordArrivee64, pIA.getPlateau());
		pIA.setCompteurTours(pIA.getCompteurTours() + 1);
		pIA.setCompteurCoups(pIA.getCompteurCoups() + 1);
		if (pIA.isPartieFinieIA()) {
			initPlateau();

		}

		pIA.jouerContreIaStockfish();
		if (pIA.isPartieFinieIA()) {
			System.out.println("Test");
			initPlateau();

		}
		return pIA.getPlateau();

	}

	// CHRONO ??

}
