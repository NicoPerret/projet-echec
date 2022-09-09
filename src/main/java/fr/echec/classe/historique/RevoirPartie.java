package fr.echec.classe.historique;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.echec.service.HistoriquePartieService;

@Service
public class RevoirPartie {
	@Autowired
	private HistoriquePartieService srvHistPartie;
	
	private HistoriquePartie histPartie;
	
	
	public void trouveHistoriquePartie(int id) {
		try {
			this.histPartie = srvHistPartie.findById(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void donneInfosPartie() {
		// Affiche les infos associées à la partie
	}
	
	public void generationPartie() {
		// Generer le plateau, joueurs, ...
	}
	
	public void defilementAvant() {
		// avancer
	}
	
	public void defilementArriere() {
		 // reculer
	}
	
	public void demanderUnTrucAuMonsieur() {
		// quand on fait un scanner
	}
	
	public void affichagePlateauEnCours() {
		// On affiche le plateau à un instant t
		// On affiche aussi le numéro du coup (ex : 12 / 50)
	}
	
	public void finPartie() {
		// On affiche les infos de fin de partie (Qui a gagné ? Pq ?)
		// On demande confirmation avant de quitter ou retour arriere
	}
	
	public void revoirPartieApplication(int id) {
		// C'est ce qu'on appelle depuis application
		
		trouveHistoriquePartie(id);
		
		donneInfosPartie();
		
		generationPartie();
		
	}

}
