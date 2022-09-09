package fr.echec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.echec.classe.historique.NotationCoup;
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
	protected Plateau p;
	
	@Autowired
	protected CoupsPossibles coupPossible;
	
	@Autowired
	protected ProblemeService srvProbleme;
	
	protected List<Integer> listeCoup = new ArrayList<>();
	protected Probleme probleme = new Probleme();

	public void jouerPb(int id) throws IdNegatifException, ProblemeNotFoundException { // utlisateur idPb
		
		// probleme service findById
		probleme = srvProbleme.findById(id);
		
		int coordArrivee;
		int coordDepart;
		boolean verif = true;
		String[] tabCoups = probleme.getListeDeplacement().split(" ");
		for (int i = 0; i < tabCoups.length; i++) {
			if (i % 2 == 0) { // tour de l'ordi
				coupOrdi.coupOrdi(tabCoups[i], p, probleme.isTraitAuBlanc());
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
		if (probleme.isTraitAuBlanc()){
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
		sc.close();
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
		sc.close();
		return coordArrivee;
	}
}
