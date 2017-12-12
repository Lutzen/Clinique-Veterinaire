package fr.eni.cliniqueVeterinaire.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import fr.eni.cliniqueVeterinaire.bo.Races;

public class RaceDAOJdbcImpl implements RaceDAO{
	

	private Connection connection = null;

	private void openConnection() throws DALException {
		try {
			if (connection == null)
				connection = JdbcTools.getConnection();
		} catch (SQLException e1) {
			throw new DALException("Erreur à l'ouverture de la connexion", e1);
		}
	}

	@Override
	public List<Races> selectByEspece(String espece) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Races WHERE Espece=? ";
		PreparedStatement statement = null;
		List<Races> race = new LinkedList<>();
		

		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, espece);
			ResultSet resultSet = statement.executeQuery();
			

			while(resultSet.next()) {
				Races espece1 = new Races("", "");
				espece1.setRace(resultSet.getString("Race"));
				race.add(espece1);
			}
			return race;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération d'une espèce", e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à la récupération d'une espèce", e);
			}
		}
	}

	@Override
	public List<Races> selectAll() throws DALException {
		openConnection();

		String sql = "select distinct Espece from Races   ";
		PreparedStatement statement = null;
		List<Races> espece = new LinkedList<>();
		

		try {
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			

			while(resultSet.next()) {
				Races espece2 = new Races("", "");
				espece2.setEspece(resultSet.getString("Espece"));
				espece.add(espece2);
			
			}
			return espece;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération des espèces", e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à la récupération des espèces", e);
			}
		}
	}

}
