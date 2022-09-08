package fr.echec.application;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.parametres.ParametresPartie;
import fr.echec.classe.partie.JcJ;
import fr.echec.exception.HistoriquePartieNotFoundException;
import fr.echec.exception.IdNegatifException;
import fr.echec.service.HistoriquePartieService;
import fr.echec.service.UtilisateursService;

public class Application {

	public static void main(String[] args) throws HistoriquePartieNotFoundException, IdNegatifException {

		boolean typePartie = true;
		if (typePartie) {
			
			// SETUP RUDIMENTAIRE MAIS FONCTIONNEL
			ParametresPartie param = new ParametresPartie();

			JcJ p = new JcJ(param);

			UtilisateursService srvUti = new UtilisateursService();

			Utilisateur j1 = srvUti.findById(1);
			Utilisateur j2 = srvUti.findById(2);

			p.setJ1(j1);
			p.setJ2(j2);
			// UNE PARTIE
			while (true) {
			// Jouer
				p.jouer();

				// TP
				// p.teleportation();

				if (p.isPartieFinie() == true) {
					break;
				}
			}

			p.savePartieEtHistorique();
		} else {
			// UN PROBLEME

		}

//		String a = "00sHx,q3k1nr/1pp1nQpp/3p4/1P2p3/4P3/B1PP1b2/B5PP/5K2 b k - 0 17,e8d7 a2e6 d7d8 f7f8,1760,80,83,72,mate mateIn2 middlegame short,https://lichess.org/yyznGmXs/black#34,Italian_Game,Italian_Game_Classical_Variation\r\n";
//		String[] words = a.split(",");
//		
//		for (String s : words) {
//			System.out.println(s);
//		}

		String a = "A1A2";
		String b = a.substring(0, 2);
		String c = a.substring(2, 4);
		System.out.println(b);
		System.out.println(c);

	}
}
