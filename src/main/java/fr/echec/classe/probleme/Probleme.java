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
import fr.echec.classe.jeu.Piece;
import fr.echec.classe.jeu.Plateau;
import fr.echec.classe.mouvements.Deplacement;
import fr.echec.service.ProblemeService;

@Entity
@Table(name = "probleme")
public class Probleme {

//	private ProblemeService srvProbleme = new ProblemeService();

	// VARIABLES from BDD
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prob_id")
	private int id;

	@Column(name = "prob_fen_depart", length = 100, nullable = false)
	private String fenDepart;

	@Column(name = "prob_liste_deplacement", length = 100)
	private String listeDeplacements;

	@Column(name = "prob_traitaublanc", nullable = false)
	private boolean traitAuBlanc;

	@Column(name = "prob_difficulte", nullable = false)
	private int difficulte;

	protected Deplacement d = new Deplacement();
	
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

	// construire plateau en meme temps
	
	// METHODES
//	String[] tabCoups = listeDeplacement.split(" ");
//	for(int i = 0; i < tabCoups.size(); i++) {
//		if (i%2 ==0) { //coup ordi
//			coupOrdi(plateau, tabCoups[i]);
//		}else { // coup joueur
//			seleciontPiecePb()
//			while()
//				verifBonCoup(
//		}
//	}

	// Jouer les coups par l'ordi découper le string avec "", upperCase, convertir        //PROBLEMES : tailles des problemes inconstant (4, 5, 6 déplacements)
	// en 64, prendre les 2 coordonnées et faire déplacement

	public void coupOrdi(Plateau p, String coupAJouer) {
		NotationCoup nt = new NotationCoup(0,0);
		int coorDepart = nt.conversionLettreTo64(coupAJouer.substring(0, 2));
		int coorArrivee = nt.conversionLettreTo64(coupAJouer.substring(2, 4));
		this.d.deplacement(p.getPieceCase(coorDepart), coorArrivee, p);
	}
	
	// Verif coup joué est le bon
	
	public boolean verifBonCoup(String coupJoueur, Plateau p, String coupAJouer) {
		NotationCoup nt = new NotationCoup(0,0);
		if (coupJoueur.equals(coupAJouer)) {
			this.d.deplacement(	p.getPieceCase(nt.conversionLettreTo64(coupJoueur.substring(0, 2))),
							nt.conversionLettreTo64(coupJoueur.substring(2, 4))
							,p);
			return true;
		}
		else {
			System.out.println("Ce n'est pas le bon coup! Reessayer");
			return false;
		}
	}
}

