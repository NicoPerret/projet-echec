package fr.echec.classe.partie;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.parametres.ParametresPartie;
import fr.echec.enumerateur.CouleursPiece;


public class JcJ extends Partie {

	// VARIABLES
	protected CouleursPiece couleurJoueur1;
	protected CouleursPiece couleurJoueur2;

	// constructeur
	public JcJ(ParametresPartie param) {
		super(param);
	}
	// Methodes 
	
	public void calculElo(Utilisateur joueur1, Utilisateur joueur2, double sa, double sb) {
		// Real Rating Player A/B
		// Elo des joueurs avant la partie
		
		int ra = joueur1.getElo();
		int rb = joueur2.getElo();

		// Outcome of the game for Player A/B (1 for win / 0.5 for draw / 0 for loss)
		// Résultat de la partie (1 pour victoire / 0.5 pour Nulle / 0 pour défaite)

		// float sa;
		// float sb;

		// Expected outcome for Player A/B (percententage of win)
		// Pourcentage de victoire théorique

		float ea;
		float eb;

		// Weight Factor
		// constante de pondération

		float k = 15;

		// New Rating PlayerA/B
		// Nouveau elo après match

		float ran;
		float rbn;

		// Calcul du résultat attendu

		ea = (float) (1 / (1 + (Math.pow(10, (rb - ra) / 400))));
		eb = (float) (1 / (1 + (Math.pow(10, (ra - rb) / 400))));

		// Calcul du nouveau Elo
		int differentielA = (int) Math.ceil(k * (sa - ea));
		int differentielB = (int) Math.ceil(k * (sb - eb));

		ran = ra + differentielA;
		rbn = rb + differentielB;

		// Arrondie au superieur

		joueur1.setElo((int) Math.ceil(ran));
		joueur2.setElo((int) Math.ceil(rbn));

		// System.out.println(" Ancien Elo Joueur A : " + ra);
		// System.out.println(" Ancien Elo Joueur B : " + rb);
		// System.out.println("Changement A : " + differentielA);
		// System.out.println("Changement B : " + differentielB);
		System.out.println(" Nouveau Elo Joueur A : " + joueur1.getElo());
		System.out.println(" Nouveau Elo Joueur B : " + joueur2.getElo());

	}
	
	public boolean isPartieFinie() {
		double resJ1 = 0.5;
		double resJ2 = 0.5;
		boolean fin = false;
		
		if (tourBlanc()) {
			
			fin = finPartie.isEchecMatOuPat(plateau, couleurJoueurActif);

			if (getChronoJ1().isDefaiteTemps() || isSurrJ1() == true || finPartie.isEchecMat()) {
				h.setVainqueurId(j2.getId());
				System.out.println("Le joueur 2 gagne !");
				resJ1 = 0;
				resJ2 = 1;
				calculElo(j1, j2, resJ1, resJ2);
				return true;

			}

		} else {
				finPartie.isEchecMatOuPat(plateau, couleurJoueurActif);
			if (getChronoj2().isDefaiteTemps() || isSurrJ2() || finPartie.isEchecMat()) {
				System.out.println("Le joueur 1 gagne !");
				h.setVainqueurId(j1.getId());
				resJ1 = 1;
				resJ2 = 0;
				calculElo(j1, j2, resJ1, resJ2);
				return true;
			}
			if(fin) {
				calculElo(j1, j2, resJ1, resJ2);
				return true;
			}
		}
		if (finPartie.isMatchNulRepetition(plateau) || finPartie.isMatchNulmateriel(plateau) || isDraw() == true) {
			System.out.println("Match nul ! ");
			calculElo(j1, j2, resJ1, resJ2);
			return true;
		}
		
		return false;
	}
	
	
}
