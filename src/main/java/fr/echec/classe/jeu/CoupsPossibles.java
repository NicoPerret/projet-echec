package fr.echec.classe.jeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.echec.enumerateur.CouleursPiece;
import fr.echec.enumerateur.TypePiece;

public class CoupsPossibles {
	
	// Reste à faire :
	//   - pion : prise en passant
	//   - gestion du Roque

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
		
		this.coupsTypePiece.put(TypePiece.PION, new int[] {7, 8, 9, 16, -7, -8, -9, -16});
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
				
				if (pieceVoisine.getCouleur() != piece.getCouleur()) {
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
			
			if (pieceVoisine != null && piece.getCouleur() == pieceVoisine.getCouleur()) {
				coordASupprimer.add(coord);
			}
			
		}
		
		for (int coord : coordASupprimer) {
			destinationsDispo.remove(Integer.valueOf(coord));
		}
		
		return destinationsDispo;
		
	}
	
	
	private void filtreDeplacementsPion(List<Integer> coupsReglementaires, int[] casesDispoBordPlateau, Plateau plateau, Piece piece) {
		
		// Gestion de la couleur du pion et de l'avancement 1 / 2 cases
		
		// Pas prise en face, prise en diagonale
		
		int coord = piece.getCoordonnee();
		CouleursPiece couleur = piece.getCouleur();
		
		if (couleur == CouleursPiece.BLANC) {
			coupsReglementaires.remove(Integer.valueOf(-7)); // coups couleur opposée
			coupsReglementaires.remove(Integer.valueOf(-8));
			coupsReglementaires.remove(Integer.valueOf(-9));
			coupsReglementaires.remove(Integer.valueOf(-16));
			// deux cases et pas prise en face
			if (coord / 8 != 1 || (coord / 8 == 1 && plateau.getPieceCase(coord+16) != null)) {
				coupsReglementaires.remove(Integer.valueOf(16)); 
			} 
			if (casesDispoBordPlateau[0] >= 1 && plateau.getPieceCase(coord+8) != null) {
				coupsReglementaires.remove(Integer.valueOf(8));
			}
			// Prise en diagonale possible
			if (casesDispoBordPlateau[4] == 0 || plateau.getPieceCase(coord+7) == null) {
				coupsReglementaires.remove(Integer.valueOf(7));
			}
			if (casesDispoBordPlateau[5] == 0 || plateau.getPieceCase(coord+9) == null) {
				coupsReglementaires.remove(Integer.valueOf(9));
			}
			
		}
		else {
			coupsReglementaires.remove(Integer.valueOf(7));
			coupsReglementaires.remove(Integer.valueOf(8));
			coupsReglementaires.remove(Integer.valueOf(9));
			coupsReglementaires.remove(Integer.valueOf(16));
			if (coord / 8 != 6 || (coord / 8 == 6 && plateau.getPieceCase(coord-16) != null)) {
				coupsReglementaires.remove(Integer.valueOf(-16)); 
			}
			if (casesDispoBordPlateau[1] >= 1 && plateau.getPieceCase(coord-8) != null) {
				coupsReglementaires.remove(Integer.valueOf(-8));
			}
			// Prise en diagonale possible
			if (casesDispoBordPlateau[7] == 0 || plateau.getPieceCase(coord-7) == null) {
				coupsReglementaires.remove(Integer.valueOf(-7));
			}
			if (casesDispoBordPlateau[6] == 0 || plateau.getPieceCase(coord-9) == null) {
				coupsReglementaires.remove(Integer.valueOf(-9));
			}
		}
		
	}
	
	// ==============================================================================
	// ============================ GESTION ECHEC ===================================
	// ==============================================================================

	
	private boolean surMemeLigne(int coordRoi, int diffCoord) {
		return ((coordRoi + diffCoord) / 8 == coordRoi / 8);
	}
	
	private boolean impossibleEchec(Plateau plateau, Piece piece, int coup) {
		
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
			dpltClasse.deplacement(pieceSimul, coup, plateauSimul);
		}
		
		// On repère le roi de la même couleur de la pièce.
		
		CouleursPiece couleurPiece = pieceSimul.getCouleur();
		Piece roi = null;
		if (couleurPiece== CouleursPiece.BLANC) {roi = plateauSimul.getByNomPlateau("rb ");}
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
				if(Arrays.stream(this.coupsTypePiece.get(TypePiece.TOUR)).anyMatch(i -> i == diffCoord)) {
					if (surMemeLigne(coordRoi, diffCoord)) {
						mvtImpossibleEchec = true;
					}
					else if (diffCoord >= 8) {
						mvtImpossibleEchec = true;
					}
				}
				break;
				
			case CAVALIER :
				if(Arrays.stream(this.coupsTypePiece.get(TypePiece.CAVALIER)).anyMatch(i -> i == diffCoord)) {
					if (!(surMemeLigne(coordRoi, diffCoord))) {
						mvtImpossibleEchec = true;
					}
				}
				break;
				
			case FOU :
				if(Arrays.stream(this.coupsTypePiece.get(TypePiece.FOU)).anyMatch(i -> i == diffCoord)) {
					if (!(surMemeLigne(coordRoi, diffCoord))) {
						mvtImpossibleEchec = true;
					}
				}
				break;
				
			case DAME :
				if(Arrays.stream(this.coupsTypePiece.get(TypePiece.DAME)).anyMatch(i -> i == diffCoord)) {
					mvtImpossibleEchec = true;
				}
				break;
				
			case ROI :
				if(Arrays.stream(this.coupsTypePiece.get(TypePiece.ROI)).anyMatch(i -> i == diffCoord)) {
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
	
	public boolean isEchec(Plateau plateau, CouleursPiece couleur) {
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
		System.out.println("coordRoi " +roi.getCoordonnee());
		boolean enEchec = impossibleEchec(plateau, roi, 0);
		
		return enEchec;
		
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
	

	public List<Integer> sousfctFiltres(List<Integer> coupsReglementaires, int[] casesDispoBordPlateau, Plateau plateau, Piece piece) {
		
		List<Integer> coupsReglementairesModif = coupsReglementaires;
		
		if (piece.getNom() == TypePiece.PION) {
			filtreDeplacementsPion(coupsReglementaires, casesDispoBordPlateau, plateau, piece);
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
		coupsReglementaires = sousfctFiltres(coupsReglementaires, casesDispoBordPlateau, plateau, piece);
		 
		
		// On ajoute toutes les destinations dispos pour le type de la piece qui rentrent dans les cases dispos. 
		// On vérifie aussi pour chaque destination dispo que ça ne met pas le roi en échec
		List<Integer> destinationsJouables = new ArrayList<>();
				 
		for (int deplacement : coupsReglementaires) {
			for (int destination : destinationsDispo) {
				if (coordPiece + deplacement == destination && impossibleEchec(plateau, piece, destination) == false) {
					destinationsJouables.add(coordPiece + deplacement);
				}
			}
		}
					
		return destinationsJouables;
		 	
	}



}
