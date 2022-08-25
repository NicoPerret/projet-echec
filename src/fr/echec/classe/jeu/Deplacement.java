package fr.echec.classe.jeu;

import java.util.Scanner;

import fr.echec.enumerateur.TypePiece;

public class Deplacement {
	public String read() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

	// sous-fonction "Capture"
	public void deplacement(Piece piece, int coord, Plateau p) {
		p.setCaseTableau("   ", piece.getCoordonnee());
		p.setCaseTableau(piece.getNomPlateau(), coord);
		
		if (p.getPieceCase(coord) != null) {

			Capture(p.getPieceCase(coord));
		}

		p.getPieceCase(piece.getCoordonnee()).setCoordonnee(coord);
	}

	public void Capture(Piece piece) {

		piece.setCoordonnee(-1);
		piece.setEnVie(false);

	}

//public static void Capture() {

//sous-fonction "Promotion"
	public void Promotion(Piece piece) {
		if (piece.getNom() == TypePiece.PION) { // verif coord dernier deplace pion ou pas ?
			if (piece.getCoordonnee() == 56 || piece.getCoordonnee() == 57 || piece.getCoordonnee() == 58
					|| piece.getCoordonnee() == 59 || piece.getCoordonnee() == 60 || piece.getCoordonnee() == 61
					|| piece.getCoordonnee() == 62 || piece.getCoordonnee() == 63 || piece.getCoordonnee() == 0
					|| piece.getCoordonnee() == 1 || piece.getCoordonnee() == 2 || piece.getCoordonnee() == 3
					|| piece.getCoordonnee() == 4 || piece.getCoordonnee() == 5 || piece.getCoordonnee() == 6
					|| piece.getCoordonnee() == 7) {

				System.out.println("Quel pièce voulez-vous comme promotion?");
				piece.getNom().valueOf(read()); // a convertir avec un switch // toLowerCase
				piece.getNomPlateau(); // a completer
			}
		}
	}
}
//	// sous-fonction roque
// Petit roque et grand roque 
//	// public void Roque() {
//	//if(piece.getNom(TypePiece.ROI).getCoordonnee()==5&&piece.getNom(TypePiece.TOUR).getCoordonnee()==8) // utiliser le boolean "aBouge" et verifier les cases entre
//
//	{
//		//piece.getNom(TypePiece.ROI).setCoordonnee(7);
//		piece.getNom(TypePiece.TOUR).setCoordonnee(6);
//	}
//
//}}
