package fr.eni.cliniqueVeterinaire.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.cliniqueVeterinaire.bo.Animal;
import fr.eni.cliniqueVeterinaire.bo.Client;
import fr.eni.cliniqueVeterinaire.bo.Personnel;

public class AnimalDAOJdbcImpl implements AnimalDAO {
	
	
	private Connection connection = null;
	
	private Animal getAnimalFromResultset(ResultSet resultSet) throws SQLException {
		Animal animal = new Animal("", "", "", "", "", 0l, "", "");
		animal.setNomAnimal(resultSet.getString("NomAnimal"));
		animal.setSexe(resultSet.getString("Sexe"));
		animal.setCouleur(resultSet.getString("Couleur"));
		animal.setRace(resultSet.getString("Race"));
		animal.setEspece(resultSet.getString("Espece"));
		animal.setCodeClient(resultSet.getLong("CodeClient"));
		animal.setTatouage(resultSet.getString("Tatouage"));
		animal.setAntecedents(resultSet.getString("Antecedents"));

		
		return animal;
	}
	
	private void FillStatementFromAnimal(PreparedStatement statement, Animal animal) throws SQLException {
		statement.setString(1, animal.getCodeAnimal());
		statement.setString(2, animal.getNomAnimal());
		statement.setString(3, animal.getSexe());
		statement.setString(4, animal.getCouleur());
		statement.setString(5, animal.getRace());
		statement.setString(6, animal.getEspece());
		statement.setLong(7, animal.getCodeClient());
		statement.setString(8, animal.getTatouage());
		statement.setString(9, animal.getAntecedents());


	}
	
	private void openConnection() throws DALException {
		try {
			if (connection == null)
				connection = JdbcTools.getConnection();
		} catch (SQLException e1) {
			throw new DALException("Erreur � l'ouverture de la connexion", e1);
		}
	}

	@Override
	public Animal selectByClient(int codeClient) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Animaux WHERE CodeClient=? and Archive=0";
		PreparedStatement statement = null;
		

		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, codeClient);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return getAnimalFromResultset(resultSet);
			}			
		} catch (SQLException e) {
			throw new DALException("Erreur � la r�cup�ration par code client de l'animal", e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur � la r�cup�ration par code client de l'animal", e);
			}
		}
		return null;
	}


	@Override
	public void delete(Animal animal) throws DALException {
		openConnection();

		String sql = "UPDATE Animaux SET Archive=?,"
				+ " WHERE CodeAnimal=?,NomAnimal=?,Sexe=?,Couleur=?,Race=?,Espece=?,CodeClient=?,Tatouage=?,Antecedents=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			FillStatementFromAnimal(statement, animal);
			statement.setInt(1, 1);
			statement.setString(2, animal.getCodeAnimal());
			statement.setString(3, animal.getNomAnimal());
			statement.setString(4, animal.getSexe());
			statement.setString(5, animal.getCouleur());
			statement.setString(6, animal.getRace());
			statement.setString(7, animal.getEspece());
			statement.setLong(8, animal.getCodeClient());
			statement.setString(9, animal.getTatouage());
			statement.setString(10, animal.getAntecedents());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'archivage d'un animal : " + animal, e);
		}		
	}

	@Override
	public void insert(Animal animal) throws DALException {
		openConnection();

		String sql = "INSERT INTO Animaux ([NomAnimal],[Sexe],[Couleur],[Race],[Espece],[CodeClient],[Tatouage],[Antecedents])"
				+ " VALUES " + "(?,?,?,?,?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			FillStatementFromAnimal(statement, animal);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur � l'insertion d'un membre du personnel : " + animal, e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur � l'insertion d'un membre du personnel: " + animal, e);
			}
		}
		
	}

	@Override
	public void update(Animal animal) throws DALException {
		openConnection();

		String sql = "UPDATE Personnels SET NomAnimal=?,Sexe=?,Couleur=?,Race=?,Espece=?,CodeClient=?,Tatouage=?,Antecedents=?,"
				+ " WHERE CodeAnimal=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			FillStatementFromAnimal(statement, animal);
			statement.setString(1, animal.getNomAnimal());
			statement.setString(2, animal.getSexe());
			statement.setString(3, animal.getCouleur());
			statement.setString(4, animal.getRace());
			statement.setString(5, animal.getEspece());
			statement.setLong(6, animal.getCodeClient());
			statement.setString(7, animal.getTatouage());
			statement.setString(8, animal.getAntecedents());
			statement.setString(9, animal.getCodeAnimal());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la mise � jour de l'animal: " + animal, e);
		}
		
	}

}


