package fr.echec.classe.jeu;

import java.util.ArrayList;
import java.util.List;

import fr.echec.enumerateur.TypePiece;

public class Plateau {
	// Déclaration des variables

	private String[] plateau = new String[64];
	private int numPartie;
	private List<Piece> Pieces = new ArrayList<>();

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
	public Plateau() {
		String nom = "   ";
		
		Piece pi = new Piece(TypePiece.PION, false);
		this.Pieces.add(pi);
		Piece pi2 = new Piece(TypePiece.TOUR, true);
		this.Pieces.add(pi2);
		Piece pi3 =  new Piece(TypePiece.TOUR, true);
		this.Pieces.add(pi3);
		
		int cptPionBlanc = 1,cptPionNoir = 1, cptTourBlanc = 1, cptTourNoir = 1, cpt = 0; 
		int cptFouBlanc = 1, cptFouNoir = 1, cptCavBlanc = 1, cptCavNoir = 1;
		for(Piece p : this.Pieces) {
			switch(p.getNom()) {
			case PION :
				nom = "p"+(p.isCouleur()==true ? "b"+cptPionBlanc++ : "n"+cptPionNoir++);
				break;
			case TOUR :
				nom = "t"+(p.isCouleur()==true ? "b"+cptTourBlanc++ : "n"+cptTourNoir++);
				break;
			case FOU :
				nom = "f"+(p.isCouleur()==true ? "b"+cptFouBlanc++ : "n"+cptFouNoir++);
				break;
			case CAVALIER :
				nom = "c"+(p.isCouleur()==true ? "b"+cptCavBlanc++ : "n"+cptCavNoir++);
				break;
			case ROI :
				nom = "r"+(p.isCouleur()==true ? "b " : "n ");
				break;
			case DAME :
				nom = "d"+(p.isCouleur()==true ? "b " : "n ");
				break;
			default:
				break;
			}
			
			this.plateau[cpt] = nom;
			if(cpt==16) {
				cpt += 32;
			}else {
				cpt++;
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
