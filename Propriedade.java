package gerenciador;

//subclasse abstrata de espaco responsável por ser modelo para os 3 tipos de propriedades. 

public abstract class Propriedade extends Espaco {
  protected int precoDeCompra;
  protected Jogador proprietario;


//metodo abstrato a ser sobreescrito pelas classes filhas(Utilidade,EstacaoDeMetro e Lote)
  public abstract int calcularAluguel(int valor);

//metodo construtor que incializa a classe espaco e seta proprietario inexistente
  public Propriedade(String nome, String tipo, int precoDeCompra) {
    super(nome, tipo);
    this.precoDeCompra = precoDeCompra;
    this.proprietario = null;
  }

//metodos acessores dos atributos
  public int getPrecoDeCompra() {
    return this.precoDeCompra;
  }

  public boolean temProprietario() {
    return this.proprietario != null;
  }

  public void setProprietario(Jogador j) {
    this.proprietario = j;
  }

  public Jogador getProprietario() {
    return this.proprietario;
  }
}
