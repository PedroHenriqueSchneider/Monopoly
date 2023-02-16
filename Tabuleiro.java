package gerenciador;

import java.util.ArrayList;

public class Tabuleiro {
  private final Espaco[] espacos;
  private GrupoDoLote[] gruposLotes;

  public Tabuleiro() {

    // Espaços pré-definidos da tabela.
    this.espacos = new Espaco[40];
    this.espacos[0] = new PontoDePartida(NomeDoEspaco.PONTO_PARTIDA.toString());
    this.espacos[1] = new Lote(NomeDoEspaco.AV_SUMARE.toString(), "Marrom", 60, 50, 2, 10);
    this.espacos[2] = new EspacoDeCarta(NomeDoEspaco.COFRE.toString());
    this.espacos[3] = new Lote(NomeDoEspaco.PRACA_SE.toString(), "Marrom", 60, 50, 4, 20);
    this.espacos[4] = new ImpostoDeRenda(NomeDoEspaco.IMPOSTO.toString());
    this.espacos[5] = new EstacaoDeMetro(NomeDoEspaco.METRO_MARACANA.toString(), 200);
    this.espacos[6] = new Lote(NomeDoEspaco.RUA_25_MARCO.toString(), "Azul claro", 100, 50, 6, 30);
    this.espacos[7] = new EspacoDeCarta(NomeDoEspaco.SORTE.toString());
    this.espacos[8] = new Lote(NomeDoEspaco.AV_SAO_JOAO.toString(), "Azul claro", 100, 50, 6, 30);
    this.espacos[9] = new Lote(NomeDoEspaco.AV_PAULISTA.toString(), "Azul claro", 120, 50, 8, 40);
    this.espacos[10] = new Cadeia(NomeDoEspaco.CADEIA.toString());
    this.espacos[11] = new Lote(NomeDoEspaco.AV_VIEIRA_SOUTO.toString(), "Rosa", 140, 100, 10, 50);
    this.espacos[12] = new Utilidade(NomeDoEspaco.COMP_ELETRICA.toString(), 150);
    this.espacos[13] = new Lote(NomeDoEspaco.NITEROI.toString(), "Rosa", 140, 100, 10, 50);
    this.espacos[14] = new Lote(NomeDoEspaco.AV_ATLANTICA.toString(), "Rosa", 160, 100, 12, 60);
    this.espacos[15] = new EstacaoDeMetro(NomeDoEspaco.METRO_CARIOCA.toString(), 200);
    this.espacos[16] = new Lote(NomeDoEspaco.AV_PRES_JUSCELINO.toString(), "Laranja", 180, 100, 14, 70);
    this.espacos[17] = new EspacoDeCarta(NomeDoEspaco.COFRE.toString());
    this.espacos[18] = new Lote(NomeDoEspaco.AV_ENG_LUIS.toString(), "Laranja", 180, 100, 14, 70);
    this.espacos[19] = new Lote(NomeDoEspaco.AV_BRIGADEIRO.toString(), "Laranja", 200, 100, 16, 80);
    this.espacos[20] = new EstacionamentoGratis(NomeDoEspaco.ESTACIONAMENTO.toString());
    this.espacos[21] = new Lote(NomeDoEspaco.IPANEMA.toString(), "Vermelho", 220, 150, 18, 90);
    this.espacos[22] = new EspacoDeCarta(NomeDoEspaco.SORTE.toString());
    this.espacos[23] = new Lote(NomeDoEspaco.LEBLON.toString(), "Vermelho", 220, 150, 18, 90);
    this.espacos[24] = new Lote(NomeDoEspaco.COPACABANA.toString(), "Vermelho", 240, 150, 20, 100);
    this.espacos[25] = new EstacaoDeMetro(NomeDoEspaco.METRO_CONSOLACAO.toString(), 200);
    this.espacos[26] = new Lote(NomeDoEspaco.IBIRAPUERA.toString(), "Amarelo", 260, 150, 22, 110);
    this.espacos[27] = new Lote(NomeDoEspaco.IBIRAPUERA.toString(), "Amarelo", 260, 150, 22, 110);
    this.espacos[28] = new Utilidade(NomeDoEspaco.COMP_AGUA.toString(), 150);
    this.espacos[29] = new Lote(NomeDoEspaco.IBIRAPUERA.toString(), "Amarelo", 280, 150, 24, 120);
    this.espacos[30] = new VaParaCadeia(NomeDoEspaco.VA_PARA_CADEIA.toString());
    this.espacos[31] = new Lote(NomeDoEspaco.BARRA_DA_TIJUCA.toString(), "Verde", 300, 200, 24, 120);
    this.espacos[32] = new Lote(NomeDoEspaco.JARDIM_BOTANICO.toString(), "Verde", 300, 200, 26, 130);
    this.espacos[33] = new EspacoDeCarta(NomeDoEspaco.COFRE.toString());
    this.espacos[34] = new Lote(NomeDoEspaco.LAGOA_RODRIGO.toString(), "Verde", 320, 200, 28, 150);
    this.espacos[35] = new EstacaoDeMetro(NomeDoEspaco.METRO_REPUBLICA.toString(), 200);
    this.espacos[36] = new EspacoDeCarta(NomeDoEspaco.SORTE.toString());
    this.espacos[37] = new Lote(NomeDoEspaco.AV_MORUMBI.toString(), "Azul", 350, 200, 35, 175);
    this.espacos[38] = new TaxaDeRiqueza(NomeDoEspaco.TAXA_RIQUEZA.toString());
    this.espacos[39] = new Lote(NomeDoEspaco.RUA_OSCAR_FREIRE.toString(), "Azul", 400, 200, 50, 200);

    this.gruposLotes = new GrupoDoLote[8];
    this.gruposLotes[0] = new GrupoDoLote(); // Marrom
    this.gruposLotes[0].adicionarLote((Lote) this.espacos[1]);
    this.gruposLotes[0].adicionarLote((Lote) this.espacos[3]);

    this.gruposLotes[1] = new GrupoDoLote(); // Azul claro
    this.gruposLotes[1].adicionarLote((Lote) this.espacos[6]);
    this.gruposLotes[1].adicionarLote((Lote) this.espacos[8]);
    this.gruposLotes[1].adicionarLote((Lote) this.espacos[9]);

    this.gruposLotes[2] = new GrupoDoLote(); // Rosa
    this.gruposLotes[2].adicionarLote((Lote) this.espacos[11]);
    this.gruposLotes[2].adicionarLote((Lote) this.espacos[13]);
    this.gruposLotes[2].adicionarLote((Lote) this.espacos[14]);

    this.gruposLotes[3] = new GrupoDoLote(); // Laranja
    this.gruposLotes[3].adicionarLote((Lote) this.espacos[16]);
    this.gruposLotes[3].adicionarLote((Lote) this.espacos[18]);
    this.gruposLotes[3].adicionarLote((Lote) this.espacos[19]);

    this.gruposLotes[4] = new GrupoDoLote(); // Vermelho
    this.gruposLotes[4].adicionarLote((Lote) this.espacos[21]);
    this.gruposLotes[4].adicionarLote((Lote) this.espacos[23]);
    this.gruposLotes[4].adicionarLote((Lote) this.espacos[24]);

    this.gruposLotes[5] = new GrupoDoLote(); // Amarelo
    this.gruposLotes[5].adicionarLote((Lote) this.espacos[26]);
    this.gruposLotes[5].adicionarLote((Lote) this.espacos[27]);
    this.gruposLotes[5].adicionarLote((Lote) this.espacos[29]);

    this.gruposLotes[6] = new GrupoDoLote(); // Verde
    this.gruposLotes[6].adicionarLote((Lote) this.espacos[31]);
    this.gruposLotes[6].adicionarLote((Lote) this.espacos[32]);
    this.gruposLotes[6].adicionarLote((Lote) this.espacos[34]);

    this.gruposLotes[7] = new GrupoDoLote(); // Azul
    this.gruposLotes[7].adicionarLote((Lote) this.espacos[37]);
    this.gruposLotes[7].adicionarLote((Lote) this.espacos[39]);

  }

