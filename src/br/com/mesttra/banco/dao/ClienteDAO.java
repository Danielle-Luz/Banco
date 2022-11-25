package br.com.mesttra.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.mesttra.banco.connectionfactory.ConnectionFactory;

import br.com.mesttra.banco.pojo.ClientePojo;
import br.com.mesttra.banco.pojo.PessoaFisicaPojo;

public class ClienteDAO {
	
  static private Connection conexao = ConnectionFactory.getConnection();
	
	static public boolean transfereSaldo (ClientePojo contaTransferidora, ClientePojo contaReceptora, float valor) {
		String transferidor = contaTransferidora instanceof PessoaFisicaPojo ? "pessoa_fisica" : "pessoa_juridica";
		String receptor = contaReceptora instanceof PessoaFisicaPojo ? "pessoa_fisica" : "pessoa_juridica";
		
		String transfere = "UPDATE " + transferidor + " SET saldo = (saldo - ?) WHERE numeroConta = " + contaTransferidora.getNumeroConta() + ";";
		String recebe = "UPDATE " + receptor + " SET saldo = (saldo + ?) WHERE numeroConta = " + contaReceptora.getNumeroConta() + ";";
		
		if (contaTransferidora.getSaldo() < valor) {
			System.err.println("Saldo insuficiente");
			return false;
		} else {
			
			try {
				PreparedStatement stt1 = conexao.prepareStatement(transfere);
				stt1.setFloat(1, valor);
				stt1.execute();
				stt1.close();
				PreparedStatement stt2 = conexao.prepareStatement(recebe);
				stt2.setFloat(1, valor);
				stt2.execute();
				stt2.close();
				System.out.println("\n[Transferência realizada com sucesso]\n");
				return true;
			} catch (SQLException e) {
				System.err.println("\n[Erro na transferência]\n");
				return false;
			}
		}
	}

  static public void atualizarCliente (ClientePojo cliente, Object valor, String coluna) {
    String nomeTabela = cliente instanceof PessoaFisicaPojo ? "pessoa_fisica" : "pessoa_juridica";

    String comando = String.format("UPDATE %s SET %s = ? WHERE numeroConta = '%s'", nomeTabela, coluna, cliente.getNumeroConta());

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
