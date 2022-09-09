package fr.echec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.echec.classe.historique.HistoriquePartie;

public interface IHistoriquePartie extends 	JpaRepository<HistoriquePartie, Integer> {
	public  HistoriquePartie findById(int id);
}
