package fr.echec.classe.partie;

import fr.echec.classe.parametres.ParametresPartie;
import fr.echec.enumerateur.CouleursPiece;

public class JcIA extends Partie{

	// VARIABLES
	protected CouleursPiece couleurJoueur;
	protected int niveauIA;
	
	public JcIA(ParametresPartie param) {
		super(param);
	}
	
}
