package fr.echec.demowebsocket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import fr.echec.classe.historique.NotationCoup;
import fr.echec.classe.jeu.Fen;
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

	private int coordDepart;
	private int coordArrivee;

	@MessageMapping("/init-IA_facile")
	@SendTo("/topic/JCIA-facile")
	public JcIA initPlateau() throws Exception {
		ParametresPartie param = new ParametresPartie();
		pIA.setParam(param);

		Utilisateur j1 = srvUti.findById(3);
		pIA.setCouleurJoueurActif(CouleursPiece.BLANC);

		return pIA;
	}

	@MessageMapping("/cp-IA-facile")
	@SendTo("/topic/JCIA-facile")
	public List<String> coupPossible(CoordDepart coord) throws Exception {
		coordDepart = NotationCoup.conversionLettreTo64(coord.getCoup());
		List<Integer> listeCoup64 = coupPossible.trouveDestinationsPossibles(pIA.getPlateau(),
				pIA.getPlateau().getPieceCase(coordDepart));
		List<String> listeCoup = new ArrayList<>();
		for (Integer i : listeCoup64) {
			listeCoup.add(NotationCoup.conversion64ToLettre(i));
		}
		return listeCoup;
	}

	@MessageMapping("/jc-IA-facile")
	@SendTo("/topic/JCIA-facile")
	public JcIA jouerCoup(CoordDepart coord) throws Exception {
		coordArrivee = NotationCoup.conversionLettreTo64(coord.getCoup());
		d.deplacement(pIA.getPlateau().getPieceCase(coordDepart), coordArrivee, pIA.getPlateau());
		pIA.jouerContreIaFacile();
		pIA.isPartieFinieIA(); // ?
		return pIA;
	}

	@MessageMapping("/jc-stockfish")
	@SendTo("/topic/JCIA-facile")
	public JcIA jouerCoupSF(CoordDepart coord) throws Exception {
		coordArrivee = NotationCoup.conversionLettreTo64(coord.getCoup());
		d.deplacement(pIA.getPlateau().getPieceCase(coordDepart), coordArrivee, pIA.getPlateau());
		pIA.jouerContreIaStockfish();
		pIA.isPartieFinieIA(); // ?
		return pIA;
	}

	// CHRONO ??

}
