package fr.echec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.echec.classe.historique.HistoriquePartie;
import fr.echec.classe.parametres.ParametresPartie;

public interface IParametresPartie extends JpaRepository<ParametresPartie, Integer>  {
	public  ParametresPartie findById(int id);
}
