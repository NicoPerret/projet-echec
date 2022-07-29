package fr.echec.application;

import fr.echec.classe.NotationCoup;
import fr.echec.classe.jeu.Piece;
import fr.echec.enumerateur.TypePartie;
import fr.echec.enumerateur.TypePiece;

public class Application {

	public static void main(String[] args) {
		
		
Piece pionBlanc = new Piece (TypePiece.PION, true);
Piece fouBlanc = new Piece (TypePiece.FOU, true);
Piece cavalierBlanc = new Piece (TypePiece.CAVALIER, true);
Piece tourBlanc = new Piece (TypePiece.TOUR, true);
Piece dameBlanc = new Piece (TypePiece.DAME, true);
Piece roiBlanc = new Piece (TypePiece.ROI, true);
		
		System.out.println(pionBlanc);
		//System.out.println("Choisissez le type de partie : " );
		//int choix = readInt();
		
		//switch(choix) {
		
		//case 1 : System.out.println("Problème");
				//typeDeLaPartie PROBLEME;
				//break;
		
		//case 2 :System.out.println("Joueur contre Joueur");
				//typeDeLaPartie = TypePartie.JOUEURVSJOUEUR;
				//break;
				
		//case 3 :System.out.println("Joueur contre IA");
				//typeDeLaPartie = TypePartie.JOUEURVSIA;
				//break;
				
		//default : System.out.println("Mauvais choix... recommencez");
		}
		
	}

}
