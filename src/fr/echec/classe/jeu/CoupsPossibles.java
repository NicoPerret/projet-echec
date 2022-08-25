package fr.echec.classe.jeu;

import java.util.*;

import fr.echec.enumerateur.TypePiece;

public class CoupsPossibles {
	
	// Reste à faire :
	//   - pion : prise en passant, prise en diagonale, pas prise en face
	//   - gestion du Roque
	//   - vérifier qu'un coup est possible par rapport à l'échec au roi
	
	// Exemple d'utilisation :
	//
	// Plateau p = new Plateau("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
	// System.out.println(p);
	// Mouvement mvt = new Mouvement(p);
	// mvt.setPiece(p.getPieces().get(8)); // Correspond à pn1
	// System.out.println(mvt.trouveDestinationsPossibles());
	// mvt.setPiece(p.getPieces().get(1)); // Correspond à cn1
	// System.out.println(mvt.trouveDestinationsPossibles());
	
	
	private Piece piece;

	// Liste des coups possibles par pieces et par couleur
	private Map<TypePiece, int[]> coupsTypePiece = new HashMap<TypePiece, int[]>();

	
	//  ======================  getters - setters ====================
	
	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public Map<TypePiece, int[]> getCoupsTypePiece() {
		return coupsTypePiece;
	}

	public void setCoupsTypePiece(Map<TypePiece, int[]> coupsTypePiece) {
		this.coupsTypePiece = coupsTypePiece;
	}

	// ========================= Constructeur =============================
	
	public CoupsPossibles() {
		createCoupsTypePiece();
		
	}
	
	// ============= Définition des déplacements par pièce ==================
	
