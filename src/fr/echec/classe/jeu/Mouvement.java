package fr.echec.classe.jeu;

import java.util.*;
import java.util.List;
import java.util.Map;

import fr.echec.enumerateur.TypePiece;

public class Mouvement {
	
	private Piece piece;
	
	private Plateau plateau;
	
	// Contient le nombre de cases entre la piece et le bord du plateau
	// [haut, bas, gauche, droite, HG, HD, BG, BD]
	// private int[] casesDispoBordPlateau = new int[8];
	
	// Liste des coups possibles par pieces et par couleur
	private Map<TypePiece, int[]> coupsTypePiece = new HashMap<TypePiece, int[]>();
	// pour rajouter une une clé : map.put("Key", "Value");
	// pour récupérer une valeur : map.get("Key")
	
	
	// Methodes
	
	private Piece trouvePieceCoord(int coord) {
		List<Piece> pieces = plateau.getPieces(); // fonction a rajouter ?
		for (Piece piece : pieces) {
			if (piece.getCoordonnee() == coord) {
				return piece;
			}
		}
		
		System.out.println("ATTENTION PAS DE PIECE ICI !");
		
	}
	
	
	public void createCoupsTypePiece() {
		
		this.coupsTypePiece.put(TypePiece.PION, new int[] {8, -8}); // devant // ajouter 2cases au debut + prise en diag + prise en passant
		this.coupsTypePiece.put(TypePiece.TOUR, new int[] {8,16,24,32,40,48,56, // un deplt en trop
												   -8,-16,-24,-32,-40,-48,-56,
												   1,2,3,4,5,6,7,
												   -1,-2,-3,-4,-5,-6,-7}); // vertical (+8 ou -8) ou horizontal
		this.coupsTypePiece.put(TypePiece.CAVALIER, new int[] {6,10,15,17,-6,-10,-15,-17});
		this.coupsTypePiece.put(TypePiece.FOU, new int[] {9,18,27,36,45,54,63,
												  -9,-18,-27,-36,-45,-54,-63,
												  7,14,21,28,35,42,49,
												  -7,-14,-21,-28,-35,-42,-49});
		this.coupsTypePiece.put(TypePiece.DAME, new int[] {8,16,24,32,40,48,56,
				   								   -8,-16,-24,-32,-40,-48,-56,
				   								   1,2,3,4,5,6,7,
				   								   -1,-2,-3,-4,-5,-6,-7,
				   								   9,18,27,36,45,54,63,
												  -9,-18,-27,-36,-45,-54,-63,
												   7,14,21,28,35,42,49,
												  -7,-14,-21,-28,-35,-42,-49});
		this.coupsTypePiece.put(TypePiece.ROI, new int[] {1,7,8,9,-1,-7,-8,-9});
		
	}
	
	private int[] trouveCasesDispoBordPlateau() {
		
		int[] casesDispoBordPlateau = new int[8];
		
		int coordPiece = this.piece.getCoordonnee();
		// haut
		casesDispoBordPlateau[0] = 7 - coordPiece / 8 ;
		// bas
		casesDispoBordPlateau[1] = 7 - coordPiece / 8;
		// gauche
		casesDispoBordPlateau[2] = 7 - coordPiece % 8;
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
	
	private int trouvePieceVoisineDirection(int direction, int casesBordPlateau) {

		// renvoie le nombre de cases jusqu'a la premiere piece trouvee ou par defaut jusqu au bord
		
		int coordPiece = this.piece.getCoordonnee();
		int casesPieceVoisine = 0;
		
		for (int i=1; i <= casesBordPlateau; i++) {
			if (this.plateau.getPlateau()[coordPiece + i*direction] != "   ") {
				break;
			}
			else {
				casesPieceVoisine ++;
			}
		}
		
		return casesPieceVoisine;
		
		// !!!!!!!!!!!!!!!! ATTENTION !!!!!!!!!!!!!! La gestion de la couleur n'est pas prise en compte !!!
	}
	
	public List<Integer> trouveDestinationsPossibles() {
		
		int coordPiece = this.piece.getCoordonnee();
		
		int[] casesDispoBordPlateau = trouveCasesDispoBordPlateau();
		
		int[] directions = new int[] {8,-8,-1,1,7,9,-9,-7}; // Haut-Bas-Gauche-Droite-HG-HD-BG-BD
		
		List<Integer> destinationsDispo = new ArrayList<>(); // sans tenir compte du type de la piece
		List<Integer> destinationsJouables = new ArrayList<>(); // en tenant compte du type de la piece
		
		int[] placeDispo = new int[8];
		
		
		// On trouve le nombre de cases entre la piece et le bord du plateau dans chaque direction
		
		this.trouveCasesDispoBordPlateau();
		
		// On trouve le nombre de cases dispo en prenant compte les autres pieces
		
		for (int i = 0; i < 8; i++) {
			placeDispo[i] = this.trouvePieceVoisineDirection(directions[i], casesDispoBordPlateau[i]);
		}
		
		// On determine l'ensemble des destinations dispos sans tenir compte du type de la piece
		
		for (int i = 0; i < 8; i++) {
			for (int j = 1; j <= placeDispo[i]; j++) {
				destinationsDispo.add(coordPiece + directions[i]*placeDispo[j]);
			}
		}
		
		// On ajoute toutes les destinations dispos pour le type de la piece qui rentrent dans les cases dispos. 
		
		for (int deplacement : this.coupsTypePiece.get(piece.getNom())) {
			for (int destination : destinationsDispo) {
				if (coordPiece + deplacement == destination) {
					destinationsJouables.add(coordPiece + deplacement);
				}
			}
		}
		
		return destinationsJouables;
		
		
	}
//	// Méthodes

	

	
//
////sous-fonction "Déplacement impossible"
//	public void DeplacementImpossible() {
//		for (Piece p : Pieces) {
//			if (p.coordonnee == coordonnee||p.couleur==couleur) {
//				System.out.println("Déplacement impossible");
//			}
//		}
//
//		// sous-fonction "Capture"
//
//		for (Piece p : Pieces) {
//			if (p.coordonnee == coordonnee|| p.couleur!=couleur) {
//				p.Capture();
//			}
//			p.coordonnee = coordonnee;
//		}
//	}
//
//	public void Capture() {
//		Pieces.remove(this);
//	}
//
////public static void Capture() {
//
//
////sous-fonction "Promotion"
//	public void Promotion() {
//		for (Piece p : Pieces) {
//			if (p.coordonnee == 56 || p.coordonnee == 57 || p.coordonnee == 58 || p.coordonnee == 59
//					|| p.coordonnee == 60 || p.coordonnee == 61 || p.coordonnee == 62 || p.coordonnee == 63
//					|| p.coordonnee == 0 || p.coordonnee == 1 || p.coordonnee == 2 || p.coordonnee == 3
//					|| p.coordonnee == 4 || p.coordonnee == 5 || p.coordonnee == 6 || p.coordonnee == 7) {
//
//				System.out.println("Quel pièce voulez-vous comme promotion?");
//				p.nom = read();
//			}
//		}
//	}
}

//public static void Promotion() {
//	if piece(coordonnées) se déplace sur coordonnées2 (A1-H1 ou A8-H8) { 
//      demander au joueur quelle pièce il veut; pièce-> piece choisie

}
