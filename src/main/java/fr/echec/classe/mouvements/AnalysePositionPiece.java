package fr.echec.classe.mouvements;

import fr.echec.classe.jeu.Piece;
import fr.echec.classe.jeu.Plateau;

public class AnalysePositionPiece {

	protected int[] trouveCasesDispoBordPlateau(Piece piece) {

		// Renvoie le nombre de cases entre la piece et le bord du plateau
		// [haut, bas, gauche, droite, HG, HD, BG, BD]

		int[] casesDispoBordPlateau = new int[8];

		int coordPiece = piece.getCoordonnee();
		// haut
		casesDispoBordPlateau[0] = 7 - coordPiece / 8;
		// bas
		casesDispoBordPlateau[1] = coordPiece / 8;
		// gauche
		casesDispoBordPlateau[2] = coordPiece % 8;
		// droite
		casesDispoBordPlateau[3] = 7 - coordPiece % 8;
		// haut-gauche
		casesDispoBordPlateau[4] = Math.min(casesDispoBordPlateau[0], casesDispoBordPlateau[2]);
		// haut-droite
		casesDispoBordPlateau[5] = Math.min(casesDispoBordPlateau[0], casesDispoBordPlateau[3]);
		// bas-gauche
		casesDispoBordPlateau[6] = Math.min(casesDispoBordPlateau[1], casesDispoBordPlateau[2]);
		// bas-droite
		casesDispoBordPlateau[7] = Math.min(casesDispoBordPlateau[1], casesDispoBordPlateau[3]);

		return casesDispoBordPlateau;

	}

	protected int trouvePieceVoisineDirection(int direction, int casesBordPlateau, Plateau plateau, Piece piece) {

		// renvoie le nombre de cases jusqu'à la premiere pièce trouvée ou par défaut
		// jusqu'au bord
		// la pièce voisine trouvée est incluse dans le nombre de cases si elle est de
		// couleur opposée

		int coordPiece = piece.getCoordonnee();
		int casesPieceVoisine = 0;

		for (int i = 1; i <= casesBordPlateau; i++) {

			Piece pieceVoisine = plateau.getPieceCase(coordPiece + i * direction);

			if (pieceVoisine != null) {

				if (pieceVoisine.getCouleur() != piece.getCouleur()) {
					casesPieceVoisine++;
				}

				break;
			}

			casesPieceVoisine++;
		}

		return casesPieceVoisine;

	}

}
