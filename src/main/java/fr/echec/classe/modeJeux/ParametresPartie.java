package fr.echec.classe.modeJeux;

import fr.echec.classe.jeu.Chrono;
import fr.echec.enumerateur.TypePartie;

public class ParametresPartie {
	
	// VARIABLES from BDD
	// ATTENTION PENALITE ==> MODIFIER LE FEN
	private String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
	
	// VARIABLES
	private Chrono chrono = new Chrono(5, 0, 0); // a modif avec HTML
	private int choixAdversaire; // a voir si boolean et a setup que si 1v1
	private TypePartie typeDeLaPartie;

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

	public Chrono getChrono() {
		return chrono;
	}

	public void setChrono(Chrono chrono) {
		this.chrono = chrono;
	}

	public String getFen() {
		return fen;
	}

	public void setFen(String fen) {
		this.fen = fen;
	}

// Constructeur

	public ParametresPartie() {
	}

	public ParametresPartie(int minute, int seconde, int increment, String fen) {
		this.chrono = new Chrono(minute, seconde, increment);
		this.fen = fen;
	}

	// Méthode
	public void choixMode() {
		System.out.println("Choisissez le type de partie : ");
		// ajouter system.in
		int choix = 0;
		// choix = readInt();

		switch (choix) {

		case 1:
			System.out.println("Problème");
			typeDeLaPartie = TypePartie.PROBLEME;
			break;

		case 2:
			System.out.println("Joueur contre Joueur");
			typeDeLaPartie = TypePartie.JOUEURVSJOUEUR;
			break;

		case 3:
			System.out.println("Joueur contre IA");
			typeDeLaPartie = TypePartie.JOUEURVSIA;
			break;

		default:
			System.out.println("Mauvais choix... recommencez");
		}
	}

}
