package fr.echec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.echec.classe.joueur.Utilisateur;

@Service
public class UtilisateurDetailsService implements UserDetailsService {

	@Autowired
	private UtilisateursService srvUtilisateur;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur utilisateur = srvUtilisateur.findByPseudo(username);
		if (utilisateur != null) {
			return utilisateur;
		}
		throw new UsernameNotFoundException("utilisateur inconnu");
	}

}
