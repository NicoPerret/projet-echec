package fr.echec.repository.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class RepositorySql {
	
	private Connection connection;

	protected Connection connect() throws SQLException {
		
		this.connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/echecs", "postgres", "root");

		return this.connection;
	}

	
	protected PreparedStatement prepare(String query) throws SQLException {

		return this.connect().prepareStatement(query);
	}

	// Méthode pour se déconnecter
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
