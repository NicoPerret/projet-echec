package fr.echec.repository.jpa;

import fr.echec.classe.historique.HistoriquePartie;
import fr.echec.repository.IHistoriquePartie;

public class HistoriquePartieJpa extends AbstractRepository<HistoriquePartie> implements IHistoriquePartie {

	public HistoriquePartieJpa() {
		super(HistoriquePartie.class);
		
	}

	@Override
	public void save(HistoriquePartie entity) {
		super.save(entity, (entity.getId() == 0));
		
		
	}}
