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

		// SETUP RUDIMENTAIRE MAIS FONCTIONNEL
		ParametresPartie param = new ParametresPartie();

		JcJ p = new JcJ(param);

	
		HistoriquePartieService srvHistPartie = new HistoriquePartieService();
		UtilisateursService srvUti = new UtilisateursService();
		
		Utilisateur j1 = srvUti.findById(1);
		Utilisateur j2 = srvUti.findById(2);
		
		p.setJ1(j1);
		p.setJ2(j2);
		
//		// UNE PARTIE
			while (true) {
//
//			// Jouer
				p.jouer();

				// TP
				// p.teleportation();


			if (p.isPartieFinie() == true) {
				break;
			}
		}

		p.savePartieEtHistorique();
		

	}
}
