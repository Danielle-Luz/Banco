package br.com.mesttra.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.mesttra.banco.connectionfactory.ConnectionFactory;
import br.com.mesttra.banco.pojo.PessoaFisicaPojo;

public class PessoaFisicaDAO {
  Connection connection = ConnectionFactory.getConnection();


  public ArrayList<PessoaFisicaPojo> retornaClientes (ResultSet cliente) throws SQLException {
    ArrayList<PessoaFisicaPojo> listaPessoas = new ArrayList<>();

    while(cliente.next()) {
      String cpf = cliente.getString("cpf");
      String nome = cliente.getString("nome");
      String dataNascimento = cliente.getString("data_nascimento");
      String numeroConta = cliente.getString("numeroConta");
      int agencia = cliente.getInt("agencia");
      String telefone = cliente.getString("telefone");
      float saldo = cliente.getFloat("saldo");
      float limiteCheque = cliente.getFloat("limiteCheque");

      PessoaFisicaPojo pessoaFisica = new PessoaFisicaPojo(cpf, nome, dataNascimento, numeroConta, agencia, telefone, saldo, limiteCheque);

      listaPessoas.add(pessoaFisica);
    }
    
    return listaPessoas;
  }

}
