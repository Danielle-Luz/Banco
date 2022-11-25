package br.com.mesttra.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.mesttra.banco.connectionfactory.ConnectionFactory;

import br.com.mesttra.banco.pojo.ClientePojo;
import br.com.mesttra.banco.pojo.PessoaFisicaPojo;

public class ClienteDAO {
  static Connection connection = ConnectionFactory.getConnection();

  static public void adicionaSaldo (ClientePojo cliente, float valor) {
    float novoSaldo = (float) cliente.getSaldo() + valor;
    String contaCliente = cliente.getNumeroConta();

    String nomeTabela = cliente instanceof PessoaFisicaPojo ? "pessoa_fisica" : "pessoa_juridica";

    String comando = "UPDATE " + nomeTabela + " SET saldo = ? WHERE numeroConta = ?";

    try (PreparedStatement sql = connection.prepareStatement(comando)) {
      sql.setFloat(1, novoSaldo);
      sql.setString(1, contaCliente);

      sql.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
