package gerenciador;

//subclasse de espaco
public class Cadeia extends Espaco {

  private static final int fianca = 50;
  
//metodo construtor que inicializa a classe espaco 
  public Cadeia(String nome) {
    super(nome, "Cadeia");
  }

  // Prende o jogador e seta seu status de prisão para true
  public static void prender(Jogador j) {
    j.setPreso(true);
  }

  public static void soltar(Jogador j) {
    j.setPreso(false);
  }

  // Retorna fiança do preso
  public static int getFianca() {
    return fianca;
  }
}
