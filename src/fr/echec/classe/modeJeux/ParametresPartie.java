package fr.echec.classe.modeJeux;

import fr.echec.enumerateur.TypePartie;

public class ParametresPartie {
	private int temps;
	private int penalitePiece;
	private int choixAdversaire; //a voir si boolean et a setup que si 1v1
	private TypePartie typeDeLaPartie;

	public int getTemps() {
		return temps;
	}

	public void setTemps(int temps) {
		this.temps = temps;
	}

	public int getPenalitePiece() {
		return penalitePiece;
	}

	public void setPenalitePiece(int penalitePiece) {
		this.penalitePiece = penalitePiece;
	}

	public int getchoixAdversaire() {
		return choixAdversaire;
	}

	public void setchoixAdversaire(int choixAdversaire) {
		this.choixAdversaire = choixAdversaire;
	}

	public TypePartie getTypeDeLaPartie() {
		return typeDeLaPartie;
	}

	public void setTypeDeLaPartie(TypePartie typeDeLaPartie) {
		this.typeDeLaPartie = typeDeLaPartie;
	}

}
