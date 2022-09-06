package fr.echec.repository.jpa;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.repository.IUtilisateurs;

public class UtilisateursJpa extends AbstractRepository<Utilisateur> implements IUtilisateurs {

	public UtilisateursJpa() {
		super(Utilisateur.class);
		
	}

	@Override
	public void save(Utilisateur entity) {
		super.save(entity);
		
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	
	


	

	

}
