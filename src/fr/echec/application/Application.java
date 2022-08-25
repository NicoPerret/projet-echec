package fr.echec.application;

import fr.echec.classe.Partie;
import fr.echec.classe.jeu.Chrono;
import fr.echec.classe.jeu.Plateau;

public class Application {

	public static void main(String[] args) {

		// SETUP RUDIMENTAIRE MAIS FONCTIONNEL 
		Partie p = new Partie(); 
		Plateau pl = new Plateau("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
		Chrono chrono = new Chrono(0,10,2);
		
		
		p.setChronos(chrono);
		p.setPlateau(pl);
		p.setCompteurTours(3);
		
		
		// UNE PARTIE 
		while(true) {
			
			System.out.println(p.getPlateau());
			
			
			
			//selection 
			p.selectionPiece();
			
			// Envoie dans coup possible 
			
			
			// affichage et récupp de la liste des coups possible 
			
			
			
			// Jouer la piece 
			p.jouerPiece();
			
			// vérifier que le coup appartient à coup possible 
			
			
			// Jouer le coup 
			
			
			
			// Fin du tour 
			
			p.finTour();
		}
	
	

	
	
//		
//		Mouvement moove = new Mouvement(p);
//		moove.setPiece(p.getPieces().get(1));
//		List<Integer> deplacement = moove.trouveDestinationsPossibles();
//		System.out.println(deplacement);
//	Piece pionBlanc = new Piece (TypePiece.PION, true);
//	Piece fouBlanc = new Piece (TypePiece.FOU, true);
//	Piece cavalierBlanc = new Piece (TypePiece.CAVALIER, true);
//	Piece tourBlanc = new Piece (TypePiece.TOUR, true);
//	Piece dameBlanc = new Piece (TypePiece.DAME, true);
//	Piece roiBlanc = new Piece (TypePiece.ROI, true);

//		System.out.println(pionBlanc);
		

	}
}
