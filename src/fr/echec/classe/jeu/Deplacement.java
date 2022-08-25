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
//	if (piece.getCoordonnee() == coord && piece.couleur != piece.trouvePieceCoordonnee(coord).couleur)) { //if case remplie alors capture
//			piece=null;
//			piece.Capture();
//		}
//		piece.trouvePieceCoordonnee(coord).Capture();
//	}
//}

	public void Capture(Piece piece) {
		if (piece==null) {
			plateau.remove(piece);
		}
	}

//public static void Capture() {

//sous-fonction "Promotion"
	public void Promotion() {
		if (piece.getNom() == TypePiece.PION) { // verif coord dernier deplace pion ou pas ?
			if (piece.getCoordonnee() == 56 || piece.getCoordonnee() == 57 || piece.getCoordonnee() == 58
					|| piece.getCoordonnee() == 59 || piece.getCoordonnee() == 60 || piece.getCoordonnee() == 61
					|| piece.getCoordonnee() == 62 || piece.getCoordonnee() == 63 || piece.getCoordonnee() == 0
					|| piece.getCoordonnee() == 1 || piece.getCoordonnee() == 2 || piece.getCoordonnee() == 3
					|| piece.getCoordonnee() == 4 || piece.getCoordonnee() == 5 || piece.getCoordonnee() == 6
					|| piece.getCoordonnee() == 7) {

				System.out.println("Quel pièce voulez-vous comme promotion?");
				piece.getNom().valueOf(read());
			}
		}
	}
}
//	// sous-fonction roque
//	// public void Roque() {
//	//if(piece.getNom(TypePiece.ROI).getCoordonnee()==5&&piece.getNom(TypePiece.TOUR).getCoordonnee()==8)
//
//	{
//		//piece.getNom(TypePiece.ROI).setCoordonnee(7);
//		piece.getNom(TypePiece.TOUR).setCoordonnee(6);
//	}
//
//}}
