package fr.echec.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.probleme.Probleme;
import fr.echec.classe.probleme.ResultatProbleme;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.ProblemeNotFoundException;
import fr.echec.exception.ResultatProblemeNotFoundException;
import fr.echec.exception.UtilisateurNotFoundException;
import fr.echec.repository.IResultatProbleme;

@Service
public class ResultatProblemeService {

	@Autowired
	private IResultatProbleme repoResultatProbleme;
	
	protected ResultatProbleme probleme = new ResultatProbleme();

	public void save(@Valid ResultatProbleme probleme) throws ProblemeNotFoundException {
		if (probleme == null) {
			throw new ProblemeNotFoundException();
		}

		repoResultatProbleme.save(probleme);

	}
	public List<ResultatProbleme> findAll() {
		List<ResultatProbleme> resultatsPb = repoResultatProbleme.findAll();

		if (resultatsPb == null) {
			return new ArrayList<>();
		}

		return resultatsPb;
	}
//	public ResultatProbleme findByIdFetchUtilisateur(int id) throws UtilisateurNotFoundException, ResultatProblemeNotFoundException {
//		return repoResultatProbleme.findByIdFetchingUtilisateur(id).orElseThrow(ResultatProblemeNotFoundException::new);
//	}
	public ResultatProbleme findById(int id) throws IdNegatifException, ResultatProblemeNotFoundException {

		if (id <= 0) {
			throw new IdNegatifException();
		}

		ResultatProbleme probleme = repoResultatProbleme.findById(id);

		if (probleme == null) {
			throw new ResultatProblemeNotFoundException(); //
		}

		return probleme;
	}
	public void deleteById(Integer id) throws IdNegatifException {
		if (id <= 0) {
			throw new IdNegatifException();
		}

		repoResultatProbleme.deleteById(id);
	}

}
