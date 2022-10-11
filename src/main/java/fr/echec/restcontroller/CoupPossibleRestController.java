package fr.echec.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.echec.classe.historique.NotationCoup;
import fr.echec.classe.jeu.Plateau;
import fr.echec.classe.mouvements.analyse.CoupsPossibles;

@RestController
@RequestMapping("/api/coup-possible")
@CrossOrigin(origins = "*")
public class CoupPossibleRestController {

	@Autowired
	protected CoupsPossibles coupPossible;

	@PostMapping("/{coord}")
	public List<String> coupPossible(@RequestBody Plateau plateau, @PathVariable("coord") String coord) {
		int coordDepart = NotationCoup.conversionLettreTo64(coord);
		List<Integer> listeCoup64 = coupPossible.trouveDestinationsPossibles(plateau,
				plateau.getPieceCase(coordDepart));
		List<String> listeCoup = new ArrayList<>();
		for (Integer i : listeCoup64) {
			listeCoup.add(NotationCoup.conversion64ToLettre(i));
		}
		return listeCoup;

	}
}
