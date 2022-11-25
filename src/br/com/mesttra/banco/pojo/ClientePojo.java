package br.com.mesttra.banco.pojo;

public class ClientePojo {

  private String numeroConta;
  private int agencia;
  private String telefone;
  private float saldo;
  private float limiteCheque;

  public ClientePojo(
    String numeroConta,
    int agencia,
    String telefone,
    float saldo,
    float limiteCheque
  ) {
    this.agencia = agencia;
    this.numeroConta = numeroConta;
    this.telefone = telefone;
    this.saldo = saldo;
    this.limiteCheque = limiteCheque;
  }

  public String getNumeroConta() {
    return numeroConta;
  }

  public int getAgencia() {
    return agencia;
  }

  public String getTelefone() {
    return telefone;
  }

  public float getSaldo() {
    return saldo;
  }

  public float getLimiteCheque() {
    return limiteCheque;
  }

  public void setSaldo(float saldo) {
    this.saldo = saldo;
  }

  public void aumentarSaldo(float valor) {
    this.saldo += valor;
  }

  public void diminuirSaldo(float valor) {
    this.saldo -= valor;
  }

  public void aumentarCheque(float valor) {
    this.limiteCheque += valor;
  }

  public void diminuirCheque(float valor) {
    this.limiteCheque -= valor;
  }
}
