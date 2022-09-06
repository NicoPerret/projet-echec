package fr.echec.classe.mouvements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import fr.echec.classe.jeu.Piece;
import fr.echec.classe.jeu.Plateau;
import fr.echec.enumerateur.CouleursPiece;
import fr.echec.enumerateur.TypePiece;

public class GestionEchec {
	
	private static boolean surMemeLigne(int coordRoi, int diffCoord) {
		return ((coordRoi + diffCoord) / 8 == coordRoi / 8);
	}
	
	public static boolean mvtEchec(Plateau plateau, Piece piece, int coup) {
		
		boolean mvtImpossibleEchec = false;
		
		// On fait une copie de plateau et de la piece
		
		Plateau plateauSimul = new Plateau();
		//plateauSimul.setPlateau(plateau.getPlateau());
		String[] s = new String[64];
		for (int i =0; i<=63;i++) {
			s[i]= plateau.getPlateau()[i];
		}
		
		List<Piece> liste = new ArrayList<>();
		for(Piece p : plateau.getPieces()) {
			Piece p1 = new Piece(p.getNom(),p.getCouleur());
			p1.setCoordonnee(p.getCoordonnee());
			p1.setNomPlateau(p.getNomPlateau());
			liste.add(p1);
		}
		
		plateauSimul.setPieces(liste);
		
		Piece pieceSimul = plateauSimul.getPieceCase(piece.getCoordonnee());
		
		// On simule le mouvement possible dans la copie de plateau.
		
		Deplacement dpltClasse = new Deplacement();
		if (coup != 0) {
			dpltClasse.bougerPiece(pieceSimul, pieceSimul.getCoordonnee()+coup, plateauSimul);
		}
		
		// On repère le roi de la même couleur de la pièce.
		
		CouleursPiece couleurPiece = pieceSimul.getCouleur();
		Piece roi = null;
		if (couleurPiece== CouleursPiece.BLANC) {roi = plateauSimul.getByNomPlateau("rb ");}
		else {roi = plateauSimul.getByNomPlateau("rn ");}
		
		// On repère toutes les cases où une pièce adverse pourrait atteindre le roi.
		
		int[] roiCasesDispoBordPlateau = AnalysePositionPiece.trouveCasesDispoBordPlateau(roi);
		
		List<Integer> zoneDeRisque = 
				AnalysePlaceDisponible.destinationsDispoGlobal(roiCasesDispoBordPlateau, true ,plateauSimul, roi);
		
		// On fait la liste des pièces adverses effectivement présentes sur ces cases.
		
		List<Piece> listePiecesAdversesDangereuses = new ArrayList<>();
		
		for (int destination : zoneDeRisque) {
			Piece pieceAdverse = plateauSimul.getPieceCase(destination);
			if (pieceAdverse != null) {
				listePiecesAdversesDangereuses.add(pieceAdverse);
			}
		}
		
		// Pour chaque pièce adverse trouvée, on regarde si elle peut effectivement atteindre le roi selon son type :
		
		Map<TypePiece, int[]> coupsTypePiece = AnalyseCoupsReglementaires.createCoupsTypePiece();
		
		for (Piece pieceAdverse : listePiecesAdversesDangereuses) {
			
			int coordRoi = roi.getCoordonnee();
			int coordPieceAdverse = pieceAdverse.getCoordonnee(); 
			int diffCoord = coordRoi - coordPieceAdverse;
			
			switch (pieceAdverse.getNom()) {
			
			case PION :
				

				if (pieceAdverse.getCouleur()== CouleursPiece.BLANC) {
					if ((diffCoord == 7 && !(surMemeLigne(coordRoi, diffCoord))) || diffCoord == 9) {
						mvtImpossibleEchec = true;	
					}	
				} else {
					if ((diffCoord == -7 && !(surMemeLigne(coordRoi, diffCoord))) || diffCoord == -9) {
						mvtImpossibleEchec = true;	
					}
				}
					
				break;
				
			case TOUR :
				if(Arrays.stream(coupsTypePiece.get(TypePiece.TOUR)).anyMatch(i -> i == diffCoord)) {
					if (surMemeLigne(coordRoi, diffCoord)) {
						mvtImpossibleEchec = true;
					}
					else if (diffCoord >= 8) {
						mvtImpossibleEchec = true;
					}
				}
				break;
				
			case CAVALIER :
				if(Arrays.stream(coupsTypePiece.get(TypePiece.CAVALIER)).anyMatch(i -> i == diffCoord)) {
					if (!(surMemeLigne(coordRoi, diffCoord))) {
						mvtImpossibleEchec = true;
					}
				}
				break;
				
			case FOU :
				if(Arrays.stream(coupsTypePiece.get(TypePiece.FOU)).anyMatch(i -> i == diffCoord)) {
					if (!(surMemeLigne(coordRoi, diffCoord))) {
						mvtImpossibleEchec = true;
					}
				}
				break;
				
			case DAME :
				if(Arrays.stream(coupsTypePiece.get(TypePiece.DAME)).anyMatch(i -> i == diffCoord)) {
					mvtImpossibleEchec = true;
				}
				break;
				
			case ROI :
				if(Arrays.stream(coupsTypePiece.get(TypePiece.ROI)).anyMatch(i -> i == diffCoord)) {
					if (diffCoord == 1  || diffCoord != -1) {
						mvtImpossibleEchec = true;
					}
					else if (!(surMemeLigne(coordRoi, diffCoord))) {
						mvtImpossibleEchec = true;
					}
				}
				break;
			}
			
			// Si une pièce peut atteindre le roi : on s'arrête et on renvoie que le déplacement est impossible
			if (mvtImpossibleEchec) {
				break;
			}
		}
		
		return mvtImpossibleEchec; 
		
	}
	
	public static boolean isEchec(Plateau plateau, CouleursPiece couleur) {
		// Renvoie si le joueur actuel est en échec
		
		// On repère la position du roi
		Piece roi = null;
		
		if (couleur == CouleursPiece.NOIR) {
			roi = plateau.getByNomPlateau("rb ");
			}
		else {
			roi = plateau.getByNomPlateau("rn ");
		}
		
		// On applique la fonction impossibleEchec sur le roi avec un déplacement nul
		// ça permet de voir si le roi à sa position actuelle est en échec
		
		boolean enEchec = mvtEchec(plateau, roi, 0);
		
		return enEchec;
		
	}
	
	public static boolean casePlateauEchec(Plateau plateau, int casePlateau, CouleursPiece couleur) {
		// Renvoie si une case du plateau donnée met le roi du joueur en échec
		
		Piece roi = null;
		
		if (couleur == CouleursPiece.BLANC) {
			roi = plateau.getByNomPlateau("rb ");
			}
		else {
			roi = plateau.getByNomPlateau("rn ");
		}
		System.out.println(casePlateau);
		System.out.println(roi.getCoordonnee());
		boolean enEchec = mvtEchec(plateau, roi, casePlateau - roi.getCoordonnee());
		
		return enEchec;
		
	}

}
