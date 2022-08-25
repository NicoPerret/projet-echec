package fr.echec.repository.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.echec.classe.HistoriquePartie;
import fr.echec.classe.joueur.Utilisateur;
import fr.echec.repository.IHistoriquePartie;

public class HistoriquePartieRepoSql extends RepositorySql implements IHistoriquePartie {

	// A FINIR : map et save

	// A FINIR
	private HistoriquePartie map(ResultSet myResult) {
		try {

			HistoriquePartie maPartie = new HistoriquePartie();
			Utilisateur j1 = new Utilisateur();
			Utilisateur j2 = new Utilisateur();
			// Message messages = new Message();
			// ID Partie

			maPartie.setId(myResult.getInt("hsp_id"));
			j1.setId(myResult.getInt("hsp_id_j1"));
			j2.setId(myResult.getInt("hsp_id_j2"));
			maPartie.setVainqueurId(myResult.getInt("hsp_vainqueur_id"));
			// maPartie.setDate( myResult.getTime("pro_prix_vente") );

			return maPartie;
		}

		catch (SQLException e) {
			return null;
		}
	}

	@Override
	public HistoriquePartie findById(int id) {
		try {
			PreparedStatement myStatement = this.prepare("SELECT * FROM produit WHERE pro_id = ?");

			myStatement.setInt(1, id);

			ResultSet myResult = myStatement.executeQuery();

			HistoriquePartie maPartie = null;

			if (myResult.next()) {
				maPartie = this.map(myResult);
			}

			return maPartie;
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			this.disconnect();
		}

		return null;
	}

	@Override
	public List<HistoriquePartie> findAll() {

		List<HistoriquePartie> parties = new ArrayList<>();

		try {
			PreparedStatement myStatement = this.prepare("SELECT * FROM produit");
			ResultSet myResult = myStatement.executeQuery();

			while (myResult.next()) {
				HistoriquePartie maPartie = this.map(myResult);
				parties.add(maPartie);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			this.disconnect();
		}

		return parties;
	}

	// A FINIR
	@Override
	public void save(HistoriquePartie entity) {
		try {
			PreparedStatement myStatement = this.prepare(
					"INSERT INTO historiquepartie (hsp_id_j1, hsp_id_j2, hsp_message_id, hsp_deplacements, hsp_vainqueur_id) VALUES (?,?,?,?,?)");

			// On injecte les paramètres à la requête (l'index commence à 1)
			myStatement.setInt(1, entity.getJ1().getId());
			myStatement.setInt(2, entity.getJ2().getId());
			myStatement.setInt(3, entity.getIdMessages()); // classe Message à completer
			
			// myStatement.setString(4, entity.); // A FINIR
			myStatement.setInt(2, entity.getVainqueurId());

			myStatement.execute();

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			this.disconnect();
		}

	}

	@Override
	public void deleteById(int id) {
		try {
			PreparedStatement myStatement = this.prepare("DELETE FROM fournisseur WHERE fou_id = ?");

			myStatement.setInt(1, id);

			myStatement.execute();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			this.disconnect();
		}

	}

}
