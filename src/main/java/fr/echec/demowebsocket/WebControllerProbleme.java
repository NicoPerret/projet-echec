package fr.echec.demowebsocket;

import java.util.concurrent.TimeUnit;

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
	public Plateau initPlateau(IdProbleme idProbleme) throws Exception {
		
		int id = idProbleme.getIdProbleme();
		Utilisateur j1 = srvUti.findById(2);
		problemePartie.setJ1(j1);
		
		Probleme probleme = srvProbleme.findById(id);
		String[] tabCoups = probleme.getListeDeplacement().toUpperCase().split(" ");
		
		problemePartie.setProbleme(probleme);
		problemePartie.setPlateau(probleme.getFenDepart());
		problemePartie.setTabCoups(tabCoups);
		
		System.out.println("LES COUPS A JOUER SONT : ");
		for (String coup : problemePartie.getTabCoups()) {
			System.out.println(coup);;
		}
		
		TimeUnit.SECONDS.sleep(1);
		
		coupOrdi.coupOrdi(problemePartie.getTabCoups()[problemePartie.getNumCoup()],
						  problemePartie.getPlateau(),
						  problemePartie.getProbleme().isTraitAuBlanc());
		problemePartie.setNumCoup(problemePartie.getNumCoup()+1);
		
		
		return problemePartie.getPlateau();
	}

	@MessageMapping("/jc-pb")
	@SendTo("/topic/probleme")
	public Plateau jouerCoup(Coords coord) throws Exception {
		System.out.println("BONJOUR");
		if (problemePartie.getNumCoup() < problemePartie.getTabCoups().length) {
			System.out.println("JE SUIS ICI");
			
			coordArrivee64 = NotationCoup.conversionLettreTo64(coord.getCoupArrivee());
			coordDepart64 = NotationCoup.conversionLettreTo64(coord.getCoupDepart());
			String coupJoueur = coord.getCoupDepart() + coord.getCoupArrivee();
			System.out.println("COUP JOUEUR : " + coupJoueur);
			System.out.println("COUP A JOUER : " + problemePartie.getTabCoups()[problemePartie.getNumCoup()]);
			boolean verif = srvResolutionProbleme.verifBonCoupWeb(coupJoueur, problemePartie.getTabCoups()[problemePartie.getNumCoup()]);
			if (!verif) {
				System.out.println("JE FAIS LE COUP !");
				d.deplacement(problemePartie.getPlateau().getPieceCase(coordDepart64), coordArrivee64, problemePartie.getPlateau());
				problemePartie.setNumCoup(problemePartie.getNumCoup()+1);
				System.out.println(problemePartie.getPlateau());
				
				TimeUnit.SECONDS.sleep(2);
				
				if (problemePartie.getNumCoup() < problemePartie.getTabCoups().length) {
					coupOrdi.coupOrdi(problemePartie.getTabCoups()[problemePartie.getNumCoup()],
									  problemePartie.getPlateau(),
									  problemePartie.getProbleme().isTraitAuBlanc());
					problemePartie.setNumCoup(problemePartie.getNumCoup()+1);
				}
			}
		
		} else {
			IdProbleme id = new IdProbleme();
			id.setIdProbleme(problemePartie.getProbleme().getId()+1);
			initPlateau(id);
			System.out.println("GG t'as gagné, t'es le best !");
		}
		
		return problemePartie.getPlateau();
		
	}

}

