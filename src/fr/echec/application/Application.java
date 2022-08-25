package fr.echec.application;

import java.util.ArrayList;
import java.util.List;

import fr.echec.classe.NotationCoup;
import fr.echec.classe.Partie;
import fr.echec.classe.jeu.Chrono;
import fr.echec.classe.jeu.CoupsPossibles;
import fr.echec.classe.jeu.Fen;
import fr.echec.classe.jeu.Plateau;

public class Application {

	public static void main(String[] args) {

		// SETUP RUDIMENTAIRE MAIS FONCTIONNEL 
		NotationCoup nt = new NotationCoup(0, 0);
		Partie p = new Partie(); 
		Fen fen = new Fen();
		Plateau pl = new Plateau();
		pl = fen.creationPlateau("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
		Chrono chrono = new Chrono(3,0,2);
		List<Integer> listeCoup = new ArrayList<>();
		
		p.setChronos(chrono);
		p.setPlateau(pl);
	
	

		
//		// UNE PARTIE 
		while(true) {
			
			System.out.println(p.getPlateau());
			
			
			
			//selection 
			String selection = p.selectionPiece();
			int select = nt.conversionLettreTo64(selection);
			
			// Envoie dans coup possible 
			CoupsPossibles coup = new CoupsPossibles(pl);
			
			coup.setPiece(pl.getPieceCase(select));
			listeCoup = coup.trouveDestinationsPossibles(); 
			// affichage et récupp de la liste des coups possible 
			System.out.println("Coups possible : ");
			for (Integer i : listeCoup) {
				System.out.println(nt.conversion64ToLettre(i));
			}
			
			
			// Jouer la piece 
			p.jouerPiece();
			
			// vérifier que le coup appartient à coup possible 
			
			
			// Jouer le coup 
			
			
			
			// Fin du tour 
			
			p.finTour();
			
			if (p.getChronoJ1().isDefaiteTemps() || p.getChronoj2().isDefaiteTemps()) {
				break;
			}
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
