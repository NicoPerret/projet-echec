package fr.echec.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.echec.classe.jeu.Fen;
import fr.echec.classe.jeu.Piece;
import fr.echec.classe.jeu.Plateau;
import fr.echec.classe.mouvements.analyse.CoupsPossibles;
import fr.echec.classe.mouvements.analyse.GestionEchec;
import fr.echec.classe.mouvements.deplacement.Deplacement;
import fr.echec.enumerateur.CouleursPiece;
import fr.echec.enumerateur.TypePiece;

@Service
public class FinPartie {

// VARIABLES 
	@Autowired
	private Deplacement deplacement ;
	@Autowired
	private CoupsPossibles coupsPossibles;

	
	@Autowired
	private NulleParRepetitionMethode test;
	@Autowired
	private Fen fen ; 
	@Autowired
	private GestionEchec gestionEchec ;
	
	
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
		List<Integer> listeCoup = new ArrayList<>();
		for (int i = 0; i < 64; i++) {

			
			

			
			
				if(plateau.getPieceCase(i) != null ) {
				
					
					
					if(plateau.getPieceCase(i).getCouleur() == couleur) {
						
						
						listeCoup = coupsPossibles.trouveDestinationsPossibles(plateau, plateau.getPieceCase(i));
						
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
		if(listeCoup.isEmpty() == true && gestionEchec.isEchec(plateau, couleur)) {
			
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

	public boolean isMatchNulRepetition(Plateau plateau) {
		 ArrayList<String> listeFen = new ArrayList<>();
		listeFen.add(fen.creationFen(plateau));
		return test.countFrequencies(listeFen);
		

	}
	
	public boolean isMatchNulmateriel(Plateau plateau) {
		if (plateau.getPieces().size() >= 4) {
			return false;
			}
		else {
			for (Piece p : plateau.getPieces()) {
				if (p.getNom() == TypePiece.PION ||p.getNom() == TypePiece.DAME ||p.getNom() == TypePiece.TOUR ) {
					return false;
				}
			}
			return true;
		}
		
	}

}
