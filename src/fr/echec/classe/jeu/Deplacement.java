package fr.echec.classe.jeu;

public class Deplacement {

//	// sous-fonction "Capture"
//
//	for (Piece p : Pieces) { //if case remplie alors capture
//		if (p.coordonnee == coordonnee && p.couleur!=couleur) {
//			p.Capture();
//		}
//		p.coordonnee = coordonnee;
//	}
//}
	
	public void Capture() {
		Pieces.remove(this); // l'appliquer sur la bonne piece
	}

	
//public static void Capture() {


//sous-fonction "Promotion"
	public void Promotion() {
		for (Piece p : Pieces) { // verif coord dernier deplace pion ou pas ?
			if (p.coordonnee == 56 || p.coordonnee == 57 || p.coordonnee == 58 || p.coordonnee == 59
					|| p.coordonnee == 60 || p.coordonnee == 61 || p.coordonnee == 62 || p.coordonnee == 63
					|| p.coordonnee == 0 || p.coordonnee == 1 || p.coordonnee == 2 || p.coordonnee == 3
					|| p.coordonnee == 4 || p.coordonnee == 5 || p.coordonnee == 6 || p.coordonnee == 7) {

				System.out.println("Quel pièce voulez-vous comme promotion?");
				p.nom = read();
			}
		}
	}
}
	
}
