package fr.eni.cliniqueVeterinaire.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import fr.eni.cliniqueVeterinaire.bo.Animal;


public class AnimalDAOJdbcImpl implements AnimalDAO {
	
	
	private Connection connection = null;
	
	private Animal getAnimalFromResultset(ResultSet resultSet) throws SQLException {
		Animal animal = new Animal(0,"", "", "", "", "", 0, "", "");
		animal.setNomAnimal(resultSet.getString("NomAnimal"));
		animal.setSexe(resultSet.getString("Sexe"));
		animal.setCouleur(resultSet.getString("Couleur"));
		animal.setRace(resultSet.getString("Race"));
		animal.setEspece(resultSet.getString("Espece"));
		animal.setCodeClient(resultSet.getInt("CodeClient"));
		animal.setTatouage(resultSet.getString("Tatouage"));
		animal.setAntecedents(resultSet.getString("Antecedents"));
		animal.setCodeAnimal(resultSet.getInt("CodeAnimal"));
		
		return animal;
	}
	
	private void FillStatementFromAnimal(PreparedStatement statement, Animal animal) throws SQLException {
		statement.setString(1, animal.getNomAnimal());
		statement.setString(2, animal.getSexe());
		statement.setString(3, animal.getCouleur());
		statement.setString(4, animal.getRace());
		statement.setString(5, animal.getEspece());
		statement.setInt(6, animal.getCodeClient());
		statement.setString(7, animal.getTatouage());
		statement.setString(8, animal.getAntecedents());



	}
	
	private void openConnection() throws DALException {
		try {
			if (connection == null)
				connection = JdbcTools.getConnection();
		} catch (SQLException e1) {
			throw new DALException("Erreur à l'ouverture de la connexion", e1);
		}
	}

	@Override
	public List<Animal> selectByClient(int codeClient) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Animaux WHERE CodeClient=? and Archive=0";
		PreparedStatement statement = null;
		List<Animal> animal = new LinkedList<>();
		

		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, codeClient);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				 animal.add(getAnimalFromResultset(resultSet));
			}
			return animal;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération par code client de l'animal", e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à la récupération par code client de l'animal", e);
			}
		}
	}


	@Override
	public void delete(Animal animal) throws DALException {
		openConnection();

		String sql = "UPDATE Animaux SET Archive=?"
				+ " WHERE CodeAnimal=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, 1);
			statement.setInt(2, animal.getCodeAnimal());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'archivage d'un animal : " + animal, e);
		}		
	}

	@Override
	public void insert(Animal animal) throws DALException {
		openConnection();

		String sql = "INSERT INTO Animaux ([NomAnimal],[Sexe],[Couleur],[Race],[Espece],[CodeClient],[Tatouage],[Antecedents],[Archive])"
				+ " VALUES " + "(?,?,?,?,?,?,?,?,0)";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			FillStatementFromAnimal(statement, animal);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur à l'insertion d'un animal : " + animal, e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à l'insertion d'un animal: " + animal, e);
			}
		}
		
	}

	@Override
	public void update(Animal animal) throws DALException {
		openConnection();

		String sql = "UPDATE Personnels SET NomAnimal=?,Sexe=?,Couleur=?,Race=?,Espece=?,CodeClient=?,Tatouage=?,Antecedents=?"
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
			statement.setInt(9, animal.getCodeAnimal());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la mise à jour de l'animal: " + animal, e);
		}
		
	}

	@Override
	public Animal selectByCode(int codeAnimal) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Animaux WHERE CodeAnimal=? and Archive=0";
		PreparedStatement statement = null;
		

		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, codeAnimal);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				  return getAnimalFromResultset(resultSet);
			}
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération par code client de l'animal", e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à la récupération par code client de l'animal", e);
			}
		}
		return null;
		
	}

}


