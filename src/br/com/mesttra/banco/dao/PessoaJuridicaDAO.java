package br.com.mesttra.banco.dao;

import br.com.mesttra.banco.connectionfactory.ConnectionFactory;
import br.com.mesttra.banco.pojo.PessoaJuridicaPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PessoaJuridicaDAO {

  static Connection conexao = ConnectionFactory.getConnection();

  public static void inserePJ(PessoaJuridicaPojo pj) {
    String sql =
      "INSERT INTO pessoa_juridica (cnpj, razao_social, nome_fantasia, numeroConta, agencia, telefone, saldo, limiteCheque) " +
      "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    try {
      boolean clienteJaCadastrado =
        PessoaFisicaDAO.consultarCliente(pj.getNumeroConta()) != null;

      if (clienteJaCadastrado) throw new ClienteJaCadastradoException(
        "O número de conta já pertence a uma pessoa física"
      );

      PreparedStatement stt = conexao.prepareStatement(sql);
      stt.setString(1, pj.getCnpj());
      stt.setString(2, pj.getRazaoSocial());
      stt.setString(3, pj.getNomeFantasia());
      stt.setString(4, pj.getNumeroConta());
      stt.setInt(5, pj.getAgencia());
      stt.setString(6, pj.getTelefone());
      stt.setFloat(7, pj.getSaldo());
      stt.setFloat(8, pj.getLimiteCheque());
      stt.execute();
      stt.close();
      System.out.println("\n[Cliente PJ Cadastrado]\n");
    } catch (SQLException e) {
      System.err.println("\n[Erro ao Cadastrar Pessoa Jurídica]\n");
    } catch (ClienteJaCadastradoException e) {
      System.err.println(e.getMessage());
    }
  }

  private static ArrayList<PessoaJuridicaPojo> retornaClientes(
    ResultSet cliente
  ) throws SQLException {
    ArrayList<PessoaJuridicaPojo> listaPessoas = new ArrayList<>();

    while (cliente.next()) {
      String cnpj = cliente.getString("cnpj");
      String razaoSocial = cliente.getString("razao_social");
      String nomeFantasia = cliente.getString("nome_Fantasia");
      String numeroConta = cliente.getString("numeroConta");
      int agencia = cliente.getInt("agencia");
      String telefone = cliente.getString("telefone");
      float saldo = cliente.getFloat("saldo");
      float limiteCheque = cliente.getFloat("limiteCheque");

      PessoaJuridicaPojo pessoaJuridica = new PessoaJuridicaPojo(
        cnpj,
        razaoSocial,
        nomeFantasia,
        numeroConta,
        agencia,
        telefone,
        saldo,
        limiteCheque
      );

      listaPessoas.add(pessoaJuridica);
    }

    return listaPessoas;
  }

  public static PessoaJuridicaPojo consultarCliente(String numeroConta) {
    PessoaJuridicaPojo clienteEncontrado = null;

    String comando = "SELECT * FROM pessoa_juridica WHERE numeroConta = ?";

    try (PreparedStatement query = conexao.prepareStatement(comando)) {
      query.setString(1, numeroConta);

      ResultSet registro = query.executeQuery();

      ArrayList<PessoaJuridicaPojo> listaPessoas = retornaClientes(registro);

      if (!listaPessoas.isEmpty()) clienteEncontrado = listaPessoas.get(0);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return clienteEncontrado;
  }

  public static ArrayList<PessoaJuridicaPojo> obterPessoasJuridicas() {
    String query = "SELECT * FROM pessoa_juridica";

    ArrayList<PessoaJuridicaPojo> listaPessoas = null;

    try {
      PreparedStatement sql = conexao.prepareStatement(query);
  
      ResultSet pessoasJuridicas = sql.executeQuery();
  
      listaPessoas = retornaClientes(pessoasJuridicas);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return listaPessoas;
  }
}