package fr.echec.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.echec.classe.JsonViews;
import fr.echec.classe.JsonViews.Common;
import fr.echec.classe.historique.Statistiques;
import fr.echec.exception.HistoriquePartieNotFoundException;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.UtilisateurNotFoundException;
import fr.echec.service.StatistiquesService;

@RestController
@RequestMapping("/api/statistiques")
public class StatistiquesRestController {

	@Autowired
	private StatistiquesService srvStatistiques;

	@GetMapping("")
	@JsonView(JsonViews.Statistiques.class)
	public List<Statistiques> findAll() {
		return srvStatistiques.findAll();
	}

	@GetMapping("/{id}")
	@JsonView(Common.class)
	public Statistiques findById(@PathVariable("id") Integer id)
			throws IdNegatifException, HistoriquePartieNotFoundException {
		return srvStatistiques.findById(id);
	}

	@GetMapping("/utilisateur/{id}")
	@JsonView(Common.class)
	public Statistiques findByUtilisateur(@PathVariable("id") Integer id)
			throws IdNegatifException, HistoriquePartieNotFoundException, UtilisateurNotFoundException {
		return srvStatistiques.findByUtilisateur(id);

	}
}
