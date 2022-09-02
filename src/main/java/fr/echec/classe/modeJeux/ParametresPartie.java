package fr.echec.classe.modeJeux;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import fr.echec.classe.jeu.Chrono;
import fr.echec.enumerateur.TypePartie;

@Entity
@Table(name = "parametres")
public class ParametresPartie {
	
	// VARIABLES from BDD
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "par_id")
	private int id;	
	@Column(name = "par_chrono", nullable = false)
	private int paramChrono =5;
	
	@JoinColumn (name = "par_penalite_piece_id", nullable = false)
	private PenalitePiece penalitePiece;
	
	// VARIABLES
	// ATTENTION PENALITE ==> MODIFIER LE FEN
	private Chrono chrono = new Chrono(paramChrono, 0, 0); // a modif avec HTML
	private String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
	private int choixAdversaire; // a voir si boolean et a setup que si 1v1
	private TypePartie typeDeLaPartie;

	public int getchoixAdversaire() {
		return choixAdversaire;
	}

	public void setchoixAdversaire(int choixAdversaire) {
		this.choixAdversaire = choixAdversaire;
	}

	public TypePartie getTypeDeLaPartie() {
		return typeDeLaPartie;
	}

	public void setTypeDeLaPartie(TypePartie typeDeLaPartie) {
		this.typeDeLaPartie = typeDeLaPartie;
	}

	public Chrono getChrono() {
		return chrono;
	}

	public void setChrono(Chrono chrono) {
		this.chrono = chrono;
	}

	public String getFen() {
		return fen;
	}

	public void setFen(String fen) {
		this.fen = fen;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getParamChrono() {
		return paramChrono;
	}
	
	public void setParamChrono(int paramChrono) {
		this.paramChrono = paramChrono;
	}
	
// Constructeur

	public ParametresPartie() {
	}
	
	public ParametresPartie(int minute, int seconde, int increment, String fen) {
		this.chrono = new Chrono(minute, seconde, increment);
		this.fen = fen;
	}

	// Méthode
	public void choixMode() {
		System.out.println("Choisissez le type de partie : ");
		// ajouter system.in
		int choix = 0;
		// choix = readInt();

		switch (choix) {

		case 1:
			System.out.println("Problème");
			typeDeLaPartie = TypePartie.PROBLEME;
			break;

		case 2:
			System.out.println("Joueur contre Joueur");
			typeDeLaPartie = TypePartie.JOUEURVSJOUEUR;
			break;

		case 3:
			System.out.println("Joueur contre IA");
			typeDeLaPartie = TypePartie.JOUEURVSIA;
			break;

		default:
			System.out.println("Mauvais choix... recommencez");
		}
	}

}
