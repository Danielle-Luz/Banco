package br.com.mesttra.banco.validacao;

import br.com.mesttra.banco.scanner.Scanner;

public class ValidadorCpf {

  static private boolean validaDigito(
    int inicial,
    int digitoVerificador,
    int[] digitosNumericos
  ) {
    int soma = 0;

    for (int fator = inicial, i = 0; fator >= 2; fator--, i++) {
      soma += digitosNumericos[i] * fator;
    }

    int valorComparado = 0;

    int restoDivisao = (soma * 10) % 11;

    if (restoDivisao != 10 && restoDivisao != 11) {
      valorComparado = restoDivisao;
    }

    if (valorComparado == digitoVerificador) {
      return true;
    }

    return false;
  }

  static private int[] obterArrayDeNumeros(String[] digitosString) {
    int[] digitosNumericos = new int[digitosString.length];

    for (int i = 0; i < digitosString.length; i++) {
      digitosNumericos[i] = Integer.parseInt(digitosString[i]);
    }

    return digitosNumericos;
  }

  static private boolean validaAutenticidadeCpf(String cpf) {
    String[] digitosString = cpf.substring(0, 9).split("");

    int digitoVerificadorUm = Integer.parseInt(cpf.split("")[9]);
    int digitoVerificadorDois = Integer.parseInt(cpf.split("")[10]);

    int[] digitosNumericos = obterArrayDeNumeros(digitosString);

    boolean primeiroDigitoEhValido = validaDigito(
      10,
      digitoVerificadorUm,
      digitosNumericos
    );

    digitosString = cpf.substring(0, 10).split("");
    digitosNumericos = obterArrayDeNumeros(digitosString);

    boolean segundoDigitoEhValido = validaDigito(
      11,
      digitoVerificadorDois,
      digitosNumericos
    );

    boolean cpfEhValido = primeiroDigitoEhValido && segundoDigitoEhValido;

    return cpfEhValido;
  }

  static private String validaDigitosDoCpf() {
    while (true) {
      String cpf = Scanner.lerValorAlfanumerico("Insira o CPF: ");

      if (!cpf.matches("^[0-9]+$")) {
        System.out.println("O CPF deve possuir apenas números");

        continue;
      }

      if (cpf.length() != 11) {
        System.out.println("O CPF deve ter 11 dígitos");

        continue;
      }

      return cpf;
    }
  }

  static public String validaCpf() {
    while (true) {
      String cpf = validaDigitosDoCpf();

      boolean cpfEhValido = validaAutenticidadeCpf(cpf);

      if (!cpfEhValido) {
        System.out.println("Digite um CPF verdadeiro");

        continue;
      }

      return cpf;
    }
  }
}
