package fr.echec.classe.probleme;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import fr.echec.classe.historique.NotationCoup;
import fr.echec.classe.jeu.Plateau;
import fr.echec.classe.mouvements.CoupsPossibles;
import fr.echec.classe.mouvements.Deplacement;

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

	protected Plateau p;
	protected List<Integer> listeCoup = new ArrayList<>();
	protected CoupsPossibles coupPossible = new CoupsPossibles();
	protected int coordDepart;

	@Transient
	@Autowired
	protected Deplacement d;

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
<<<<<<< HEAD
	// construire plateau en meme temps

=======

	Plateau p = new Plateau();
	
>>>>>>> FMM
	// METHODES
	
	
	String[] tabCoups = listeDeplacements.split(" ");
	for(int i = 0; i < tabCoups.size(); i++) {
		if (i%2 ==0) { 								//tour de l'ordi
			coupOrdi(p, tabCoups[i]);
		}else { 									//tour du joueur
			String coupJoueur.selectionPieceProbleme();
			coupJoueur.jouerPieceProbleme();
			while(true) {
				verifBonCoup(coupJoueur, p, tabCoups[i]);
		}
	}}

	// Jouer les coups par l'ordi découper le string avec "", upperCase, convertir
	// //PROBLEMES : tailles des problemes inconstant (4, 5, 6 déplacements)
	// en 64, prendre les 2 coordonnées et faire déplacement

	public void coupOrdi(String coupAJouer) {
		NotationCoup nt = new NotationCoup(0, 0);
		int coorDepart = nt.conversionLettreTo64(coupAJouer.substring(0, 2));
		int coorArrivee = nt.conversionLettreTo64(coupAJouer.substring(2, 4));
		this.d.deplacement(p.getPieceCase(coorDepart), coorArrivee, p);
	}

	// Verif coup joué est le bon

	public boolean verifBonCoup(String coupJoueur, String coupAJouer) {
		NotationCoup nt = new NotationCoup(0, 0);
		if (coupJoueur.equals(coupAJouer)) {
			this.d.deplacement(p.getPieceCase(nt.conversionLettreTo64(coupJoueur.substring(0, 2))),
					nt.conversionLettreTo64(coupJoueur.substring(2, 4)), p);
			return true;
		} else {
			System.out.println("Ce n'est pas le bon coup! Reessayer");
			return false;
		}
	}


	public void selectionPieceProbleme() {
		Scanner sc;
		while (true) {
			System.out.println("Saisir une piece : ");

			String saisie = sc.nextLine();

			int coordDepart = NotationCoup.conversionLettreTo64(saisie);

			if (p.getPieceCase(coordDepart) != null) {
				listeCoup = coupPossible.trouveDestinationsPossibles(p, p.getPieceCase(coordDepart));

				if (listeCoup.isEmpty() == false) {
					System.out.println("Coup(s) possible(s) : ");
					NotationCoup.setCoordDepartStandard(saisie);
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

	}

	public void jouerPieceProbleme() {
		Scanner sc;
		System.out.println("Saisir 0 pour changer de piece");

		System.out.println("Déplacer la piece : ");

		boolean verifIfSaisieCoupPossible = false;
		boolean verifChangerPiece = false;
		int coordArrivee = 0;

		while (verifIfSaisieCoupPossible == false) {

			String saisie = sc.nextLine();
			coordArrivee = NotationCoup.conversionLettreTo64(saisie);

			for (Integer i : listeCoup) {

				if (coordArrivee == i) {

					NotationCoup.setCoordArriveeStandard(saisie);
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
			d.promotion(p.getPieceCase(coordArrivee), p);
		}
	}
}
