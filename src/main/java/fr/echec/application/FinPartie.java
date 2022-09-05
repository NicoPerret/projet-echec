package fr.echec.application;

import java.util.ArrayList;
import java.util.List;

import fr.echec.classe.jeu.Fen;
import fr.echec.classe.jeu.Plateau;
import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.mouvements.CoupsPossibles;
import fr.echec.classe.mouvements.Deplacement;

public class FinPartie {

// VARIABLES 

	private Deplacement deplacement;
	private CoupsPossibles coupsPossibles;
	private List<Integer> listeCoup = new ArrayList<>();
	private List<String> listeFen = new ArrayList<>();
	private Fen fen;

// GETTERS ET SETTERS 

	public Deplacement getDeplacement() {
		return deplacement;
	}

	public void setDeplacement(Deplacement deplacement) {
		this.deplacement = deplacement;
	}

	public CoupsPossibles getCoupsPossibles() {
		return coupsPossibles;
	}

	public void setCoupsPossibles(CoupsPossibles coupsPossibles) {
		this.coupsPossibles = coupsPossibles;
	}

// CONSTRUCTEUR 

// METHODES

	public boolean isEchecMatOuPat(Plateau plateau, Utilisateur joueurActif) {

		for (int i = 0; i < 64; i++) {
			// if plateau.getPIece(i).getCOuleur == jouueractif.getCOuleur
			listeCoup = coupsPossibles.trouveDestinationsPossibles(plateau, plateau.getPieceCase(i));

			if (plateau.getPieceCase(i).getCouleur() != joueurActif.getCouleur()) {
				listeCoup.clear();
			}

			if (listeCoup.isEmpty() == false) {

				return false;

			}

		}
		if (coupsPossibles.isEchec(plateau, joueurActif.getCouleur())) {

			System.out.println("Echec et mat");
			return true;

		} else {
			System.out.println("Match nul par Pat");
			return true;
		}

	}

	public boolean isMatchNul(Plateau plateau) {
		fen.creationFen(plateau);

		return false;

	}

}
