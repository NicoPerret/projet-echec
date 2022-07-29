package fr.echec.classe.jeu;

import fr.echec.enumerateur.TypePiece;

public class Piece {

	// Déclaration des variables
	private int coordonnee = -1;
	private TypePiece nom;
	private boolean couleur;
	private boolean enVie = true;
	private int valeurMateriel;

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

	//Constructeurs
	
	
	
	// Méthodes

	public void seDeplacer() {

	}

}
