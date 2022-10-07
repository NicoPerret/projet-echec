package fr.echec.classe.probleme;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import fr.echec.classe.JsonViews;

@Entity
@Table(name = "probleme")
public class Probleme {

//	private ProblemeService srvProbleme = new ProblemeService();

	// VARIABLES from BDD
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prob_id")
	@JsonView(JsonViews.Common.class)
	private int id;

	@Column(name = "prob_fen_depart", length = 100, nullable = false)
	@JsonView(JsonViews.Common.class)
	private String fenDepart;

	@Column(name = "prob_liste_deplacements", length = 100)
	@JsonView(JsonViews.Common.class)
	private String listeDeplacements;

	@Column(name = "prob_traitaublanc", nullable = false)
	@JsonView(JsonViews.Common.class)
	private boolean traitAuBlanc;

	@Column(name = "prob_difficulte")
	@JsonView(JsonViews.Common.class)
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
		return listeDeplacements;
	}

	public void setListeDeplacement(String listeDeplacements) {
		this.listeDeplacements = listeDeplacements;
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
	public Probleme() {
		
	}

	// METHODES
	
}
