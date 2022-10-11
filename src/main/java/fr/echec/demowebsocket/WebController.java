package fr.echec.demowebsocket;

import java.util.ArrayList;
import java.util.List;

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

	private int coordDepart;
	private int coordArrivee;

	@MessageMapping("/initialisation")
	@SendTo("/topic/hi")
	public Plateau initPlateau() throws Exception {
		ParametresPartie param = new ParametresPartie();
		p.setParam(param);

		Utilisateur j1 = srvUti.findById(3);
		Utilisateur j2 = srvUti.findById(2);

		p.setJ1(j1);
		p.setJ2(j2);
		return p.getPlateau();
	}

	@MessageMapping("/coup-possible")
	@SendTo("/topic/test")
	public List<String> coupPossible(CoordDepart coord) throws Exception {
		coordDepart = NotationCoup.conversionLettreTo64(coord.getCoup());
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
	public void jouerCoup(CoordDepart coord) throws Exception {
		coordArrivee = NotationCoup.conversionLettreTo64(coord.getCoup());
		d.deplacement(p.getPlateau().getPieceCase(coordDepart), coordArrivee, p.getPlateau());
		p.isPartieFinie(); // ?
	}

	// CHRONO ??

}
