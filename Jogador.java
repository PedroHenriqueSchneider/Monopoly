package gerenciador;

import java.util.ArrayList;
/*
* @author: Pedro Henrique Candido de Sousa
*/

public class Jogador {
  private final String nome;
  private int saldo;
  private ArrayList<Propriedade> propriedades;
  private boolean preso;
  private boolean falido;
  private Espaco espacoAtual;
  private int posicaoAtual;

  // variaveis auxiliares
  private int estacoes; // quantas estacoes o jogador possui
  private int utilidades; // quantas utilidades o jogador possui
  private int dadoDuplas;
  private int monopolios;
  private int tentouSair; // quantas vezes seguidas tentou sair da cadeia

  // metodo construtor
  public Jogador(String nome) {
    this.nome = nome;
    this.saldo = 1500;
    this.preso = false;
    this.falido = false;
    this.propriedades = new ArrayList<>();
    this.posicaoAtual = 0;
    this.espacoAtual = null;
    this.estacoes = 0;
    this.utilidades = 0;
    this.dadoDuplas = 0;
    this.monopolios = 0;
  }

  // metodos acessores dos atributos
  public String getNome() {
    return this.nome;
  }

  public int getSaldo() {
    return this.saldo;
  }

  public Espaco getEspacoAtual() {
    return this.espacoAtual;
  }

  public void setEspacoAtual(Espaco espacoAtual) {
    this.espacoAtual = espacoAtual;
  }

  public int getPosicaoAtual() {
    return this.posicaoAtual;
  }

  public void setPosicaoAtual(int valor) {
    this.posicaoAtual = valor;
  }

  public boolean getPreso() {
    return this.preso;
  }

  public void setPreso(boolean p) {
    this.preso = p;
  }
  // fim dos metodos acessores

  // metodo para adicionar ou remover estacoes
  public void modEstacoes(int v) {
    this.estacoes += v;
  }

  // metodo que retorna a variavel auxiliar estacoes
  public int getEstacoes() {
    return this.estacoes;
  }

  // metodo para adicionar ou remover utilidades
  public void modUtilidades(int v) {
    this.utilidades += v;
  }

  // metodo que retorna a variavel auxiliar utilidades
  public int getUtilidades() {
    return this.utilidades;
  }

  // metodo que define o atributo falido
  public void setFalido(boolean f) {
    this.falido = f;
  }

  // metodo que define o atributo falido
  public boolean estaFalido() {
    return this.falido;
  }

  public void addPropriedade(Propriedade p) {
    p.setProprietario(this);
    this.propriedades.add(p);
  }

  public ArrayList<Propriedade> getPropriedades() {
    return this.propriedades;
  }

  /*
   * Função que lança os dados da classe Dado.
   * Caso iguais somamos ao contatos de duplas.
   * Retorna a soma dos dois dados.
   */
  public int lancarDados(Dado d1, Dado d2) {
    d1.rolar();
    d2.rolar();

    if (d1.getValorDado() == d2.getValorDado())
      this.dadoDuplas++;
    else
      this.dadoDuplas = 0;

    return d1.getValorDado() + d2.getValorDado();
  }

  // Retorna verdadeiro caso o jogador tenha rolado 3 vezes os dados iguais.
  public boolean jogouTresDuplas() {
    return this.dadoDuplas == 3;
  }

  // Adiciona um saldo não negativo a conta do jogador
  public void addSaldo(int saldo) {
    if (saldo < 0)
      return;
    this.saldo += saldo;
  }

  // Função retorna true se for removido o saldo, senão retorna false
  public boolean removeSaldo(int saldo) {
    if (saldo > this.saldo)
      return false;
    else
      this.saldo -= saldo;
    return true;
  }

  public void mostrarMonopolios() {
    if (this.propriedades.isEmpty()) {
      System.out.println("Este jogador não possui monopólios!");
      return;
    }
    int i = 1;
    for (Propriedade p : this.propriedades) {
      if ("Lote".equals(p.getTipo())) {
        if (((Lote) p).temMonopolio()) {
          System.out.println(i + " - " + p.getNome());
          i++;
        }
      }
    }
  }

  public boolean mostrarPropriedades() {
    if (this.propriedades.isEmpty()) {
      System.out.println("Este jogador não possui propriedades!");
      return false;
    }
    int i = 1;
    for (Propriedade p : this.propriedades) {
      System.out.println(i + " - " + p.getNome());
      i++;
    }
    return true;
  }

  public void removerPropriedades() {
    for (Propriedade p : this.propriedades) {
      p.setProprietario(null);
      if (p.getTipo().equals("Lote")) {
        Lote l = (Lote) p;
        l.setCasa(false);
        l.setHotel(false);
        if (l.temMonopolio() && this.getMonopolios() > 0)
          this.modMonopolios(-1);
      } else if (p.getTipo().equals("Utilidade"))
        this.modUtilidades(-1);
      else if (p.getTipo().equals("Estacao"))
        this.modEstacoes(-1);
    }
    this.propriedades.clear();
  }

  public void removerPropriedade(Propriedade p) {
    p.setProprietario(null);
    if (p.getTipo().equals("Lote")) {
      Lote l = (Lote) p;
      l.setCasa(false);
      l.setHotel(false);
      if (l.temMonopolio())
        this.modMonopolios(-1);
    } else if (p.getTipo().equals("Utilidade")) {
      this.modUtilidades(-1);
    } else if (p.getTipo().equals("Estacao")) {
      this.modEstacoes(-1);
    }
    this.propriedades.remove(p);

  }

  public int calcularFortuna() {
    int fortunaTotal = 0;
    fortunaTotal += this.getSaldo();
    for (Propriedade p : this.propriedades) {
      fortunaTotal += p.getPrecoDeCompra();
      if (p.getTipo().equals("Lote")) {
        Lote l = (Lote) p;
        if (l.temCasa())
          fortunaTotal += l.getPrecoCasa();
        else if (l.temHotel())
          fortunaTotal += l.getPrecoHotel();
      }
    }
    return fortunaTotal;
  }

  public int getMonopolios() {
    return this.monopolios;
  }

  public void modMonopolios(int valor) {
    this.monopolios += valor;
  }

  public void comprarProriedade(Propriedade p) {
    this.propriedades.add(p);
    p.setProprietario(this);
    if (p.getTipo().equals("Utilidade"))
      this.modUtilidades(1);
    else if (p.getTipo().equals("Estacao"))
      this.modEstacoes(1);
  }

  public void modTentouSair(int valor) {
    this.tentouSair += valor;
  }

  public int getTentouSair() {
    return this.tentouSair;
  }

  public ArrayList<Lote> getLotesMonopolizados() {
    ArrayList<Lote> lotes = new ArrayList<>();
    for (Propriedade p : this.propriedades) {
      if (p.getTipo().equals("Lote"))
        if (((Lote) p).temMonopolio())
          lotes.add((Lote) p);
    }
    if (lotes.isEmpty())
      return null;
    return lotes;
  }
}
