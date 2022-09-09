package fr.echec.classe.mouvements;

import java.util.Scanner;

import fr.echec.classe.jeu.Piece;
import fr.echec.classe.jeu.Plateau;
import fr.echec.enumerateur.CouleursPiece;
import fr.echec.enumerateur.TypePiece;

public class Deplacement {
	// VARIABLES 
	private boolean petitRoque = false;
	private boolean grandRoque = false; 

	// GETTERS ET SETTERS 

	public boolean isPetitRoque() {
		return petitRoque;
	}
	
	public void setPetitRoque(boolean petitRoque) {
		this.petitRoque = petitRoque;
	}
	
	public boolean isGrandRoque() {
		return grandRoque;
	}
	
	public void setGrandRoque(boolean grandRoque) {
		this.grandRoque = grandRoque;
	}
	// CONSTRUCTEUR 

	// METHODES
	
	
	public int read() {
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

	public void deplacement(Piece piece, int coord, Plateau p) {
		petitRoque = false;
		grandRoque = false;
		if (piece.getNom() == TypePiece.ROI && coord == piece.getCoordonnee()+2) {
			jouerPetitRoque(piece, p);
		} else if (piece.getNom() == TypePiece.ROI && coord == piece.getCoordonnee()-2) {
			jouerGrandRoque(piece, p);
		} else if (piece.getNom() == TypePiece.PION 
				&& (coord - piece.getCoordonnee()) % 2 == 1 &&  p.getPieceCase(coord) == null) {
				// Si déplacement en diagonal (impair) et que la case de destination est vide
				jouerPriseEnPassant(piece, coord, p);
		}else {
			bougerPiece(piece, coord, p);
			promotion(piece, p);
		}
		
		gestionBooleenPriseEnPassant(piece, p);
		
	}
	
	public void bougerPiece(Piece piece, int coord, Plateau p) {
		
		if (p.getPieceCase(coord) != null) {
			capture(p.getPieceCase(coord), p);
		}
		
		p.setCaseTableau("   ", piece.getCoordonnee());
		p.setCaseTableau(piece.getNomPlateau(), coord);
		
		p.getPieceCase(piece.getCoordonnee()).setCoordonnee(coord);
		piece.setaBouge(true);;
		
	}

	public void capture(Piece piece, Plateau p) {
		
		p.setCaseTableau("   ", piece.getCoordonnee());
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
		
		bougerPiece(roi, roi.getCoordonnee()+2, p);
		bougerPiece(tour, tour.getCoordonnee()-2, p);
		petitRoque = true;
		
		
	}
	
	public void jouerGrandRoque(Piece roi, Plateau p) {
		
		Piece tour = p.getPieceCase(roi.getCoordonnee()-4);
		
		bougerPiece(roi, roi.getCoordonnee()-2, p);
		bougerPiece(tour, tour.getCoordonnee()+3, p);
		grandRoque = true;
	}
	
	// Prise en passant
	
	public void gestionBooleenPriseEnPassant(Piece pion, Plateau p) {
		
		CouleursPiece couleur = pion.getCouleur();
		
		for (Piece piece : p.getPieces()) {
			if (piece.getCouleur() == couleur && piece.getNom() == TypePiece.PION) {
				piece.setPriseEnPassantPossible(false);
			}
		}
		
		pion.setPriseEnPassantPossible(true);
		
	}
	
	public void jouerPriseEnPassant(Piece pion, int coord, Plateau p) {
		
		if (coord == pion.getCoordonnee()+7 || coord == pion.getCoordonnee()-9) {
			capture(p.getPieceCase(pion.getCoordonnee() -1), p); // prise en passant à gauche
		} else if (coord == pion.getCoordonnee()+9 || coord == pion.getCoordonnee()-7) {
			capture(p.getPieceCase(pion.getCoordonnee() +1), p); // prise en passant à droite
		}
		
		bougerPiece(pion, coord, p);
		
	}

	
	
}
