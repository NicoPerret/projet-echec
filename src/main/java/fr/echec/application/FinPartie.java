package fr.echec.application;

import java.util.ArrayList;
import java.util.List;

import fr.echec.classe.jeu.Fen;
import fr.echec.classe.jeu.Plateau;
import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.mouvements.CoupsPossibles;
import fr.echec.classe.mouvements.Deplacement;
import fr.echec.enumerateur.CouleursPiece;

public class FinPartie {

// VARIABLES 

	private Deplacement deplacement = new Deplacement();
	private CoupsPossibles coupsPossibles = new CoupsPossibles();
	private List<Integer> listeCoup = new ArrayList<>();
	private List<String> listeFen = new ArrayList<>();

	private Fen fen = new Fen(); 
	private boolean nulle = false;
	private boolean echecMat = false;
	
	

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

	public boolean isNulle() {
		return nulle;
	}
	
	public void setNulle(boolean nulle) {
		this.nulle = nulle;
	}
	
	public boolean isEchecMat() {
		return echecMat;
	}
	
	public void setEchecMat(boolean echecMat) {
		this.echecMat = echecMat;
// CONSTRUCTEUR 

// METHODES

	}

	public boolean isEchecMatOuPat(Plateau plateau, CouleursPiece couleur) {

		for (int i = 0; i < 64; i++) {

			
			

			
			
				if(plateau.getPieceCase(i) != null ) {
				
					
					
					if(plateau.getPieceCase(i).getCouleur() == couleur) {
						
						listeCoup = coupsPossibles.trouveDestinationsPossibles(plateau, plateau.getPieceCase(i));
						System.out.println(listeCoup);
					}
				}
				
			
			
			if(listeCoup.isEmpty() == false) {
				

				return false;

			}

			
		}
		if (couleur == CouleursPiece.BLANC) {
			couleur = CouleursPiece.NOIR;
			
		}else {
			couleur = CouleursPiece.BLANC;
		}
		if(listeCoup.isEmpty() == true && coupsPossibles.isEchec(plateau, couleur)) {
			
			System.out.println("Echec et mat");
			echecMat = true;
			return true;
			
		}else if (listeCoup.isEmpty() == false){

			System.out.println("Match nul par Pat");
			nulle = true;
			return true;
		}
		return false;
	}

	public boolean isMatchNul(Plateau plateau) {
		fen.creationFen(plateau);

		return false;

	}

}
