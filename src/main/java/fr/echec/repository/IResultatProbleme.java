package fr.echec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.echec.classe.historique.HistoriquePartie;
import fr.echec.classe.probleme.Probleme;
import fr.echec.classe.probleme.ResultatProbleme;

public interface IResultatProbleme extends JpaRepository<ResultatProbleme, Integer> {
	public  ResultatProbleme findById(int id);

	public void save(Probleme probleme);
}
