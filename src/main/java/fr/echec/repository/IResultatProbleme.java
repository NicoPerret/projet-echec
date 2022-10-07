package fr.echec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.echec.classe.historique.HistoriquePartie;
import fr.echec.classe.joueur.Utilisateur;
import fr.echec.classe.probleme.Probleme;
import fr.echec.classe.probleme.ResultatProbleme;

public interface IResultatProbleme extends JpaRepository<ResultatProbleme, Integer> {
	public  ResultatProbleme findById(int id);


//@Query("select * from ResultatProbleme where res_utilisateur_id = ?1")
//public Optional<ResultatProbleme> findByIdFetchingUtilisateur(int id);

}