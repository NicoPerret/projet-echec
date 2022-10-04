package fr.echec;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import fr.echec.classe.joueur.Utilisateur;
import fr.echec.exception.UtilisateurNotFoundException;
import fr.echec.service.UtilisateursService;

@SpringBootTest
class ProjetEchecApplicationTests {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UtilisateursService srvUtilisateur;

	@Test
	void contextLoads() {
	}

	@Test
	@Transactional
	@Commit
	@Disabled
	void initUser() throws UtilisateurNotFoundException {
		Utilisateur admin = new Utilisateur("Admin_Nico", passwordEncoder.encode("adminNico"), "Perret", "Nicolas",
				"adminNico@gmail.com", "ADMIN");
		srvUtilisateur.save(admin);
		Utilisateur admin2 = new Utilisateur("Admin_Gortex", passwordEncoder.encode("adminGortex"), "Desmoulin",
				"Felix", "adminGortex@gmail.com", "ADMIN");
		srvUtilisateur.save(admin2);
		Utilisateur admin3 = new Utilisateur("Admin_Francois", passwordEncoder.encode("adminFrancois"), "Philippe",
				"Francois", "adminFrancois@gmail.com", "ADMIN");
		srvUtilisateur.save(admin3);
		Utilisateur admin4 = new Utilisateur("Admin_FM", passwordEncoder.encode("adminFM"), "Masson", "Francois-Marie",
				"adminFM@gmail.com", "ADMIN");
		srvUtilisateur.save(admin4);
	}
}
