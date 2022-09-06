package fr.echec.classe.probleme;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.echec.classe.historique.NotationCoup;
import fr.echec.classe.jeu.Plateau;
import fr.echec.classe.mouvements.Deplacement;

@Entity
@Table(name = "probleme")
public class Probleme {
	

	// VARIABLES from BDD
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prob_id")
	private int id;
	
	@Column (name = "prob_fen_depart",length = 100, nullable = false)
	private String fenDepart;
	
	@Column (name = "prob_liste_deplacement",length = 100)
	private String listeDeplacement;
	
	@Column(name = "prob_traitaublanc", nullable = false)
	private boolean traitAuBlanc;
	
	@Column(name = "prob_difficulte", nullable = false)
	private int difficulte;

// GETTERS ET SETTERS 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFenDepart() {
		return fenDepart;
	}

	public void setFenDepart(String fenDepart) {
		this.fenDepart = fenDepart;
	}

	public String getListeDeplacement() {
		return listeDeplacement;
	}

	public void setListeDeplacement(String listeDeplacement) {
		this.listeDeplacement = listeDeplacement;
	}

	public boolean isTraitAuBlanc() {
		return traitAuBlanc;
	}

	public void setTraitAuBlanc(boolean traitAuBlanc) {
		this.traitAuBlanc = traitAuBlanc;
	}

	public int getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(int difficulte) {
		this.difficulte = difficulte;
	}

	// CONSTRUCTEUR

	// METHODES

	// Jouer les coups par l'ordi
	
	public void coupOrdi() {
		Deplacement d = new Deplacement();
		List coupOrdi = new ArrayList(); 
		for(int i : listeDeplacement.length()) { 
			if(coupOrdi[i]==){
			// jouer le coup après le "/" converti en fen;
			d.deplacement(p.getPieceCase(coorDepart), coorArrivee, p);
		}
	}
	
	// Verif coup joué est le bon
	
	public void verifBonCoup(NotationCoup coupJoueur, Plateau p) {
	Deplacement d = new Deplacement();
	Scanner sc = new Scanner(System.in);
	System.out.println("Saisissez une pièce");
	String saisie = sc.nextLine();
	int coorDepart = coupJoueur.conversionLettreTo64(saisie);
	System.out.println("Déplacer la piece");
	String saisie2 = sc.nextLine();
	int coorArrivee = coupJoueur.conversionLettreTo64(saisie2);
	
	NotationCoup coupEnregistre = new NotationCoup(0, 0); // remplacer les 0 par les coordonnées enregistrées dans la BDD problème
		if (coupJoueur.getCoordArriveeStandard().equals(coupEnregistre.getCoordArriveeStandard())== true) {
			d.deplacement(p.getPieceCase(coorDepart), coorArrivee, p);
		}
  else { System.out.println("Ce n'est pas le bon coup! Reessayer");
	  }
//	}
	
}}