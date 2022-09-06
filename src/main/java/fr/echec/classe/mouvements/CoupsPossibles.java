package fr.echec.classe.mouvements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.echec.classe.jeu.Piece;
import fr.echec.classe.jeu.Plateau;
import fr.echec.enumerateur.CouleursPiece;
import fr.echec.enumerateur.TypePiece;

public class CoupsPossibles extends GestionRoque {
	
	
	public List<Integer> trouveDestinationsPossibles(Plateau plateau, Piece piece) {
		 
		int coordPiece = piece.getCoordonnee();
		
		// Cases libres autour de la pièce
		int[] casesDispoBordPlateau = AnalysePositionPiece.trouveCasesDispoBordPlateau(piece);
				
		// Liste des coups réglementaires par pièce
		List<Integer> coupsReglementaires = AnalyseCoupsReglementaires.TrouveCoupsReglementaires(casesDispoBordPlateau, piece, plateau);
			
		// Place disponible en tenant compte du type de la pièce
		List<Integer> destinationsDispo = 
				AnalysePlaceDisponible.destinationsDispoGlobal(casesDispoBordPlateau, true, plateau, piece); 
		
		
		// On ajoute toutes les destinations dispos pour le type de la piece qui rentrent dans les cases dispos. 
		// On vérifie aussi pour chaque destination dispo que ça ne met pas le roi en échec
		List<Integer> destinationsJouables = new ArrayList<>();
				 
		for (int deplacement : coupsReglementaires) {
			for (int destination : destinationsDispo) {
				if (coordPiece + deplacement == destination && GestionEchec.mvtEchec(plateau, piece, deplacement) == false) {
					destinationsJouables.add(coordPiece + deplacement);
				}
			}
		}
		// On regarde si les roques sont possibles
		destinationsJouables = ajouteDestinationRoque(destinationsJouables, plateau, piece);
					
		return destinationsJouables;
		 	
	}



}
