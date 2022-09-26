package fr.echec.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.echec.classe.probleme.Probleme;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.ProblemeNotFoundException;
import fr.echec.repository.IProbleme;

@Service
public class ProblemeService {
	@Autowired
	private IProbleme repoProbleme;

	public Probleme findById(int id) throws IdNegatifException, ProblemeNotFoundException {
		if (id <= 0) {
			throw new IdNegatifException();

		}
		Probleme leProbleme = repoProbleme.findById(id);

		if (leProbleme == null) {
			throw new ProblemeNotFoundException(); //
		}

		return leProbleme;
	}

	public List<Probleme> findAll() {
		List<Probleme> problemes = repoProbleme.findAll();

		if (problemes == null) {
			return new ArrayList<>();
		}

		return problemes;
	}

	public void save(Probleme leProbleme) throws ProblemeNotFoundException {
		if (leProbleme == null) {
			throw new ProblemeNotFoundException();
		}

		repoProbleme.save(leProbleme);

	}
	
	public void deleteById(int id) throws IdNegatifException {
		if (id <= 0) {
			throw new IdNegatifException();
		}
		repoProbleme.deleteById(id);
	}
	
	public List<Probleme> findByDifficultyBetween (int a, int b) {
		List<Probleme> listeProblemes = repoProbleme.findByDifficultyBetween(a, b);
		return listeProblemes;
	}
}
