package fr.eni.cliniqueVeterinaire.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.sql.Connection;

import fr.eni.cliniqueVeterinaire.bo.Personnel;

public class PersonnelDAOJdbcImpl implements PersonnelDAO {
	
	private Connection connection = null;
	
	private Personnel getPersonnelFromResultset(ResultSet resultSet) throws SQLException {
		Personnel personnel = new Personnel("", "", "");
		personnel.setNom(resultSet.getString("Nom"));
		personnel.setPass(resultSet.getString("MotPasse"));
		personnel.setRole(resultSet.getString("Role"));
		return personnel;
	}
	
	private void FillStatementFromPersonnel(PreparedStatement statement, Personnel personne) throws SQLException {
		statement.setString(1, personne.getNom());
		statement.setString(2, personne.getPass());
		statement.setString(3, personne.getRole());


	}
	
	private void openConnection() throws DALException {
		try {
			if (connection == null)
				connection = JdbcTools.getConnection();
		} catch (SQLException e1) {
			throw new DALException("Erreur à l'ouverture de la connexion", e1);
		}
	}
	
	public Personnel selectByNom(String nom) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Personnels WHERE Nom=? and Archive=0";
		PreparedStatement statement = null;
		

		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, nom);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return getPersonnelFromResultset(resultSet);
			}			
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération de la personne", e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à la récupération de la personne", e);
			}
		}
		return null;
	}
	
	public void delete(Personnel personne) throws DALException {
		openConnection();

		String sql = "UPDATE Personnels SET Archive=?"
				+ " WHERE Nom=? and MotPasse=? and Role=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			FillStatementFromPersonnel(statement, personne);
			statement.setInt(1, 1);
			statement.setString(2, personne.getNom());
			statement.setString(3, personne.getPass());
			statement.setString(4, personne.getRole());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'archivage d'un membre du personnel : " + personne, e);
		}
	}
		

	public void update(Personnel personne,String pass) throws DALException {
		openConnection();

		String sql = "UPDATE Personnels SET MotPasse=?"
				+ " WHERE Nom=? and MotPasse=? and Role=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			FillStatementFromPersonnel(statement, personne);
			statement.setString(1, pass);
			statement.setString(2, personne.getNom());
			statement.setString(3, personne.getPass());
			statement.setString(4, personne.getRole());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la mise à jour du mot de passe: " + personne, e);
		}
	}
	
	public List<Personnel> selectAll() throws DALException {
		openConnection();

		String sql = "SELECT Nom,MotPasse,Role FROM Personnels WHERE Archive=0";
		List<Personnel> personnels = new LinkedList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				personnels.add(getPersonnelFromResultset(resultSet));
			}
			return personnels;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération de tous le personnel", e);
		}
	}

	@Override
	public void insert(Personnel personne) throws DALException {
		openConnection();

		String sql = "INSERT INTO Personnels VALUES (?,?,?,0)";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			FillStatementFromPersonnel(statement, personne);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur à l'insertion d'un membre du personnel : " + personne, e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à l'insertion d'un membre du personnel: " + personne, e);
			}
		}

		
	}

	@Override
	public List<Personnel> selectByRole(String role) throws DALException {
		openConnection();

		String sql = "SELECT Nom,MotPasse,Role FROM Personnels WHERE role=? and Archive=0";
		PreparedStatement statement = null;
		List<Personnel> personnels = new LinkedList<>();

		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, role);
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				personnels.add(getPersonnelFromResultset(resultSet));
			}
			return personnels;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération du personnel dont le role est:"+ role, e);
		}
	}

}
