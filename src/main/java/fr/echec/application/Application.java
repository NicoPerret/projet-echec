package fr.echec.application;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.parametres.ParametresPartie;
import fr.echec.classe.partie.JcJ;
import fr.echec.classe.probleme.Probleme;
import fr.echec.config.AppConfig;
import fr.echec.exception.HistoriquePartieNotFoundException;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.ProblemeNotFoundException;
import fr.echec.service.ResolutionProbleme;
import fr.echec.service.UtilisateursService;

public class Application {

	public static void main(String[] args) throws HistoriquePartieNotFoundException, IdNegatifException, ProblemeNotFoundException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		boolean typePartie = false;
		if (typePartie) {

			ParametresPartie param = new ParametresPartie();

			JcJ p = ctx.getBean(JcJ.class);
			p.setParam(param);

			UtilisateursService srvUti = ctx.getBean(UtilisateursService.class);

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
				System.out.println(p.getH().getListeCoups());
			}

			p.savePartieEtHistorique();
		} else {
			// UN PROBLEME  ('r6k/pp2r2p/4Rp1Q/3p4/8/1N1P2R1/PqP2bPP/7K','false','f2g3 e6e7 b2b1 b3c1 b1c1 h6c1'),
			ResolutionProbleme prob = ctx.getBean(ResolutionProbleme.class);
			prob.jouerPb(1);
		}
		
		

		ctx.close();

	}
}
