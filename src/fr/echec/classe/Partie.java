package fr.echec.classe;

import java.util.Scanner;

import fr.echec.classe.jeu.Chrono;
import fr.echec.classe.jeu.Plateau;
import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.modeJeux.ParametresPartie;

public class Partie {

	// VARIABLES

	private int id;
	private Plateau plateau;
	private Utilisateur j1;
	private Utilisateur j2;
	private Chrono chronoJ1;
	private Chrono chronoJ2;
	private int compteurTours = 1;
	private ParametresPartie parametre;

	// GETTERS AND SETTERS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public Utilisateur getJ1() {
		return j1;
	}

	public void setJ1(Utilisateur j1) {
		this.j1 = j1;
	}

	public Utilisateur getJ2() {
		return j2;
	}

	public void setJ2(Utilisateur j2) {
		this.j2 = j2;
	}

	public Chrono getChronoJ1() {
		return chronoJ1;
	}

	public void setChronoJ1(Chrono chronoJ1) {
		this.chronoJ1 = chronoJ1;
	}

	public Chrono getChronoj2() {
		return chronoJ2;
	}

	public void setChronoj2(Chrono chronoj2) {
		this.chronoJ2 = chronoj2;
	}

	public int getCompteurTours() {
		return compteurTours;
	}

	public void setCompteurTours(int compteurTours) {
		this.compteurTours = compteurTours;
	}

	public ParametresPartie getParametre() {
		return parametre;
	}

	public void setParametre(ParametresPartie parametre) {
		this.parametre = parametre;
	}

// METHODES 

	public void setChronos(Chrono parametreChrono) {
		this.setChronoJ1(parametreChrono);
		this.setChronoj2(parametreChrono);
	}
	

	
	public String selectionPiece() {

		if (compteurTours % 2 == 1) {
			System.out.println("Tour du Joueur 1 : ");
			System.out.println("Saisir une piece : ");
			this.chronoJ1.start();

		} else {
			System.out.println("Tour du Joueur 2 : ");
			System.out.println("Saisir une piece : ");
			this.chronoJ2.start();
		}

		Scanner sc = new Scanner(System.in);
		String saisie = sc.nextLine();
		

		return saisie;
	}

	public String jouerPiece() {

		if (compteurTours % 2 == 1) {
			System.out.println("Tour du Joueur 1 : ");
			System.out.println("Déplacer la piece : ");
		

		} else {
			System.out.println("Tour du Joueur 2 : ");
			System.out.println("Déplacer la piece : ");
			
		}

		Scanner sc = new Scanner(System.in);
		String saisie = sc.nextLine();
		

		return saisie;
	}

	public void finTour() {
		if (this.getCompteurTours() % 2 == 1) {

			this.chronoJ1.runnig();
			this.chronoJ1.stop();
			this.chronoJ1.getAffichageTempsRestant(chronoJ1.getTempsRestant());

		} else {
			this.chronoJ2.runnig();
			this.chronoJ2.stop();
			this.chronoJ2.getAffichageTempsRestant(chronoJ2.getTempsRestant());

		}
		this.compteurTours++;
	}
}
