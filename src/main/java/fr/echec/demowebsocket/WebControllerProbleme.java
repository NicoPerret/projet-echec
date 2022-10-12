package fr.echec.demowebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import fr.echec.classe.historique.NotationCoup;
import fr.echec.classe.jeu.Fen;
import fr.echec.classe.jeu.Plateau;
import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.mouvements.analyse.CoupsPossibles;
import fr.echec.classe.mouvements.deplacement.Deplacement;
import fr.echec.classe.partie.ProblemePartie;
import fr.echec.classe.probleme.Probleme;
import fr.echec.finpartie.FinPartie;
import fr.echec.ia.CoupOrdi;
import fr.echec.service.ProblemeService;
import fr.echec.service.ResolutionProbleme;
import fr.echec.service.UtilisateursService;

@Controller
public class WebControllerProbleme {

	@Autowired
	ProblemePartie problemePartie;
	@Autowired
	protected NotationCoup nt;
	@Autowired
	protected Deplacement d;

	@Autowired
	protected CoupsPossibles coupPossible;
	
	@Autowired
	protected CoupOrdi coupOrdi;

	@Autowired
	protected Fen fen;

	@Autowired
	protected FinPartie finPartie;

	@Autowired
	UtilisateursService srvUti;
	
	@Autowired
	protected ProblemeService srvProbleme;
	
	@Autowired
	protected ResolutionProbleme srvResolutionProbleme;

	private int coordDepart64;
	private int coordArrivee64;

	@MessageMapping("/init-probleme")
	@SendTo("/topic/probleme")
	public Plateau initPlateau(int id) throws Exception {
		
		Utilisateur j1 = srvUti.findById(1);
		problemePartie.setJ1(j1);
		
		Probleme probleme = srvProbleme.findById(id);
		String[] tabCoups = probleme.getListeDeplacement().toUpperCase().split(" ");
		
		problemePartie.setProbleme(probleme);
		problemePartie.setPlateau(probleme.getFenDepart());
		problemePartie.setTabCoups(tabCoups);
		
		return problemePartie.getPlateau();
	}

	@MessageMapping("/jc-pb")
	@SendTo("/topic/probleme")
	public void jouerCoup(Coords coord) throws Exception {
		if (problemePartie.getNumCoup() < problemePartie.getTabCoups().length) {
			
			coordArrivee64 = NotationCoup.conversionLettreTo64(coord.getCoupArrivee());
			coordDepart64 = NotationCoup.conversionLettreTo64(coord.getCoupDepart());
			String coupJoueur = coordDepart64 + "" + coordArrivee64;
			boolean verif = srvResolutionProbleme.verifBonCoup(coupJoueur, problemePartie.getTabCoups()[problemePartie.getNumCoup()]);
			if (verif) {
				d.deplacement(problemePartie.getPlateau().getPieceCase(coordDepart64), coordArrivee64, problemePartie.getPlateau());
				problemePartie.setNumCoup(problemePartie.getNumCoup()+1);
				System.out.println(problemePartie.getPlateau());
			}
		
		} else {
			System.out.println("GG t'as gagné, t'es le best !");
		}
		
	}
	
	@MessageMapping("/jcordi-pb")
	@SendTo("/topic/probleme")
	public void jouerCoupOrdi(Coords coord) throws Exception {
		if (problemePartie.getNumCoup() < problemePartie.getTabCoups().length) {
			coupOrdi.coupOrdi(problemePartie.getTabCoups()[problemePartie.getNumCoup()],
							  problemePartie.getPlateau(),
							  problemePartie.getProbleme().isTraitAuBlanc());
			problemePartie.setNumCoup(problemePartie.getNumCoup()+1);
			
		} else {
			System.out.println("GG t'as gagné, t'es le best !");
		}
		
	}

}
