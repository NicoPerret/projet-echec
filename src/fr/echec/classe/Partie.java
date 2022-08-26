package fr.echec.classe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.echec.classe.jeu.Chrono;
import fr.echec.classe.jeu.CoupsPossibles;
import fr.echec.classe.jeu.Deplacement;
import fr.echec.classe.jeu.Fen;
import fr.echec.classe.jeu.Plateau;
import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.modeJeux.ParametresPartie;

public class Partie {

	// VARIABLES BDD
	private int id;
	private Plateau plateau;
	private Utilisateur j1;
	private Utilisateur j2;
	private ParametresPartie parametre;

	// VARIABLES
	private Chrono chronoJ1;
	private Chrono chronoJ2;
	private NotationCoup nt = new NotationCoup(0, 0);
	private Scanner sc = new Scanner(System.in);
	private int compteurTours = 1;
	private Deplacement d = new Deplacement();
	private int coordDepart;
	private Fen fen = new Fen();
	// 0 = Blanc / 1 = Noir
	private String couleurJoueurActif = "Blanc";
	private CoupsPossibles coupPossible = new CoupsPossibles();
	private List<Integer> listeCoup = new ArrayList<>();

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

	public void setPlateau(String fenEntree) {

		this.plateau = fen.creationPlateau(fenEntree);
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

	public String getCouleurJoueurActif() {
		return couleurJoueurActif;
	}

	public void setCouleurJoueurActif(String couleurJoueurActif) {
		this.couleurJoueurActif = couleurJoueurActif;
	}

// Constructeur 

	public Partie(ParametresPartie param) {
		this.chronoJ1 = param.getChrono();
		this.chronoJ2 = param.getChrono();
		this.plateau = fen.creationPlateau(param.getFen());
	}

// METHODES 

	public void setChronos(Chrono parametreChrono) {
		this.setChronoJ1(parametreChrono);
		this.setChronoj2(parametreChrono);
	}

	public void selectionPiece() {

		if (compteurTours % 2 == 1) {
			System.out.println("Tour du Blanc : ");
			System.out.println("Saisir une piece : ");
			this.chronoJ1.start();
			this.couleurJoueurActif = "Blanc";

		} else {
			System.out.println("Tour du Noir : ");
			System.out.println("Saisir une piece : ");
			this.chronoJ2.start();
			this.couleurJoueurActif = "Noir";
		}

		while (true) {

			String saisie = sc.nextLine();
			coordDepart = nt.conversionLettreTo64(saisie);

			if (plateau.getPieceCase(coordDepart) != null
					&& plateau.getPieceCase(coordDepart).isCouleur() == this.couleurJoueurActif) {
				listeCoup = coupPossible.trouveDestinationsPossibles(plateau,plateau.getPieceCase(coordDepart));

				if (listeCoup.isEmpty() == false) {
					System.out.println("Coup(s) possible(s) : ");

					for (Integer i : listeCoup) {
						System.out.println(nt.conversion64ToLettre(i));
					}
					break;
				} else {
					System.out.println("Aucun coup possible pour cette piece");
				}

			} else {
				System.out.println("Mauvaise saisie : Piece non trouvée ou mauvaise couleur ");

			}
		}

	}

	public void jouerPiece() {

		if (compteurTours % 2 == 1) {
			System.out.println("Tour du Blanc : ");
			System.out.println("Déplacer la piece : ");

		} else {
			System.out.println("Tour du Noir : ");
			System.out.println("Déplacer la piece : ");

		}
		boolean vrai = false;
		int coordArrivee = 0;
		while (vrai == false) {
			String saisie = sc.nextLine();
			coordArrivee = nt.conversionLettreTo64(saisie);

			for (Integer i : listeCoup) {
				if (coordArrivee == i) {
					// mettre promotion dans deplacement
					d.deplacement(plateau.getPieceCase(coordDepart), coordArrivee, plateau); 
					vrai = true;
					break;
				}
			}
			if (vrai == false) {
				
				System.out.println("Déplacement illégal");
				System.out.println("Veuillez saisir un coup dans la liste ci-dessus");
				
			}
		}
		d.promotion(plateau.getPieceCase(coordArrivee),plateau);

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
