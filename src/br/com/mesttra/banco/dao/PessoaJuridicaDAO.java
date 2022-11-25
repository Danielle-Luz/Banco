package br.com.mesttra.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.mesttra.banco.connectionfactory.ConnectionFactory;
import br.com.mesttra.banco.pojo.PessoaJuridicaPojo;

public class PessoaJuridicaDAO {
	
	private Connection conexao;

	public PessoaJuridicaDAO() {
		this.conexao = ConnectionFactory.getConnection();
	}
	
	public boolean inserePJ (PessoaJuridicaPojo pj) {
		String sql = "INSERT INTO pessoa_juridica (cnpj, razao_social, nome_fantasia, numeroConta, agencia, telefone, saldo, limiteCheque) "
				   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement stt = this.conexao.prepareStatement(sql);
			stt.setString(1, pj.getCnpj());
			stt.setString(2, pj.getRazaoSocial());
			stt.setString(3, pj.getNomeFantasia());
			stt.setString(4, pj.getNumeroConta());
			stt.setInt   (5, pj.getAgencia());
			stt.setString(6, pj.getTelefone());
			stt.setDouble(7, pj.getSaldo());
			stt.setDouble(8, pj.getLimiteCheque());
			stt.execute();
			stt.close();
			System.out.println("\n[Cliente PJ Cadastrado]\n");
			return true;
		} catch (SQLException e) {
			System.err.println("\n[Erro ao Cadastrar Pessoa Jurídica]\n");
			return false;
		}
	}
  static Connection connection = ConnectionFactory.getConnection();

  static private ArrayList<PessoaJuridicaPojo> retornaClientes (ResultSet cliente) throws SQLException {

    ArrayList<PessoaJuridicaPojo> listaPessoas = new ArrayList<>();

    while(cliente.next()) {
      String cnpj = cliente.getString("cnpj");
      String razaoSocial = cliente.getString("razao_social");
      String nomeFantasia = cliente.getString("nome_Fantasia");
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

  static public PessoaJuridicaPojo consultarCliente (String numeroConta) {
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
/* 
public ArrayList<Clientes> imprimirRelatorio() {
		String getPj = "SELECT * FROM pessoa_juridica";

		try {
			

			return clientes;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null
	}
 */
