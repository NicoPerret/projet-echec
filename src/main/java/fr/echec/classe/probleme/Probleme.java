package fr.echec.classe.probleme;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.echec.service.ProblemeService;

@Entity
@Table(name = "probleme")
public class Probleme {
	
	//private ProblemeService srvProbleme = new ProblemeService();

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
	//public void coupOrdi() {
		//for(un caractère "lambda" sur une chaîne de caractères) { 
		//	if("lambda"=="/"){
		//jouer le coup après le "/" converti en fen;
//		}
//	}
//	
	// Verif coup joué est le bon
	
//	public void bonCoup() {
//		if (coup joué par joueur == coup stocké dans problème) {
//	  faire le coup sur l'échiquier;
//	  else { System.out.println("Ce n'est pas le bon coup! Reessayer");
//	  }
//	}
	
}
