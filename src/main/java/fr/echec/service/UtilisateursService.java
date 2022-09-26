package fr.echec.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.exception.HistoriquePartieNotFoundException;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.UtilisateurNotFoundException;
import fr.echec.repository.IUtilisateurs;

@Service
public class UtilisateursService {
	@Autowired
	private IUtilisateurs repoUtilisateur ;

	public Utilisateur findById(int id) throws IdNegatifException, UtilisateurNotFoundException {

		if (id <= 0) {
			throw new IdNegatifException();
		}

		Utilisateur utilisateur = repoUtilisateur.findById(id);

		if (utilisateur == null) {
			throw new UtilisateurNotFoundException(); //
		}

		return utilisateur;
	}
	
	public Utilisateur findByIdFetchHistorique(int id) throws UtilisateurNotFoundException {
		return repoUtilisateur.findByIdFetchingHistorique(id).orElseThrow(UtilisateurNotFoundException::new);
	}

	public List<Utilisateur> findAll() {
		
		List<Utilisateur> utilisateur = repoUtilisateur.findAll();

		if (utilisateur == null) {
			return new ArrayList<>();
		}

		return utilisateur;
	}

	public void save(Utilisateur utilisateur) throws UtilisateurNotFoundException {


		if (utilisateur == null) {
			throw new UtilisateurNotFoundException();
		}

		repoUtilisateur.save(utilisateur);

	}
}
