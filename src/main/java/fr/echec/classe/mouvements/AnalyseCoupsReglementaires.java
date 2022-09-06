package fr.echec.classe.mouvements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.echec.classe.jeu.Piece;
import fr.echec.classe.jeu.Plateau;
import fr.echec.enumerateur.CouleursPiece;
import fr.echec.enumerateur.TypePiece;

public class AnalyseCoupsReglementaires {

	public static Map<TypePiece, int[]> createCoupsTypePiece() { // coup reglementaire par type de pieces

		Map<TypePiece, int[]> coupsTypePiece = new HashMap<TypePiece, int[]>();

		coupsTypePiece.put(TypePiece.PION, new int[] { 7, 8, 9, 16, -7, -8, -9, -16 });
		coupsTypePiece.put(TypePiece.TOUR, new int[] { 8, 16, 24, 32, 40, 48, 56, -8, -16, -24, -32, -40, -48, -56, 1,
				2, 3, 4, 5, 6, 7, -1, -2, -3, -4, -5, -6, -7 });
		coupsTypePiece.put(TypePiece.CAVALIER, new int[] { 6, 10, 15, 17, -6, -10, -15, -17 });
		coupsTypePiece.put(TypePiece.FOU, new int[] { 9, 18, 27, 36, 45, 54, 63, -9, -18, -27, -36, -45, -54, -63, 7,
				14, 21, 28, 35, 42, 49, -7, -14, -21, -28, -35, -42, -49 });
		coupsTypePiece.put(TypePiece.DAME,
				new int[] { 8, 16, 24, 32, 40, 48, 56, -8, -16, -24, -32, -40, -48, -56, 1, 2, 3, 4, 5, 6, 7, -1, -2,
						-3, -4, -5, -6, -7, 9, 18, 27, 36, 45, 54, 63, -9, -18, -27, -36, -45, -54, -63, 14, 21, 28, 35,
						42, 49, -14, -21, -28, -35, -42, -49 });
		coupsTypePiece.put(TypePiece.ROI, new int[] { 1, 7, 8, 9, -1, -7, -8, -9 });

		return coupsTypePiece;

	}

