package fr.echec.application;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.echec.classe.historique.RevoirPartie;
import fr.echec.classe.historique.Statistiques;
import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.parametres.ParametresPartie;
import fr.echec.classe.partie.JcIA;
import fr.echec.classe.partie.JcJ;
import fr.echec.config.JpaConfig;
import fr.echec.enumerateur.CouleursPiece;
import fr.echec.exception.HistoriquePartieNotFoundException;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.ProblemeNotFoundException;
import fr.echec.exception.UtilisateurNotFoundException;
import fr.echec.service.ResolutionProbleme;
import fr.echec.service.UtilisateursService;

public class Application {

	public static void main(String[] args)
			throws IdNegatifException, ProblemeNotFoundException, UtilisateurNotFoundException, HistoriquePartieNotFoundException {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JpaConfig.class);


		// 0 ==> JCJ
		// 1 ==> PROBLEMES
		// 2 ==> REVOIR PARTIE
		// 3 ==> JCIA FACILE 
		// 4 ==> JCIA STOCKFISH

		int typePartie = 0;


		if (typePartie == 0) {

			ParametresPartie param = new ParametresPartie();

			JcJ p = ctx.getBean(JcJ.class);
			p.setParam(param);

			UtilisateursService srvUti = ctx.getBean(UtilisateursService.class);

			Utilisateur j1 = srvUti.findById(1);
			Utilisateur j2 = srvUti.findById(2);
			
			Statistiques statsJ1 = ctx.getBean(Statistiques.class);
			Statistiques statsJ2 = ctx.getBean(Statistiques.class);
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
					statsJ1.calculStats(p.getJ1());
					statsJ2.calculStats(p.getJ2());
					break;
				}
			}
			Utilisateur J1 = srvUti.findById(1);
			Utilisateur J2 = srvUti.findById(2);
			
			
			statsJ1.calculStats(J1);
			statsJ2.calculStats(J2);

		} else if (typePartie == 1) {
			// UN PROBLEME ('r6k/pp2r2p/4Rp1Q/3p4/8/1N1P2R1/PqP2bPP/7K','false','f2g3 e6e7
			// b2b1 b3c1 b1c1 h6c1'),
			ResolutionProbleme prob = ctx.getBean(ResolutionProbleme.class);

			prob.jouerPbId(15);

		} else if (typePartie == 2) {
			RevoirPartie revoirPartie = ctx.getBean(RevoirPartie.class);
			revoirPartie.revoirPartieApplication(4);
		}

		else if (typePartie == 3) {
			ParametresPartie param = new ParametresPartie();
			JcIA p = ctx.getBean(JcIA.class);
			p.setParam(param);

			UtilisateursService srvUti = ctx.getBean(UtilisateursService.class);

			Utilisateur j1 = srvUti.findById(1);

			p.setJ1(j1);
			p.setCouleurJoueurActif(CouleursPiece.BLANC);

			while (true) {

				p.jouerContreIaFacile();

				if (p.isPartieFinieIA() == true) {
					break;
				}
				System.out.println(p.getH().getListeCoups());
			}
		}
		else if (typePartie == 4) {
			ParametresPartie param = new ParametresPartie();
			JcIA p = ctx.getBean(JcIA.class);
			p.setParam(param);
			
			UtilisateursService srvUti = ctx.getBean(UtilisateursService.class);
			
			Utilisateur j1 = srvUti.findById(1);
			
			p.setJ1(j1);
			p.setCouleurJoueurActif(CouleursPiece.BLANC);
			
			while (true) {
				
				p.jouerContreIaStockfish();
				
				if (p.isPartieFinieIA() == true) {
					break;
				}
				System.out.println(p.getH().getListeCoups());
			}
		}
		
	
	
		ctx.close();
		System.exit(0);
	}
}
