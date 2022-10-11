package fr.echec.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.echec.classe.historique.HistoriquePartie;
import fr.echec.exception.HistoriquePartieNotFoundException;
import fr.echec.exception.IdNegatifException;
import fr.echec.repository.IHistoriquePartie;

@Service
@Transactional
public class HistoriquePartieService {
	@Autowired
	private IHistoriquePartie repoPartie;

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

		List<HistoriquePartie> historiques = repoPartie.findAll();

		if (historiques == null) {
			return new ArrayList<>();
		}

		return historiques;
	}

	public void save(HistoriquePartie laPartie) throws HistoriquePartieNotFoundException {

		if (laPartie == null) {
			throw new HistoriquePartieNotFoundException();
		}

		repoPartie.save(laPartie);

	}

	public void deleteById(int id) throws IdNegatifException {
		if (id <= 0) {
			throw new IdNegatifException();
		}

		repoPartie.deleteById(id);
	}
}