	protected static List<Integer> filtreCoupsReglementairesPion(List<Integer> coupsReglementairesPion, int[] casesDispoBordPlateau, Plateau plateau, Piece piece) {
		
		// Filtre les coups réglementaires pour le pion selon sa situation (couleur, position, prises...)
		
		int coord = piece.getCoordonnee();
		CouleursPiece couleur = piece.getCouleur();
		
		if (couleur == CouleursPiece.BLANC) {
			coupsReglementairesPion.remove(Integer.valueOf(-7)); // coups couleur opposée
			coupsReglementairesPion.remove(Integer.valueOf(-8));
			coupsReglementairesPion.remove(Integer.valueOf(-9));
			coupsReglementairesPion.remove(Integer.valueOf(-16));
			// deux cases et pas prise en face
			if (coord / 8 != 1 || (coord / 8 == 1 && plateau.getPieceCase(coord+16) != null)) {
				coupsReglementairesPion.remove(Integer.valueOf(16)); 
			} 
			if (casesDispoBordPlateau[0] >= 1 && plateau.getPieceCase(coord+8) != null) {
				coupsReglementairesPion.remove(Integer.valueOf(8));
			}
			// Prise en diagonale possible
			if (casesDispoBordPlateau[4] == 0 || plateau.getPieceCase(coord+7) == null) {
				coupsReglementairesPion.remove(Integer.valueOf(7));
			}
			if (casesDispoBordPlateau[5] == 0 || plateau.getPieceCase(coord+9) == null) {
				coupsReglementairesPion.remove(Integer.valueOf(9));
			}
			
		}
		else {
			coupsReglementairesPion.remove(Integer.valueOf(7));
			coupsReglementairesPion.remove(Integer.valueOf(8));
			coupsReglementairesPion.remove(Integer.valueOf(9));
			coupsReglementairesPion.remove(Integer.valueOf(16));
			if (coord / 8 != 6 || (coord / 8 == 6 && plateau.getPieceCase(coord-16) != null)) {
				coupsReglementairesPion.remove(Integer.valueOf(-16)); 
			}
			if (casesDispoBordPlateau[1] >= 1 && plateau.getPieceCase(coord-8) != null) {
				coupsReglementairesPion.remove(Integer.valueOf(-8));
			}
			// Prise en diagonale possible
			if (casesDispoBordPlateau[7] == 0 || plateau.getPieceCase(coord-7) == null) {
				coupsReglementairesPion.remove(Integer.valueOf(-7));
			}
			if (casesDispoBordPlateau[6] == 0 || plateau.getPieceCase(coord-9) == null) {
				coupsReglementairesPion.remove(Integer.valueOf(-9));
			}
		}
		
		// Prise en passant à gauche
		if (casesDispoBordPlateau[2] >= 1 && plateau.getPieceCase(coord-1) != null
				&& (coord / 8 == 3 || coord / 8 == 4)) {
			
			Piece pieceVoisine = plateau.getPieceCase(coord-1);
				
			if (pieceVoisine.getNom() == TypePiece.PION && pieceVoisine.getCouleur() != couleur
					&& pieceVoisine.isPriseEnPassantPossible()) {
				if (couleur == CouleursPiece.BLANC && plateau.getPieceCase(coord+7) == null) {
					coupsReglementairesPion.add(Integer.valueOf(7));
				} else if (couleur == CouleursPiece.NOIR && plateau.getPieceCase(coord-9) == null) {
					coupsReglementairesPion.add(Integer.valueOf(-9));
				}
			}
			
		}
		
		// Prise en passant à droite
		if (casesDispoBordPlateau[3] >= 1 && plateau.getPieceCase(coord+1) != null
				&& (coord / 8 == 3 || coord / 8 == 4)) {
			
			Piece pieceVoisine = plateau.getPieceCase(coord+1);
				
			if (pieceVoisine.getNom() == TypePiece.PION && pieceVoisine.getCouleur() != couleur
					&& pieceVoisine.isPriseEnPassantPossible()) {
				if (couleur == CouleursPiece.BLANC && plateau.getPieceCase(coord+9) == null) {
					coupsReglementairesPion.add(Integer.valueOf(9));
				} else if (couleur == CouleursPiece.NOIR && plateau.getPieceCase(coord-7) == null) {
					coupsReglementairesPion.add(Integer.valueOf(-7));
				}
			}
			
		}
		
		return coupsReglementairesPion;
		
	}

	protected static List<Integer> filtreCoupsReglementairesTour(List<Integer> coupsReglementairesTour,
			int[] casesDispoBordPlateau, Plateau plateau, Piece piece) {
		// Pour éviter un mouvement +7 ou -7 en diagonal
		if (casesDispoBordPlateau[3] < 7) { // à droite
			coupsReglementairesTour.remove(Integer.valueOf(7));
		}
		if (casesDispoBordPlateau[2] < 7) { // à gauche
			coupsReglementairesTour.remove(Integer.valueOf(-7));
		}

		return coupsReglementairesTour;

	}

	protected static List<Integer> TrouveCoupsReglementaires(int[] casesDispoBordPlateau, Piece piece,
			Plateau plateau) {

		Map<TypePiece, int[]> coupsTypePiece = createCoupsTypePiece();

		// On récupère la liste des coups réglementaires selon le type de la pièce
		List<Integer> coupsReglementaires = new ArrayList<>();
		for (int coup : coupsTypePiece.get(piece.getNom())) {
			coupsReglementaires.add(coup);
		}
		// On filtre dans le cas du pion ou la tour
		if (piece.getNom() == TypePiece.PION) {
			filtreCoupsReglementairesPion(coupsReglementaires, casesDispoBordPlateau, plateau, piece);
		} else if (piece.getNom() == TypePiece.TOUR) {
			filtreCoupsReglementairesTour(coupsReglementaires, casesDispoBordPlateau, plateau, piece);
		}

		return coupsReglementaires;
	}

}
