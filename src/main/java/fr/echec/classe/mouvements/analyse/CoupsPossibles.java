package fr.echec.classe.mouvements.analyse;

import java.util.ArrayList;
import java.util.List;

import fr.echec.classe.jeu.Piece;
import fr.echec.classe.jeu.Plateau;

public class CoupsPossibles {
	
	private AnalysePositionPiece posPiece = new AnalysePositionPiece();
	private AnalysePlaceDisponible placeDispo = new AnalysePlaceDisponible();
	private AnalyseCoupsReglementaires coupsReglo = new AnalyseCoupsReglementaires();
	public GestionEchec gestionEchec = new GestionEchec();
	private GestionRoque gestionRoque = new GestionRoque();
	
	public List<Integer> trouveDestinationsPossibles(Plateau plateau, Piece piece) {
		 
		int coordPiece = piece.getCoordonnee();
		
		// Cases libres autour de la pièce
		int[] casesDispoBordPlateau = posPiece.trouveCasesDispoBordPlateau(piece);
				
		// Liste des coups réglementaires par pièce
		
		List<Integer> coupsReglementaires = coupsReglo.TrouveCoupsReglementaires(casesDispoBordPlateau, piece, plateau);
			
		// Place disponible en tenant compte du type de la pièce
		List<Integer> destinationsDispo = 
				placeDispo.destinationsDispoGlobal(casesDispoBordPlateau, true, plateau, piece); 
		
		
		// On ajoute toutes les destinations dispos pour le type de la piece qui rentrent dans les cases dispos. 
		// On vérifie aussi pour chaque destination dispo que ça ne met pas le roi en échec
		List<Integer> destinationsJouables = new ArrayList<>();
				 
		for (int deplacement : coupsReglementaires) {
			for (int destination : destinationsDispo) {
				if (coordPiece + deplacement == destination && gestionEchec.mvtEchec(plateau, piece, deplacement) == false) {
					destinationsJouables.add(coordPiece + deplacement);
				}
			}
		}
		// On regarde si les roques sont possibles
		destinationsJouables = gestionRoque.ajouteDestinationRoque(destinationsJouables, plateau, piece);
					
		return destinationsJouables;
		 	
	}



}
