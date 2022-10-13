package fr.echec.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.echec.classe.historique.Statistiques;
import fr.echec.classe.joueur.Utilisateur;
import fr.echec.exception.HistoriquePartieNotFoundException;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.UtilisateurNotFoundException;
import fr.echec.repository.IStatistiques;
import fr.echec.repository.IUtilisateurs;

@Service
public class StatistiquesService {
	@Autowired
	private IStatistiques repoStatistiques;
	
	@Autowired
	private IUtilisateurs repoUtilisateur;

	public Statistiques findById(int id) throws IdNegatifException, HistoriquePartieNotFoundException {

		if (id <= 0) {
			throw new IdNegatifException();
		}

		Statistiques stat = repoStatistiques.findById(id);

		if (stat == null) {
			throw new HistoriquePartieNotFoundException(); //
		}

		return stat;
	}

	public List<Statistiques> findAll() {

		List<Statistiques> stats = repoStatistiques.findAll();

		if (stats == null) {
			return new ArrayList<>();
		}

		return stats;
	}

	public void save(Statistiques stat) throws HistoriquePartieNotFoundException {


		if (stat == null) {
			throw new HistoriquePartieNotFoundException();
		}

		repoStatistiques.save(stat);

	}
	public void deleteById(int id) throws IdNegatifException {
		if (id <= 0) {
			throw new IdNegatifException();
		}
		
		repoStatistiques.deleteById(id);
	}
	
	
	public Statistiques findByUtilisateur(int id) throws UtilisateurNotFoundException {
		return repoStatistiques.findByUtilisateur(id).orElseThrow(UtilisateurNotFoundException::new);
	}
	
}
