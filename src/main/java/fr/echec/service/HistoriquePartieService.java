package fr.echec.service;

import java.util.ArrayList;
import java.util.List;

import fr.echec.classe.HistoriquePartie;
import fr.echec.exception.HistoriquePartieNotFoundException;
import fr.echec.exception.IdNegatifException;
import fr.echec.repository.IHistoriquePartie;
import fr.echec.repository.sql.HistoriquePartieRepoSql;

public class HistoriquePartieService {

	public HistoriquePartie findById(int id) throws IdNegatifException, HistoriquePartieNotFoundException {
		IHistoriquePartie repoPartie = new HistoriquePartieRepoSql();

		if (id <= 0) {
			throw new IdNegatifException();
		}

		HistoriquePartie laPartie = repoPartie.findById(id);

		if (laPartie == null) {
			throw new HistoriquePartieNotFoundException(); //
		}

		return laPartie;
	}

	public List<HistoriquePartie> findAll() {
		IHistoriquePartie repoProduit = new HistoriquePartieRepoSql();
		List<HistoriquePartie> historiques = repoProduit.findAll();

		if (historiques == null) {
			return new ArrayList<>();
		}

		return historiques;
	}

	public void save(HistoriquePartie laPartie) throws HistoriquePartieNotFoundException {

		IHistoriquePartie repoFournisseur = new HistoriquePartieRepoSql();

		if (laPartie == null) {
			throw new HistoriquePartieNotFoundException();
		}

		repoFournisseur.save(laPartie);

	}
}
