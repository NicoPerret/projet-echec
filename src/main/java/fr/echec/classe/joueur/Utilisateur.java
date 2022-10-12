package fr.echec.classe.joueur;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonView;

import fr.echec.classe.JsonViews;
import fr.echec.classe.historique.HistoriquePartie;
import fr.echec.enumerateur.CouleursPiece;

@Entity
@Table(name = "utilisateurs")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Utilisateur implements UserDetails {

	private static final long serialVersionUID = 1L;

	// VARIABLES from BDD
	@JsonView(JsonViews.Common.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uti_id")
	protected int id;
	@JsonView(JsonViews.Common.class)
	@Column(name = "uti_pseudo", length = 20, nullable = false)
	protected String pseudo;
	@JsonView(JsonViews.Common.class)
	@Column(name = "uti_mdp", length = 150, nullable = false)
	protected String mdp;
	
	@JsonView(JsonViews.Common.class)
	@Column(name = "uti_nom", length = 20, nullable = false)
	protected String nom;
	
	@JsonView(JsonViews.Common.class)
	@Column(name = "uti_prenom", length = 20, nullable = false)
	protected String prenom;
	
	@JsonView(JsonViews.Common.class)
	@Column(name = "uti_elo", nullable = false)
	protected int elo = 800;
	
	@JsonView(JsonViews.Common.class)
	@Column(name = "uti_email", length = 50, nullable = false, unique=true)
	protected String email;
	
	@JsonView(JsonViews.Common.class)
	@Column(name = "uti_role", length = 10, nullable = false)
	protected String role = "USER";

	@ManyToMany(mappedBy = "joueurs")
	protected List<HistoriquePartie> historiqueParties;

	// VARIABLES
	@Transient
	protected CouleursPiece couleur;

	// GETTERS / SETTERS
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getElo() {
		return elo;
	}

	public void setElo(int elo) {
		this.elo = elo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CouleursPiece getCouleur() {
		return couleur;
	}

	public void setCouleur(CouleursPiece couleur) {
		this.couleur = couleur;
	}

	public List<HistoriquePartie> getHistoriqueParties() {
		return historiqueParties;
	}

	public void setHistoriqueParties(List<HistoriquePartie> historiqueParties) {
		this.historiqueParties = historiqueParties;
	}

	// Constructeurs
	public Utilisateur() {
	}

	public Utilisateur(String pseudo, String mdp, String nom, String prenom, String email, String role) {
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.role = role;
	}

	// Methodes
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
		// return Arrays.asList(new
		// SimpleGrantedAuthority("ROLE_"+getClass().getSimpleName().toUpperCase()));
	}

	@Override
	public String getPassword() {
		return mdp;
	}

	@Override
	public String getUsername() {
		return pseudo;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
