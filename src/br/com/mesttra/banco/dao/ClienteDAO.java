package br.com.mesttra.banco.dao;

import br.com.mesttra.banco.connectionfactory.ConnectionFactory;
import br.com.mesttra.banco.pojo.ClientePojo;
import br.com.mesttra.banco.pojo.PessoaFisicaPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {

  private static Connection conexao = ConnectionFactory.getConnection();

  public static void atualizarCliente(
    ClientePojo cliente,
    Object valor,
    String coluna
  ) {
    String nomeTabela = cliente instanceof PessoaFisicaPojo
      ? "pessoa_fisica"
      : "pessoa_juridica";

    String comando = String.format(
      "UPDATE %s SET %s = ? WHERE numeroConta = '%s'",
      nomeTabela,
      coluna,
      cliente.getNumeroConta()
    );

    try (PreparedStatement sql = conexao.prepareStatement(comando)) {
      if (valor instanceof String) {
        sql.setString(1, (String) valor);
      } else if (valor instanceof Float) {
        sql.setFloat(1, (float) valor);
      } else {
        sql.setInt(1, (int) valor);
      }

      sql.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
