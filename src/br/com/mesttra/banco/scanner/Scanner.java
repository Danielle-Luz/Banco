package br.com.mesttra.banco.scanner;

import java.util.InputMismatchException;

public class Scanner {

  public static String lerValorAlfanumerico(String mensagem) {
    java.util.Scanner scan = new java.util.Scanner(System.in);

    System.out.print(mensagem);

    String valor = scan.nextLine();

    return valor;
  }

  public static double lerValorMonetario(String mensagem) {
    double valor = 0;

    do {
      java.util.Scanner scan = new java.util.Scanner(System.in);

      try {
        System.out.print(mensagem);

        valor = scan.nextFloat();

        break;
      } catch (InputMismatchException ex) {
        System.out.println("Insira um valor monetário válido.");
      }
    } while (true);

    return valor;
  }

  public static int lerValorInteiro(String mensagem) {
    int valor = 0;

    do {
      try {
        java.util.Scanner scan = new java.util.Scanner(System.in);

        System.out.print(mensagem);

        valor = scan.nextInt();

        break;
      } catch (InputMismatchException ex) {
        System.out.println("Insira um número inteiro válido.");
      }
    } while (true);

    return valor;
  }

  public static int lerValorInteiroComLimites (int min, int max, String mensagem) {
    while (true) {
      int valor = lerValorInteiro(mensagem);

      if ((valor >= min) && (valor <= max)) return valor;

      System.out.printf("Digite um valor entre %d e %d\n", min, max);
    }
  }
}
