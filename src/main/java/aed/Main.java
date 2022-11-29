package aed;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main {

	protected Connection conectar;
	private String url;
	private String usuario;
	private String password;

	public static void main(String[] args) {

		try {
			new Main().abrirConexion(
					"jdbc:sqlite:D:/Users/Alumno/eclipse-workspace/LlamadaProcedimieno/src/main/resources/DBLlamada",
					"/Script.sql");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void abrirConexion(String url, String Script) throws IOException {
		try {
			Properties conexion = new Properties();
			conexion.load(getClass().getResourceAsStream("/ConexionDB.properties"));
//			url = conexion.getProperty("url");
			usuario = conexion.getProperty("usuario");
			password = conexion.getProperty("password", "");

			conexion.getProperty("driver", "org.sqlite.JDBC");

			conectar = DriverManager.getConnection(url, usuario, password);

			System.out.println("Conexion Exitosa");
			
			ProcessBuilder processBuilder = new ProcessBuilder();
					processBuilder.directory(new File("D:/Users/Alumno/eclipse-workspace/LlamadaProcedimieno/src/main/resources"+Script));
					Process process = processBuilder.start();
					
					int exitCode = process.exitValue();
					
					System.out.println(exitCode);
					
					process.destroy();

//			Statement s = conectar.createStatement();
//			ResultSet rs = s.executeQuery("SELECT * FROM Habitaciones");
//			while (rs.next())
//				System.out.printf("-> %s, %s\n", rs.getString(1), rs.getString(2));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
