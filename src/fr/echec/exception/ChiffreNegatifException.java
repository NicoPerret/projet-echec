package fr.echec.exception;

public class ChiffreNegatifException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private int chiffre;

	public int getChiffre() {
		return chiffre;
	}

	public void setChiffre(int chiffre) {
		this.chiffre = chiffre;
	}
	
	public ChiffreNegatifException(int chiffre) {
		this.chiffre = chiffre;
	}
}
