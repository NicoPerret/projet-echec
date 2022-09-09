package fr.echec.application;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.parametres.ParametresPartie;
import fr.echec.classe.partie.JcIA;
import fr.echec.config.AppConfig;
import fr.echec.enumerateur.CouleursPiece;
import fr.echec.exception.HistoriquePartieNotFoundException;
import fr.echec.exception.IdNegatifException;
import fr.echec.service.UtilisateursService;

public class ApplicationJvIA {

	public static void main(String[] args) throws IdNegatifException, HistoriquePartieNotFoundException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		ParametresPartie param = new ParametresPartie();

		JcIA p = ctx.getBean(JcIA.class);
		p.setParam(param);

		UtilisateursService srvUti = ctx.getBean(UtilisateursService.class);

		Utilisateur j1 = srvUti.findById(1);
		

		p.setJ1(j1);
		p.setCouleurJoueurActif(CouleursPiece.BLANC);
		// UNE PARTIE
		while (true) {
			// Jouer
			p.jouerContreIa();

			// TP
			// p.teleportation();

			if (p.isPartieFinieIA() == true) {
				break;
			}
			System.out.println(p.getH().getListeCoups());
		}
		
		ctx.close();

	}

}
