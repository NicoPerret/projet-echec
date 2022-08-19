package fr.echec.application;

import java.util.List;

import fr.echec.classe.jeu.Mouvement;
import fr.echec.classe.jeu.Plateau;

public class Application {

	public static void main(String[] args) {

		Plateau p = new Plateau("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
		System.out.println(p);
		
		Mouvement moove = new Mouvement(p);
		moove.setPiece(p.getPieces().get(1));
		List<Integer> deplacement = moove.trouveDestinationsPossibles();
		System.out.println(deplacement);
//	Piece pionBlanc = new Piece (TypePiece.PION, true);
//	Piece fouBlanc = new Piece (TypePiece.FOU, true);
//	Piece cavalierBlanc = new Piece (TypePiece.CAVALIER, true);
//	Piece tourBlanc = new Piece (TypePiece.TOUR, true);
//	Piece dameBlanc = new Piece (TypePiece.DAME, true);
//	Piece roiBlanc = new Piece (TypePiece.ROI, true);

//		System.out.println(pionBlanc);
		
	}
}
