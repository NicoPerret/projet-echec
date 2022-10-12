package fr.echec.classe.partie;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.enumerateur.CouleursPiece;
import fr.echec.exception.HistoriquePartieNotFoundException;
import fr.echec.exception.UtilisateurNotFoundException;


public class JcJ extends Partie {

	// VARIABLES
	protected CouleursPiece couleurJoueur1;
	protected CouleursPiece couleurJoueur2;

	// Methodes

	public void calculElo(Utilisateur joueur1, Utilisateur joueur2, double sa, double sb) {
		
		int ra = joueur1.getElo();
		int rb = joueur2.getElo();
		float ea;
		float eb;

		float k = 15;

		float ran;
		float rbn;

		ea = (float) (1 / (1 + (Math.pow(10, (rb - ra) / 400))));
		eb = (float) (1 / (1 + (Math.pow(10, (ra - rb) / 400))));

		int differentielA = (int) Math.ceil(k * (sa - ea));
		int differentielB = (int) Math.ceil(k * (sb - eb));

		ran = ra + differentielA;
		rbn = rb + differentielB;

		joueur1.setElo((int) Math.ceil(ran));
		joueur2.setElo((int) Math.ceil(rbn));

		// System.out.println(" Ancien Elo Joueur A : " + ra);
		// System.out.println(" Ancien Elo Joueur B : " + rb);
//		 System.out.println("Changement A : " + differentielA);
//		 System.out.println("Changement B : " + differentielB);
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
				h.ajouterCoup("#");
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
				h.ajouterCoup("#");
				return true;
			}
			if (fin) {
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

	public void savePartieEtHistorique() throws HistoriquePartieNotFoundException, UtilisateurNotFoundException {

		this.getH().setDate(LocalDateTime.now());
		this.getH().setJ1(j1);
		this.getH().setJ2(j2);
		this.getH().setMessages("TEST");
		List<Utilisateur> joueurs = new ArrayList<>();
		joueurs.add(j1);
		joueurs.add(j2);
		this.getH().setJoueurs(joueurs);
		
		srvHistPartie.save(this.getH());
		srvUti.save(this.getJ1());
		srvUti.save(this.getJ2());

	}

}
