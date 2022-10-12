package fr.echec.classe.jeu;

import org.springframework.stereotype.Component;

import fr.echec.classe.historique.NotationCoup;
import fr.echec.enumerateur.CouleursPiece;
import fr.echec.enumerateur.TypePiece;
@Component
public class Piece {

	// VARIABLES
	private int coordonnee = -1;
	private String coordonneeLettre ="";
	
	private TypePiece nom;
	
	private CouleursPiece couleur;
	private boolean enVie = true;
	private int valeurMateriel; // NB : pion = 1 point, fou = 3 points, cavalier = 3 points, tour = 5 points,
								// dame = 9 points
	private String nomPlateau;
	private boolean aBouge = false;
	private boolean priseEnPassantPossible = false; // true quand un pion vient de bouger de deux cases

	// Getters et Setters
	public int getCoordonnee() {
		return coordonnee;
	}

	public void setCoordonnee(int coordonnee) {
		this.coordonnee = coordonnee;
		if (coordonnee !=-1) {
			this.coordonneeLettre = NotationCoup.conversion64ToLettre(coordonnee);
		}else {
			this.coordonneeLettre = "";
		}
		
	}
	
	public String getCoordonneeLettre() {
		return coordonneeLettre;
	}

	public void setCoordonneeLettre(String coordonneeLettre) {
		this.coordonneeLettre = coordonneeLettre;
	}

	public TypePiece getNom() {
		return nom;
	}

	public void setNom(TypePiece nom) {
		this.nom = nom;
	}

	public CouleursPiece getCouleur() {
		return couleur;
	}

	public void setCouleur(CouleursPiece couleur) {
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

	public String getNomPlateau() {
		return nomPlateau;
	}

	public void setNomPlateau(String nomPlateau) {
		this.nomPlateau = nomPlateau;
	}
	public boolean isaBouge() {
		return aBouge;
	}

	public void setaBouge(boolean aBouge) {
		this.aBouge = aBouge;
	}

	public boolean isPriseEnPassantPossible() {
		return priseEnPassantPossible;
	}

	public void setPriseEnPassantPossible(boolean priseEnPassantPossible) {
		this.priseEnPassantPossible = priseEnPassantPossible;
	}
	
	

	// Constructeur pièce
	public Piece(TypePiece nom, CouleursPiece couleur) {
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
	public Piece() {
		
	}

	

	
}
