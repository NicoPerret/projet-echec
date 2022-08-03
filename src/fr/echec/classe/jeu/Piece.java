package fr.echec.classe.jeu;

import fr.echec.enumerateur.TypePiece;

import java.util.LinkedList;

public class Piece extends Plateau {

	// Déclaration des variables
	private int coordonnee = -1;
	private TypePiece nom;
	private String couleur;
	private boolean enVie = true;
	private int valeurMateriel; // NB : pion = 1 point, fou = 3 points, cavalier = 3 points, tour = 5 points,
								// dame = 9 points
	private String nomPlateau;;

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

	public String isCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
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

	public Piece(TypePiece nom, String couleur) {
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
		Pieces.add(this);
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

//sous-fonction "Déplacement impossible"
	public void DeplacementImpossible() {
		for (Piece p : Pieces) {
			if (p.coordonnee == coordonnee||p.couleur==couleur) {
				System.out.println("Déplacement impossible");
			}
		}

		// sous-fonction "Capture"

		for (Piece p : Pieces) {
			if (p.coordonnee == coordonnee|| p.couleur!=couleur) {
				p.Capture();
			}
			this.coordonnee = coordonnee;
		}
	}

	public void Capture() {
		Pieces.remove(this);
	}

//public static void Capture() {


//sous-fonction "Promotion"
	public void Promotion() {
		for (Piece p : Pieces) {
			if (p.coordonnee == 56 || p.coordonnee == 57 || p.coordonnee == 58 || p.coordonnee == 59
					|| p.coordonnee == 60 || p.coordonnee == 61 || p.coordonnee == 62 || p.coordonnee == 63
					|| p.coordonnee == 0 || p.coordonnee == 1 || p.coordonnee == 2 || p.coordonnee == 3
					|| p.coordonnee == 4 || p.coordonnee == 5 || p.coordonnee == 6 || p.coordonnee == 7) {

				System.out.println("Quel pièce voulez-vous comme promotion?");
				p.nom = read();
			}
		}
	}
}

//public static void Promotion() {
//	if piece(coordonnées) se déplace sur coordonnées2 (A1-H1 ou A8-H8) { 
//      demander au joueur quelle pièce il veut; pièce-> piece choisie
