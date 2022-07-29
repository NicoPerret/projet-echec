package fr.echec.classe.jeu;

import java.util.HashMap;
import java.util.Map;

import fr.echec.enumerateur.TypePiece;

public class Mouvement {
	
	private int coordPiece;
	private TypePiece typePiece;
	private int couleurPiece;
	
	// indique l'ensemble des cases potentiellement disponibles pour la pièce sélectionnée
	//private int[] destinationsPossibles = new int[];
	
	// Contient le nombre de cases entre la piece et le bord du plateau
	// [haut, bas, gauche, droite]
	private int[] casesDispoBordPlateau = new int[4];
	
	// Contient la premiere piece voisine sur les 8 directions
	// [haut, bas, gauche, droite, HG, HD, BG, BD]
	private int[] piecesVoisines = new int[8];
	
	// Liste des coups possibles par pieces et par couleur
	private Map<String, int[]> coupsTypePiece = new HashMap<String, int[]>();
	// pour rajouter une une clé : map.put("Key", "Value");
	// pour récupérer une valeur : map.get("Key")
	
	
	// Methodes
	
	public void createCoupsTypePiece() {
		
		this.coupsTypePiece.put("pionB", new int[] {8}); // devant // ajouter 2cases au debut + prise en diag + prise en passant
		this.coupsTypePiece.put("pionN", new int[] {-8});
		this.coupsTypePiece.put("tour", new int[] {8,16,24,32,40,48,56,64, // un deplt en trop
												   -8,-16,-24,-32,-40,-48,-56,-64,
												   1,2,3,4,5,6,7,8,
												   -1,-2,-3,-4,-5,-6,-7,-8}); // vertical (+8 ou -8) ou horizontal
		this.coupsTypePiece.put("cavalier", new int[] {6,10,15,17,-6,-10,-15,-17});
		this.coupsTypePiece.put("fou", new int[] {9,18,27,36,45,54,63,
												  -9,-18,-27,-36,-45,-54,-63,
												  7,14,21,28,35,42,49,56,
												  -7,-14,-21,-28,-35,-42,-49,-56});
		this.coupsTypePiece.put("dame", new int[] {8,16,24,32,40,48,56,64,
				   								   -8,-16,-24,-32,-40,-48,-56,-64,
				   								   1,2,3,4,5,6,7,8,
				   								   -1,-2,-3,-4,-5,-6,-7,-8,
				   								   9,18,27,36,45,54,63,
												  -9,-18,-27,-36,-45,-54,-63,
												   7,14,21,28,35,42,49,56,
												  -7,-14,-21,-28,-35,-42,-49,-56});
		this.coupsTypePiece.put("roi", new int[] {1,7,8,9,-1,-7,-8,-9});
		
	}
	
	public void trouveCasesDispoBordPlateau() {
		// haut
		this.casesDispoBordPlateau[0] = 8-(this.coordPiece / 8 ) - 1;
		// bas
		this.casesDispoBordPlateau[1] = this.coordPiece / 8 - 1;
		// gauche
		this.casesDispoBordPlateau[2] = 8-(this.coordPiece % 8 ) - 1;
		// droite
		this.casesDispoBordPlateau[3] = 8-(this.coordPiece % 8 ) - 1;
	}
	
	public void trouvePiecesVoisines() {
		//TODO : pour une case donnée, savoir si une pièce est situee dessus
	}
	
	public void trouveDestinationsPossibles() {
		
		// TODO : récupérer le nom de la pièce
		String nomPiece = "pion";
		int[] destinationsAutorisees = this.coupsTypePiece.get(nomPiece);
		//finir !!!!!!!!!!!!
	}

}
