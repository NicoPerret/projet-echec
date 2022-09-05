package fr.echec.classe.mouvements;

import java.util.Scanner;

import fr.echec.classe.jeu.Piece;
import fr.echec.classe.jeu.Plateau;
import fr.echec.enumerateur.CouleursPiece;
import fr.echec.enumerateur.TypePiece;

public class Deplacement {
	public int read() {
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

	public void deplacement(Piece piece, int coord, Plateau p) {

		p.setCaseTableau("   ", piece.getCoordonnee());
		p.setCaseTableau(piece.getNomPlateau(), coord);

		if (p.getPieceCase(coord) != null) {

			capture(p.getPieceCase(coord));
		}
		
		if (piece.getNom() == TypePiece.ROI && coord == piece.getCoordonnee()+2) {
			jouerPetitRoque(piece, p);
		} else if (piece.getNom() == TypePiece.ROI && coord == piece.getCoordonnee()-2) {
			jouerGrandRoque(piece, p);
		} else {
			p.getPieceCase(piece.getCoordonnee()).setCoordonnee(coord);
			piece.setaBouge(true);
			promotion(piece, p);
		}
		
		
	}

	public void capture(Piece piece) {

		piece.setCoordonnee(-1);
		piece.setEnVie(false);

	}

//sous-fonction "Promotion"
	public void promotion(Piece piece, Plateau p) {
		if (piece.getNom() == TypePiece.PION)
			if (piece.getCouleur() == CouleursPiece.BLANC) {
				if (piece.getCoordonnee() == 56 || piece.getCoordonnee() == 57 || piece.getCoordonnee() == 58
						|| piece.getCoordonnee() == 59 || piece.getCoordonnee() == 60 || piece.getCoordonnee() == 61
						|| piece.getCoordonnee() == 62 || piece.getCoordonnee() == 63) {

					System.out.println("Quel pièce voulez-vous comme promotion?");
					System.out.println("1 - Fou");
					System.out.println("2 - Cavalier");
					System.out.println("3 - Tour");
					System.out.println("4 - Dame");
					int promotion = read();
					switch (promotion) {
					case 1:
						piece.setNom(TypePiece.FOU);

						piece.setNomPlateau("fbp");
						p.setCaseTableau("fbp", piece.getCoordonnee());

						break;
					case 2:
						piece.setNom(TypePiece.CAVALIER);
						piece.setNomPlateau("cbp");
						p.setCaseTableau("cbp", piece.getCoordonnee());

						break;

					case 3:
						piece.setNom(TypePiece.TOUR);

						piece.setNomPlateau("tbp");
						p.setCaseTableau("tbp", piece.getCoordonnee());
						break;
					case 4:
						piece.setNom(TypePiece.DAME);
						piece.setNomPlateau("dbp");
						p.setCaseTableau("dbp", piece.getCoordonnee());
						break;
					}
				} else if (piece.getCouleur() == CouleursPiece.NOIR) {
					if (piece.getCoordonnee() == 0 || piece.getCoordonnee() == 1 || piece.getCoordonnee() == 2
							|| piece.getCoordonnee() ==3 || piece.getCoordonnee() == 4 || piece.getCoordonnee() == 5
							|| piece.getCoordonnee() == 6 || piece.getCoordonnee() == 7) {
						
						System.out.println("Quel pièce voulez-vous comme promotion?");
						System.out.println("1 - Fou");
						System.out.println("2 - Cavalier");
						System.out.println("3 - Tour");
						System.out.println("4 - Dame");
						int promotion = read();
						switch (promotion) {
						case 1:
							piece.setNom(TypePiece.FOU);
							piece.setNomPlateau("fnp");
							p.setCaseTableau("fnp", piece.getCoordonnee());

							break;
						case 2:
							piece.setNom(TypePiece.CAVALIER);
							piece.setNomPlateau("cnp");
							p.setCaseTableau("cnp", piece.getCoordonnee());

							break;

						case 3:
							piece.setNom(TypePiece.TOUR);
							piece.setNomPlateau("tnp");
							p.setCaseTableau("tnp", piece.getCoordonnee());

							break;
						case 4:
							piece.setNom(TypePiece.DAME);
							piece.setNomPlateau("dnp");
							p.setCaseTableau("dnp", piece.getCoordonnee());

							break;
						}

					}
				}
			}
	}

//	 sous-fonction Petit Roque et Grand Roque
	
	public void jouerPetitRoque(Piece roi, Plateau p) {
		
		Piece tour = p.getPieceCase(roi.getCoordonnee()+3);
		
		p.setCaseTableau("   ", tour.getCoordonnee());
		p.setCaseTableau(tour.getNomPlateau(), tour.getCoordonnee()-2);
		
		roi.setCoordonnee(roi.getCoordonnee()+2);
		tour.setCoordonnee(tour.getCoordonnee()-2);
		roi.setaBouge(true);
		tour.setaBouge(true);
		
		p.getPieceCase(roi.getCoordonnee()).setCoordonnee(roi.getCoordonnee()+2);
		p.getPieceCase(tour.getCoordonnee()).setCoordonnee(tour.getCoordonnee()-2);
		p.getPieceCase(roi.getCoordonnee()+2).setaBouge(true);
		p.getPieceCase(tour.getCoordonnee()-2).setaBouge(true);
		
	}
	
	public void jouerGrandRoque(Piece roi, Plateau p) {
		
		Piece tour = p.getPieceCase(roi.getCoordonnee()-4);
		
		p.setCaseTableau("   ", tour.getCoordonnee());
		p.setCaseTableau(tour.getNomPlateau(), tour.getCoordonnee()+3);
		
		roi.setCoordonnee(roi.getCoordonnee()-2);
		tour.setCoordonnee(tour.getCoordonnee()+3);
		roi.setaBouge(true);
		tour.setaBouge(true);
		
		p.getPieceCase(roi.getCoordonnee()).setCoordonnee(roi.getCoordonnee()-2);
		p.getPieceCase(tour.getCoordonnee()).setCoordonnee(tour.getCoordonnee()+3);
		p.getPieceCase(roi.getCoordonnee()-2).setaBouge(true);
		p.getPieceCase(tour.getCoordonnee()+3).setaBouge(true);
	}
	
}
