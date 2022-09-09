package fr.echec.classe.mouvements.deplacement;

import org.springframework.stereotype.Component;

import fr.echec.classe.jeu.Piece;
import fr.echec.classe.jeu.Plateau;
@Component
public class Roque {
	
	public void jouerPetitRoque(Piece roi, Plateau p) {
		
		Piece tour = p.getPieceCase(roi.getCoordonnee()+3);
		
		Deplacement.bougerPiece(roi, roi.getCoordonnee()+2, p);
		Deplacement.bougerPiece(tour, tour.getCoordonnee()-2, p);
		
		
	}
	
	public void jouerGrandRoque(Piece roi, Plateau p) {
		
		Piece tour = p.getPieceCase(roi.getCoordonnee()-4);
		
		Deplacement.bougerPiece(roi, roi.getCoordonnee()-2, p);
		Deplacement.bougerPiece(tour, tour.getCoordonnee()+3, p);
		
	}

}
