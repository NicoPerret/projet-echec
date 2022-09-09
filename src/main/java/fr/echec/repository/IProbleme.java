package fr.echec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.echec.classe.probleme.Probleme;

public interface IProbleme extends JpaRepository<Probleme, Integer> {
	public Probleme findById(int id);
}
