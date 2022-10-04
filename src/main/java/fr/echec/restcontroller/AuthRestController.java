package fr.echec.restcontroller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.echec.classe.JsonViews;
import fr.echec.classe.joueur.Utilisateur;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthRestController {

	@GetMapping("")
	@JsonView(JsonViews.Utilisateur.class)
	public Utilisateur authentication(@AuthenticationPrincipal Utilisateur utilisateur) {
		return utilisateur;
	}
}