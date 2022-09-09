package fr.echec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.echec.classe.probleme.Probleme;
import fr.echec.exception.ProblemeNotFoundException;
import fr.echec.repository.IResultatProbleme;

@Service
public class ResultatProblemeService {

	@Autowired
	private IResultatProbleme repoResultatProbleme;
	
	protected Probleme probleme = new Probleme();

	public void save(Probleme probleme) throws ProblemeNotFoundException {
		if (probleme == null) {
			throw new ProblemeNotFoundException();
		}

		repoResultatProbleme.save(probleme);

	}

}
