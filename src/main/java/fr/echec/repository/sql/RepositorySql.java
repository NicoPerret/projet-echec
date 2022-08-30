package fr.echec.repository.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class RepositorySql {
	
	// VARIABLES
	private Connection connection;
	
	// Connexion à la BDD
	protected Connection connect() throws SQLException {
		
		this.connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/echecs", "postgres", RepositorySqlParametres.sqlCodeConnection);

		return this.connection;
	}

	// Envoie une requête
	
	protected PreparedStatement prepare(String query) throws SQLException {

		return this.connect().prepareStatement(query);
	}

	
	// Déconnexion de la BDD
	protected void disconnect() {
		if (this.connection != null) {
			try {
				this.connection.close();
			}

			catch (SQLException e) {
				System.out.println("La déconnexion n'a pas fonctionné.");
			}
		}
	}
	
	
	
	
}
