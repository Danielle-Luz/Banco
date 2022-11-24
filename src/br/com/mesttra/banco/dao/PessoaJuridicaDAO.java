package br.com.mesttra.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.mesttra.banco.connectionfactory.ConnectionFactory;
import br.com.mesttra.banco.pojo.PessoaJuridicaPojo;

public class PessoaJuridicaDAO {
  Connection connection = ConnectionFactory.getConnection();

  public ArrayList<PessoaJuridicaPojo> retornaClientes (ResultSet cliente) throws SQLException {

    ArrayList<PessoaJuridicaPojo> listaPessoas = new ArrayList<>();

    while(cliente.next()) {
      String cnpj = cliente.getString("cnpj");
      String razaoSocial = cliente.getString("razaoSocial");
      String nomeFantasia = cliente.getString("nomeFantasia");
      String numeroConta = cliente.getString("numeroConta");
      int agencia = cliente.getInt("agencia");
      String telefone = cliente.getString("telefone");
      float saldo = cliente.getFloat("saldo");
      float limiteCheque = cliente.getFloat("limiteCheque");

      PessoaJuridicaPojo pessoaJuridica = new PessoaJuridicaPojo(cnpj, razaoSocial, nomeFantasia, numeroConta, agencia, telefone, saldo, limiteCheque);

      listaPessoas.add(pessoaJuridica);
    }
    
    return listaPessoas;
  }

  public PessoaJuridicaPojo consultarCliente (String numeroConta) {
    PessoaJuridicaPojo clienteEncontrado = null;

    String comando = "SELECT * FROM pessoa_juridica WHERE numeroConta = ?";

    try (PreparedStatement query = connection.prepareStatement(comando)) {
      query.setString(1, numeroConta);

      ResultSet registro = query.executeQuery();

      ArrayList<PessoaJuridicaPojo> listaPessoas = retornaClientes(registro);

      clienteEncontrado = listaPessoas.get(0);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return clienteEncontrado;
  }
}