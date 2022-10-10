package fr.echec.demowebsocket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import fr.echec.classe.historique.NotationCoup;
import fr.echec.classe.jeu.Fen;
import fr.echec.classe.jeu.Piece;
import fr.echec.classe.mouvements.analyse.CoupsPossibles;
import fr.echec.classe.mouvements.analyse.GestionEchec;
import fr.echec.classe.mouvements.deplacement.Deplacement;
import fr.echec.classe.partie.JcJ;
import fr.echec.finpartie.FinPartie;

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
	private GestionEchec gestionEchec;

	private int coordDepart;
	private int coordArrivee;

	@MessageMapping("/initialisation")
	@SendTo("/topic/hi")
	public List<Piece> initPlateau() throws Exception {
		return p.getPlateau().getPieces();
	}

	@MessageMapping("/coup-possible")
	@SendTo("/topic/hi")
	public List<String> coupPossible(String coord) throws Exception {
		coordDepart = NotationCoup.conversionLettreTo64(coord);
		List<Integer> listeCoup64 = coupPossible.trouveDestinationsPossibles(p.getPlateau(),
				p.getPlateau().getPieceCase(coordDepart));
		List<String> listeCoup = new ArrayList<>();
		for (Integer i : listeCoup64) {
			listeCoup.add(NotationCoup.conversion64ToLettre(i));
		}
		return listeCoup;
	}

	@MessageMapping("/jouer-coup")
	@SendTo("/topic/hi")
	public void jouerCoup(String coord) throws Exception {
		coordArrivee = NotationCoup.conversionLettreTo64(coord);
		d.deplacement(p.getPlateau().getPieceCase(coordDepart), coordArrivee, p.getPlateau());
		p.isPartieFinie(); // ?
	}

	// CHRONO ??

}
