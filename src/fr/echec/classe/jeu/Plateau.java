package fr.echec.classe.jeu;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
	// Déclaration des variables
	private String[] plateau = new String[64];
	private List<Piece> pieces = new ArrayList<>();

	// Getters et Setter
	public String[] getPlateau() {
		return plateau;
	}

	public void setPlateau(String[] plateau) {
		this.plateau = plateau;
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

	// Constructeurs
	public Plateau() {
	}

	// Méthodes
	public Piece getPieceCase(int coord) {
		return this.pieces.stream()
			.filter(piece -> piece.getCoordonnee() == coord)
			.findFirst()
			.orElse(null);
	}
	
	//que pour la console
	public void setCaseTableau(String nom, int coord) {
		this.plateau[coord] = nom;
	}

	public String toString() {
		String tab;
		tab = "  -------------------------------------------------\n";
		for (int i = 0; i < 8; i++) {
			tab = tab + (8-i);
			for (int j = 0; j < 8; j++) {
				tab = tab + " | " + this.plateau[8 * (7 - i) + j];
			}
			tab += " |\n  -------------------------------------------------\n";
			
		}
		tab += "    A     B     C     D     E     F     G     H";
		return tab;
	}
}

