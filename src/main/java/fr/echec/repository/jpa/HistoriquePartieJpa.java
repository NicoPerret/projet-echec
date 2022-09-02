package fr.echec.repository.jpa;

import fr.echec.classe.HistoriquePartie;
import fr.echec.repository.IHistoriquePartie;

public class HistoriquePartieJpa extends AbstractRepository<HistoriquePartie> implements IHistoriquePartie {

	public HistoriquePartieJpa() {
		super(HistoriquePartie.class);
		
	}

	@Override
	public void save(HistoriquePartie entity) {
		super.save(entity, (entity.getId() == 0));
		
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public HistoriquePartie findById(int id) {
		
		return null;
	}


	


	

	

}
