package gerenciador;

import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {
  private Tabuleiro tabuleiro;
  private Jogador[] jogadores;
  private int nJogadores;
  private int turno;
  private Dado d1;
  private Dado d2;
  private DeckDeCartas cofre;
  private DeckDeCartas sorte;
  private Banco banco;

  public static final Scanner s = new Scanner(System.in); // Scanner global para evitar erros

  public Jogo(int nJogadores, String jog1, String jog2) {
    this.tabuleiro = new Tabuleiro();
    this.nJogadores = nJogadores;
    this.jogadores = new Jogador[nJogadores];
    this.jogadores[0] = new Jogador(jog1);
    this.jogadores[1] = new Jogador(jog2);
    this.turno = 0;
    this.d1 = new Dado();
    this.d2 = new Dado();
    this.cofre = new DeckDeCartas("Cofre");
    this.sorte = new DeckDeCartas("Sorte");
    this.banco = new Banco();
  }

  public Jogo(int nJogadores, String jog1, String jog2, String jog3) {
    this.tabuleiro = new Tabuleiro();
    this.nJogadores = nJogadores;
    this.jogadores = new Jogador[nJogadores];
    this.jogadores[0] = new Jogador(jog1);
    this.jogadores[1] = new Jogador(jog2);
    this.jogadores[2] = new Jogador(jog3);
    this.turno = 0;
    this.d1 = new Dado();
    this.d2 = new Dado();
    this.cofre = new DeckDeCartas("Cofre");
    this.sorte = new DeckDeCartas("Sorte");
    this.banco = new Banco();
  }

  public Jogo(int nJogadores, String jog1, String jog2, String jog3, String jog4) {
    this.tabuleiro = new Tabuleiro();
    this.nJogadores = nJogadores;
    this.jogadores = new Jogador[nJogadores];
    this.jogadores[0] = new Jogador(jog1);
    this.jogadores[1] = new Jogador(jog2);
    this.jogadores[2] = new Jogador(jog3);
    this.jogadores[3] = new Jogador(jog4);
    this.turno = 0;
    this.d1 = new Dado();
    this.d2 = new Dado();
    this.cofre = new DeckDeCartas("Cofre");
    this.sorte = new DeckDeCartas("Sorte");
    this.banco = new Banco();
  }

  public void jogar() {
    tabuleiro.iniciar(jogadores);
    boolean fimDeJogo = false;
    this.definirOrdem();
    int distancia;
    
    while (!fimDeJogo) {
      distancia = 0;
      Jogador j = this.jogadores[this.turno];
      if (j.estaFalido()) {
        System.out.println("Jogador " + j.getNome() + " está falido!");
        System.out.println("Passando para o próximo");
        this.passarTurno();
        continue;
      }

      System.out.println("É a vez de " + j.getNome());

      if (j.getPreso()) {
        System.out.println(j.getNome() + ", voce esta preso!");
        if (j.getTentouSair() == 3) {
          System.out.println("Voce falhou em escapar da cadeia nas suas ultimas 3 tentativas");
          System.out.println("Agora será obrigado a pagar a fiança de " + Cadeia.getFianca() + "$ para sair da cadeia");
          if (!this.banco.cobrarJogador(j, Cadeia.getFianca())) {
            System.out.println(j.getNome() + ", você não possui saldo para pagar a fiança, está falido :(");
            this.passarTurno();
            continue;
          } else {
            j.modTentouSair(-j.getTentouSair());
            Cadeia.soltar(j);
            System.out.println("Agora voce está liberado da cadeia e pode continuar o jogo");
            System.out.println("Novo saldo: " + j.getSaldo() + "$");
          }
        } else { // se ainda nao tentou sair 3 vezes com o dado
          int escolha;
          System.out.println("Escolha uma das opções a seguir para sair da cadeia");
          while (true) {
            System.out.println("1 - Tentar rolar uma dupla nos dados");
            System.out.println("2 - Pagar a fiança");
            try {
              escolha = s.nextInt();
              this.validarOpcao(escolha, 2);
              if (escolha == 2 && j.getSaldo() < Cadeia.getFianca())
                System.out.println("Voce nao tem saldo para pagar a fiança, tente sair pelos dados!");
              else
                break;
            } catch (Exception e) {
              s.nextLine();
              System.out.println("Entrada inválida, tente novamente!");
            }
            s.nextLine();
          } // loop de verificação de entrada

          if (escolha == 1) { // sair pelos dados
            j.lancarDados(d1, d2);
            System.out.println("Voce tirou" + d1.getValorDado() + " e " + d2.getValorDado());
            if (d1.getValorDado() != d2.getValorDado()) {
              j.modTentouSair(1);
              System.out.println("Não foi dessa vez :(");
              this.passarTurno();
              System.out.println("Aperte ENTER para continuar");
              s.nextLine();
              continue;
            } else {
              distancia = d1.getValorDado() + d2.getValorDado();
              System.out.println(
                  "Voce conseguiu uma dupla! Será liberado da cadeia e seguirá o jogo com o resultado dos dados, que foi "
                      + distancia);
              Cadeia.soltar(j);
              j.modTentouSair(-j.getTentouSair());
            }
          } else { // pagar fiança
            j.modTentouSair(-j.getTentouSair());
            Cadeia.soltar(j);
            this.banco.cobrarJogador(j, Cadeia.getFianca());
            System.out.println(j.getNome() + ", agora voce está livre! Pode voltar ao jogo!");
            System.out.println("Novo saldo: " + j.getSaldo() + "$");
            System.out.println("Aperte ENTER para continuar");
            s.nextLine();
          }
        }
        this.infoJogador(j); // mostrar informaçoes quando sair da cadeia, antes de começar o movimento
      }

      else { // se nao está preso
        this.infoJogador(j); // mostrar informaçoes antes de fazer escolha de jogada
        int opcao = 0;
        boolean melhorouLote = false;
        while (opcao != 1) {
          while (true) {
            this.mostrarMenu();
            if (j.getMonopolios() == 0) {
              try {
                opcao = s.nextInt();
                this.validarOpcao(opcao, 2);
                break;
              } catch (Exception e) {
                System.out.println("Opção inválida, tente novamente!");
                s.nextLine();
              }

            } else if (j.getMonopolios() != 0 && !melhorouLote) { // quando j tem monopolio e ainda nao fez
                                                                  // melhoria na
              // sua vez
              System.out.println("3 - Comprar casa/hotel");
              try {
                opcao = s.nextInt();
                this.validarOpcao(opcao, 3);
                break;
              } catch (Exception e) {
                System.out.println("Opção inválida, tente novamente!");
                s.nextLine();
              }
            }
            break;
          } // loop de verificaçao de entrada
          s.nextLine();

          if (opcao == 2) {
            while (true) {
              System.out.println("Escolha um dos jogadores a seguir para negociar");
              int x = 1;
              for (int i = 0; i < this.nJogadores; i++) {
                if (this.jogadores[i] != j) {
                  System.out.println(x + " - " + this.jogadores[i].getNome());
                  x++;
                }
              }

              int escolha;
              while (true) {
                try {
                  escolha = s.nextInt();
                  validarOpcao(escolha, this.nJogadores - 1);
                  break;
                } catch (Exception e) {
                  System.out.println("Entrada inválida, tente novamente");
                  s.nextLine();
                }
              }
              s.nextLine();
              if (escolha <= this.turno) // arrumando indices da lista de jogadores
                escolha--;

              int imovel = -1;
              while (true) {
                try {
                  System.out.println("Qual propriedade deseja negociar?");
                  if (!this.jogadores[escolha].mostrarPropriedades())
                    break;
                  imovel = s.nextInt();
                  validarOpcao(imovel, this.jogadores[escolha].getPropriedades().size());
                  imovel--;
                  break;
                } catch (Exception e) {
                  System.out.println("Entrada inválida, tente novamente!");
                  s.nextLine();
                }
              }
              if (imovel == -1)
                break;
              s.nextLine();

              int valorDeCompra;
              while (true) {
                try {
                  System.out.println("Digite o valor que quer oferecer nessa propriedade");
                  valorDeCompra = s.nextInt();
                  if (valorDeCompra > j.getSaldo()) {
                    System.out.println("Você não possui esse dinheiro! Digite outro valor.");
                    continue;
                  }
                  validarValor(valorDeCompra);
                  break;
                } catch (Exception e) {
                  System.out.println("Entrada inválida, tente novamente!");
                  s.nextLine();
                }
              }
              s.nextLine();

              int opcaoComprador;
              while (true) {
                try {
                  System.out.format("Jogador %s, você aceita a proposta de %s,"
                      + " para trocar %s por %d reais?\n"
                      + " Digite 1 para sim ou 2 para não:", this.jogadores[escolha].getNome(), j.getNome(),
                      this.jogadores[escolha].getPropriedades().get(imovel).getNome(), valorDeCompra);
                  opcaoComprador = s.nextInt();
                  validarOpcao(opcaoComprador, 2);
                  break;
                } catch (Exception e) {
                  System.out.println("Entrada inválida, tente novamente!");
                  s.nextLine();
                }
              }
              s.nextLine();
              if (opcaoComprador == 1) {
                this.banco.intermediar(j, this.jogadores[escolha], valorDeCompra);
                System.out.println("Novo saldo de " + j.getNome() + ":" + j.getSaldo() + "$");
                System.out.println("Novo saldo de " + this.jogadores[escolha].getNome() + ":"
                    + this.jogadores[escolha].getSaldo() + "$");
                Propriedade imovelNegociado = this.jogadores[escolha].getPropriedades().get(imovel);
                this.jogadores[escolha].removerPropriedade(imovelNegociado);
                j.addPropriedade(imovelNegociado);
                this.tabuleiro.checarLotes();
                System.out.println("Transação concluida com sucesso!");
              } else {
                System.out.println("Transação não concuída!");
              }
              int opcaoContinuar;
              while (true) {
                try {
                  System.out.println("Você gostaria de negociar novamente?");
                  System.out.println("Digite 1 para sim ou 2 para não!");
                  opcaoContinuar = s.nextInt();
                  this.validarOpcao(opcaoContinuar, 2);
                  break;
                } catch (Exception e) {
                  System.out.println("Entrada inválida, tente novamente!");
                  s.nextLine();
                }
              }
              s.nextLine();
              if (opcaoContinuar != 1)
                break;
            } // loop que permite o jogador negociar quantas vezes quiser

          }

          else if (opcao == 3) {
            ArrayList<Lote> lotesList = j.getLotesMonopolizados();
            boolean todosTemCasa = true;
            for (Lote k : lotesList)
              if (!(k.temCasa()))
                todosTemCasa = false;

            int imovel;
            while (true) {
              try {
                System.out.println("Qual propriedade deseja construir?");
                int i = 1;
                for (Lote l : lotesList) {
                  System.out.format("%d - %s", i, l.getNome());
                  if (l.temCasa())
                    System.out.print(" (tem casa)");
                  i++;
                  System.out.println("");
                }

                imovel = s.nextInt();
                validarOpcao(imovel, lotesList.size());
                break;
              } catch (Exception e) {
                System.out.println("Entrada inválida, tente novamente!");
                s.nextLine();
              }
            }
            s.nextLine();
            imovel--;
            int tipoConstrucao = 0;
            while (true) {
              try {
                if (todosTemCasa) {
                  System.out.println("Deseja comprar um hotel nesse esse lote? Preço: " + lotesList.get(imovel).getPrecoHotel());
                  System.out.println("Digite 1 para sim ou 2 para não");
                  tipoConstrucao = s.nextInt();
                  validarOpcao(tipoConstrucao, 2);
                  break;
                } else if (!todosTemCasa && lotesList.get(imovel).temCasa()) {
                  System.out.println("Você já construiu uma casa nesse lote, mas ainda não pode construir um hotel");
                  break;
                } else {
                  System.out.println("Deseja comprar uma casa nesse esse lote? Preço: " + lotesList.get(imovel).getPrecoCasa());
                  System.out.println("Digite 1 para sim ou 2 para não");
                  tipoConstrucao = s.nextInt();
                  validarOpcao(tipoConstrucao, 2);
                  break;
                }
              } catch (Exception e) {
                System.out.println("Entrada inválida, tente novamente!");
                s.nextLine();
              }
            }
            s.nextLine();
            Lote imovelParaMelhorar = lotesList.get(imovel);
            if (tipoConstrucao == 1) {
              if (!imovelParaMelhorar.temHotel() && !imovelParaMelhorar.temCasa()) {// Se não tem hotel e não tem casa
                                                                                    // construir casa;
                if (j.getSaldo() >= imovelParaMelhorar.getPrecoCasa()) {
                  this.banco.cobrarJogador(j, imovelParaMelhorar.getPrecoCasa());
                  System.out.println("Compra realizada com sucesso! Casa construida!");
                  System.out.println("Novo saldo: " + j.getSaldo() + "$");
                  System.out.println("Aperte ENTER para continuar");
                  s.nextLine();
                  imovelParaMelhorar.setCasa(true);
                  melhorouLote = true;
                } else {
                  System.out.println("Saldo insuficiente!");
                }
              } else if (imovelParaMelhorar.temCasa() && !imovelParaMelhorar.temHotel()) {
                if (j.getSaldo() >= imovelParaMelhorar.getPrecoHotel()) {
                  this.banco.cobrarJogador(j, imovelParaMelhorar.getPrecoHotel());
                  System.out.println("Compra realizada com sucesso! Hotel construido!");
                  System.out.println("Novo saldo: " + j.getSaldo() + "$");
                  System.out.println("Aperte ENTER para continuar");
                  s.nextLine();
                  imovelParaMelhorar.setHotel(true);
                  melhorouLote = true;
                } else {
                  System.out.println("Saldo insuficiente");
                }
              }
            }
          }
        } // loop para sair quando a opcao for a de lançar dados
      }

      if (distancia == 0) {
        System.out.println("Pressione ENTER para lançar os dados!");
        s.nextLine();
        while (true) {
          distancia = j.lancarDados(this.d1, this.d2);
          System.out
              .println("Voce lançou " + d1.getValorDado() + " e " + d2.getValorDado() + ", totalizando " + distancia);

          if (!j.jogouTresDuplas()) {
            this.movimentacao(j, distancia, 1);
            this.verificarEspaco(j);
            if (j.estaFalido())
              break;
            if (this.d1.getValorDado() != this.d2.getValorDado() || j.getPreso())
              break;
            else {
              System.out.println("Lançou dados iguais, poderá lançá-los novamente!");
              System.out.println("Pressione ENTER para lançar os dados!");
              s.nextLine();
            }
          } // se nao jogos 3 duplas
          else {
            System.out
                .println("Voce está com muita sorte, conseguiu 3 duplas seguidas. Como punição, vá para a cadeia!");
            this.prenderJogador(j);
          }

        }
      } else { // quando o jogador sai da cadeia usando uma dupla
        this.movimentacao(j, distancia, 1);
        this.verificarEspaco(j);
      }

      if (this.verificarGanhador())
        fimDeJogo = true;
      this.passarTurno();
    } // fim while

  }

  // Ordena o vetor de Jogadores de acordo com o maior lançamento dos dados
  private void definirOrdem() {
    System.out.println("Para estabelecer a ordem dos jogadores, todos deverão lançar os dados!");
    System.out.println("Os que obtiverem os maiores numeros vão na frente!");
    int[] somas = new int[this.nJogadores];
    for (int i = 0; i < this.nJogadores; i++) {
      System.out.println("Jogador " + this.jogadores[i].getNome() + ", aperte ENTER para rolar os dados!");
      s.nextLine();
      int somaDados = this.jogadores[i].lancarDados(this.d1, this.d2);
      System.out.println("Voce tirou " + this.d1.getValorDado() + " e " + this.d2.getValorDado());
      somas[i] = somaDados;
    }

    for (int i = 0; i < this.nJogadores; i++) {
      int max = i;
      for (int j = i + 1; j < this.nJogadores; j++)
        if (somas[j] > somas[max])
          max = j;
      if (max != i) {
        int temp = somas[max];
        somas[max] = somas[i];
        somas[i] = temp;
        Jogador jtemp = this.jogadores[max];
        this.jogadores[max] = this.jogadores[i];
        this.jogadores[i] = jtemp;
      }
    }
  }

  private void passarTurno() {
    this.turno = (this.turno + 1) % this.nJogadores;
  }

  private void mostrarMenu() {
    System.out.println("Escolha seu próximo movimento");
    System.out.println("1 - Lançar os dados");
    System.out.println("2 - Negociar com outro jogador");
  }

  private void prenderJogador(Jogador j) {
    Cadeia.prender(j);
    this.tabuleiro.moverEspecifico(j, 10);
  }

  private void validarOpcao(int n, int max) throws Exception {
    if (n < 1 || n > max)
      throw new Exception();
  }

  private void validarValor(int valor) throws Exception {
    if (valor < 0)
      throw new Exception();
  }

  private void movimentacao(Jogador j, int distancia, int sentido) {
    for (int i = 0; i < distancia; i++) {
      this.tabuleiro.mover(j, sentido);
      if (j.getPosicaoAtual() != 0 && i < distancia - 1)
        System.out.println("Passando por: " + j.getEspacoAtual().getNome());
      if (j.getPosicaoAtual() == 0 && sentido == 1) {
        System.out.println("Voce passou pelo Ponto de Partida, receba 200");
        this.banco.pagarJogador(j, PontoDePartida.getValor());
        System.out.println("Novo saldo: " + j.getSaldo() + "$");
      }
    }
  }

  private void verificarEspaco(Jogador j) {
    if (j.getEspacoAtual().getTipo().equals("Lote") || j.getEspacoAtual().getTipo().equals("Estacao")
        || j.getEspacoAtual().getTipo().equals("Utilidade")) {
      Propriedade p = (Propriedade) j.getEspacoAtual();
      if (!p.temProprietario()) {
        System.out.println("Você caiu em " + p.getNome() + ", deseja comprar?");
        System.out.println("Preço de compra: " + p.getPrecoDeCompra());
        System.out.println("Insira 1 para sim ou 2 para não");
        int escolha;
        while (true) {
          try {
            escolha = s.nextInt();
            validarOpcao(escolha, 2);
            break;
          } catch (Exception e) {
            System.out.println("Entrada inválida, tente novamente");
            s.nextLine();
          }
        }
        s.nextLine();
        if (escolha == 1) {
          if (p.getPrecoDeCompra() > j.getSaldo())
            System.out.println("Você não possui saldo para isso! Continuando o jogo...");
          else {
            this.banco.cobrarJogador(j, p.getPrecoDeCompra());
            j.comprarProriedade(p);
            System.out.println("Voce agora possui a propriedade " + p.getNome() + "!");
            System.out.println("Novo saldo: " + j.getSaldo() + "$");
            this.tabuleiro.checarLotes();
          }
        }

      } else if (p.getProprietario() != j) { // se p tem proprietario
        System.out.println("Você caiu em " + p.getNome() + ", uma propriedade com dono!");
        System.out.println(
            "Agora deverá pagar o aluguel de " + p.calcularAluguel(this.d1.getValorDado() + this.d2.getValorDado())
                + " para " + p.getProprietario().getNome());
        System.out.println("Aperte ENTER para pagar continuar");
        s.nextLine();
        if (!this.banco.intermediar(j, p.getProprietario(),
            p.calcularAluguel(this.d1.getValorDado() + this.d2.getValorDado()))) {
          System.out.println("Você não possui dinheiro para pagar o aluguel, está falido :(");
          System.out.println(
              "Removendo propriedades de " + j.getNome() + " e passando para " + p.getProprietario().getNome());
          this.tabuleiro.checarLotes();
        } else {
          System.out.println("Novo saldo de " + j.getNome() + ":" + j.getSaldo() + "$");
          System.out
              .println("Novo saldo de " + p.getProprietario().getNome() + ":" + p.getProprietario().getSaldo() + "$");
        }
      } else {
        System.out.format("Você caiu em sua propriedade %s, continuando o jogo...\n", p.getNome());
      }
      System.out.println("Aperte ENTER para continuar");
      s.nextLine();
    } else if (j.getEspacoAtual().getTipo().equals("Carta")) {
      Carta c;
      if (j.getEspacoAtual().getNome().equals("Sorte")) {
        System.out.println("Você caiu em um espaço de carta de sorte!");
        System.out.println("Aperte ENTER para puxar uma carta do deck de sorte");
        s.nextLine();
        c = this.sorte.puxarCarta();
      } else {
        System.out.println("Você caiu em um espaço de carta de sorte!");
        System.out.println("Aperte ENTER para puxar uma carta do deck de sorte");
        s.nextLine();
        c = this.cofre.puxarCarta();
      }
      System.out.println("Carta: " + c.getDescricao());
      System.out.println("Aperte ENTER para continuar");
      s.nextLine();
      if (c.getTipoCarta().equals("Dinheiro")) {
        CartaDeDinheiro cde = (CartaDeDinheiro) c;
        if (cde.getOperacao() == 1) {
          this.banco.pagarJogador(j, cde.getQuantia());
          System.out.println("Novo saldo: " + j.getSaldo() + "$");
        } else {
          if (!this.banco.cobrarJogador(j, cde.getQuantia()))
            System.out.println("Você não possui dinheiro para obedecer a carta, está falido :(");
          else {
            System.out.println("Novo saldo: " + j.getSaldo() + "$");
          }
        }
        System.out.println("Aperte ENTER para continuar");
        s.nextLine();
      } else if (c.getTipoCarta().equals("Movimento")) {
        CartaDeMovimento cdm = (CartaDeMovimento) c;
        if (cdm.getSentido() == 0 && cdm.getMovimentos() < 0) {
          this.tabuleiro.moverProximo(j, cdm.getMovimentos());
          this.verificarEspaco(j);
        } else if (cdm.getSentido() == 0) {
          this.tabuleiro.moverEspecifico(j, cdm.getMovimentos());
          this.verificarEspaco(j);
        } else {
          this.movimentacao(j, cdm.getMovimentos(), cdm.getSentido());
          this.verificarEspaco(j);
        }
      } else { // carta VaParaCadeia
        System.out.println(j.getNome() + ", você está preso!");
        this.prenderJogador(j);
        System.out.println("Aperte ENTER para continuar");
        s.nextLine();
      }
    } else if (j.getEspacoAtual().getTipo().equals("Imposto")) {
      System.out.println(
          "Você caiu no espaço Imposto de Renda, deverá escolher pagar a taxa de 200$ ou 10% de sua fortuna total");
      if (!this.banco.cobrarJogador(j, ImpostoDeRenda.calcular(j))) {
        System.out.println("Você não tem saldo suficiente para pagar 10% de sua fortuna, está falido :(");
      } else {
        System.out.println("Novo saldo: " + j.getSaldo() + "$");
      }
      System.out.println("Aperte ENTER para continuar");
      s.nextLine();
    } else if (j.getEspacoAtual().getTipo().equals("Taxa")) {
      System.out.println("Você caiu no espaço Taxa De Riqueza, deverá pagar a taxa de 200$!");
      System.out.println("Aperte ENTER para pagar");
      s.nextLine();
      if (!this.banco.cobrarJogador(j, TaxaDeRiqueza.getTaxa()))
        System.out.println("Você não possui dinheiro para pagar a taxa, você faliu :(");
      else
        System.out.println("Novo saldo: " + j.getSaldo() + "$");
      System.out.println("Aperte ENTER para continuar");
      s.nextLine();
    } else if (j.getEspacoAtual().getTipo().equals("VaParaCadeia")) {
      System.out.println("Você caiu no espaço Va Para Cadeia.");
      System.out.println(j.getNome() + ", você está preso!");
      this.prenderJogador(j);
      System.out.println("Aperte ENTER para continuar");
      s.nextLine();
    } else if (j.getEspacoAtual().getTipo().equals("Cadeia")) {
      System.out.println("Você caiu no espaço da Cadeia, mas está apenas visitando!");
      System.out.println("Aperte ENTER para continuar");
      s.nextLine();
    } else if (j.getEspacoAtual().getTipo().equals("Estacionamento")) {
      System.out.println("Você caiu em estacionamento grátis, nada acontece");
      System.out.println("Aperte ENTER para continuar");
      s.nextLine();
    } else {
      System.out.println("Você caiu no ponto de partida!");
      System.out.println("Aperte ENTER para continuar");
      s.nextLine();
    }
  }

  private boolean verificarGanhador() {
    // ultimo jogador restante
    int cont = 0;
    for (int i = 0; i < this.nJogadores; i++)
      if (this.jogadores[i].estaFalido())
        cont++;
    if (cont == this.nJogadores - 1)
      for (int i = 0; i < this.nJogadores; i++)
        if (!(this.jogadores[i].estaFalido())) {
          System.out.println("PARABENS, " + this.jogadores[i].getNome()
              + ", POR SER O ULTIMO RESTANTE. VOCÊ VENCEU ESSE JOGO DE MONOPOLY!!");
          return true;
        }

    // 2 monopolios
    for (int i = 0; i < this.nJogadores; i++)
      if (this.jogadores[i].getMonopolios() == 2) {
        System.out.println("PARABENS, " + this.jogadores[i].getNome()
            + ", POR CONQUISTAR 2 MONOPOLIOS. VOCÊ VENCEU ESSE JOGO DE MONOPOLY!!");
        return true;
      }

    // hotel construido
    for (int i = 0; i < this.nJogadores; i++) {
      ArrayList<Lote> l = this.jogadores[i].getLotesMonopolizados();
      if (l != null) {
        for (Lote k : l)
          if (k.temHotel()) {
            System.out.println("PARABENS, " + this.jogadores[i].getNome()
                + ", POR OBTER 1 HOTEL EM SEU MONOPÓLIO. VOCÊ VENCEU ESSE JOGO DE MONOPOLY!!");
            return true;
          }
      }
    }
    return false;
  }

  private void infoJogador(Jogador j) {
    System.out.println("*****");
    System.out.println("* Jogador: " + j.getNome());
    System.out.println("* Saldo: " + j.getSaldo() + "$");
    System.out.println("* Espaço atual: " + j.getEspacoAtual().getNome());
    System.out.println("*****");
  }
}
