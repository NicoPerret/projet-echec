package fr.echec.classe;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.modeJeux.ParametresPartie;


@Entity
@Table(name = "historiquepartie")
public class HistoriquePartie {

	/*
	 * Liste de tous les coups Peut ajouter un coup Récupère le nom des joueurs
	 * Récupère l'id de la partie Indique le numero du coup
	 */
	
	

	// VARIABLES to BDD
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hsp_id")
	private int id;
	
	@ManyToOne
	@JoinColumn (name = "hsp_id_J1", nullable = false)
	private Utilisateur j1;
	
	@ManyToOne
	@JoinColumn (name = "hsp_id_J2", nullable = false)
	private Utilisateur j2;
	
	@Column (name = "hsp_messages", nullable = false)
	@Lob
	private String messages; // a revoir avec la classe message
	
	@Column (name = "hsp_liste_coups", nullable = false)
	@Lob
	private String listeCoups = "1 : ";//peut etre stocker sous une liste de string et concatener pour la bdd
	
	@Column(name = "hsp_date")
	private LocalDateTime date; // a revoir avec bon format pour la bdd
	
	@Column(name = "hsp_vainqueur_id")
	private int vainqueurId;
	
	@ManyToOne
	@JoinColumn(name = "hsp_param_id", nullable = false)
	private ParametresPartie param; 
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
	
	public ParametresPartie getParam() {
		return param;
	}
	
	public void setParam(ParametresPartie param) {
		this.param = param;
	}
	
	// Methodes
	public void ajouterCoup(String coup) {
		this.listeCoups= this.listeCoups.concat(coup);
	}


}
