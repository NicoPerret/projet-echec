package fr.echec.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.echec.classe.parametres.PenalitePiece;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.PenaliteNotFoundException;
import fr.echec.repository.IPenalitePiece;

@Service
public class PenalitePieceService {

	private IPenalitePiece repoPenalitePiece;
	
	public PenalitePiece findById(int id) throws IdNegatifException, PenaliteNotFoundException {
		if (id <= 0) {
			throw new IdNegatifException();

		}
		PenalitePiece laPenalite = repoPenalitePiece.findById(id);

		if (laPenalite == null) {
			throw new PenaliteNotFoundException(); //
		}

		return laPenalite;
	}

	public List<PenalitePiece> findAll() {
		List<PenalitePiece> penalites = repoPenalitePiece.findAll();

		if (penalites == null) {
			return new ArrayList<>();
		}

		return penalites;
	}

	public void save(PenalitePiece laPenalite) throws PenaliteNotFoundException {
		if (laPenalite == null) {
			throw new PenaliteNotFoundException();
		}

		repoPenalitePiece.save(laPenalite);

	}
	
	public void deleteById(int id) throws IdNegatifException {
		if (id <= 0) {
			throw new IdNegatifException();
		}
		
		repoPenalitePiece.deleteById(id);
	}
	
	
}


