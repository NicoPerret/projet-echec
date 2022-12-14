package fr.echec.classe.partie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import fr.echec.classe.historique.HistoriquePartie;
import fr.echec.classe.historique.NotationCoup;
import fr.echec.classe.jeu.Chrono;
import fr.echec.classe.jeu.Fen;
import fr.echec.classe.jeu.Plateau;
import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.mouvements.analyse.CoupsPossibles;
import fr.echec.classe.mouvements.analyse.GestionEchec;
import fr.echec.classe.mouvements.deplacement.Deplacement;
import fr.echec.classe.parametres.ParametresPartie;
import fr.echec.enumerateur.CouleursPiece;
import fr.echec.enumerateur.TypePiece;
import fr.echec.finpartie.FinPartie;
import fr.echec.service.HistoriquePartieService;
import fr.echec.service.UtilisateursService;

public abstract class Partie {

	// VARIABLES from BDD
	protected Utilisateur j1;
	protected Utilisateur j2;

	protected ParametresPartie parametre;

	// VARIABLES
	protected int id;
	protected Plateau plateau;
	protected Chrono chronoJ1;
	protected Chrono chronoJ2;
	protected int compteurTours = 1;
	protected int compteurCoups = 1;
	protected int coordDepart;
	protected CouleursPiece couleurJoueurActif = CouleursPiece.BLANC;

	protected Scanner sc = new Scanner(System.in);
	protected List<Integer> listeCoup = new ArrayList<>();
	protected HistoriquePartie h = new HistoriquePartie();

	// LOL LES BOOLEANS
	protected boolean verifChangerPiece = false;
	protected boolean surrJ1 = false;
	protected boolean surrJ2 = false;
	private boolean draw = false;

	// AUTOWIRED

	@Autowired
	protected NotationCoup nt;

	@Autowired
	protected Deplacement d;

	@Autowired
	protected CoupsPossibles coupPossible;

	@Autowired
	protected Fen fen;

	@Autowired
	protected FinPartie finPartie;

	@Autowired
	private GestionEchec gestionEchec;

	@Autowired
	protected HistoriquePartieService srvHistPartie;

	@Autowired
	protected UtilisateursService srvUti;

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

	public CouleursPiece getCouleurJoueurActif() {
		return couleurJoueurActif;
	}

	public void setCouleurJoueurActif(CouleursPiece couleurJoueurActif) {
		this.couleurJoueurActif = couleurJoueurActif;
	}

	public int getCompteurCoups() {
		return compteurCoups;
	}

	public void setCompteurCoups(int compteurCoups) {
		this.compteurCoups = compteurCoups;
	}

	public HistoriquePartie getH() {
		return h;
	}

	public void setH(HistoriquePartie h) {
		this.h = h;
	}

	public boolean isSurrJ1() {
		return surrJ1;
	}

	public void setSurrJ1(boolean surrJ1) {
		this.surrJ1 = surrJ1;
	}

	public boolean isSurrJ2() {
		return surrJ2;
	}

	public void setSurrJ2(boolean surrJ2) {
		this.surrJ2 = surrJ2;
	}

	public boolean isDraw() {
		return draw;
	}

	public void setDraw(boolean draw) {
		this.draw = draw;
	}

// Constructeur 

	public void setParam(ParametresPartie param) {
		this.chronoJ1 = param.getChrono();
		this.chronoJ2 = param.getChrono();
		this.plateau = fen.creationPlateau(param.getFen());
	}

// METHODES 

	public void setChronos(Chrono parametreChrono) {
		this.setChronoJ1(parametreChrono);
		this.setChronoj2(parametreChrono);
	}

	public boolean tourBlanc() {
		if (compteurTours % 2 == 1) {
			this.couleurJoueurActif = CouleursPiece.BLANC;
			return true;
		} else {
			this.couleurJoueurActif = CouleursPiece.NOIR;
			return false;
		}
	}

	public void selectionPiece() {

		if (tourBlanc()) {
			System.out.println("Tour du Blanc : ");

			this.chronoJ1.start();

		} else {
			System.out.println("Tour du Noir : ");
			this.chronoJ2.start();

		}

		while (true) {
			System.out.println("Taper DRAW pour demander un match nul /  FF pour abbandonner");
			System.out.println("Saisir une piece : ");
			String saisie = sc.nextLine();

			coordDepart = NotationCoup.conversionLettreTo64(saisie);

			if (plateau.getPieceCase(coordDepart) != null
					&& plateau.getPieceCase(coordDepart).getCouleur() == this.couleurJoueurActif) {
				listeCoup = coupPossible.trouveDestinationsPossibles(plateau, plateau.getPieceCase(coordDepart));

				if (listeCoup.isEmpty() == false) {
					System.out.println("Coup(s) possible(s) : ");
					nt.setCoordDepartStandard(saisie);
					for (Integer i : listeCoup) {
						System.out.println(NotationCoup.conversion64ToLettre(i));
					}
					break;
				} else {
					System.out.println("Aucun coup possible pour cette piece");
				}

			} else if (saisie.equals("FF")) {

				if (this.couleurJoueurActif == CouleursPiece.BLANC) {
					surrJ1 = true;

					break;
				}

				else {
					surrJ2 = true;
					break;
				}

			}

			else if (saisie.equals("DRAW")) {
				System.out.println("Votre adversaire propose un match null");
				System.out.println("Saisir 1 pour accepter, 2 pour refuser");
				saisie = sc.nextLine();
				if (saisie.equals("1")) {
					draw = true;
					break;
				} else {
					System.out.println("Draw refus??");
				}

			}

			else {
				System.out.println("Mauvaise saisie : Piece non trouv??e ou mauvaise couleur ");

			}

		}

	}

