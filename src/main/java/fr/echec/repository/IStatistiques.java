package fr.echec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.echec.classe.historique.Statistiques;
import fr.echec.classe.joueur.Utilisateur;

public interface IStatistiques extends 	JpaRepository<Statistiques, Integer> {
	public  Statistiques findById(int id);
	
	
	
	@Query("select s from Statistiques s left join fetch s.utilisateur where s.utilisateur.id = ?1")
	public Optional<Statistiques> findByUtilisateur(int id);
	
	
	
}
