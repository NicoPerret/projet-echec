package fr.echec.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import fr.echec.classe.JsonViews.Common;
import fr.echec.classe.joueur.Utilisateur;
import fr.echec.exception.IdNegatifException;
import fr.echec.exception.UtilisateurNotFoundException;
import fr.echec.service.UtilisateursService;

@RestController
@RequestMapping("/api/utilisateur")
@CrossOrigin(origins = "*")
public class UtilisateurRestController {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private UtilisateursService srvUtilisateurs;

	private Utilisateur utilisateur;

	@GetMapping("")
	@JsonView(Common.class)
	public List<Utilisateur> findAll(){
		return srvUtilisateurs.findAll();
	}
	
	@GetMapping("/{id}")
	@JsonView(Common.class)

	public Utilisateur findById(@PathVariable("id") Integer id)
			throws IdNegatifException, UtilisateurNotFoundException {
		
		utilisateur = srvUtilisateurs.findById(id);
		return utilisateur;
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(Common.class)
	public Utilisateur create(@Valid @RequestBody Utilisateur utilisateur, BindingResult br)
			throws UtilisateurNotFoundException, IdNegatifException {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		utilisateur.setMdp(passwordEncoder.encode(utilisateur.getMdp()));
		srvUtilisateurs.save(utilisateur);
		return srvUtilisateurs.findById(utilisateur.getId());

	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		try {
			srvUtilisateurs.deleteById(id);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@JsonView(Common.class)
	@PutMapping("/{id}")
	public Utilisateur replace(@PathVariable("id") Integer id, @Valid @RequestBody Utilisateur utilisateur,
			BindingResult br) throws IdNegatifException, UtilisateurNotFoundException {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		srvUtilisateurs.findById(id);
		utilisateur.setId(id);
		srvUtilisateurs.save(utilisateur);
		return utilisateur;
	}
}
