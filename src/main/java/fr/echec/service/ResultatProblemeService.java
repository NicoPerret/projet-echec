package fr.echec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.echec.classe.jeu.Plateau;
import fr.echec.classe.mouvements.analyse.CoupsPossibles;
import fr.echec.classe.mouvements.deplacement.Deplacement;
import fr.echec.classe.mouvements.deplacement.Promotion;
import fr.echec.classe.probleme.Probleme;
import fr.echec.exception.ProblemeNotFoundException;
import fr.echec.repository.IProbleme;
import fr.echec.repository.IResultatProbleme;

@Service
public class ResultatProblemeService {

	@Autowired
	private IResultatProbleme repoResultatProbleme;

	@Autowired
	protected Plateau p;

	@Autowired
	protected List<Integer> listeCoup;

	@Autowired
	protected CoupsPossibles coupPossible;

	@Autowired
	protected Deplacement d;

	@Autowired
	protected Promotion promo;
	
	@Autowired
	protected Probleme probleme;

	public void save(Probleme probleme) throws ProblemeNotFoundException {
		if (probleme == null) {
			throw new ProblemeNotFoundException();
		}

		repoResultatProbleme.save(probleme);

	}

}