	public void createCoupsTypePiece() { // coup reglementaire par type de pieces
		
		this.coupsTypePiece.put(TypePiece.PION, new int[] {8, 16, -8, -16});
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
	
	// ==================== Sous-fonctions générales ======================
	
	private int[] trouveCasesDispoBordPlateau() {
		
		// Renvoie le nombre de cases entre la piece et le bord du plateau
		// [haut, bas, gauche, droite, HG, HD, BG, BD]
		
		int[] casesDispoBordPlateau = new int[8];
		
		int coordPiece = this.piece.getCoordonnee();
		// haut
		casesDispoBordPlateau[0] = 7 - coordPiece / 8 ;
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
	
	private int trouvePieceVoisineDirection(int direction, int casesBordPlateau, Plateau plateau) {

		// renvoie le nombre de cases jusqu'à la premiere pièce trouvée ou par défaut jusqu'au bord
		// la pièce voisine trouvée est incluse dans le nombre de cases si elle est de couleur opposée
		
		int coordPiece = this.piece.getCoordonnee();
		int casesPieceVoisine = 0;
		
		for (int i=1; i <= casesBordPlateau; i++) {
			
			Piece pieceVoisine = plateau.getPieceCase(coordPiece + i*direction);
			
			if (pieceVoisine != null) {
				
				if (pieceVoisine.isCouleur() != this.piece.isCouleur()) {
					casesPieceVoisine ++;
				}
				
				break;
			}
			
			casesPieceVoisine ++;
		}
		
		return casesPieceVoisine;
		
	}

	// ================================= Cas particuliers ======================================
	
	private List<Integer> placeDispoCavalier(int[] casesDispoBordPlateau, Plateau plateau) {
		// Fonction spéciale pour les mouvements du cavalier
		
		// mvts du cavalier : {6,10,15,17,-6,-10,-15,-17}
		
		List<Integer> destinationsDispo = new ArrayList<>();
		
		int haut = casesDispoBordPlateau[0];
		int bas = casesDispoBordPlateau[1];
		int gauche = casesDispoBordPlateau[2];
		int droite = casesDispoBordPlateau[3];
		
		int coordCav = this.piece.getCoordonnee();
		
		// On ajoute toutes les destinations qui rentrent dans le plateau
		
		if (haut >= 2 && gauche >= 1) {destinationsDispo.add(coordCav + 15);}
		if (haut >= 2 && droite >= 1) {destinationsDispo.add(coordCav + 17);}
		if (haut >= 1 && gauche >= 2) {destinationsDispo.add(coordCav + 6);}
		if (haut >= 1 && droite >= 2) {destinationsDispo.add(coordCav + 10);}
		if (bas >= 2 && gauche >= 1) {destinationsDispo.add(coordCav - 17);}
		if (bas >= 2 && droite >= 1) {destinationsDispo.add(coordCav - 15);}
		if (bas >= 1 && gauche >= 2) {destinationsDispo.add(coordCav - 10);}
		if (bas >= 1 && droite >= 2) {destinationsDispo.add(coordCav - 6);}
		
		// On supprime les destinations occupées par des pièces de même couleur
		
		List<Integer> coordASupprimer = new ArrayList<>();
		
		for (int coord : destinationsDispo) {
			
			Piece pieceVoisine = plateau.getPieceCase(coord);
			
			if (pieceVoisine != null && this.piece.isCouleur() == pieceVoisine.isCouleur()) {
				coordASupprimer.add(coord);
			}
			
		}
		
		for (int coord : coordASupprimer) {
			destinationsDispo.remove(Integer.valueOf(coord));
		}
		
		return destinationsDispo;
		
	}
	
	
	private void filtreDeplacementsPion(List<Integer> coupsReglementaires) {
		
		// Gestion de la couleur du pion et de l'avancement 1 / 2 cases
		
		int coord = this.piece.getCoordonnee();
		String couleur = this.piece.isCouleur();
		
		if (couleur == "Blanc") {
			coupsReglementaires.remove(Integer.valueOf(-8));
			coupsReglementaires.remove(Integer.valueOf(-16));
			if (coord / 8 != 1) {
				coupsReglementaires.remove(Integer.valueOf(16));
			}
		}
		else {
			coupsReglementaires.remove(Integer.valueOf(8));
			coupsReglementaires.remove(Integer.valueOf(16));
			if (coord / 8 != 6) {
				coupsReglementaires.remove(Integer.valueOf(-16));
			}
		}
		
	}
	
	
	private boolean ImpossibleEchec(int coord) {
		//  on regarde pour l'ensemble des pieces adverses si 
		// 	le roi est dans la liste de deplacement possible...
		// Il faut simuler le deplacement de la piece avant ?
		// -> une idée est de faire une version modifiée de TrouvePièceCoordonnée
		
		// On change la coordonnée de la pièce à l'emplacement voulu
		// (donc la vérification est à faire à la fin)
		// On cherche la coordonnée du roi de sa couleur
		// Pour chaque pièce de couleur opposée :
		// 	- si la coordonnée du roi est inclus dans destinations jouables
		//	  on renvoie que le coup est considéré comme impossible
		//  sinon c'est bon et on effectue bien le coup
		
		// Attention à ne pas faire de boucle sur "trouveDestinationsPossibles"
		// => On a besoin d'une sous fonction...
		// Faut simplifier aussi...
		
		return false; // juste pour pas voir du rouge
		
	}	
	
	
	
	
	// ==============================================================================
	// ========================= FONCTION PRINCIPALE ================================
	// ==============================================================================
	
	public List<Integer> sousfctCoupsReglementaires() {
		List<Integer> coupsReglementaires = new ArrayList<>(); // Liste des coups réglementaires par pièce
		for (int coup : this.coupsTypePiece.get(piece.getNom())) {
			coupsReglementaires.add(coup);
		}
		
		return coupsReglementaires;	
	}
	
	public List<Integer> sousfctDestinationsDispo(int[] casesDispoBordPlateau, Plateau plateau) {
		
		int[] placeDispo = new int[8];
		
		int coordPiece = this.piece.getCoordonnee();
		
		int[] directions = new int[] {8,-8,-1,1,7,9,-9,-7}; // Haut-Bas-Gauche-Droite-HG-HD-BG-BD
		
		List<Integer> destinationsDispo = new ArrayList<>();
		
		for (int i = 0; i < 8; i++) {
			placeDispo[i] = this.trouvePieceVoisineDirection(directions[i], casesDispoBordPlateau[i], plateau);
		}
		
		if (piece.getNom() == TypePiece.CAVALIER) {
			destinationsDispo = placeDispoCavalier(casesDispoBordPlateau, plateau);
		}
		
		else {
			for (int i = 0; i < 8; i++) {
				for (int j = 1; j <= placeDispo[i]; j++) {
					destinationsDispo.add(coordPiece + directions[i]*j);
				}
			}
		}
		
		return destinationsDispo;
		
		
		
	}
	

	public List<Integer> sousfctFiltres(List<Integer> coupsReglementaires) {
		
		List<Integer> coupsReglementairesModif = coupsReglementaires;
		
		if (piece.getNom() == TypePiece.PION) {
			filtreDeplacementsPion(coupsReglementaires);
		}
		
		return coupsReglementairesModif;
		
	}
	
	
	public List<Integer> trouveDestinationsPossibles(Plateau plateau) { //service ?
		 
		int coordPiece = this.piece.getCoordonnee();
		
		// Liste des coups réglementaires par pièce
		List<Integer> coupsReglementaires = sousfctCoupsReglementaires();
		 
		// Cases libres autour de la pièce
		int[] casesDispoBordPlateau = trouveCasesDispoBordPlateau();
		 
		// Place disponible sans tenir compte du type de la piece
		List<Integer> destinationsDispo = 
				sousfctDestinationsDispo(casesDispoBordPlateau,plateau); 
		
		// Application de cas particuliers (restriction mvt des pions)
		coupsReglementaires = sousfctFiltres(coupsReglementaires);
		 
		
		// On ajoute toutes les destinations dispos pour le type de la piece qui rentrent dans les cases dispos. 
		List<Integer> destinationsJouables = new ArrayList<>();
				 
		for (int deplacement : coupsReglementaires) {
			for (int destination : destinationsDispo) {
				if (coordPiece + deplacement == destination) {
					destinationsJouables.add(coordPiece + deplacement);
					}
				}
			}
					
		return destinationsJouables;
		 
		
	}



}
