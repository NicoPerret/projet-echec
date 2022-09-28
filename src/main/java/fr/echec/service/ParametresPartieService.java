package fr.echec.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.echec.classe.parametres.ParametresPartie;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.ParametreNotFoundException;
import fr.echec.repository.IParametresPartie;

@Service
public class ParametresPartieService {

	private IParametresPartie repoParametresPartie;
	
	public ParametresPartie findById(int id) throws IdNegatifException, ParametreNotFoundException {
		if (id <= 0) {
			throw new IdNegatifException();

		}
		ParametresPartie leParametre = repoParametresPartie.findById(id);

		if (leParametre == null) {
			throw new ParametreNotFoundException(); //
		}

		return leParametre;
	}

	public List<ParametresPartie> findAll() {
		List<ParametresPartie> parametres = repoParametresPartie.findAll();

		if (parametres == null) {
			return new ArrayList<>();
		}

		return parametres;
	}

	public void save(ParametresPartie leParametre) throws ParametreNotFoundException {
		if (leParametre == null) {
			throw new ParametreNotFoundException();
		}

		repoParametresPartie.save(leParametre);

	}
	
	public void deleteById(int id) throws IdNegatifException {
		if (id <= 0) {
			throw new IdNegatifException();
		}
		
		repoParametresPartie.deleteById(id);
	}
	
}

