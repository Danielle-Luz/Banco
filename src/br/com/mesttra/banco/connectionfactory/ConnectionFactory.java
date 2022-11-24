package br.com.mesttra.banco.connectionfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("");
		} catch (SQLException e) {
			System.err.println("Erro de conexão");
			return null;
		}
	}
}
