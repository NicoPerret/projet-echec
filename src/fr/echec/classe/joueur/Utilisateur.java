package fr.echec.classe.joueur;

public class Utilisateur {
	protected  int id;
	protected String pseudo;
	protected String mdp;
	protected String nom;
	protected String prenom;
	protected String email;
	protected int elo;
	protected String couleur;
	
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
	public int getElo() {
		return elo;
	}
	public void setElo(int elo) {
		this.elo = elo;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
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
