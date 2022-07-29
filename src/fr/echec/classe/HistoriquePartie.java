package fr.echec.classe;

import java.util.ArrayList;
import java.util.List;

public class HistoriquePartie {
	
	/*	Liste de tous les coups
	 *  Peut ajouter un coup
	 *  Récupère le nom des joueurs
	 *  Récupère l'id de la partie
	 *  Indique le numero du coup
	 */ 
	
	// Attributs
	private int compteurCoup = 0;
	
	private List<String> listeCoups = new ArrayList<>();
	
	private String idJ1;
	private String idJ2;
	private String idPartie;
	
	// get-set methodes
	
	public int getCompteurCoup() {
		return compteurCoup;
	}
	public void setCompteurCoup(int compteurCoup) {
		this.compteurCoup = compteurCoup;
	}
	public List<String> getListeCoups() {
		return listeCoups;
	}
	public void setListeCoups(List<String> listeCoups) {
		this.listeCoups = listeCoups;
	}
	public String getIdJ1() {
		return idJ1;
	}
	public void setIdJ1(String idJ1) {
		this.idJ1 = idJ1;
	}
	public String getIdJ2() {
		return idJ2;
	}
	public void setIdJ2(String idJ2) {
		this.idJ2 = idJ2;
	}
	public String getIdPartie() {
		return idPartie;
	}
	public void setIdPartie(String idPartie) {
		this.idPartie = idPartie;
	}
	
	// Methodes
	
	public void ajouterCoup(NotationCoup coup) {
		String dernierCoup = coup.getCoupFormatStandard();
		this.listeCoups.add(dernierCoup);
	}
	

	
	
	

}
