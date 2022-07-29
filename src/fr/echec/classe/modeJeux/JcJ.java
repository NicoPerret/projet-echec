package fr.echec.classe.modeJeux;

public class JcJ {
	boolean couleurJoueur1;
	boolean couleurJoueur2;
	
	
	public void calculElo(int ra, int rb, double d, double e) {

		// Real Rating Player A/B
		// Elo des joueurs avant la partie
		//
		// float ra;
		// float rb;

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
		int differentielA =   (int) Math.ceil(k * (d - ea));
		int differentielB = (int) Math.ceil(k* (e - eb)) ;
		
		
		ran = ra + differentielA;
		rbn = rb + differentielB;

		// Arrondie au superieur

		int nouveauEloA =  (int) Math.ceil(ran);
		int nouveauEloB = (int) Math.ceil(rbn);

		System.out.println( " Ancien Elo Joueur A : " + ra );
		System.out.println( " Ancien Elo Joueur B : " + rb );
		System.out.println("Changement A : " + differentielA);
		System.out.println("Changement B : " + differentielB);
		System.out.println( " Nouveau Elo Joueur A : " + nouveauEloA );
		System.out.println( " Nouveau Elo Joueur B : " + nouveauEloB );
		
		
	}
}
