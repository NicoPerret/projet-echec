package fr.echec.application;

import fr.echec.classe.parametres.ParametresPartie;
import fr.echec.classe.partie.JcJ;
import fr.echec.classe.partie.Partie;
import fr.echec.service.HistoriquePartieService;

public class Application {

	public static void main(String[] args) {

		// SETUP RUDIMENTAIRE MAIS FONCTIONNEL
		ParametresPartie param = new ParametresPartie();
		Partie p = new JcJ(param);
		// HistoriquePartieService srvHistPartie = new HistoriquePartieService();
		// UNE PARTIE
		while (true) {

			// Jouer
//			p.jouer();

			// TP
			p.teleportation();
			
			System.out.println(p.getH().getListeCoups());
			
			if (p.getChronoJ1().isDefaiteTemps() || p.getChronoj2().isDefaiteTemps()) {
				break;
			}
		}
	}
}
