package gerenciador;

import java.util.ArrayList;

public class Banco {
  // Função que adiciona saldo ao jogador.
  public void pagarJogador(Jogador j, int valor) {
    j.addSaldo(valor);
  }

  // Falencia com o banco
  private void falenciaJogador(Jogador j) {
    j.removeSaldo(j.getSaldo());
    j.removerPropriedades();
    j.setFalido(true);
  }

  // Falencia de j1 por outro jogador j2
  private void falenciaJogador(Jogador j1, Jogador j2) {
    ArrayList<Propriedade> prop = new ArrayList<>();
    for (Propriedade p : j1.getPropriedades()) {
      prop.add(p);
    }
    j1.removerPropriedades();
    
    for (Propriedade p : prop)
        j2.addPropriedade(p);
    
    j2.addSaldo(j1.getSaldo());
    j1.removeSaldo(j1.getSaldo());
    j1.setFalido(true);
  }

  // Função que retira saldo do jogador.
  public boolean cobrarJogador(Jogador j, int valor) {
    if (j.removeSaldo(valor) == false) {
      this.falenciaJogador(j);
      return false;
    }
    return true;
  }

  // Função que atua como intermediador entre dois jogadores.
  public boolean intermediar(Jogador j1, Jogador j2, int valor) {
    if (j1.removeSaldo(valor) == false) {
      this.falenciaJogador(j1, j2);
      return false;
      // j1 tem que passar propriedades para j2
    } else {
      j2.addSaldo(valor);
      return true;
    }
  }

}
