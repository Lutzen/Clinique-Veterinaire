package fr.eni.cliniqueVeterinaire.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.eni.cliniqueVeterinaire.dal.Settings;



public class JdbcTools {

	private static String connexionString;

	//bloc d'initialisation statique
	static {
		try {
			Class.forName(Settings.getProperty("driverdb"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		connexionString = Settings.getProperty("connexionString");

		System.out.println("connexionString: " + connexionString);

	}
	
	public static Connection getConnection() throws SQLException{
		Connection connection = DriverManager.getConnection(connexionString);
		
		return connection;
	}
	
}