  /*
   * Responsável por mover o jogador, faz com que sua posição seja atualizada e
   * define o
   * sentido em que o jogador vai. Toma conta para que não estore 40 posições.
   */
  public void mover(Jogador j, int sentido) {
    int novaPosicao = (j.getPosicaoAtual() + sentido) % 40;
    if (novaPosicao < 0)
      novaPosicao = 39;
    j.setPosicaoAtual(novaPosicao);
    j.setEspacoAtual(this.espacos[novaPosicao]);
  }

  // Responsável por mover o jogador para uma posição específica, quando for o
  // caso.
  public void moverEspecifico(Jogador j, int indice) {
    j.setPosicaoAtual(indice);
    j.setEspacoAtual(this.espacos[indice]);
  }

  public void moverProximo(Jogador j, int qual) {
    int metro1 = 5;
    int metro2 = 15;
    int metro3 = 25;
    int metro4 = 35;

    int util1 = 12;
    int util2 = 28;
    int posicaoAtual = j.getPosicaoAtual();
    if (qual == -1) { // Utilidade mais proxima
      if (util2 > posicaoAtual && util1 < posicaoAtual) {
        j.setPosicaoAtual(util2);
        j.setEspacoAtual(this.espacos[util2]);
      } else {
        j.setPosicaoAtual(util1);
        j.setEspacoAtual(this.espacos[util1]);
      }

    } else { // Metro mais proximo
      if (posicaoAtual < metro1) {
        j.setPosicaoAtual(metro1);
        j.setEspacoAtual(this.espacos[metro1]);
      } else if (posicaoAtual < metro2) {
        j.setPosicaoAtual(metro2);
        j.setEspacoAtual(this.espacos[metro2]);
      } else if (posicaoAtual < metro3) {
        j.setPosicaoAtual(metro3);
        j.setEspacoAtual(this.espacos[metro3]);
      } else if (posicaoAtual < metro4) {
        j.setPosicaoAtual(metro4);
        j.setEspacoAtual(this.espacos[metro4]);
      } else {
        j.setPosicaoAtual(metro1);
        j.setEspacoAtual(this.espacos[metro1]);
      }
    }
  }

