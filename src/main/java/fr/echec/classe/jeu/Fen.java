package fr.echec.classe.jeu;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.stereotype.Component;

import fr.echec.classe.partie.JcIA;
import fr.echec.enumerateur.CouleursPiece;
import fr.echec.enumerateur.TypePiece;

@Component
public class Fen {

	public Plateau creationPlateau(String fen) {
		Plateau plat = new Plateau();
		String[] plateau = new String[64];
		List<Piece> pieces = new ArrayList<>();

		// pour la version console
		// --------
		String nom = "   ";
		for (int i = 0; i < 64; i++) {
			plateau[i] = nom;
		}
		int cptPionBlanc = 1, cptPionNoir = 1, cptTourBlanc = 1, cptTourNoir = 1;
		int cptFouBlanc = 1, cptFouNoir = 1, cptCavBlanc = 1, cptCavNoir = 1;
		// --------

		Hashtable<Character, String> test = new Hashtable<Character, String>();
		test.put('R', "TOUR");
		test.put('N', "CAVALIER");
		test.put('B', "FOU");
		test.put('Q', "DAME");
		test.put('K', "ROI");
		test.put('P', "PION");

		char[] fenTab = new char[fen.length()];
		for (int i = 0; i < fen.length(); i++) {
			fenTab[i] = fen.charAt(i);
		}
		int lig = 0, col = 7;
		for (char c : fenTab) {
			if (c == '/') {
				lig = 0;
				col--;
			} else {
				if (Character.isDigit(c)) {
					lig += Character.getNumericValue(c);
				} else {
					CouleursPiece coul = (Character.isUpperCase(c) ? CouleursPiece.BLANC : CouleursPiece.NOIR);
					TypePiece type = TypePiece.valueOf(test.get(Character.toUpperCase(c)));

					Piece pi = new Piece(type, coul);

					// pour console
					// ------
					switch (pi.getNom()) {
					case PION:
						pi.setNomPlateau(
								"p" + (coul == CouleursPiece.BLANC ? "b" + cptPionBlanc++ : "n" + cptPionNoir++));
						break;
					case TOUR:
						pi.setNomPlateau(
								"t" + (coul == CouleursPiece.BLANC ? "b" + cptTourBlanc++ : "n" + cptTourNoir++));
						break;
					case FOU:
						pi.setNomPlateau(
								"f" + (coul == CouleursPiece.BLANC ? "b" + cptFouBlanc++ : "n" + cptFouNoir++));
						break;
					case CAVALIER:
						pi.setNomPlateau(
								"c" + (coul == CouleursPiece.BLANC ? "b" + cptCavBlanc++ : "n" + cptCavNoir++));
						break;
					case ROI:
						pi.setNomPlateau("r" + (coul == CouleursPiece.BLANC ? "b " : "n "));
						break;
					case DAME:
						pi.setNomPlateau("d" + (coul == CouleursPiece.BLANC ? "b " : "n "));
						break;
					default:
						break;
					}
					// ----
					pi.setCoordonnee(col * 8 + lig);
					pieces.add(pi);
					plateau[col * 8 + lig] = pi.getNomPlateau();

					lig++;
				}
			}
		}
		plat.setPieces(pieces);
		plat.setPlateau(plateau);
		return plat;
	}

	public String creationFen(Plateau plat) {
		String fen = "";
		// Plateau plat = partie.getPlateau();

		// position piece
		String str;
		char piece = 0;
		char p = 0;
		int j = 8;
		int[] a = new int[] { 56, 40, 24, 8, -8, -24, -40, -56 };
		int k = 0;
		for (int i = 0; i < plat.getPlateau().length; i++) {
			j--;
			str = plat.getPlateau()[i + a[k]];
			switch (str.charAt(0)) {
			case 't':
				piece = 'r';
				break;
			case 'c':
				piece = 'n';
				break;
			case 'f':
				piece = 'b';
				break;
			case 'r':
				piece = 'k';
				break;
			case 'd':
				piece = 'q';
				break;
			case 'p':
				piece = 'p';
			}

			if (str.charAt(1) == 'b') {
				p = Character.toUpperCase(piece);
			} else if (str.charAt(1) == 'n') {
				p = Character.toLowerCase(piece);
			} else {
				p = '1';
			}
			fen = fen + p;
			if (j % 8 == 0 && i != 0) {
				k++;
				fen = fen + "/";
			}
		}
		return fen;
	}

	public String creationFenIA(JcIA partie) {
		Plateau plat = partie.getPlateau();

		// position piece
		String fen = creationFen(plat);
		fen += " ";

		// trait
		if (partie.getCouleurJoueurActif() == CouleursPiece.BLANC) {
			fen += "w ";
		} else {
			fen += "b ";
		}

		// roque
		if (plat.getPieceCase(4) != null && plat.getPieceCase(4).isaBouge() == false) {
			if (plat.getPieceCase(0) != null && plat.getPieceCase(0).isaBouge() == false) {
				fen += "K";
			}
			if (plat.getPieceCase(7) != null && plat.getPieceCase(7).isaBouge() == false) {
				fen += "Q";
			}
		}
		if (plat.getPieceCase(59) != null && plat.getPieceCase(59).isaBouge() == false) {
			if (plat.getPieceCase(56) != null && plat.getPieceCase(56).isaBouge() == false) {
				fen += "k";
			}
			if (plat.getPieceCase(63) != null && plat.getPieceCase(63).isaBouge() == false) {
				fen += "q";
			}
		}
		if (fen.charAt(fen.length() - 1) == ' ') {
			fen += "-";
		}
		return fen;
	}
}
