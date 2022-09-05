package fr.echec.classe.mouvements;

import java.util.List;

import fr.echec.classe.jeu.Piece;
import fr.echec.classe.jeu.Plateau;
import fr.echec.enumerateur.CouleursPiece;
import fr.echec.enumerateur.TypePiece;

public class GestionRoque extends GestionEchec{
	
	
	private boolean petitRoque(Piece roi, Piece tour, Plateau plateau) {
		
		boolean petitRoquePossible = false;
		CouleursPiece couleurJoueur = roi.getCouleur();
		
		// Les deux pièces sélectionnées ne sont pas les bonnes
		if (tour.getCouleur() != roi.getCouleur() || Math.abs(roi.getCoordonnee()-tour.getCoordonnee()) != 3) {
			return false;
		}
		
		if (roi.getNom() == TypePiece.ROI && roi.isaBouge() == false && isEchec(plateau, couleurJoueur) == false
				&& tour.getNom() == TypePiece.TOUR && tour.isaBouge() == false) {
			
			if (couleurJoueur == CouleursPiece.BLANC 
			    && plateau.getPieceCase(5) == null && casePlateauEchec(plateau, 5, CouleursPiece.BLANC) == false
				&& plateau.getPieceCase(6) == null && casePlateauEchec(plateau, 5, CouleursPiece.BLANC) == false) {
				
				petitRoquePossible = true;
					
			} else if (couleurJoueur == CouleursPiece.NOIR 
					&& plateau.getPieceCase(61) == null && casePlateauEchec(plateau, 61, CouleursPiece.NOIR) == false
					&& plateau.getPieceCase(62) == null && casePlateauEchec(plateau, 62, CouleursPiece.NOIR) == false) {
				
				petitRoquePossible = true;
				
			}
		}
		
		return petitRoquePossible;
	}

	private boolean grandRoque(Piece roi, Piece tour, Plateau plateau) {
		
		boolean grandRoquePossible = false;
		CouleursPiece couleurJoueur = roi.getCouleur();
		
		// Les deux pièces sélectionnées ne sont pas les bonnes
		if (tour.getCouleur() != roi.getCouleur() || Math.abs(roi.getCoordonnee()-tour.getCoordonnee()) != 4) {
			return false;
		}
		
		if (roi.getNom() == TypePiece.ROI && roi.isaBouge() == false && isEchec(plateau, couleurJoueur) == false
				&& tour.getNom() == TypePiece.TOUR && tour.isaBouge() == false) {
			
			if (couleurJoueur == CouleursPiece.BLANC 
			    && plateau.getPieceCase(1) == null
				&& plateau.getPieceCase(2) == null && casePlateauEchec(plateau, 2, CouleursPiece.BLANC) == false
				&& plateau.getPieceCase(3) == null && casePlateauEchec(plateau, 3, CouleursPiece.BLANC) == false){
				
				grandRoquePossible = true;
					
			} else if (couleurJoueur == CouleursPiece.NOIR 
					&& plateau.getPieceCase(59) == null && casePlateauEchec(plateau, 59, CouleursPiece.NOIR) == false
					&& plateau.getPieceCase(58) == null && casePlateauEchec(plateau, 58, CouleursPiece.NOIR) == false
					&& plateau.getPieceCase(57) == null) {
				
				grandRoquePossible = true;
				
			}
		}
		
		return grandRoquePossible;
		
	}
	
	public List<Integer> ajouteDestinationRoque(List<Integer> destinationsJouables, Plateau plateau, Piece piece) {
		
		Piece roi;
		Piece tourPetitRoque;
		Piece tourGrandRoque;
		
		if (piece.getNom() == TypePiece.ROI) {
			CouleursPiece couleurJoueur = piece.getCouleur();
			if (couleurJoueur == CouleursPiece.BLANC) {
				roi = piece;
				tourGrandRoque = plateau.getByNomPlateau("tb1");
				tourPetitRoque = plateau.getByNomPlateau("tb2");
			} else {
				roi = piece;
				tourGrandRoque = plateau.getByNomPlateau("tn1");
				tourPetitRoque = plateau.getByNomPlateau("tb2");
			}
		} else if (piece.getNom() == TypePiece.TOUR) {
			// TODO Faut savoir quelle tour est la tour qu'on a en entrée !!!
			CouleursPiece couleurJoueur = piece.getCouleur();
			if (couleurJoueur == CouleursPiece.BLANC) {
				roi = plateau.getByNomPlateau("rb ");
				tourGrandRoque = plateau.getByNomPlateau("tb1");
				tourPetitRoque = plateau.getByNomPlateau("tb2");
			} else {
				roi = plateau.getByNomPlateau("rn ");
				tourGrandRoque = plateau.getByNomPlateau("tn1");
				tourPetitRoque = plateau.getByNomPlateau("tb2");
			}
		}
		
		return destinationsJouables;
	}

}
