package fr.echec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.echec.classe.probleme.Probleme;

public interface IProbleme extends JpaRepository<Probleme, Integer> {
	
	public Probleme findById(int id);
	
	@Query(value ="select p from Probleme p where p.difficulte between ?1 and ?2")
	public List<Probleme> FindByDifficultyBetween (float a, float b);
}
