package fr.echec.classe.partie;

import org.springframework.stereotype.Service;

import fr.echec.classe.probleme.Probleme;

@Service
public class ProblemePartie extends Partie {
	
	protected Probleme probleme = new Probleme();
	protected String[] tabCoups;
	protected int numCoup = 0;
	protected boolean coupAuJoueur;
	
	public boolean isCoupAuJoueur() {
		return coupAuJoueur;
	}
	public void setCoupAuJoueur(boolean coupAuJoueur) {
		this.coupAuJoueur = coupAuJoueur;
	}
	public int getNumCoup() {
		return numCoup;
	}
	public void setNumCoup(int numCoup) {
		this.numCoup = numCoup;
	}
	public Probleme getProbleme() {
		return probleme;
	}
	public void setProbleme(Probleme probleme) {
		this.probleme = probleme;
	}
	public String[] getTabCoups() {
		return tabCoups;
	}
	public void setTabCoups(String[] tabCoups) {
		this.tabCoups = tabCoups;
	}
	
	
}
