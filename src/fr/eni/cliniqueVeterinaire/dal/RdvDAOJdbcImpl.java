package fr.eni.cliniqueVeterinaire.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import fr.eni.cliniqueVeterinaire.dal.RdvDAO;
import fr.eni.cliniqueVeterinaire.bo.Rdv;
import fr.eni.cliniqueVeterinaire.dal.DALException;

public class RdvDAOJdbcImpl implements RdvDAO {

	private Connection connection = null;

	private Rdv getRdvFromResultset(ResultSet resultSet) throws SQLException {
		Rdv rdv = new Rdv(0, null, 0);
		rdv.setCodeVeto(resultSet.getInt("CodeVeto"));
		rdv.setDateRdv(resultSet.getDate("DateRdv"));
		rdv.setCodeAnimal(resultSet.getInt("CodeAnimal"));
		
		return rdv;
	}

	private void FillStatementFromRdv(PreparedStatement statement, Rdv rdv) throws SQLException {
		statement.setLong(1, rdv.getCodeVeto());
		statement.setDate(2, rdv.getDateRdv());
		statement.setLong(3, rdv.getCodeAnimal());


	}

	public void insert(Rdv rdv) throws DALException {

		openConnection();

		String sql = "INSERT INTO Agendas ([CodeVeto],[DateRdv],[CodeAnimal])"
				+ " VALUES " + "(?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			FillStatementFromRdv(statement, rdv);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur à l'insertion d'un RDV : " + rdv, e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à l'insertion d'un RDV : " + rdv, e);
			}
		}

	}

	public List<Rdv> selectById(int codeveto) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Agendas where CodeVeto=? order by DateRdv ASC";
		List<Rdv> rdv = new LinkedList<>();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, codeveto);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				rdv.add(getRdvFromResultset(resultSet));
			}
			return rdv;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération d'un code veterinaire : " + codeveto, e);
		}
	}

	private void openConnection() throws DALException {
		try {
			if (connection == null)
				connection = JdbcTools.getConnection();
		} catch (SQLException e1) {
			throw new DALException("Erreur à l'ouverture de la connexion", e1);
		}
	}

	public List<Rdv> selectAll() throws DALException {
		openConnection();

		String sql = "SELECT * FROM Agendas order by DateRdv ASC";
		List<Rdv> rdv = new LinkedList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				rdv.add(getRdvFromResultset(resultSet));
			}
			return rdv;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération de tous les RDV", e);
		}
	}

	

	public void update(Rdv oldRdv,Rdv newRdv) throws DALException {
		openConnection();

		String sql = "UPDATE Agendas SET CodeVeto=?,dateRdv=?,CodeAnimal=?"
				+ " WHERE CodeVeto=? and dateRdv=? and CodeAnimal=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			FillStatementFromRdv(statement, newRdv);
			FillStatementFromRdv(statement, oldRdv);
			statement.setLong(1, newRdv.getCodeVeto());
			statement.setDate(2, newRdv.getDateRdv());
			statement.setLong(3, newRdv.getCodeAnimal());
			statement.setLong(4, oldRdv.getCodeVeto());
			statement.setDate(5, oldRdv.getDateRdv());
			statement.setLong(6, oldRdv.getCodeAnimal());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'update d'un RDV : " + newRdv, e);
		}
	}

	public void delete(Rdv rdv) throws DALException {
		openConnection();

		String sql = "DELETE FROM Agendas WHERE CodeVeto=? and dateRdv=? and CodeAnimal=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, rdv.getCodeVeto());
			statement.setDate(2, rdv.getDateRdv());
			statement.setLong(3, rdv.getCodeAnimal());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la suppression d'un rdv : " + rdv, e);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		if (connection != null) {
			connection.close();
		}
		super.finalize();
	}








}
