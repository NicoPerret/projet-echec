package fr.echec.classe.jeu;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import fr.echec.enumerateur.TypePiece;

public class Plateau {
	// Déclaration des variables

	private String[] plateau = new String[64];
	private int numPartie;
	public List<Piece> Pieces = new ArrayList<>();

	// Getters et Setter
	public String[] getPlateau() {
		return plateau;
	}

	public void setPlateau(String[] plateau) {
		this.plateau = plateau;
	}

	public int getNumPartie() {
		return numPartie;
	}

	public void setNumPartie(int numPartie) {
		this.numPartie = numPartie;
	}

	//Constructeurs
	public Plateau(String fen) {
		// pour la version console
		// --------
		String nom = "   ";
		for (int i=0; i<64; i++) {
			this.plateau[i] = nom;
		}
		int cptPionBlanc = 1,cptPionNoir = 1, cptTourBlanc = 1, cptTourNoir = 1, cpt = 0; 
		int cptFouBlanc = 1, cptFouNoir = 1, cptCavBlanc = 1, cptCavNoir = 1;
		
		
		Hashtable <Character, String> test = new Hashtable<Character, String>();
		test.put('t', "TOUR");
		test.put('c', "CAVALIER");
		test.put('f', "FOU");
		test.put('d', "DAME");
		test.put('r', "ROI");
		test.put('p', "PION");
		test.put('T', "TOUR");
		test.put('C', "CAVALIER");
		test.put('F', "FOU");
		test.put('D', "DAME");
		test.put('R', "ROI");
		test.put('P', "PION");
		// --------
		

		char[] fenTab = new char[fen.length()];
		for(int i=0; i<fen.length();i++) {
			fenTab[i] = fen.charAt(i);
		}
		int lig = 0, col = 7;
		for (char c : fenTab) {
			if (c == '/'){
				lig = 0;
				col--;
			}else {
				if(Character.isDigit(c)) {
					lig += Character.getNumericValue(c);	
				}else {
					String coul = (Character.isUpperCase(c)? "Blanc" : "Noir");
					String nomPiece = test.get(c);
					TypePiece type = TypePiece.valueOf(nomPiece);
					
					Piece pi = new Piece(type, coul);
					
					// pour console
					// ------
					boolean bool = true;
					if (pi.isCouleur() == "Blanc") {
						bool = false;
					}
					
					switch(pi.getNom()) {
					case PION :
						pi.setNomPlateau("p"+(bool == true ? "b"+cptPionBlanc++ : "n"+cptPionNoir++));
						break;
					case TOUR :
						pi.setNomPlateau("t"+(bool == true ? "b"+cptTourBlanc++ : "n"+cptTourNoir++));
						break;
					case FOU :
						pi.setNomPlateau("f"+(bool == true ? "b"+cptFouBlanc++ : "n"+cptFouNoir++));
						break;
					case CAVALIER :
						pi.setNomPlateau("c"+(bool == true ? "b"+cptCavBlanc++ : "n"+cptCavNoir++));
						break;
					case ROI :
						pi.setNomPlateau("r"+(bool == true ? "b " : "n "));
						break;
					case DAME :
						pi.setNomPlateau("d"+(bool == true ? "b " : "n "));
						break;
					default:
						break;
					}
					//----
					
					this.Pieces.add(pi);
					plateau[col*8 + lig] = pi.getNomPlateau();
					
					lig++;
				}
				
			}
		}
	}
	
	// Méthodes
	public String toString() {
		String tab;
		tab = " -------------------------------------------------\n";
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				tab = tab + " | " + this.plateau[8*(7-i)+j];
			}
			tab += " |\n -------------------------------------------------\n";
		}
		return tab;
	}
}
