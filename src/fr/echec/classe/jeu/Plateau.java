package fr.echec.classe.jeu;

public class Plateau {
	// Déclaration des variables

	private String[] plateau = new String[64];
	private int numPartie;

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
