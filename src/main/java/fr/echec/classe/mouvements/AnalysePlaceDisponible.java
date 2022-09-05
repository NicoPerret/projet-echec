package fr.echec.classe.mouvements;

import java.util.ArrayList;
import java.util.List;

import fr.echec.classe.jeu.Piece;
import fr.echec.classe.jeu.Plateau;
import fr.echec.enumerateur.TypePiece;

public class AnalysePlaceDisponible extends AnalysePositionPiece {

	protected List<Integer> placeDispoStandard(int[] casesDispoBordPlateau, Plateau plateau, Piece piece) {

		int[] placeDispo = new int[8];

		int coordPiece = piece.getCoordonnee();

		int[] directions = new int[] { 8, -8, -1, 1, 7, 9, -9, -7 }; // Haut-Bas-Gauche-Droite-HG-HD-BG-BD

		List<Integer> destinationsDispo = new ArrayList<>();

		for (int i = 0; i < 8; i++) {
			placeDispo[i] = trouvePieceVoisineDirection(directions[i], casesDispoBordPlateau[i], plateau, piece);
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 1; j <= placeDispo[i]; j++) {
				destinationsDispo.add(coordPiece + directions[i] * j);
			}
		}

		return destinationsDispo;

	}

	protected List<Integer> placeDispoCavalier(int[] casesDispoBordPlateau, Plateau plateau, Piece piece) {
		// Fonction spéciale pour les mouvements du cavalier

		// mvts du cavalier : {6,10,15,17,-6,-10,-15,-17}

		List<Integer> destinationsDispo = new ArrayList<>();

		int haut = casesDispoBordPlateau[0];
		int bas = casesDispoBordPlateau[1];
		int gauche = casesDispoBordPlateau[2];
		int droite = casesDispoBordPlateau[3];

		int coordCav = piece.getCoordonnee();

		// On ajoute toutes les destinations qui rentrent dans le plateau

		if (haut >= 2 && gauche >= 1) {
			destinationsDispo.add(coordCav + 15);
		}
		if (haut >= 2 && droite >= 1) {
			destinationsDispo.add(coordCav + 17);
		}
		if (haut >= 1 && gauche >= 2) {
			destinationsDispo.add(coordCav + 6);
		}
		if (haut >= 1 && droite >= 2) {
			destinationsDispo.add(coordCav + 10);
		}
		if (bas >= 2 && gauche >= 1) {
			destinationsDispo.add(coordCav - 17);
		}
		if (bas >= 2 && droite >= 1) {
			destinationsDispo.add(coordCav - 15);
		}
		if (bas >= 1 && gauche >= 2) {
			destinationsDispo.add(coordCav - 10);
		}
		if (bas >= 1 && droite >= 2) {
			destinationsDispo.add(coordCav - 6);
		}

		// On supprime les destinations occupées par des pièces de même couleur

		List<Integer> coordASupprimer = new ArrayList<>();

		for (int coord : destinationsDispo) {

			Piece pieceVoisine = plateau.getPieceCase(coord);

			if (pieceVoisine != null && piece.getCouleur() == pieceVoisine.getCouleur()) {
				coordASupprimer.add(coord);
			}

		}

		for (int coord : coordASupprimer) {
			destinationsDispo.remove(Integer.valueOf(coord));
		}

		return destinationsDispo;

	}
	
	
	protected List<Integer> destinationsDispoGlobal(int[] casesDispoBordPlateau, boolean specifiquePiece, Plateau plateau, Piece piece) {
		
		List<Integer> destinationsDispo = new ArrayList<>();
		
		// Cas standard
		if (piece.getNom() != TypePiece.CAVALIER) {
			destinationsDispo = placeDispoStandard(casesDispoBordPlateau, plateau, piece);
		}
		
		// Si cavalier ou si on veut voir l'espace atteignable pour tout type de pièce ()
		if (piece.getNom() == TypePiece.CAVALIER || specifiquePiece == false) {
			destinationsDispo.addAll(placeDispoCavalier(casesDispoBordPlateau, plateau, piece));
		}
		
		return destinationsDispo;
		
	}

}
