package fr.echec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.echec.classe.joueur.Utilisateur;

public interface IUtilisateurs extends JpaRepository<Utilisateur,Integer> {
public  Utilisateur findById(int id);
}
