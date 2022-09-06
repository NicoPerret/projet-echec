package fr.echec.service;

import java.util.ArrayList;
import java.util.List;

import fr.echec.classe.historique.HistoriquePartie;
import fr.echec.classe.joueur.Utilisateur;
import fr.echec.exception.HistoriquePartieNotFoundException;
import fr.echec.exception.IdNegatifException;
import fr.echec.repository.IHistoriquePartie;
import fr.echec.repository.IUtilisateurs;
import fr.echec.repository.jpa.HistoriquePartieJpa;
import fr.echec.repository.jpa.UtilisateursJpa;

public class UtilisateursService {
	private IUtilisateurs repoUtilisateur = new UtilisateursJpa();

	public Utilisateur findById(int id) throws IdNegatifException, HistoriquePartieNotFoundException {

		if (id <= 0) {
			throw new IdNegatifException();
		}

		Utilisateur utilisateur = repoUtilisateur.findById(id);

		if (utilisateur == null) {
			throw new HistoriquePartieNotFoundException(); //
		}

		return utilisateur;
	}

	public List<Utilisateur> findAll() {
		
		List<Utilisateur> utilisateur = repoUtilisateur.findAll();

		if (utilisateur == null) {
			return new ArrayList<>();
		}

		return utilisateur;
	}

	public void save(Utilisateur utilisateur) throws HistoriquePartieNotFoundException {


		if (utilisateur == null) {
			throw new HistoriquePartieNotFoundException();
		}

		repoUtilisateur.save(utilisateur);

	}
}
