package fr.echec.classe.joueur;

import fr.echec.enumerateur.CouleursPiece;

public class Utilisateur {
	// VARIABLES from BDD
	protected  int id;
	protected String pseudo;
	protected String mdp;
	protected String nom;
	protected String prenom;
	protected int elo;
	protected String email;
	
	// VARIABLES
	protected CouleursPiece couleur;
	
	
	//GETTERS / SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getElo() {
		return elo;
	}
	public void setElo(int elo) {
		this.elo = elo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public CouleursPiece getCouleur() {
		return couleur;
	}
	public void setCouleur(CouleursPiece couleur) {
		this.couleur = couleur;
	}
	
	
	//Methodes
	public void jouer() {
	}
	
	public void ecrireChat(){
	}
	
	public void regarderSpectateur (String nom) {
		
	}
	
	public void choisirRegles() {
		//System.out.println("Choisissez votre mode de jeu : ");
		//choixMode();
		// if mode 1vs1 --> n° adversaire ou aléatoire
		//System.out.println("Choisissez votre temps : ");
		//decompte({
		//int temps = readInt();
		//System.out.println("Vous êtes prêt à jouer");
		}
	
	public void recupererHistorique() {
		
	}

}
