package fr.echec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import fr.echec.classe.historique.RevoirPartie;
import fr.echec.classe.historique.Statistiques;
import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.parametres.ParametresPartie;
import fr.echec.classe.partie.JcIA;
import fr.echec.classe.partie.JcJ;
import fr.echec.enumerateur.CouleursPiece;

@Service
public class AppliConsoleService implements CommandLineRunner {

	@Autowired
	JcJ p;
	@Autowired
	JcIA pIA;
	@Autowired
	UtilisateursService srvUti;
	@Autowired
	Statistiques stats;
	@Autowired
	ResolutionProbleme prob;
	@Autowired
	RevoirPartie revoirPartie;
	
	@Override
	public void run(String... args) throws Exception {

		// 0 ==> JCJ
		// 1 ==> PROBLEMES
		// 2 ==> REVOIR PARTIE
		// 3 ==> JCIA FACILE
		// 4 ==> JCIA STOCKFISH

		int typePartie = 0;

		if (typePartie == 0) {

			ParametresPartie param = new ParametresPartie();

			p.setParam(param);

			Utilisateur j1 = srvUti.findById(3);
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

					p.savePartieEtHistorique();
					break;
				}
			}
			Utilisateur J1 = srvUti.findById(3);
			Utilisateur J2 = srvUti.findById(2);

			stats.calculStats(J1);
			stats.calculStats(J2);

		} else if (typePartie == 1) {
			// UN PROBLEME ('r6k/pp2r2p/4Rp1Q/3p4/8/1N1P2R1/PqP2bPP/7K','false','f2g3 e6e7
			// b2b1 b3c1 b1c1 h6c1'),

			prob.jouerPbId(15);

		} else if (typePartie == 2) {
			revoirPartie.revoirPartieApplication(4);
		}

		else if (typePartie == 3) {
			ParametresPartie param = new ParametresPartie();
			pIA.setParam(param);

			Utilisateur j1 = srvUti.findById(2);

			pIA.setJ1(j1);
			pIA.setCouleurJoueurActif(CouleursPiece.BLANC);

			while (true) {

				pIA.jouerContreIaFacile();

				if (pIA.isPartieFinieIA() == true) {
					break;
				}
				System.out.println(pIA.getH().getListeCoups());
			}
		} else if (typePartie == 4) {
			ParametresPartie param = new ParametresPartie();
			pIA.setParam(param);

			Utilisateur j1 = srvUti.findById(2);

			pIA.setJ1(j1);
			pIA.setCouleurJoueurActif(CouleursPiece.BLANC);

			while (true) {

				pIA.jouerContreIaStockfish();

				if (pIA.isPartieFinieIA() == true) {
					break;
				}
				System.out.println(pIA.getH().getListeCoups());
			}
		}

	}

}
