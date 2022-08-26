package fr.echec.classe.jeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	

	// Liste des coups possibles par pieces et par couleur
	private Map<TypePiece, int[]> coupsTypePiece = new HashMap<TypePiece, int[]>();

	
	//  ======================  getters - setters ====================

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
												   14,21,28,35,42,49,
												  -14,-21,-28,-35,-42,-49});
		this.coupsTypePiece.put(TypePiece.ROI, new int[] {1,7,8,9,-1,-7,-8,-9});
		
	}
	
	// ==================== Sous-fonctions générales ======================
	
	private int[] trouveCasesDispoBordPlateau(Piece piece) {
		
		// Renvoie le nombre de cases entre la piece et le bord du plateau
		// [haut, bas, gauche, droite, HG, HD, BG, BD]
		
		int[] casesDispoBordPlateau = new int[8];
		
		int coordPiece = piece.getCoordonnee();
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
	
	private int trouvePieceVoisineDirection(int direction, int casesBordPlateau, Plateau plateau, Piece piece) {

		// renvoie le nombre de cases jusqu'à la premiere pièce trouvée ou par défaut jusqu'au bord
		// la pièce voisine trouvée est incluse dans le nombre de cases si elle est de couleur opposée
		
		int coordPiece = piece.getCoordonnee();
		int casesPieceVoisine = 0;
		
		for (int i=1; i <= casesBordPlateau; i++) {
			
			Piece pieceVoisine = plateau.getPieceCase(coordPiece + i*direction);
			
			if (pieceVoisine != null) {
				
				if (pieceVoisine.isCouleur() != piece.isCouleur()) {
					casesPieceVoisine ++;
				}
				
				break;
			}
			
			casesPieceVoisine ++;
		}
		
		return casesPieceVoisine;
		
	}

	// ================================= Cas particuliers ======================================
	
	private List<Integer> placeDispoCavalier(int[] casesDispoBordPlateau, Plateau plateau, Piece piece) {
		// Fonction spéciale pour les mouvements du cavalier
		
		// mvts du cavalier : {6,10,15,17,-6,-10,-15,-17}
		
		List<Integer> destinationsDispo = new ArrayList<>();
		
		int haut = casesDispoBordPlateau[0];
		int bas = casesDispoBordPlateau[1];
		int gauche = casesDispoBordPlateau[2];
		int droite = casesDispoBordPlateau[3];
		
		int coordCav = piece.getCoordonnee();
		
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
			
			if (pieceVoisine != null && piece.isCouleur() == pieceVoisine.isCouleur()) {
				coordASupprimer.add(coord);
			}
			
		}
		
		for (int coord : coordASupprimer) {
			destinationsDispo.remove(Integer.valueOf(coord));
		}
		
		return destinationsDispo;
		
	}
	
	
	private void filtreDeplacementsPion(List<Integer> coupsReglementaires, Piece piece) {
		
		// Gestion de la couleur du pion et de l'avancement 1 / 2 cases
		
		int coord = piece.getCoordonnee();
		String couleur = piece.isCouleur();
		
		if (couleur.equals("Blanc")) {
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
	
	private boolean surMemeLigne(int coord, int coup) {
		return ((coord + 7) / 8 == coord / 8);
	}
	
	private boolean ImpossibleEchec(Plateau plateau, Piece piece, int coup) {
		
		// à mettre vers la fin
		// On fait une copie de plateau
		// On simule le mouvement possible dans la copie de plateau.
		
		// On repère la position du roi de la même couleur de la pièce.
		
		// On repère toutes les pièces adverses susceptible de le menacer :
		//    - Faire placeDispoBordPlateau sur le roi
		// 	  - trouver les destination dispos pour le roi
		//	  - parmis les destinations dispos, trouver les cases contenant des pièces adverses
		
		// Pour chaque pièce adverse trouvée, on regarde si elle peut atteindre le roi :
		//	  - On peut juste faire des conditions par type de pièce sur la difference de coordonnee:
		//	  		- TOUR : diff inclus dans coupsTypePiece(cavalier) et on change PAS de ligne
		//			- CAVALIER : diff inclus dans coupsTypePiece(cavalier) et on change de ligne
		//			- FOU : diff inclus dans coupsTypePiece(fou) et on change de ligne
		//			- Roi : diff inclus dans coupsTypePiece(fou) et si abs(diff) > 1 on change de ligne
		//			- Dame : diff inclus dans coupsTypePiece(dame)
		//			- Pion : si noir diff = -7 ou -9 et on change de ligne; si blanc 7 ou 9 et on change de ligne
		
		// 	  - changer de ligne : (coordPieceEnnemie + dplt) / 8 != coordPieceEnnemie / 8
		//    		-> Applicable si abs(dplt) <= 7 
		
		//
		// Si une pièce peut atteindre le roi :
		// 	  - on s'arrête et on renvoie que le déplacement est impossible
		// Si on finit la boucle sans que le roi soit menacé :
		//	  - on renvoie que le déplacement est possible
		
		// --------------------------------------------------------
		
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
			Piece p1 = new Piece(p.getNom(),p.isCouleur());
			p1.setCoordonnee(p.getCoordonnee());
			p1.setNomPlateau(p.getNomPlateau());
			liste.add(p1);
		}
		
		plateauSimul.setPieces(liste);
		
		Piece pieceSimul = plateauSimul.getPieceCase(piece.getCoordonnee());
		
		// On simule le mouvement possible dans la copie de plateau.
		
		Deplacement dpltClasse = new Deplacement();
		dpltClasse.deplacement(pieceSimul, coup, plateauSimul);
		
		// On repère le roi de la même couleur de la pièce.
		
		String couleurPiece = pieceSimul.isCouleur();
		Piece roi = null;
		if (couleurPiece.equals("Blanc")) {roi = plateauSimul.getByNomPlateau("rb ");}
		else {roi = plateauSimul.getByNomPlateau("rn ");}
		
		// On repère toutes les cases où une pièce adverse pourrait atteindre le roi.
		
		int[] roiCasesDispoBordPlateau = trouveCasesDispoBordPlateau(roi);
		
		List<Integer> destinationsMenace = 
				sousfctDestinationsDispo(roiCasesDispoBordPlateau,plateauSimul, roi);
		
		destinationsMenace.addAll(placeDispoCavalier(roiCasesDispoBordPlateau,plateauSimul, roi));
		
		// On fait la liste des pièces adverses effectivement présentes sur ces cases.
		
		List<Piece> listePiecesMenace = new ArrayList<>();
		
		for (int destination : destinationsMenace) {
			Piece pieceAdverse = plateauSimul.getPieceCase(destination);
			if (pieceAdverse != null) {
				listePiecesMenace.add(pieceAdverse);
			}
		}
		
		// Pour chaque pièce adverse trouvée, on regarde si elle peut effectivement atteindre le roi selon son type :
		
		for (Piece pieceAdverse : listePiecesMenace) {
			System.out.println(pieceAdverse.getCoordonnee());
		}

		for (Piece pieceAdverse : listePiecesMenace) {
			
			int coordPiece = roi.getCoordonnee();
			int coordPieceAdverse = pieceAdverse.getCoordonnee(); 
			int diffCoord = coordPiece - coordPieceAdverse;
			
			switch (pieceAdverse.getNom()) {
			
			case PION :
				
				if (pieceAdverse.isCouleur().equals("Blanc")) {
					if ((diffCoord == 7 && !(surMemeLigne(coordPieceAdverse, 7))) || diffCoord == 9) {
						mvtImpossibleEchec = true;	
					}	
				} else {
					if ((diffCoord == -7 && !(surMemeLigne(coordPieceAdverse, -7))) || diffCoord == -9) {
						mvtImpossibleEchec = true;	
					}
				}
					
				break;
				
			case TOUR :
				if(Arrays.stream(this.coupsTypePiece.get(TypePiece.TOUR)).anyMatch(i -> i == diffCoord)) {
					if (surMemeLigne(coordPieceAdverse, diffCoord)) {
						mvtImpossibleEchec = true;
					}
				}
				break;
				
			case CAVALIER :
				if(Arrays.stream(this.coupsTypePiece.get(TypePiece.CAVALIER)).anyMatch(i -> i == diffCoord)) {
					if (!(surMemeLigne(coordPieceAdverse, diffCoord))) {
						mvtImpossibleEchec = true;
					}
				}
				break;
				
			case FOU :
				if(Arrays.stream(this.coupsTypePiece.get(TypePiece.FOU)).anyMatch(i -> i == diffCoord)) {
					if (!(surMemeLigne(coordPieceAdverse, diffCoord))) {
						mvtImpossibleEchec = true;
					}
				}
				break;
				
			case DAME :
				if(Arrays.stream(this.coupsTypePiece.get(TypePiece.CAVALIER)).anyMatch(i -> i == diffCoord)) {
					mvtImpossibleEchec = true;
				}
				break;
				
			case ROI :
				if(Arrays.stream(this.coupsTypePiece.get(TypePiece.CAVALIER)).anyMatch(i -> i == diffCoord)) {
					if (diffCoord == 1  || diffCoord != -1) {
						mvtImpossibleEchec = true;
					}
					else if (!(surMemeLigne(coordPieceAdverse, diffCoord))) {
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
	
	
	
	
	// ==============================================================================
	// ========================= FONCTION PRINCIPALE ================================
	// ==============================================================================
	
	public List<Integer> sousfctCoupsReglementaires(Piece piece) {
		List<Integer> coupsReglementaires = new ArrayList<>(); // Liste des coups réglementaires par pièce
		for (int coup : this.coupsTypePiece.get(piece.getNom())) {
			coupsReglementaires.add(coup);
		}
		
		return coupsReglementaires;	
	}
	
	public List<Integer> sousfctDestinationsDispo(int[] casesDispoBordPlateau, Plateau plateau, Piece piece) {
		
		int[] placeDispo = new int[8];
		
		int coordPiece = piece.getCoordonnee();
		
		int[] directions = new int[] {8,-8,-1,1,7,9,-9,-7}; // Haut-Bas-Gauche-Droite-HG-HD-BG-BD
		
		List<Integer> destinationsDispo = new ArrayList<>();
		
		for (int i = 0; i < 8; i++) {
			placeDispo[i] = this.trouvePieceVoisineDirection(directions[i], casesDispoBordPlateau[i], plateau, piece);
		}
		
		if (piece.getNom() == TypePiece.CAVALIER) {
			destinationsDispo = placeDispoCavalier(casesDispoBordPlateau, plateau, piece);
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
	

	public List<Integer> sousfctFiltres(List<Integer> coupsReglementaires, Piece piece) {
		
		List<Integer> coupsReglementairesModif = coupsReglementaires;
		
		if (piece.getNom() == TypePiece.PION) {
			filtreDeplacementsPion(coupsReglementaires, piece);
		}
		
		return coupsReglementairesModif;
		
	}
	
	
	public List<Integer> trouveDestinationsPossibles(Plateau plateau, Piece piece) { //service ?
		 
		int coordPiece = piece.getCoordonnee();
		
		// Liste des coups réglementaires par pièce
		List<Integer> coupsReglementaires = sousfctCoupsReglementaires(piece);
		 
		// Cases libres autour de la pièce
		int[] casesDispoBordPlateau = trouveCasesDispoBordPlateau(piece);
		 
		// Place disponible sans tenir compte du type de la piece
		List<Integer> destinationsDispo = 
				sousfctDestinationsDispo(casesDispoBordPlateau,plateau, piece); 
		
		// Application de cas particuliers (restriction mvt des pions)
		coupsReglementaires = sousfctFiltres(coupsReglementaires, piece);
		 
		
		// On ajoute toutes les destinations dispos pour le type de la piece qui rentrent dans les cases dispos. 
		// On vérifie aussi pour chaque destination dispo que ça ne met pas le roi en échec
		List<Integer> destinationsJouables = new ArrayList<>();
				 
		for (int deplacement : coupsReglementaires) {
			for (int destination : destinationsDispo) {
				if (coordPiece + deplacement == destination && ImpossibleEchec(plateau, piece, destination) == false) {
					destinationsJouables.add(coordPiece + deplacement);
				}
			}
		}
					
		return destinationsJouables;
		 	
	}



}
