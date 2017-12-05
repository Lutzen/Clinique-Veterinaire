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
		Client client = new Client("", "", "", "", "", "", "", "", "", "");
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
		statement.setString(7, client.getNumTel());
		statement.setString(8, client.getAssurance());
		statement.setString(9, client.getEmail());
		statement.setString(10, client.getRemarque());


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

				String sql = "SELECT CodeClient,NomClient,PrenomClient,Adresse1,Adresse2,CodePostal,Ville FROM Clients ";
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

		String sql = "SELECT * FROM Clients WHERE Nom=?";
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, nom);
			ResultSet resultSet = statement.executeQuery();
			return getClientFromResultset(resultSet);
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
	}

	@Override
	public void delete(Client client) throws DALException {
		openConnection();

		String sql = "UPDATE Personnel SET Archive=?,"
				+ " WHERE CodeClient=?,NomClient=?,PrenomClient=?,Adresse1=?,Adresse2=?,CodePostal=?,Ville=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			FillStatementFromClient(statement, client);
			statement.setInt(1, 1);
			statement.setString(2, client.getCodeClient());
			statement.setString(3, client.getNomClient());
			statement.setString(4, client.getPrenomClient());
			statement.setString(5, client.getAdresse1());
			statement.setString(6, client.getAdresse2());
			statement.setString(7, client.getCodePostal());
			statement.setString(8, client.getVille());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'archivage d'un client : " + client, e);
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
				+ " WHERE Nom=?,MotPasse=?,Role=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			FillStatementFromClient(statement, client);
			statement.setString(1, client.getNomClient());
			statement.setString(2, client.getPrenomClient());
			statement.setString(3, client.getAdresse1());
			statement.setString(4, client.getAdresse2());
			statement.setString(5, client.getCodePostal());
			statement.setString(6, client.getVille());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la mise à jour du client: " + client, e);
		}
		
	}


	

}
