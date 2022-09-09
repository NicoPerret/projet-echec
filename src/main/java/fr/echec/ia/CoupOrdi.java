package fr.echec.ia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.echec.classe.historique.NotationCoup;
import fr.echec.classe.jeu.Plateau;
import fr.echec.classe.mouvements.deplacement.Deplacement;
import fr.echec.classe.mouvements.deplacement.Promotion;
import fr.echec.classe.mouvements.deplacement.Roque;

@Component
public class CoupOrdi {
	// Coup joué par l'ordi
	
		@Autowired
		Roque roque;
		
		@Autowired
		Promotion promo;
		
		@Autowired
		Deplacement d;
		
		
		public void coupOrdi(String coupAJouer, Plateau p, boolean traitAuBlanc) {
			if(coupAJouer.equals("O-O")) {
				if(traitAuBlanc) {
					roque.jouerPetitRoque(p.getPieceCase(3), p);
				}else {
					roque.jouerPetitRoque(p.getPieceCase(59), p);
				}
			}
			else if (coupAJouer.equals("O-O-O")) {
				if(traitAuBlanc) {
					roque.jouerGrandRoque(p.getPieceCase(3), p);

				}else {
					roque.jouerGrandRoque(p.getPieceCase(59), p);
				}
			}else {
				int coordDepart = NotationCoup.conversionLettreTo64(coupAJouer.substring(0, 2));
				int coordArrivee = NotationCoup.conversionLettreTo64(coupAJouer.substring(2, 4));
		
				d.deplacement(p.getPieceCase(coordDepart), coordArrivee, p);
				// promo.promotionAutomatique(p.getPieceCase(coordArrivee), p, coupAJouer.charAt(5));
			}
		}
}
