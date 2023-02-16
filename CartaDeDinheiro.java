package gerenciador;

//subclasse de Carta
public class CartaDeDinheiro extends Carta {
    private final int operacao; // 1 para adiçao e -1 para remoção
    private final int quantia; // valor a ser adicionado ou removido

  //metodo construtor
    public CartaDeDinheiro(int operacao, int quantia, String descricao) {
        super(descricao, "Dinheiro");
        this.operacao = operacao;
        this.quantia = quantia;
    }

  //metodos acessores
    public int getOperacao() {
        return this.operacao;
    }

    public int getQuantia() {
        return this.quantia;
    }

}
