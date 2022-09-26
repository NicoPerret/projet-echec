package fr.echec.classe.partie;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.echec.classe.historique.NotationCoup;
import fr.echec.classe.jeu.Fen;
import fr.echec.enumerateur.CouleursPiece;
import fr.echec.ia.CoupOrdi;

@Service
public class JcIA extends Partie {

	// VARIABLES
	protected CouleursPiece couleurJoueur = CouleursPiece.BLANC;
	protected int niveauIA;
	int coordArrivee;
	String coupJoueParIa;
	String coupStockfish;
	@Autowired
	protected CoupOrdi coupOrdi;
	@Autowired
	protected Fen fen;
	
	Timer timer = new Timer();
	Random rand = new Random();

	public void jouerContreIaFacile() {
		if (couleurJoueur == couleurJoueurActif) {

			this.jouer();
		}

		else {

			while (true) {
				coordDepart = rand.nextInt(63 - 0 + 1) + 0;
				if (plateau.getPieceCase(coordDepart) != null
						&& plateau.getPieceCase(coordDepart).getCouleur() != couleurJoueur && coupPossible
								.trouveDestinationsPossibles(plateau, plateau.getPieceCase(coordDepart)).size() != 0) {

					break;
				}
			}
			while (true) {

				coordArrivee = rand.nextInt(30 - 0 + 1) + 0;
				if (coupPossible.trouveDestinationsPossibles(plateau, plateau.getPieceCase(coordDepart))
						.size() > coordArrivee) {

					coordArrivee = coupPossible.trouveDestinationsPossibles(plateau, plateau.getPieceCase(coordDepart))
							.get(coordArrivee);
					break;
				}
			}

			coupJoueParIa = NotationCoup.conversion64ToLettre(coordDepart)
					+ NotationCoup.conversion64ToLettre(coordArrivee);
			System.out.println(coordArrivee);
			coupOrdi.coupOrdi(coupJoueParIa, plateau, false);
			compteurTours++;

		}

	}

	public void jouerContreIaStockfish() {
		if (couleurJoueur == couleurJoueurActif) {

			this.jouer();
		}

		else {

			RestTemplate restTemplate = new RestTemplate();

			while (true) {
				
				fen = creationFenIA(this);
				timer.schedule(new TimerTask() {
					  @Override
					  public void run() {
						  coupStockfish = restTemplate
								  .getForObject("https://www.chessdb.cn/cdb.php?action=querybest&board=" + fen, String.class);
					    
					  }
					}, 1000);

				System.out.println(coupStockfish);

				if (coupStockfish.equals("nobestmove") == false) {
					break;
				}
			}

			coupJoueParIa = coupStockfish.substring(5).toUpperCase();
			coupOrdi.coupOrdi(coupJoueParIa, plateau, false);
			compteurTours++;

		}

	}

	public boolean isPartieFinieIA() {

		boolean fin = false;

		if (tourBlanc()) {

			fin = finPartie.isEchecMatOuPat(plateau, couleurJoueurActif);

			if (getChronoJ1().isDefaiteTemps() || isSurrJ1() == true || finPartie.isEchecMat()) {

				System.out.println("Le joueur 2 gagne !");

				return true;

			}

		} else {
			finPartie.isEchecMatOuPat(plateau, couleurJoueurActif);
			if (getChronoj2().isDefaiteTemps() || isSurrJ2() || finPartie.isEchecMat()) {
				System.out.println("Le joueur 1 gagne !");

				return true;
			}
			if (fin) {

				return true;
			}
		}
		if (finPartie.isMatchNulRepetition(plateau) || finPartie.isMatchNulmateriel(plateau) || isDraw() == true) {
			System.out.println("Match nul ! ");

			return true;
		}

		return false;
	}
}
