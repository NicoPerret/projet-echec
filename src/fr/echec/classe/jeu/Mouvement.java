package fr.echec.classe.jeu;

import java.util.*;

import fr.echec.enumerateur.TypePiece;

public class Mouvement {
	
	// Reste a faire :
	//   - pion : déplacement selon la couleur pour le cavalier
	//   - pion : coups particuliers (2 cases, prise, ...)
	//   - gérer la prise en général selon la couleur de la pièce adverse
	//   - gestion du Roque
	//   - vérifier qu'un coup est possible par rapport à l'échec au roi
	
	
	private Piece piece;
	
	private Plateau plateau;
	
	// Liste des coups possibles par pieces et par couleur
	private Map<TypePiece, int[]> coupsTypePiece = new HashMap<TypePiece, int[]>();
	
	
	
	//  ======================  Methodes ====================
	
	// Constructeur
	public Mouvement(Plateau plateau) {
		createCoupsTypePiece();
		this.plateau = plateau;
	}
	
	private Piece trouvePieceCoord(int coord) {
		
		// Pour une case donnée regarde si une pièce est présente
		
		List<Piece> pieces = plateau.Pieces; // l'attribut est public
		for (Piece piece : pieces) {
			if (piece.getCoordonnee() == coord) {
				return piece;
			}
		}
		
		return null;
		
	}
	
	
	public void createCoupsTypePiece() {
		
		this.coupsTypePiece.put(TypePiece.PION, new int[] {8, -8}); // devant // ajouter 2cases au debut + prise en diag + prise en passant
		this.coupsTypePiece.put(TypePiece.TOUR, new int[] {8,16,24,32,40,48,56,
												   -8,-16,-24,-32,-40,-48,-56,
												   1,2,3,4,5,6,7,
												   -1,-2,-3,-4,-5,-6,-7});
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
		
		// Renvoie le nombre de cases entre la piece et le bord du plateau
		// [haut, bas, gauche, droite, HG, HD, BG, BD]
		
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
		// la piece voisine trouvée est incluse dans le nombre de cases si elle est de couleur opposée
		
		int coordPiece = this.piece.getCoordonnee();
		int casesPieceVoisine = 0;
		
		for (int i=1; i <= casesBordPlateau; i++) {
			
			if (this.plateau.getPlateau()[coordPiece + i*direction] != "   ") {
				
				Piece pieceVoisine = trouvePieceCoord(coordPiece + i*direction);
				
				if (pieceVoisine.isCouleur() == this.piece.isCouleur()) {
					break;
				}
				else {
					casesPieceVoisine ++;
					break;
				}
				
			}
			
			casesPieceVoisine ++;
		}
		
		return casesPieceVoisine;
		
	}
		
	
	private List<Integer> placeDispoCavalier(int[] casesDispoBordPlateau, List<Integer> destinationsDispo) {
		// Fonction spéciale pour les mouvements du cavalier
		
		// mvts du cavalier : {6,10,15,17,-6,-10,-15,-17}
		
		int haut = casesDispoBordPlateau[0];
		int bas = casesDispoBordPlateau[1];
		int gauche = casesDispoBordPlateau[2];
		int droite = casesDispoBordPlateau[3];
		
		if (haut >= 2) {
			if (gauche >= 1) {
				destinationsDispo.add(15);
			}
			if (droite >= 1) {
				destinationsDispo.add(17);
			}
		}
			
		if (haut >= 1) {
			if (gauche >= 2) {
				destinationsDispo.add(6);
			}
			if (droite >= 1) {
				destinationsDispo.add(10);
			} 
		}
		
		if (bas >= 2) {
			if (gauche >= 1) {
				destinationsDispo.add(-17);
			}
			if (droite >= 1) {
				destinationsDispo.add(-15);
			} 
		}
			
		if (bas >= 1) {
			if (gauche >= 2) {
				destinationsDispo.add(-10);
			}
			if (droite >= 1) {
				destinationsDispo.add(-6);
			} 
		}
		
		return destinationsDispo;
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
		// à l'exception du cavalier
		
		if (piece.getNom() == TypePiece.CAVALIER) {
			placeDispoCavalier(casesDispoBordPlateau, destinationsDispo);
		}
		
		else {
			for (int i = 0; i < 8; i++) {
				for (int j = 1; j <= placeDispo[i]; j++) {
					destinationsDispo.add(coordPiece + directions[i]*placeDispo[j]);
				}
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
//}

//public static void Promotion() {
//	if piece(coordonnées) se déplace sur coordonnées2 (A1-H1 ou A8-H8) { 
//      demander au joueur quelle pièce il veut; pièce-> piece choisie

}
