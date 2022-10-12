package fr.echec.classe.historique;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.echec.classe.jeu.Fen;
import fr.echec.classe.jeu.Plateau;
import fr.echec.classe.mouvements.deplacement.Deplacement;
import fr.echec.classe.parametres.ParametresPartie;
import fr.echec.service.HistoriquePartieService;

@Service
public class RevoirPartie {
	
	private String[] listeCoups;
	private List<String> listeFen = new ArrayList<>();
	private int nbCoups;
	private int coupActuel = 0;
	
	@Autowired
	private HistoriquePartieService srvHistPartie;
	@Autowired
	protected Fen fen;
	@Autowired
	protected Plateau plateau;
	@Autowired
	protected Deplacement dplt;
	
	ParametresPartie param = new ParametresPartie();
	
	private HistoriquePartie histPartie;
	

	public int getNbCoups() {
		return nbCoups;
	}

	public void setNbCoups(int nbCoups) {
		this.nbCoups = nbCoups;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
	
	
	public void trouveHistoriquePartie(int id) {
		try {
			System.out.println(id);
			this.histPartie = srvHistPartie.findById(id);
		}
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public void donneInfosPartie() {
		// Affiche les infos associées à la partie
		System.out.println(" Historique de la partie numéro " + this.histPartie.getId());
		System.out.println("==================================");
		System.out.println("------------ joueurs -------------");
		System.out.println("Joueur 1 : " + this.histPartie.getJ1().getPseudo()
							+ " (elo : " + this.histPartie.getJ1().getElo() + ")"
							+ " joue les blancs");
		System.out.println("Joueur 2 : " + this.histPartie.getJ2().getPseudo()
							+ " (elo : " + this.histPartie.getJ2().getElo() + ")"
							+ " joue les noirs");
		System.out.println("------------- date ---------------");
		System.out.println(this.histPartie.getDate());
		System.out.println("-------- Paramètres partie -------");
		System.out.println(this.histPartie.getParam()); // Faut voir quoi mettre...
		System.out.println("==================================");
		
	}
	
	
	public void generationPartie() {
		
		// Récupérer le fen de départ dans param
		String fen_depart = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
		this.plateau = fen.creationPlateau(fen_depart);
		
		this.listeCoups = this.histPartie.getListeCoups().toUpperCase().split(" ");
		this.nbCoups = this.listeCoups.length - 1;
		System.out.println(listeCoups.length);
	}
	
	public boolean defilementAvant() {
		
		if (this.coupActuel >= this.nbCoups) {
			System.out.println("C'est le dernier coup");
			return true;
		}
		
		if (this.coupActuel >= this.listeFen.size()) {
			this.listeFen.add(this.fen.creationFen(plateau));
		}
		
		String mouvAFaire = this.listeCoups[coupActuel];
		
		this.dplt.deplacement(this.plateau.getPieceCase(NotationCoup.conversionLettreTo64(mouvAFaire.substring(0, 2))),
				NotationCoup.conversionLettreTo64(mouvAFaire.substring(2, 4)), this.plateau);
		
		
		this.coupActuel +=1;
		return false;
	}
	
	public void defilementArriere() {

		if (this.coupActuel <= 0) {
			System.out.println("C'est le premier coup");
			return;
		}
		
		this.coupActuel -= 1;
		
		this.plateau = fen.creationPlateau(this.listeFen.get(coupActuel));
	}
	
	public int interactionUtil() {
		
		System.out.println("1 : coup suivant / 2 : coup précédent / 0 : stop");
		Scanner sc = new Scanner(System.in);
		
		int saisie = sc.nextInt();
		
		//sc.close();
		
		return saisie;
	}
	
	public void affichagePlateauEnCours(int coup) {
		
		System.out.println("==================================");
		System.out.println(this.plateau);
		System.out.println("Coup " + this.coupActuel + " / " + this.nbCoups);
		
	}
	
	public void finPartie() {
		if (this.histPartie.getJ1().getId() == this.histPartie.getVainqueurId()) {
			System.out.println("Le vainqueur est "+ this.histPartie.getJ1().getPseudo());
		}else {
			System.out.println("Le vainqueur est "+ this.histPartie.getJ2().getPseudo());
		}
	}
	
	public void revoirPartieApplication(int id) {
		// C'est ce qu'on appelle depuis application
		
		boolean stop = false;
		
		trouveHistoriquePartie(id);
		
		donneInfosPartie();
		
		generationPartie();
		
		while (!stop) {
			
			affichagePlateauEnCours(this.coupActuel);
			int saisie = interactionUtil();
			
			if (saisie == 0) {
				stop = true;
			} else if (saisie == 1) {
				stop = defilementAvant();
			} else if (saisie == 2) {
				defilementArriere();
			} else {
				System.out.println("Mauvaise saisie !");
			}
			
		}
		
		finPartie();
		
	}


}