  public void iniciar(Jogador[] jogadores) {
    for (Jogador j : jogadores) {
      j.setEspacoAtual(this.espacos[0]);
    }
  }

  // Responsável por checar se o jogador atingiu monopólio.
  public void checarLotes() {
    for (int i = 0; i < 8; i++) {
      if (this.gruposLotes[i].tamanho() == 2) {
        ArrayList<Lote> l = this.gruposLotes[i].membrosDoGrupo();
        if (l.get(0).temProprietario() && l.get(1).temProprietario()) {
          if (l.get(0).getProprietario().equals(l.get(1).getProprietario())) {
            l.get(1).getProprietario().modMonopolios(1);
            l.get(0).setMonopolio(true);
            l.get(1).setMonopolio(true);
          } else {
            l.get(0).setMonopolio(false);
            l.get(1).setMonopolio(false);
          }
        }
      } else {
        ArrayList<Lote> l = this.gruposLotes[i].membrosDoGrupo();
        if (l.get(0).temProprietario() && l.get(1).temProprietario() && l.get(2).temProprietario()) {
          if (l.get(0).getProprietario().equals(l.get(1).getProprietario())
              && l.get(1).getProprietario().equals(l.get(2).getProprietario())) {
            l.get(1).getProprietario().modMonopolios(1);
            l.get(0).setMonopolio(true);
            l.get(1).setMonopolio(true);
            l.get(2).setMonopolio(true);
          } else {
            l.get(0).setMonopolio(false);
            l.get(1).setMonopolio(false);
            l.get(2).setMonopolio(false);
          }
        }
      }
    }
  }

}
