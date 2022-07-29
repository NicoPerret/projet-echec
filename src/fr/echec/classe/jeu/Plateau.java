package fr.echec.classe.jeu;

public class Plateau {
	// Déclaration des variables

	private int[] plateau = new int[63];
	private int numPartie;

	// Getters et Setter
	public int[] getPlateau() {
		return plateau;
	}

	public void setPlateau(int[] plateau) {
		this.plateau = plateau;
	}

	public int getNumPartie() {
		return numPartie;
	}

	public void setNumPartie(int numPartie) {
		this.numPartie = numPartie;
	}

	// Méthodes

}
