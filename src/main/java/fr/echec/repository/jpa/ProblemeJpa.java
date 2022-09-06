package fr.echec.repository.jpa;

import fr.echec.classe.probleme.Probleme;
import fr.echec.repository.IProbleme;

public class ProblemeJpa extends AbstractRepository<Probleme> implements IProbleme {

	public ProblemeJpa() {
		super(Probleme.class);
	}

	@Override
	public void save(Probleme entity) {
		super.save(entity);

	}

}
