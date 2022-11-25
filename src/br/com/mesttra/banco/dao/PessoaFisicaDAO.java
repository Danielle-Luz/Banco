package br.com.mesttra.banco.dao;

import br.com.mesttra.banco.connectionfactory.ConnectionFactory;
import br.com.mesttra.banco.pojo.PessoaFisicaPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PessoaFisicaDAO {

  static Connection conexao = ConnectionFactory.getConnection();

  public static void inserePF(PessoaFisicaPojo pf) {
    String cadastro = "INSERT INTO pessoa_fisica (cpf, nome, data_nascimento, numeroConta, agencia, telefone, saldo, limiteCheque) "
        +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    try {
      boolean clienteJaCadastrado = PessoaJuridicaDAO.consultarCliente(pf.getNumeroConta()) != null;

      if (clienteJaCadastrado)
        throw new ClienteJaCadastradoException(
            "O número de conta já pertence a uma pessoa jurídica");

      PreparedStatement stt = conexao.prepareStatement(cadastro);
      stt.setString(1, pf.getCpf());
      stt.setString(2, pf.getNome());
      stt.setString(3, pf.getDataNascimento());
      stt.setString(4, pf.getNumeroConta());
      stt.setInt(5, pf.getAgencia());
      stt.setString(6, pf.getTelefone());
      stt.setFloat(7, pf.getSaldo());
      stt.setFloat(8, pf.getLimiteCheque());
      stt.execute();
      stt.close();
      System.out.println("\n[Cliente PF Cadastrado]\n");
    } catch (SQLException e) {
      System.err.println("\n[Erro ao Cadastrar Pessoa Física]\n");
    } catch (ClienteJaCadastradoException e) {
      System.err.println(e.getMessage());
    }
  }

  private static ArrayList<PessoaFisicaPojo> retornaClientes(ResultSet cliente)
      throws SQLException {
    ArrayList<PessoaFisicaPojo> listaPessoas = new ArrayList<>();

    while (cliente.next()) {
      String cpf = cliente.getString("cpf");
      String nome = cliente.getString("nome");
      String dataNascimento = cliente.getString("data_nascimento");
      String numeroConta = cliente.getString("numeroConta");
      int agencia = cliente.getInt("agencia");
      String telefone = cliente.getString("telefone");
      float saldo = cliente.getFloat("saldo");
      float limiteCheque = cliente.getFloat("limiteCheque");

      PessoaFisicaPojo pessoaFisica = new PessoaFisicaPojo(
          cpf,
          nome,
          dataNascimento,
          numeroConta,
          agencia,
          telefone,
          saldo,
          limiteCheque);

      listaPessoas.add(pessoaFisica);
    }

    return listaPessoas;
  }

  public static PessoaFisicaPojo consultarCliente(String numeroConta) {
    PessoaFisicaPojo clienteEncontrado = null;

    String comando = "SELECT * FROM pessoa_fisica WHERE numeroConta = ?";

    try (PreparedStatement query = conexao.prepareStatement(comando)) {
      query.setString(1, numeroConta);

      ResultSet registro = query.executeQuery();

      ArrayList<PessoaFisicaPojo> listaPessoas = retornaClientes(registro);

      if (!listaPessoas.isEmpty()) {
        clienteEncontrado = listaPessoas.get(0);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return clienteEncontrado;
  }

  public static ArrayList<PessoaFisicaPojo> obterPessoasFisicas() {
    String query = "SELECT * FROM pessoa_fisica";

    ArrayList<PessoaFisicaPojo> listaPessoas = null;

    try {
      PreparedStatement sql = conexao.prepareStatement(query);
  
      ResultSet pessoasFisicas = sql.executeQuery();
  
      listaPessoas = retornaClientes(pessoasFisicas);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return listaPessoas;
  }
}