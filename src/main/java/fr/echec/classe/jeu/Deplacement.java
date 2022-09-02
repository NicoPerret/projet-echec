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
		
		//ajouter conditions sur roque
		
		p.setCaseTableau("   ", piece.getCoordonnee());
		p.setCaseTableau(piece.getNomPlateau(), coord);

		if (p.getPieceCase(coord) != null) {

			capture(p.getPieceCase(coord));
		}

		p.getPieceCase(piece.getCoordonnee()).setCoordonnee(coord);
		piece.setaBouge(true);
	}

	public void capture(Piece piece) {

		piece.setCoordonnee(-1);
		piece.setEnVie(false);

	}

//sous-fonction "Promotion"
	public void promotion(Piece piece, Plateau p) {
		if (piece.getNom() == TypePiece.PION) {
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
					if (piece.isCouleur().equals("Blanc")) {
						piece.setNomPlateau("fbp");
						p.setCaseTableau("fbp", piece.getCoordonnee());
					} else {
						piece.setNomPlateau("fnp");
						p.setCaseTableau("fnp", piece.getCoordonnee());
					}
					break;
				case 2:
					piece.setNom(TypePiece.CAVALIER);
					if (piece.isCouleur().equals("Blanc")) {
						piece.setNomPlateau("cbp");
						p.setCaseTableau("cbp", piece.getCoordonnee());
					} else {
						piece.setNomPlateau("cnp");
						p.setCaseTableau("cnp", piece.getCoordonnee());
					}
					break;

				case 3:
					piece.setNom(TypePiece.TOUR);
					if (piece.isCouleur().equals("Blanc")) {
						piece.setNomPlateau("tbp");
						p.setCaseTableau("tbp", piece.getCoordonnee());
					} else {
						piece.setNomPlateau("tnp");
						p.setCaseTableau("tnp", piece.getCoordonnee());
					}
					break;
				case 4:
					piece.setNom(TypePiece.DAME);
					if (piece.isCouleur().equals("Blanc")) {
						piece.setNomPlateau("dbp");
						p.setCaseTableau("dbp", piece.getCoordonnee());
					} else {
						piece.setNomPlateau("dnp");
						p.setCaseTableau("dnp", piece.getCoordonnee());
					}
					break;
				}

			}
		}
	}

//	 sous-fonction Petit Roque et Grand Roque

	public void petitRoque(Piece piece, Piece piece2, Plateau p) {
		if (piece.getNom() == TypePiece.ROI && piece.isaBouge() == false && piece2.getNom() == TypePiece.TOUR
				&& piece2.isaBouge() == false) {
			if (p.getPieceCase(5) == null && p.getPieceCase(6) == null) {
				piece.setCoordonnee(6);
				piece2.setCoordonnee(5);
				piece.setaBouge(true);
				piece2.setaBouge(true);
			}
			else if (p.getPieceCase(61) == null && p.getPieceCase(62) == null) {
				piece.setCoordonnee(61);
				piece2.setCoordonnee(62);
				piece.setaBouge(true);
				piece2.setaBouge(true);
			}
		}
	}

	public void grandRoque(Piece piece, Piece piece2, Plateau p) {
		if (piece.getNom() == TypePiece.ROI && piece.isaBouge() == false && piece2.getNom() == TypePiece.TOUR
				&& piece2.isaBouge() == false) {
			if (p.getPieceCase(1) == null && p.getPieceCase(2) == null && p.getPieceCase(3) == null) {
				piece.setCoordonnee(2);
				piece2.setCoordonnee(3);
				piece.setaBouge(true);
				piece2.setaBouge(true);
			}
			else if (p.getPieceCase(59) == null && p.getPieceCase(58) == null&& p.getPieceCase(57)==null) {
				piece.setCoordonnee(58);
				piece2.setCoordonnee(59);
				piece.setaBouge(true);
				piece2.setaBouge(true);
		}

	}}}

