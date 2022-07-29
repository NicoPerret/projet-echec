package fr.echec.classe.modeJeux;

import fr.echec.enumerateur.TypePartie;

public class ParametresPartie {
	private int temps;
	private int penalitePiece;
	private int choixAdversaire; // a voir si boolean et a setup que si 1v1
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

	public void choixMode() {
//		System.out.println("Choisissez le type de partie : " );
//		int choix = readInt();
//
//		switch(choix) {
//
//		case 1 :
//			System.out.println("Problème");
//			typeDeLaPartie PROBLEME;
//			break;
//
//		case 2 :System.out.println("Joueur contre Joueur");
//			typeDeLaPartie = TypePartie.JOUEURVSJOUEUR;
//			break;
//
//		case 3 :System.out.println("Joueur contre IA");
//			typeDeLaPartie = TypePartie.JOUEURVSIA;
//			break;
//
//		default : System.out.println("Mauvais choix... recommencez");
//		}
	}
}
