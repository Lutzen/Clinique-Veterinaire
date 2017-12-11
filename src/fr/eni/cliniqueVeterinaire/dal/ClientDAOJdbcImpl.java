package fr.eni.cliniqueVeterinaire.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import fr.eni.cliniqueVeterinaire.bo.Client;


public class ClientDAOJdbcImpl implements ClientDAO {
	
private Connection connection = null;
	
	private Client getClientFromResultset(ResultSet resultSet) throws SQLException {
		Client client = new Client();
		client.setCodeClient(resultSet.getInt("CodeClient"));
		client.setNomClient(resultSet.getString("NomClient"));
		client.setPrenomClient(resultSet.getString("PrenomClient"));
		client.setAdresse1(resultSet.getString("Adresse1"));
		client.setAdresse2(resultSet.getString("Adresse2"));
		client.setCodePostal(resultSet.getString("CodePostal"));
		client.setVille(resultSet.getString("Ville"));
		client.setNumTel(resultSet.getString("NumTel"));
		client.setAssurance(resultSet.getString("Assurance"));
		client.setEmail(resultSet.getString("Email"));
		client.setRemarque(resultSet.getString("Remarque"));
		return client;
	}
	
	private void FillStatementFromClient(PreparedStatement statement, Client client) throws SQLException {
		statement.setString(1, client.getNomClient());
		statement.setString(2, client.getPrenomClient());
		statement.setString(3, client.getAdresse1());
		statement.setString(4, client.getAdresse2());
		statement.setString(5, client.getCodePostal());
		statement.setString(6, client.getVille());



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
	public List<Client> selectAll() throws DALException {

				openConnection();

				String sql = "SELECT * FROM Clients WHERE Archive=0 ";
				List<Client> client = new LinkedList<>();

				try {
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(sql);
					while (resultSet.next()) {
						client.add(getClientFromResultset(resultSet));
					}
					return client;
				} catch (SQLException e) {
					throw new DALException("Erreur à la récupération de tous les clients", e);
				}
			}
	

	@Override
	public Client selectByNom(String nom) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Clients WHERE NomClient=? and Archive=0";
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, nom);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
			return getClientFromResultset(resultSet);
			}
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération du client", e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à la récupération du client", e);
			}
		}
		return null;
	}

	@Override
	public void delete(int codeClient) throws DALException {
		openConnection();

		String sql = "UPDATE Personnel SET Archive=?"
				+ " WHERE CodeClient=? ";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, 1);
			statement.setInt(2, codeClient);
		
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'archivage d'un client : " + codeClient, e);
		}
		
	}

	@Override
	public void insert(Client client) throws DALException {
		openConnection();
		
		String sql = "INSERT INTO Clients ([NomClient],[PrenomClient],[Adresse1],[Adresse2],[CodePostal],[Ville])"
				+ " VALUES " + "(?,?,?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			FillStatementFromClient(statement, client);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur à l'insertion d'un Client : " + client, e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à l'insertion d'un Client : " + client, e);
			}
		}
		
	}

	@Override
	public void update(Client client) throws DALException {
		openConnection();

		String sql = "UPDATE Clients SET NomClient=?,PrenomClient=?,Adresse1=?,Adresse2=?,CodePostal=?,Ville=?"
				+ " WHERE Nom=? and MotPasse=? and Role=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			FillStatementFromClient(statement, client);
				statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la mise à jour du client: " + client, e);
		}
		
	}

	@Override
	public Client selectByCode(int codeClient) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Clients WHERE CodeClient=? and Archive=0";
		PreparedStatement statement = null;
		

		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, codeClient);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				  return getClientFromResultset(resultSet);
			}
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération par code  du client"+ codeClient, e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à la récupération par code du client"+ codeClient, e);
			}
		}
		return null;
	}

	@Override
	public List<Client> selectByMotCle(String nom) throws DALException {
		openConnection();

		String sql = "select * from Clients WHERE NomClient like ?";
		List<Client> client = new LinkedList<>();
		PreparedStatement statement = null;
		

		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, nom +"%");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				
				client.add(getClientFromResultset(resultSet));
			}
			return client;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération des clients par mot clé"+ nom, e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à la récupération des clients par mot clé"+ nom, e);
			}
		}
		//return null;
	}


	

}
