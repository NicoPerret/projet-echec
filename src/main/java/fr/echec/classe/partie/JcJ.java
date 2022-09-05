package fr.echec.classe.partie;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.enumerateur.CouleursPiece;

public class JcJ {

	// VARIABLES
	CouleursPiece couleurJoueur1;
	CouleursPiece couleurJoueur2;

	public void calculElo(Utilisateur joueur1, Utilisateur joueur2, double sa, double sb) {
		// mettre joueurs en
// parametre de la fct pour
// recup elo et le modifier

		// Real Rating Player A/B
		// Elo des joueurs avant la partie
		//
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
}
