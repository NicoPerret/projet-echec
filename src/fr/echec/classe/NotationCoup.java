package fr.echec.classe;

import fr.echec.enumerateur.TypePiece;

public class NotationCoup {

	/*
	 * Récupère les coordonnées de départ et d'arrivée de la pièce en format 0-63
	 * NotationCoup coup1 = new NotationCoup(56,63);
	 * 
	 * Conversion du format [0-63] en [a1-h8] coup1.ecrireCoup();
	 * 
	 * Dernier coup stocké dans coup1.coupFormatStandard
	 * System.out.println(coup1.getCoupFormatStandard());
	 */

	// Attributs
	private int coordDepart64;
	private int coordArrivee64;
	private String coordDepartStandard;
	private String coordArriveeStandard;
	private TypePiece typePiece;
	private String coupFormatStandard;

	// constructeur

	public NotationCoup(int coordDepart64, int coordArrivee64) {
		this.coordDepart64 = coordDepart64;
		this.coordArrivee64 = coordArrivee64;
	}

	// get-set methodes

	public int getCoordDepart64() {
		return coordDepart64;
	}

	public void setCoordDepart64(int coordDepart64) {
		this.coordDepart64 = coordDepart64;
	}

	public int getCoordArrivee64() {
		return coordArrivee64;
	}

	public void setCoordArrivee64(int coordArrivee64) {
		this.coordArrivee64 = coordArrivee64;
	}

	public String getCoordDepartStandard() {
		return coordDepartStandard;
	}

	public void setCoordDepartStandard(String coordDepartStandard) {
		this.coordDepartStandard = coordDepartStandard;
	}

	public String getCoordArriveeStandard() {
		return coordArriveeStandard;
	}

	public void setCoordArriveeStandard(String coordArriveeStandard) {
		this.coordArriveeStandard = coordArriveeStandard;
	}

	public TypePiece getTypePiece() {
		return typePiece;
	}

	public void setTypePiece(TypePiece typePiece) {
		this.typePiece = typePiece;
	}

	public String getCoupFormatStandard() {
		return coupFormatStandard;
	}

	public void setCoupFormatStandard(String coupFormatStandard) {
		this.coupFormatStandard = coupFormatStandard;
	}

	// Methodes

	// Conversion du format 0-63 dans le format A1-H8
	
	public String conversion64ToLettre(int coordonnee64) {
		String coordonneeStandard = "";

		String[] listeLignes = new String[] { "A", "B", "C", "D", "E", "F", "G", "H" };
		coordonneeStandard = listeLignes[coordonnee64 % 8] + (coordonnee64 / 8 + 1);
		return coordonneeStandard;
	}
	
	public int conversionLettreTo64(String coordonneeLettre) {
		int coordonneeStandard = 0;
		
		int lettre = coordonneeLettre.charAt(0) - 'A';
		int chiffre = coordonneeLettre.charAt(1) - 48;  // Pour convertir char en int
		
		coordonneeStandard = (chiffre - 1 )  * 8 + lettre; 
	
//		String[] listeLignes = new String[] { "A", "B", "C", "D", "E", "F", "G", "H" };
//		coordonneeStandard = listeLignes[coordonnee64 % 8] + (coordonnee64 / 8 + 1);
		return coordonneeStandard;
	}

	// Ecriture du dernier coup joué
	public void ecrireCoup() { // A COMPLETER
		// Récuperation des coordonnées standards
		this.coordDepartStandard = this.conversion64ToLettre(coordDepart64);
		this.coordArriveeStandard = this.conversion64ToLettre(coordArrivee64);

		// Ecriture du coup
		this.coupFormatStandard = (this.typePiece + this.coordDepartStandard + "-" + this.coordArriveeStandard);
	}
}
