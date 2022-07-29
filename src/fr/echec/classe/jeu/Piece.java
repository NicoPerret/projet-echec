package fr.echec.classe.jeu;

import fr.echec.enumerateur.TypePiece;

public class Piece {

	// Déclaration des variables
	private int coordonnee = -1;
	private TypePiece nom;
	private boolean couleur;
	private boolean enVie = true;
	private int valeurMateriel; // NB : pion = 1 point, fou = 3 points, cavalier = 3 points, tour = 5 points,
								// dame = 9 points
	private String nomPlateau;

	// Getters et Setters
	public int getCoordonnee() {
		return coordonnee;
	}

	public void setCoordonnee(int coordonnee) {
		this.coordonnee = coordonnee;
	}

	public TypePiece getNom() {
		return nom;
	}

	public void setNom(TypePiece nom) {
		this.nom = nom;
	}

	public boolean isCouleur() {
		return couleur;
	}

	public void setCouleur(boolean couleur) {
		this.couleur = couleur;
	}

	public boolean isEnVie() {
		return enVie;
	}

	public void setEnVie(boolean enVie) {
		this.enVie = enVie;
	}

	public int getValeurMateriel() {
		return valeurMateriel;
	}

	public void setValeurMateriel(int valeurMateriel) {
		this.valeurMateriel = valeurMateriel;
	}

	// Constructeur pièce

	public Piece(TypePiece nom, boolean couleur) {
		this.nom = nom;
		this.couleur = couleur;
		switch (nom) {
		case PION:
			valeurMateriel = 1;
			break;
		case FOU:
			valeurMateriel = 3;
			break;
		case CAVALIER:
			valeurMateriel = 3;
			break;
		case TOUR:
			valeurMateriel = 5;
			break;
		case DAME:
			valeurMateriel = 9;
			break;
		case ROI:
			valeurMateriel = 0;
			break;

		}
	}

	// Méthodes

	public void seDeplacer() {

	}

	public String getNomPlateau() {
		return nomPlateau;
	}

	public void setNomPlateau(String nomPlateau) {
		this.nomPlateau = nomPlateau;
	}
}

	// sous-fonction "Capture"
//public static void Capture() {
//	if
//}
//}
