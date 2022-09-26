package fr.echec.classe.historique;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.parametres.ParametresPartie;


@Entity
@Table(name = "historiquepartie")
public class HistoriquePartie {

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
	private String listeCoups = "";//peut etre stocker sous une liste de string et concatener pour la bdd
	
	@Column(name = "hsp_date")
	private LocalDateTime date; // a revoir avec bon format pour la bdd
	
	@Column(name = "hsp_vainqueur_id")
	private int vainqueurId;
	
	@ManyToOne
	@JoinColumn(name = "hsp_param_id")
	private ParametresPartie param; 
	
	@ManyToMany
	@JoinTable(
			name = "historique_joueur",
			joinColumns = @JoinColumn(name = "histjou_historique_partie_id"),
			inverseJoinColumns = @JoinColumn(name = "histjou_utilisateur_id"))
	protected List<Utilisateur> joueurs;
	
	// VARIABLES
	
	// GETTERS - SETTERS

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
	
	public List<Utilisateur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(List<Utilisateur> joueurs) {
		this.joueurs = joueurs;
	}

	// Methodes
	public void ajouterCoup(String coup) {
		this.listeCoups= this.listeCoups.concat(coup);
	}


}
