package fr.echec.classe.jeu;

import java.util.Scanner;

import fr.echec.enumerateur.TypePiece;

public class Deplacement extends CoupsPossibles {
	String read() {
	    Scanner sc = new Scanner(System.in);
	    return sc.nextLine();
	}
//	// sous-fonction "Capture"
//
//	if (if (piece.getCoordonnee() == coord && p.couleur!=couleur)) { //if case remplie alors capture
//			piece.Capture();
//		}
//		p.coordonnee = coordonnee;
//	}
//}
	
	public void Capture() {
		piece.remove(this); // l'appliquer sur la bonne piece
	}

	
//public static void Capture() {


//sous-fonction "Promotion"
	public void Promotion() {
		if (piece.getNom()==TypePiece.PION) { // verif coord dernier deplace pion ou pas ?
			if (piece.getCoordonnee() == 56 || piece.getCoordonnee() == 57 || piece.getCoordonnee() == 58 || piece.getCoordonnee() == 59
					|| piece.getCoordonnee() == 60 || piece.getCoordonnee() == 61 || piece.getCoordonnee() == 62 || piece.getCoordonnee() == 63
					|| piece.getCoordonnee() == 0 || piece.getCoordonnee() == 1 || piece.getCoordonnee() == 2 || piece.getCoordonnee() == 3
					|| piece.getCoordonnee() == 4 || piece.getCoordonnee() == 5 || piece.getCoordonnee() == 6 || piece.getCoordonnee() == 7) {

				System.out.println("Quel pièce voulez-vous comme promotion?");
				piece.getNom().valueOf(read());
			}
		}
	}
	//sous-fonction roque
	//public void Roque( ) {
		
	}
}


