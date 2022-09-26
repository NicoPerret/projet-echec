package fr.echec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.echec.classe.joueur.Utilisateur;

public interface IUtilisateurs extends JpaRepository<Utilisateur,Integer> {
	
public  Utilisateur findById(int id);

@Query("select u from Utilisateur u left join fetch u.historiqueParties where u.id = ?1")
public Optional<Utilisateur> findByIdFetchingHistorique(int id);
}