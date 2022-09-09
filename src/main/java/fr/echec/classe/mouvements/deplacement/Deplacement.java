package fr.echec.classe.mouvements.deplacement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.echec.classe.jeu.Piece;
import fr.echec.classe.jeu.Plateau;
import fr.echec.enumerateur.TypePiece;
@Service
public class Deplacement {

	// VARIABLES
	@Autowired
	private Roque roque ;
	@Autowired
	private Promotion promotion;
	@Autowired
	private PriseEnPassant priseEnPassant;
	private boolean petitRoque;
	private boolean grandRoque;

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

	public void deplacement(Piece piece, int coord, Plateau p) {
		petitRoque = false;
		grandRoque = false;
		
		if (piece.getNom() == TypePiece.ROI && coord == piece.getCoordonnee() + 2) {

			roque.jouerPetitRoque(piece, p);
			petitRoque = true;

		}

		else if (piece.getNom() == TypePiece.ROI && coord == piece.getCoordonnee() - 2) {

			roque.jouerGrandRoque(piece, p);
			grandRoque = true;

		}

		else if (piece.getNom() == TypePiece.PION && (coord - piece.getCoordonnee()) % 2 == 1
				&& p.getPieceCase(coord) == null) {

			// Si déplacement en diagonal (impair) et que la case de destination est vide
			priseEnPassant.jouerPriseEnPassant(piece, coord, p);

		}

		else {

			bougerPiece(piece, coord, p);
			promotion.promotion(piece, p);

		}

		priseEnPassant.gestionBooleenPriseEnPassant(piece, p);

	}

	public static void bougerPiece(Piece piece, int coord, Plateau p) {

		if (p.getPieceCase(coord) != null) {
			capture(p.getPieceCase(coord), p);
		}

		p.setCaseTableau("   ", piece.getCoordonnee());
		p.setCaseTableau(piece.getNomPlateau(), coord);

		p.getPieceCase(piece.getCoordonnee()).setCoordonnee(coord);
		piece.setaBouge(true);
		;

	}

	public static void capture(Piece piece, Plateau p) {

		p.setCaseTableau("   ", piece.getCoordonnee());
		piece.setCoordonnee(-1);
		piece.setEnVie(false);

	}

}
