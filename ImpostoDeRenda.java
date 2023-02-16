package gerenciador;

import java.util.Scanner;

//subclasse de espaco
public class ImpostoDeRenda extends Espaco {

  // metodo construtor que inicializa a classe espaco
  public ImpostoDeRenda(String nome) {
    super(nome, "Imposto");
  }

  // metodo que calcula o valor a ser pago pelo jogador de acordo com sua escolha
  // (1: retorna o valor inteiro 200 a ser pago ou 2: retorna o valor de 10% de
  // sua fortuna a ser pago)
  public static int calcular(Jogador j) {
    int escolha;
    if (j.getSaldo() >= 200 && j.getSaldo() > j.calcularFortuna() / 10) {
      while (true) {
        System.out.println("1 - Pagar 200$");
        System.out.println("2 - Pagar 10% de sua fortuna total");
        try {
          escolha = Jogo.s.nextInt();
          validarEscolha(escolha);
          break;
        } catch (Exception e) {
          System.out.println("Esta não é uma escolha válida, tente novamente!");
          Jogo.s.nextLine();
        }
      }
      Jogo.s.nextLine();
      if (escolha == 1)
        return 200;
      else
        return j.calcularFortuna() / 10;
    } else if (j.getSaldo() >= 200 && !(j.getSaldo() > j.calcularFortuna() / 10)) {
      System.out.println("Você não possui saldo suficiente para pagar 10% da sua fortuna, terá que pagar 200$");
      return 200;
    } else {
      System.out.println("Você não possui 200$ para pagar a taxa, calculando 10% de sua fortuna total");
      return j.calcularFortuna() / 10;
    }
  }

  // metodo que verifica a escolha do jogador e apresenta erro em caso de fugir do
  // escopo das opcoes
  private static void validarEscolha(int escolha) throws Exception {
    if (escolha != 1 && escolha != 2)
      throw new Exception();
  }
}
