package fr.echec.classe.historique;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.exception.HistoriquePartieNotFoundException;
import fr.echec.exception.UtilisateurNotFoundException;
import fr.echec.service.StatistiquesService;
import fr.echec.service.UtilisateursService;

@Service
@Entity
@Table(name = "statistiques")
public class Statistiques {

// VARIABLES 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stat_id")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "stat_utilisateur_id", nullable = false)
	private Utilisateur utilisateur;

	@Column(name = "stat_taux_victoire")
	private float tauxVictoire;

	@Column(name = "stat_parties_jouees")
	private int partiesJouees;

	@Autowired
	@Transient
	private UtilisateursService srvUtilisateur;

	@Autowired
	@Transient
	private StatistiquesService srvStat;

// GETTERS ET SETTERS 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public float getTauxVictoire() {
		return tauxVictoire;
	}

	public void setTauxVictoire(float tauxVictoire) {
		this.tauxVictoire = tauxVictoire;
	}

	public int getPartiesJouees() {
		return partiesJouees;
	}

	public void setPartiesJouees(float nbrPartie) {
		this.partiesJouees = (int) nbrPartie;
	}

// CONSTRUCTEUR 

// METHODES

	public void calculStats(Utilisateur utilisateur)
			throws UtilisateurNotFoundException, HistoriquePartieNotFoundException {

		List<HistoriquePartie> listParties = utilisateur.getHistoriqueParties();
		float nbrPartie = 0;
		float nbrVictoires = 0;
		
		for (HistoriquePartie historiquePartie : listParties) {

			nbrPartie++;

			
			if (historiquePartie.getVainqueurId() == utilisateur.getId()) {
				nbrVictoires++;
			}
		}
		
		this.setId(utilisateur.getId());
		this.setUtilisateur(utilisateur);
		this.setPartiesJouees(nbrPartie);
	
		
		this.setTauxVictoire(nbrVictoires/nbrPartie);
		srvStat.save(this);
	}

}
