package fr.echec.application;

import java.time.LocalDateTime;
import java.util.List;

import fr.echec.classe.historique.HistoriquePartie;
import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.parametres.ParametresPartie;
import fr.echec.classe.partie.JcJ;
import fr.echec.classe.partie.Partie;
import fr.echec.exception.HistoriquePartieNotFoundException;
import fr.echec.exception.IdNegatifException;
import fr.echec.service.HistoriquePartieService;

public class Application {

	public static void main(String[] args) throws HistoriquePartieNotFoundException, IdNegatifException {

		// SETUP RUDIMENTAIRE MAIS FONCTIONNEL
		ParametresPartie param = new ParametresPartie();
		Partie p = new Partie(param);
		Utilisateur j1 = new Utilisateur();
		Utilisateur j2 = new Utilisateur();
		Partie p = new JcJ(param);
		// HistoriquePartieService srvHistPartie = new HistoriquePartieService();
		// UNE PARTIE
		while (true) {		
		j1.setElo(1000);
		j1.setId(1);
		j2.setElo(900);
		j2.setId(2);

		p.setJ1(j1);
		p.setJ2(j2);
 	 HistoriquePartieService srvHistPartie = new HistoriquePartieService();
//		// UNE PARTIE
		while (true) {
//
//			// Jouer
			p.jouer();

			// TP
			// p.teleportation();

			if (p.finPartie() == true) {
				break;
			}
		}
		
		p.getH().setDate(LocalDateTime.now());
		p.getH().setJ1(j1);
		p.getH().setJ2(j2);
		p.getH().setMessages("TEST");
//		p.getH().setParam(param);
	
		
		srvHistPartie.save(p.getH());
		
	}
}
