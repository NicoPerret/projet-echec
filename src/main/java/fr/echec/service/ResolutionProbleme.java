package fr.echec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.echec.classe.historique.NotationCoup;
import fr.echec.classe.jeu.Fen;
import fr.echec.classe.jeu.Plateau;
import fr.echec.classe.mouvements.analyse.CoupsPossibles;
import fr.echec.classe.mouvements.deplacement.Deplacement;
import fr.echec.classe.mouvements.deplacement.Promotion;
import fr.echec.classe.probleme.Probleme;
import fr.echec.enumerateur.CouleursPiece;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.ProblemeNotFoundException;
import fr.echec.ia.CoupOrdi;

@Service
public class ResolutionProbleme {

	@Autowired
	protected Deplacement d;

	@Autowired
	protected Promotion promo;

	@Autowired
	protected CoupOrdi coupOrdi;

	@Autowired
	protected Plateau plateau;
	
	@Autowired
	protected CoupsPossibles coupPossible;
	
	@Autowired
	protected ProblemeService srvProbleme;
	
	@Autowired
	protected Fen fen;
	
	protected List<Integer> listeCoup = new ArrayList<>();
	protected Probleme probleme = new Probleme();
	
	List<Probleme> listeProblemes = new ArrayList();
	Random hasard = new Random();
	
	int readInt() {
		Scanner sc = new Scanner(System.in);
	    return sc.nextInt();
	}

	public void jouerPb(int id, int difficulte) throws IdNegatifException, ProblemeNotFoundException { // utlisateur idPb
		
		// probleme service findById
		probleme = srvProbleme.findById(id);
		System.out.println("Choisissez voutre difficulté :");
		System.out.println("1 - EASY (elo 0 à 800");
		System.out.println("2 - MEDIUM (elo 800 à 1200");
		System.out.println("3 - HARD (elo supérieur à 1200");
		probleme.setDifficulte(readInt());
		switch (difficulte) {
		case 1 :
			listeProblemes = srvProbleme.FindByDifficultyBetween(0, 800); // mettre le elo du probleme entre parenthèses
			break;
		case 2 :
			listeProblemes = srvProbleme.FindByDifficultyBetween(800, 1200);
			break;
		case 3 :
			listeProblemes = srvProbleme.FindByDifficultyBetween(1200, 2000);
			break;
			
		}
		probleme.setId(hasard.nextInt(listeProblemes.size()));
		
		int coordArrivee;
		int coordDepart;
		boolean verif;
		this.plateau = fen.creationPlateau(probleme.getFenDepart());
		String[] tabCoups = probleme.getListeDeplacement().toUpperCase().split(" ");
		for (int i = 0; i < tabCoups.length; i++) {
			verif = true;
			System.out.println(this.plateau);
			if (i % 2 == 0) { // tour de l'ordi
				coupOrdi.coupOrdi(tabCoups[i], this.plateau, probleme.isTraitAuBlanc());
			} else { // tour du joueur
				while (verif) {
					do {
						coordDepart = selectionPieceProbleme();
						coordArrivee = jouerPieceProbleme(coordDepart);
					} while (coordArrivee == -1);
					String coupJoueur = NotationCoup.conversion64ToLettre(coordDepart)
							+ NotationCoup.conversion64ToLettre(coordArrivee);
					verif = verifBonCoup(coupJoueur, tabCoups[i]);
				}
			}
		}
	}

	// Verif coup joué est le bon

	public boolean verifBonCoup(String coupJoueur, String coupAJouer) {
		if (coupJoueur.equals(coupAJouer)) {
			this.d.deplacement(this.plateau.getPieceCase(NotationCoup.conversionLettreTo64(coupJoueur.substring(0, 2))),
					NotationCoup.conversionLettreTo64(coupJoueur.substring(2, 4)), this.plateau);
			return false;
		} else {
			System.out.println("Ce n'est pas le bon coup ! Reessayez !");
			return true;
		}
	}

	// Sélection de la piece renvoi coord depart
	public int selectionPieceProbleme() {
		Scanner sc = new Scanner(System.in);
		int coordDepart;
		CouleursPiece couleurJoueur = CouleursPiece.BLANC;
		if (probleme.isTraitAuBlanc()){
			couleurJoueur = CouleursPiece.NOIR;
		}
		
		while (true) {
<<<<<<< Updated upstream
			System.out.println("Vous êtes le joueur "+ couleurJoueur);
			System.out.println("Saisir une piece ou 0 poura voir un indice : ");
=======
			System.out.println("Saisir une piece : ");
>>>>>>> Stashed changes

			String saisie = sc.nextLine();

			 coordDepart = NotationCoup.conversionLettreTo64(saisie);

			if (	this.plateau.getPieceCase(coordDepart) != null
					&& this.plateau.getPieceCase(coordDepart).getCouleur() == couleurJoueur) {
				listeCoup = coupPossible.trouveDestinationsPossibles(this.plateau, this.plateau.getPieceCase(coordDepart));

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
					
					// NotationCoup.setCoordArriveeStandard(saisie);
					//d.deplacement(this.plateau.getPieceCase(coordDepart), coordArrivee, this.plateau);
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
		
		return coordArrivee;
	}
}