	public void selectionPieceTP() {

		if (tourBlanc()) {
			System.out.println("Tour du Blanc : ");
			this.chronoJ1.start();

		} else {
			System.out.println("Tour du Noir : ");

			this.chronoJ2.start();

		}
		System.out.println("Saisir une piece : ");

		while (true) {

			String saisie = sc.nextLine();

			coordDepart = NotationCoup.conversionLettreTo64(saisie);

			if (plateau.getPieceCase(coordDepart) != null
					&& plateau.getPieceCase(coordDepart).getCouleur() == this.couleurJoueurActif) {
				listeCoup = coupPossible.trouveDestinationsPossibles(plateau, plateau.getPieceCase(coordDepart));

				System.out.println("Coup(s) possible(s) : ");
				nt.setCoordDepartStandard(saisie);

				for (Integer i : listeCoup) {
					System.out.println(NotationCoup.conversion64ToLettre(i));
				}
				break;

			} else if (saisie == "FF") {

				if (this.couleurJoueurActif.toString() == "BLANC") {
					surrJ1 = true;
					break;
				}

				else {
					surrJ2 = true;
					break;
				}
			}

			else {
				System.out.println("Mauvaise saisie : Piece non trouv??e ou mauvaise couleur ");

			}

		}

	}

	public void teleportPiece() {
		if (surrJ1 == true || surrJ2 == true) {
			return;
		}
		System.out.println("Tour du Blanc : ");
		System.out.println("D??placer la piece : ");

		int coordArrivee = 0;

		String saisie = sc.nextLine();
		coordArrivee = NotationCoup.conversionLettreTo64(saisie);
		nt.setCoordArriveeStandard(saisie);

		// mettre promotion dans deplacement
		d.deplacement(plateau.getPieceCase(coordDepart), coordArrivee, plateau);

	}

	public void jouerPiece() {
		if (surrJ1 || surrJ2 || draw) {
			verifChangerPiece = true;
			return;
		}
		System.out.println("Saisir 0 pour changer de piece");

		System.out.println("D??placer la piece : ");

		boolean verifIfSaisieCoupPossible = false;
		verifChangerPiece = false;
		int coordArrivee = 0;

		while (verifIfSaisieCoupPossible == false) {

			String saisie = sc.nextLine();
			coordArrivee = NotationCoup.conversionLettreTo64(saisie);

			for (Integer i : listeCoup) {

				if (coordArrivee == i) {

					nt.setCoordArriveeStandard(saisie);
					d.deplacement(plateau.getPieceCase(coordDepart), coordArrivee, plateau);
					verifIfSaisieCoupPossible = true;
					verifChangerPiece = true;

					break;
				}
			}

			if (verifIfSaisieCoupPossible == false) {

				if (saisie.equals("0")) {
					break;
				} else {

					System.out.println("Veuillez saisir un coup dans la liste ci-dessus");
					System.out.println("D??placement ill??gal");
				}

			}

		}
		if (verifChangerPiece) {
			// d.promotion(plateau.getPieceCase(coordArrivee), plateau);

		}

	}

	public void finTour() {
		if (surrJ1 || surrJ2 || draw) {
			verifChangerPiece = true;
			return;
		}
		// IF PEtit then OO else if groque o-O-o else
		if (d.isGrandRoque()) {
			h.ajouterCoup(" O-O-O ");
		} else if (d.isPetitRoque()) {
			h.ajouterCoup(" O-O ");
		} else {
			h.ajouterCoup(nt.getCoordDepartStandard() + nt.getCoordArriveeStandard());

			if (d.getPromotion().isPromote()) {
				TypePiece type = plateau.getPieceCase(NotationCoup.conversionLettreTo64(nt.getCoordArriveeStandard()))
						.getNom();
				switch (type) {
				case CAVALIER:
					h.ajouterCoup("K");
					break;
				case DAME:
					h.ajouterCoup("Q");
					break;
				case FOU:
					h.ajouterCoup("B");
					break;

				case TOUR:
					h.ajouterCoup("R");
					break;
				default:
					break;
				}
			}
			h.ajouterCoup(" ");
		}

		if (gestionEchec.isEchec(plateau, couleurJoueurActif)) {

			h.ajouterCoup("+");
		}

		if (this.getCompteurTours() % 2 == 1) {

			this.chronoJ1.runnig();
			this.chronoJ1.stop();
			this.chronoJ1.getAffichageTempsRestant(chronoJ1.getTempsRestant());

		} else {

			this.chronoJ2.runnig();
			this.chronoJ2.stop();
			this.chronoJ2.getAffichageTempsRestant(chronoJ2.getTempsRestant());

			compteurCoups++;

		}
		this.compteurTours++;

	}

	public void jouer() {

		System.out.println(plateau);
		while (verifChangerPiece == false) {

			this.selectionPiece();
			this.jouerPiece();

		}
		verifChangerPiece = false;
		this.finTour();
	}

	public void teleportation() {

		System.out.println(plateau);
		this.selectionPieceTP();
		this.teleportPiece();
		this.finTour();
	}

	
}
