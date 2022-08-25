package fr.echec.classe;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.echec.classe.joueur.Utilisateur;

public class HistoriquePartie {

	/*
	 * Liste de tous les coups Peut ajouter un coup Récupère le nom des joueurs
	 * Récupère l'id de la partie Indique le numero du coup
	 */

	// Attributs
	private int id;
	private int compteurCoup = 0; // peut etre deja dans la classe partie
	private List<NotationCoup> listeCoups = new ArrayList<>();//peut etre stocker sous une liste de string et concatener pour la bdd
	private Utilisateur j1;
	private Utilisateur j2;
	private int idPartie; // a revoir avec la classe partie
	private int idMessages; // a revoir avec la classe message
	private LocalDateTime date; // a revoir avec bon format pour la bdd
	private int vainqueurId;

	// get-set methodes

	public int getCompteurCoup() {
		return compteurCoup;
	}

	public void setCompteurCoup(int compteurCoup) {
		this.compteurCoup = compteurCoup;
	}

	public List<NotationCoup> getListeCoups() {
		return listeCoups;
	}

	public void setListeCoups(List<NotationCoup> listeCoups) {
		this.listeCoups = listeCoups;
	}

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

	public int getIdPartie() {
		return idPartie;
	}

	public void setIdPartie(int idPartie) {
		this.idPartie = idPartie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdMessages() {
		return idMessages;
	}

	public void setIdMessages(int idMessages) {
		this.idMessages = idMessages;
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

	// Methodes
	public void ajouterCoup(NotationCoup coup) {
		this.listeCoups.add(coup);
	}
}
