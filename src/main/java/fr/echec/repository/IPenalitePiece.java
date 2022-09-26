package fr.echec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.echec.classe.historique.HistoriquePartie;
import fr.echec.classe.parametres.PenalitePiece;

public interface IPenalitePiece extends JpaRepository<PenalitePiece, Integer>  {
	public  PenalitePiece findById(int id);
}
