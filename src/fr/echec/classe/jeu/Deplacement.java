package fr.echec.classe.jeu;

import java.util.Scanner;

import fr.echec.enumerateur.TypePiece;

public class Deplacement {
	public int read() {
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
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
				System.out.println("1 - Fou");
				System.out.println("2 - Cavalier");
				System.out.println("3 - Tour");
				System.out.println("4 - Dame");
				int promotion = read();
				switch (promotion) {
				case 1:
					piece.setNom(TypePiece.FOU);
					if (piece.isCouleur() == "blanc") {
						piece.setNomPlateau("fbp");
					} else {
						piece.setNomPlateau("fnp");
					}
					break;
				case 2:
					piece.setNom(TypePiece.CAVALIER);
					if (piece.isCouleur() == "blanc") {
						piece.setNomPlateau("cbp");
					} else {
						piece.setNomPlateau("cnp");
					}
					break;

				case 3:
					piece.setNom(TypePiece.TOUR);
					if (piece.isCouleur() == "blanc") {
						piece.setNomPlateau("tbp");
					} else {
						piece.setNomPlateau("tnp");
					}
					break;
				case 4:
					piece.setNom(TypePiece.DAME);
					if (piece.isCouleur() == "blanc") {
						piece.setNomPlateau("dbp");
					} else {
						piece.setNomPlateau("dnp");
					}
					break;
				}
				
			}
		}
	}

//	 sous-fonction Petit Roque et Grand Roque

 public void PetitRoque(Piece piece, Piece piece2, Plateau p) {
 if(piece.getNom()==TypePiece.ROI && piece.isaBouge()==false && piece2.getNom()==TypePiece.TOUR&& piece2.isaBouge()==false)  
	{ if (p.getPieceCase(5)==null&&p.getPieceCase(6)==null) {
		piece.setCoordonnee(6);
		piece2.setCoordonnee(5);
		piece.setaBouge(true);
		piece2.setaBouge(true);
	}}}
	
		
	
	public void GrandRoque(Piece piece, Piece piece2, Plateau p) {
		if(piece.getNom()==TypePiece.ROI && piece.isaBouge()==false && piece2.getNom()==TypePiece.TOUR&& piece2.isaBouge()==false)  
		{ if (p.getPieceCase(5)==null&&p.getPieceCase(6)==null) {
			piece.setCoordonnee(2);
			piece2.setCoordonnee(3);
			piece.setaBouge(true);
			piece2.setaBouge(true);
		}
	}

}}
