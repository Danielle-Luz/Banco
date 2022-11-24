package br.com.mesttra.banco.controller;

import br.com.mesttra.banco.dao.PessoaFisicaDAO;
import br.com.mesttra.banco.dao.PessoaJuridicaDAO;
import br.com.mesttra.banco.pojo.PessoaFisicaPojo;
import br.com.mesttra.banco.pojo.PessoaJuridicaPojo;
import br.com.mesttra.banco.scanner.Scanner;

public class Menu {

  private void limparTela() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public void exibirAtributosPessoaFisica(
    int opcao,
    PessoaFisicaPojo clienteEncontrado
  ) {
    if (opcao == 1) {
      System.out.println(
        "Número de conta: " + clienteEncontrado.getNumeroConta()
      );
    } else if (opcao == 2) {
      System.out.println("Agência: " + clienteEncontrado.getAgencia());
    } else if (opcao == 3) {
      System.out.println(
        "Número de telefone: " + clienteEncontrado.getTelefone()
      );
    } else if (opcao == 4) {
      System.out.println("Saldo: " + clienteEncontrado.getSaldo());
    } else if (opcao == 5) {
      System.out.println(
        "Limite de cheque: " + clienteEncontrado.getLimiteCheque()
      );
    } else if (opcao == 6) {
      System.out.println("CPF: " + clienteEncontrado.getCpf());
    } else if (opcao == 7) {
      System.out.println("Nome: " + clienteEncontrado.getNome());
    } else {
      System.out.println(
        "Data de nascimento: " + clienteEncontrado.getDataNascimento()
      );
    }
  }

  public void exibirPessoaFisica() {
    limparTela();

    String numeroConta = Scanner.lerValorAlfanumerico(
      "Insira o número da conta do cliente procurado: "
    );

    PessoaFisicaPojo clienteEncontrado = PessoaFisicaDAO.consultarCliente(
      numeroConta
    );

    if (clienteEncontrado == null) {
      System.out.println("O cliente procurado não foi encontrado");
    } else {
      System.out.println("1- Número de conta");
      System.out.println("2- Agência");
      System.out.println("3- Telefone");
      System.out.println("4- Saldo");
      System.out.println("5- Limite do cheque");
      System.out.println("6- CPF");
      System.out.println("7- Nome");
      System.out.println("8- Data de nascimento");

      int opcao = Scanner.lerValorInteiroComLimites(
        1,
        8,
        "Selecione um atributo: "
      );

      exibirAtributosPessoaFisica(opcao, clienteEncontrado);
    }
  }

  public void exibirAtributosPessoaJuridica(
    int opcao,
    PessoaJuridicaPojo clienteEncontrado
  ) {
    if (opcao == 1) {
      System.out.println(
        "Número de conta: " + clienteEncontrado.getNumeroConta()
      );
    } else if (opcao == 2) {
      System.out.println("Agência: " + clienteEncontrado.getAgencia());
    } else if (opcao == 3) {
      System.out.println(
        "Número de telefone: " + clienteEncontrado.getTelefone()
      );
    } else if (opcao == 4) {
      System.out.println("Saldo: " + clienteEncontrado.getSaldo());
    } else if (opcao == 5) {
      System.out.println(
        "Limite de cheque: " + clienteEncontrado.getLimiteCheque()
      );
    } else if (opcao == 6) {
      System.out.println("CNPJ: " + clienteEncontrado.getCnpj());
    } else if (opcao == 7) {
      System.out.println("Razão social: " + clienteEncontrado.getRazaoSocial());
    } else {
      System.out.println(
        "Nome fantasia: " + clienteEncontrado.getNomeFantasia()
      );
    }
  }

  public void exibirPessoaJuridica() {
    limparTela();

    String numeroConta = Scanner.lerValorAlfanumerico(
      "Insira o número da conta do cliente procurado: "
    );

    PessoaJuridicaPojo clienteEncontrado = PessoaJuridicaDAO.consultarCliente(
      numeroConta
    );

    if (clienteEncontrado == null) {
      System.out.println("O cliente procurado não foi encontrado");
    } else {
      System.out.println("1- Número de conta");
      System.out.println("2- Agência");
      System.out.println("3- Telefone");
      System.out.println("4- Saldo");
      System.out.println("5- Limite do cheque");
      System.out.println("6- CNPJ");
      System.out.println("7- Razão social");
      System.out.println("8- Nome fantasia");

      int opcao = Scanner.lerValorInteiroComLimites(
        1,
        8,
        "Selecione um atributo: "
      );

      exibirAtributosPessoaJuridica(opcao, clienteEncontrado);
    }
  }

  public void mostrarMenu() {
    do {
      int opcao = 
        Scanner.lerValorInteiroComLimites(
          1,
          7,
          "Bem vindo, gerente.\nEscolha uma opção:\n1- Cadastrar cliente\n2- Remover cliente\n3- Alterar valor do cheque especial\n4- Fazer transferência\n5- Adicionar saldo\n6- Imprimir relatório\n7- Consultar cliente\n"
        );

      switch (opcao) {
        case 1:
          break;
        case 2:
          break;
        case 3:
          break;
        case 4:
          break;
        case 5:
          break;
        case 6:
          break;
        case 7:
          int tipoCliente = Scanner.lerValorInteiroComLimites(1, 2, "Tipo de cliente:\n1 - Físico\n2- Jurídico\n");

          if (tipoCliente == 1) {
            exibirPessoaFisica();
          } else {
            exibirPessoaJuridica();
          }

          break;
      }

      opcao =
        Scanner.lerValorInteiroComLimites(
          1,
          2,
          "Realizar nova operação?\n1- Sim\n2- Não\n"
        );

      if (opcao == 2) break;

    } while (true);
  }
}
