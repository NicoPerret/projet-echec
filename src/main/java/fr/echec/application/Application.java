package fr.echec.application;

import fr.echec.classe.Partie;
import fr.echec.classe.modeJeux.ParametresPartie;

public class Application {

	public static void main(String[] args) {

		// SETUP RUDIMENTAIRE MAIS FONCTIONNEL
		ParametresPartie param = new ParametresPartie();
		Partie p = new Partie(param);
		
		// UNE PARTIE
		while (true) {

			System.out.println(p.getPlateau());
		
//			p.selectionPiece();
//			p.jouerPiece();
			p.selectionPieceTP();
			p.teleportPiece();
			p.finTour();
			System.out.println( p.getH().getListeCoups());
			if (p.getChronoJ1().isDefaiteTemps() || p.getChronoj2().isDefaiteTemps()) {
				break;
			}
		}
	}
}
