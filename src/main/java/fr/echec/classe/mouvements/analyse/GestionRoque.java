package fr.echec.classe.mouvements.analyse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.echec.classe.jeu.Piece;
import fr.echec.classe.jeu.Plateau;
import fr.echec.enumerateur.CouleursPiece;
import fr.echec.enumerateur.TypePiece;
@Component
public class GestionRoque {
	@Autowired
	public GestionEchec gestionEchec ;
	
	protected boolean petitRoque(Piece roi, Piece tour, Plateau plateau) {
		
		boolean petitRoquePossible = false;
		CouleursPiece couleurJoueur = roi.getCouleur();
		
		// Les deux pièces sélectionnées ne sont pas les bonnes
		if (tour.getCouleur() != roi.getCouleur() || Math.abs(roi.getCoordonnee()-tour.getCoordonnee()) != 3) {
			return false;
		}
		
		if (roi.getNom() == TypePiece.ROI && roi.isaBouge() == false && gestionEchec.isEchec(plateau, couleurJoueur) == false
				&& tour.getNom() == TypePiece.TOUR && tour.isaBouge() == false) {
			
			if (couleurJoueur == CouleursPiece.BLANC 
			    && plateau.getPieceCase(5) == null && gestionEchec.casePlateauEchec(plateau, 5, CouleursPiece.BLANC) == false
				&& plateau.getPieceCase(6) == null && gestionEchec.casePlateauEchec(plateau, 5, CouleursPiece.BLANC) == false) {
				
				petitRoquePossible = true;
					
			} else if (couleurJoueur == CouleursPiece.NOIR 
					&& plateau.getPieceCase(61) == null && gestionEchec.casePlateauEchec(plateau, 61, CouleursPiece.NOIR) == false
					&& plateau.getPieceCase(62) == null && gestionEchec.casePlateauEchec(plateau, 62, CouleursPiece.NOIR) == false) {
				
				petitRoquePossible = true;
				
			}
		}
		
		return petitRoquePossible;
	}

	protected boolean grandRoque(Piece roi, Piece tour, Plateau plateau) {
		
		boolean grandRoquePossible = false;
		CouleursPiece couleurJoueur = roi.getCouleur();
		
		// Les deux pièces sélectionnées ne sont pas les bonnes
		if (tour.getCouleur() != roi.getCouleur() || Math.abs(roi.getCoordonnee()-tour.getCoordonnee()) != 4) {
			return false;
		}
		
		if (roi.getNom() == TypePiece.ROI && roi.isaBouge() == false && gestionEchec.isEchec(plateau, couleurJoueur) == false
				&& tour.getNom() == TypePiece.TOUR && tour.isaBouge() == false) {
			
			if (couleurJoueur == CouleursPiece.BLANC 
			    && plateau.getPieceCase(1) == null
				&& plateau.getPieceCase(2) == null && gestionEchec.casePlateauEchec(plateau, 2, CouleursPiece.BLANC) == false
				&& plateau.getPieceCase(3) == null && gestionEchec.casePlateauEchec(plateau, 3, CouleursPiece.BLANC) == false){
				
				grandRoquePossible = true;
					
			} else if (couleurJoueur == CouleursPiece.NOIR 
					&& plateau.getPieceCase(59) == null && gestionEchec.casePlateauEchec(plateau, 59, CouleursPiece.NOIR) == false
					&& plateau.getPieceCase(58) == null && gestionEchec.casePlateauEchec(plateau, 58, CouleursPiece.NOIR) == false
					&& plateau.getPieceCase(57) == null) {
				
				grandRoquePossible = true;
				
			}
		}
		
		return grandRoquePossible;
		
	}
	
	public List<Integer> ajouteDestinationRoque(List<Integer> destinationsJouables, Plateau plateau, Piece piece) {
		
		Piece roi = null;
		Piece tourPetitRoque = null;
		Piece tourGrandRoque = null;
		
		boolean petitRoquePossible = false;
		boolean grandRoquePossible = false;
		
		if (piece.getNom() == TypePiece.ROI) {
			
			roi = piece;
			if (piece.getCouleur() == CouleursPiece.BLANC) {
				tourGrandRoque = plateau.getByNomPlateau("tb1");
				tourPetitRoque = plateau.getByNomPlateau("tb2");
			} else {
				tourGrandRoque = plateau.getByNomPlateau("tn1");
				tourPetitRoque = plateau.getByNomPlateau("tn2");
			}
			
			petitRoquePossible = petitRoque(roi, tourPetitRoque, plateau);
			grandRoquePossible = grandRoque(roi, tourGrandRoque, plateau);
			
		} 
		
		if (petitRoquePossible) {
			destinationsJouables.add(roi.getCoordonnee() + 2);
		}
		
		if (grandRoquePossible) {
			destinationsJouables.add(roi.getCoordonnee() - 2);
		}
		
		return destinationsJouables;
	}

}
