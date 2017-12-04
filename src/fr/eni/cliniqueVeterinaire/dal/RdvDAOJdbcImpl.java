package fr.eni.cliniqueVeterinaire.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

//import fr.eni.papeterie.bo.Article;
//import fr.eni.papeterie.bo.Ramette;
//import fr.eni.papeterie.bo.Stylo;
import cliniqueVeterinaire.dal.RdvDAO;
import cliniqueVeterinaire.bo.Rdv;
import cliniqueVeterinaire.dal.DALException;

public class RdvDAOJdbcImpl implements RdvDAO {

	private Connection connection = null;

	private Rdv getRdvFromResultset(ResultSet resultSet) throws SQLException {
		Rdv rdv;
		if (resultSet.getString("type").trim().equals(Stylo.class.getSimpleName())) {
			Stylo stylo = new Stylo();
			stylo.setCouleur(resultSet.getString("couleur"));
			article = stylo;
		} else {
			Ramette ramette = new Ramette();
			ramette.setGrammage(resultSet.getInt("grammage"));
			article = ramette;
		}
		article.setDesignation(resultSet.getString("designation"));
		article.setMarque(resultSet.getString("marque"));
		article.setPrixUnitaire(resultSet.getFloat("prixUnitaire"));
		article.setReference(resultSet.getString("reference"));
		article.setQteStock(resultSet.getInt("qteStock"));
		article.setIdArticle(resultSet.getInt("idArticle"));
		return article;
	}

	private void FillStatementFromArticle(PreparedStatement statement, Article article) throws SQLException {
		statement.setString(1, article.getReference());
		statement.setString(2, article.getMarque());
		statement.setString(3, article.getDesignation());
		statement.setFloat(4, article.getPrixUnitaire());
		statement.setInt(5, article.getQteStock());
		statement.setString(8, article.getClass().getSimpleName());

		if (article instanceof Stylo) {
			Stylo stylo = (Stylo) article;
			statement.setString(7, stylo.getCouleur());
			statement.setNull(6, Types.INTEGER);
		} else {
			statement.setInt(6, ((Ramette) article).getGrammage());
			statement.setNull(7, Types.NVARCHAR);
		}
	}

	public void insert(Article article) throws DALException {

		openConnection();

		String sql = "INSERT INTO Articles ([reference],[marque],[designation]"
				+ ",[prixUnitaire],[qteStock],[grammage],[couleur],[type]) VALUES " + "(?,?,?,?,?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			FillStatementFromArticle(statement, article);
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				article.setIdArticle(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new DALException("Erreur à l'insertion d'un article : " + article, e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à l'insertion d'un article : " + article, e);
			}
		}

	}

	public Rdv selectById(Integer idArticle) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Articles where idArticle=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idArticle);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next())
				return getArticleFromResultset(resultSet);
			return null;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération d'un article : " + idArticle, e);
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

	public List<Article> selectAll() throws DALException {
		openConnection();

		String sql = "SELECT * FROM Articles";
		List<Article> articles = new LinkedList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				articles.add(getArticleFromResultset(resultSet));
			}
			return articles;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération de tous les articles", e);
		}
	}

	public List<Article> selectByMarque(String marque) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Articles WHERE marque=?";
		List<Article> articles = new LinkedList<>();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, marque);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				articles.add(getArticleFromResultset(resultSet));
			}
			return articles;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération des articles par marque", e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à la récupération des articles par marque", e);
			}
		}
	}

	public void update(Article article) throws DALException {
		openConnection();

		String sql = "UPDATE Articles SET reference=?,marque=?,designation=?"
				+ ",prixUnitaire=?,qteStock=?,grammage=?,couleur=?,type=? WHERE idArticle=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			FillStatementFromArticle(statement, article);
			statement.setInt(9, article.getIdArticle());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'update d'un article : " + article, e);
		}
	}

	public void delete(Integer idArticle) throws DALException {
		openConnection();

		String sql = "DELETE FROM Articles WHERE idArticle=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idArticle);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la suppression d'un article : " + idArticle, e);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		if (connection != null) {
			connection.close();
		}
		super.finalize();
	}

	@Override
	public List<Article> selectByMotCle(String motCle) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Articles WHERE marque=? OR designation like '%'+?+'%'";
		List<Article> articles = new LinkedList<>();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, motCle);
			statement.setString(2, motCle);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				articles.add(getArticleFromResultset(resultSet));
			}
			return articles;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération des articles par marque", e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à la récupération des articles par marque", e);
			}
		}
	}

}
