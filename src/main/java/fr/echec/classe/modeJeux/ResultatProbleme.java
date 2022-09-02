package fr.echec.classe.modeJeux;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.echec.classe.joueur.Utilisateur;

@Entity
@Table(name = "resultatProbleme")
public class ResultatProbleme {

	// VARIABLES 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "res_id")
	private int id; 
	@ManyToOne
	@JoinColumn (name = "res_utilisateur_id", nullable = false)
	private Utilisateur utilisateur;
	
	@ManyToOne
	@JoinColumn (name = "res_probleme_id", nullable = false)
	private Probleme probleme; 
	
	@Column(name = "res_date", nullable = false)
	private LocalDateTime date;
	
	// GETTERS ET SETTERS 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Probleme getProbleme() {
		return probleme;
	}
	public void setProbleme(Probleme probleme) {
		this.probleme = probleme;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	// CONSTRUCTEUR 

	// METHODES
}
