package fr.echec.classe.probleme;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import fr.echec.classe.historique.NotationCoup;
import fr.echec.classe.jeu.Piece;
import fr.echec.classe.jeu.Plateau;
import fr.echec.classe.mouvements.analyse.CoupsPossibles;
import fr.echec.classe.mouvements.deplacement.Deplacement;
import fr.echec.classe.mouvements.deplacement.Promotion;
import fr.echec.classe.mouvements.deplacement.Roque;
import fr.echec.enumerateur.CouleursPiece;

@Entity
@Table(name = "probleme")
public class Probleme {

//	private ProblemeService srvProbleme = new ProblemeService();

	// VARIABLES from BDD
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prob_id")
	private int id;

	@Column(name = "prob_fen_depart", length = 100, nullable = false)
	private String fenDepart;

	@Column(name = "prob_liste_deplacement", length = 100)
	private String listeDeplacements;

	@Column(name = "prob_traitaublanc", nullable = false)
	private boolean traitAuBlanc;

	@Column(name = "prob_difficulte", nullable = false)
	private int difficulte;

	@Transient
	protected Plateau p = new Plateau();
	@Transient
	protected List<Integer> listeCoup = new ArrayList<>();
	@Transient
	protected CoupsPossibles coupPossible = new CoupsPossibles();


	@Transient
	
	protected Deplacement d = new Deplacement();
	
	@Transient
	
	protected Promotion promo = new Promotion();

// GETTERS ET SETTERS 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFenDepart() {
		return fenDepart;
	}

	public void setFenDepart(String fenDepart) {
		this.fenDepart = fenDepart;
	}

	public String getListeDeplacement() {
		return listeDeplacements;
	}

	public void setListeDeplacement(String listeDeplacements) {
		this.listeDeplacements = listeDeplacements;
	}

	public boolean isTraitAuBlanc() {
		return traitAuBlanc;
	}

	public void setTraitAuBlanc(boolean traitAuBlanc) {
		this.traitAuBlanc = traitAuBlanc;
	}

	public int getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(int difficulte) {
		this.difficulte = difficulte;
	}

	// CONSTRUCTEUR
	public Probleme(Plateau plateau) {
		this.p = plateau;
	}
	public Probleme() {
		
	}

	// METHODES
	public void jouerPb() { // utlisateur idPb
		int coordArrivee;
		int coordDepart;
		boolean verif = true;
		String[] tabCoups = listeDeplacements.split(" ");
		for(int i = 0; i < tabCoups.length; i++) {
			if (i%2 ==0) { //tour de l'ordi
				coupOrdi(tabCoups[i]);
			}
			else { //tour du joueur
				
				while(verif) {
					do{
						coordDepart = selectionPieceProbleme();
						coordArrivee = jouerPieceProbleme(coordDepart);
					}while (coordArrivee == -1);
					String coupJoueur = NotationCoup.conversion64ToLettre(coordDepart)
							+ NotationCoup.conversion64ToLettre(coordArrivee);
					verif = verifBonCoup(coupJoueur, tabCoups[i]);
				}
			}
		}
	}

	// Coup joué par l'ordi
	
	@Autowired
	Roque roque;
	public void coupOrdi(String coupAJouer) {
		if(coupAJouer.equals("O-O")) {
			if(traitAuBlanc) {
				roque.jouerPetitRoque(p.getPieceCase(3), p);
			}else {
				roque.jouerPetitRoque(p.getPieceCase(59), p);
			}
		}
		else if (coupAJouer.equals("O-O-O")) {
			if(traitAuBlanc) {
				roque.jouerGrandRoque(p.getPieceCase(3), p);
			}else {
				roque.jouerGrandRoque(p.getPieceCase(59), p);
			}
		}else {
			int coordDepart = NotationCoup.conversionLettreTo64(coupAJouer.substring(0, 2));
			int coordArrivee = NotationCoup.conversionLettreTo64(coupAJouer.substring(2, 4));
	
			this.d.deplacement(p.getPieceCase(coordDepart), coordArrivee, p);
			promo.promotionAutomatique(p.getPieceCase(coordArrivee), p, coupAJouer.charAt(5));
		}
	}

	// Verif coup joué est le bon

	public boolean verifBonCoup(String coupJoueur, String coupAJouer) {
		if (coupJoueur.equals(coupAJouer)) {
			this.d.deplacement(p.getPieceCase(NotationCoup.conversionLettreTo64(coupJoueur.substring(0, 2))),
					NotationCoup.conversionLettreTo64(coupJoueur.substring(2, 4)), p);
			return true;
		} else {
			System.out.println("Ce n'est pas le bon coup! Reessayer");
			return false;
		}
	}

	// Sélection de la piece renvoi coord depart
	public int selectionPieceProbleme() {
		Scanner sc = new Scanner(System.in);
		int coordDepart;
		CouleursPiece couleurJoueur = CouleursPiece.BLANC;
		if (probleme.isTraitAuBlanc(){
			couleurJoueur = CouleursPiece.NOIR;
		}
		
		while (true) {
			System.out.println("Saisir une piece : ");

			String saisie = sc.nextLine();

			 coordDepart = NotationCoup.conversionLettreTo64(saisie);

			if (	p.getPieceCase(coordDepart) != null
					&& p.getPieceCase(coordDepart).getCouleur() == couleurJoueur) {
				listeCoup = coupPossible.trouveDestinationsPossibles(p, p.getPieceCase(coordDepart));

				if (listeCoup.isEmpty() == false) {
					System.out.println("Coup(s) possible(s) : ");
					for (Integer i : listeCoup) {
						System.out.println(NotationCoup.conversion64ToLettre(i));
					}
					break;
				} else {
					System.out.println("Aucun coup possible pour cette piece");
				}
			} else {
				System.out.println("Mauvaise saisie : Piece non trouvée ou mauvaise couleur ");
			}

		}
		return coordDepart ;

	}

	// Sélection de la case darrivée renvoie coordArrivee
	public int jouerPieceProbleme(int coordDepart) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisir 0 pour changer de piece");

		System.out.println("Déplacer la piece : ");

		boolean verifIfSaisieCoupPossible = false;
		boolean verifChangerPiece = false;
		int coordArrivee = -1;
		while (verifIfSaisieCoupPossible == false) {

			String saisie = sc.nextLine();
			coordArrivee = NotationCoup.conversionLettreTo64(saisie);

			for (Integer i : listeCoup) {

				if (coordArrivee == i) {

					//NotationCoup.setCoordArriveeStandard(saisie);
					d.deplacement(p.getPieceCase(coordDepart), coordArrivee, p);
					verifIfSaisieCoupPossible = true;
					verifChangerPiece = true;
					
					break;
				}
			}

			if (verifIfSaisieCoupPossible == false) {
				if (saisie.equals("0")) {
					break;
				} else {
					System.out.println("Veuillez saisir un coup dans la liste ci-dessus");
					System.out.println("Déplacement illégal");
				}
			}
		}
		if (verifChangerPiece) {
			promo.promotion(p.getPieceCase(coordArrivee), p);
		}
		return coordArrivee;
	}
}
