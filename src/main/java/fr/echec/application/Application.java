package fr.echec.application;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.parametres.ParametresPartie;
import fr.echec.classe.partie.Partie;
import fr.echec.service.HistoriquePartieService;

public class Application {

	public static void main(String[] args) {

		// SETUP RUDIMENTAIRE MAIS FONCTIONNEL
		ParametresPartie param = new ParametresPartie();
		Partie p = new Partie(param);
		Utilisateur j1 = new Utilisateur();
		Utilisateur j2 = new Utilisateur();

		j1.setId(1);
		j1.setElo(800);
		j2.setId(2);
		j2.setElo(800);

		p.setJ1(j1);
		p.setJ2(j2);
		// HistoriquePartieService srvHistPartie = new HistoriquePartieService();
		// UNE PARTIE
		while (true) {

			// Jouer
			p.jouer();

			// TP
			// p.teleportation();

			if (p.finPartie() == true) {
				break;
			}
		}
		System.out.println(p.getJ1().getElo());
		System.out.println(p.getJ2().getElo());
		

	}
}
