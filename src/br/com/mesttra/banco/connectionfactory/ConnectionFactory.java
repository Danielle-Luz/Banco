package br.com.mesttra.banco.connectionfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

  public ConnectionFactory() {
    super();
  }

  static final String URL =
    "jdbc:postgresql://ec2-54-174-31-7.compute-1.amazonaws.com:5432/d9jgrt2bbn1ig5";
  static final String USER = "sifirokgjenmsd";
  static final String PASSWORD =
    "1cfdf7006a130ab220f3174e133698590027d4f75849edd7c8c57f1ee76fde3b";

  public static Connection getConnection() {
    try {
      return DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }
}
