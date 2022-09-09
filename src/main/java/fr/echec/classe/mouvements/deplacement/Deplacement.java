package fr.echec.classe.mouvements.deplacement;

import fr.echec.classe.jeu.Piece;
import fr.echec.classe.jeu.Plateau;
import fr.echec.enumerateur.TypePiece;

public class Deplacement {
	
	private Roque roque = new Roque();
	private Promotion promotion = new Promotion();
	private PriseEnPassant priseEnPassant = new PriseEnPassant();
	

	public void deplacement(Piece piece, int coord, Plateau p) {
		
		if (piece.getNom() == TypePiece.ROI && coord == piece.getCoordonnee()+2) {
			roque.jouerPetitRoque(piece, p);
		} else if (piece.getNom() == TypePiece.ROI && coord == piece.getCoordonnee()-2) {
			roque.jouerGrandRoque(piece, p);
		} else if (piece.getNom() == TypePiece.PION 
				&& (coord - piece.getCoordonnee()) % 2 == 1 &&  p.getPieceCase(coord) == null) {
				// Si déplacement en diagonal (impair) et que la case de destination est vide
				priseEnPassant.jouerPriseEnPassant(piece, coord, p);
		}else {
			bougerPiece(piece, coord, p);
			promotion.promotion(piece, p);
		}
		
		priseEnPassant.gestionBooleenPriseEnPassant(piece, p);
		
	}
	
	public static void bougerPiece(Piece piece, int coord, Plateau p) {
		
		if (p.getPieceCase(coord) != null) {
			capture(p.getPieceCase(coord), p);
		}
		
		p.setCaseTableau("   ", piece.getCoordonnee());
		p.setCaseTableau(piece.getNomPlateau(), coord);
		
		p.getPieceCase(piece.getCoordonnee()).setCoordonnee(coord);
		piece.setaBouge(true);;
		
	}

	public static void capture(Piece piece, Plateau p) {
		
		p.setCaseTableau("   ", piece.getCoordonnee());
		piece.setCoordonnee(-1);
		piece.setEnVie(false);
		
	}
	
}
