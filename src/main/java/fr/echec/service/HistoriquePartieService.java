package fr.echec.service;

import java.util.ArrayList;
import java.util.List;

import fr.echec.classe.historique.HistoriquePartie;
import fr.echec.exception.HistoriquePartieNotFoundException;
import fr.echec.exception.IdNegatifException;
import fr.echec.repository.IHistoriquePartie;
import fr.echec.repository.jpa.HistoriquePartieJpa;

public class HistoriquePartieService {
	private IHistoriquePartie repoPartie = new HistoriquePartieJpa();

	public HistoriquePartie findById(int id) throws IdNegatifException, HistoriquePartieNotFoundException {

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
		IHistoriquePartie repoProduit = new HistoriquePartieJpa();
		List<HistoriquePartie> historiques = repoProduit.findAll();

		if (historiques == null) {
			return new ArrayList<>();
		}

		return historiques;
	}

	public void save(HistoriquePartie laPartie) throws HistoriquePartieNotFoundException {

		IHistoriquePartie repoFournisseur = new HistoriquePartieJpa();

		if (laPartie == null) {
			throw new HistoriquePartieNotFoundException();
		}

		repoFournisseur.save(laPartie);

	}
}
