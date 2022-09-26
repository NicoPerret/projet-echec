package fr.echec.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.UtilisateurNotFoundException;
import fr.echec.service.UtilisateursService;

@RestController
@RequestMapping("/PageProfil")
public class ProfilRestController {

	@Autowired
	private UtilisateursService srvUtilisateurs;
	
	@GetMapping("")
	public Utilisateur findById(Integer id) throws IdNegatifException, UtilisateurNotFoundException {
	return srvUtilisateurs.findById(id);
	
	
}}
