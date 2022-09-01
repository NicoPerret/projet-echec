package fr.echec.classe;

import java.time.LocalDateTime;

import fr.echec.classe.joueur.Utilisateur;

public class HistoriquePartie {

	/*
	 * Liste de tous les coups Peut ajouter un coup Récupère le nom des joueurs
	 * Récupère l'id de la partie Indique le numero du coup
	 */

	// VARIABLES to BDD
	private int id;
	private Utilisateur j1;
	private Utilisateur j2;
	private String messages; // a revoir avec la classe message
	private String listeCoups = "1 : ";//peut etre stocker sous une liste de string et concatener pour la bdd
	private LocalDateTime date; // a revoir avec bon format pour la bdd
	private int vainqueurId;
	
	// VARIABLES
	
	
	// get-set methodes

	



	public Utilisateur getJ1() {
		return j1;
	}

	public void setJ1(Utilisateur j1) {
		this.j1 = j1;
	}

	public Utilisateur getJ2() {
		return j2;
	}

	public void setJ2(Utilisateur j2) {
		this.j2 = j2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public int getVainqueurId() {
		return vainqueurId;
	}

	public void setVainqueurId(int vainqueurId) {
		this.vainqueurId = vainqueurId;
	}

	public String getMessages() {
		return messages;
	}
	
	public void setMessages(String messages) {
		this.messages = messages;
	}
	
	public String getListeCoups() {
		return listeCoups;
	}
	
	public void setListeCoups(String listeCoups) {
		this.listeCoups = listeCoups;
	}
	
	
	// Methodes
	public void ajouterCoup(String coup) {
		this.listeCoups= this.listeCoups.concat(coup);
	}

}
