package fr.echec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.echec.classe.historique.Statistiques;

public interface IStatistiques extends 	JpaRepository<Statistiques, Integer> {
	public  Statistiques findById(int id);
}
