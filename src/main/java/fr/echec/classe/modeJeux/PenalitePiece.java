package fr.echec.classe.modeJeux;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "penalitePiece")
public class PenalitePiece {
	
// VARIABLES 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pen_id")
private int id;
	@Column(name = "pen_nom",length = 20, nullable = false)
private String nom;
	@Column(name = "pen_fen", length = 100, nullable = false)
private String fen;

// GETTERS ET SETTERS 

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getFen() {
	return fen;
}
public void setFen(String fen) {
	this.fen = fen;
}

// CONSTRUCTEUR 

// METHODES
}
