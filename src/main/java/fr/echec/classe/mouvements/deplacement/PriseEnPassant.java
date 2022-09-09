package fr.echec.classe.mouvements.deplacement;

import org.springframework.stereotype.Component;

import fr.echec.classe.jeu.Piece;
import fr.echec.classe.jeu.Plateau;
import fr.echec.enumerateur.CouleursPiece;
import fr.echec.enumerateur.TypePiece;
@Component
public class PriseEnPassant {
	
	
	
	public void gestionBooleenPriseEnPassant(Piece pion, Plateau p) {
		
		CouleursPiece couleur = pion.getCouleur();
		
		for (Piece piece : p.getPieces()) {
			if (piece.getCouleur() == couleur && piece.getNom() == TypePiece.PION) {
				piece.setPriseEnPassantPossible(false);
			}
		}
		
		pion.setPriseEnPassantPossible(true);
		
	}
	
	public void jouerPriseEnPassant(Piece pion, int coord, Plateau p) {
		
		if (coord == pion.getCoordonnee()+7 || coord == pion.getCoordonnee()-9) {
			Deplacement.capture(p.getPieceCase(pion.getCoordonnee() -1), p); // prise en passant à gauche
		} else if (coord == pion.getCoordonnee()+9 || coord == pion.getCoordonnee()-7) {
			Deplacement.capture(p.getPieceCase(pion.getCoordonnee() +1), p); // prise en passant à droite
		}
		
		Deplacement.bougerPiece(pion, coord, p);
		
	}

}
