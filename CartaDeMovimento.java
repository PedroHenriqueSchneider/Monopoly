package gerenciador;

//subclasse de Carta
public class CartaDeMovimento extends Carta {
    private final int sentido; // 1 para frente, 0 para lugar especifico e -1 para tras

    /**
     * Numero de casas a andar para trás/frente ou
     * para lugares específicos:
     * -1 = Utilidade mais prox, -2 = Metro mais prox,
     * 0 = Partida, 3 = Praça da Sé, 6 = 25 de Março,
     * 13 = Niteroi, 16 = Juscelino K, 23 = Leblon,
     * 32 = Jardim botanico, 39 = Oscar Freire
     * 
     * ^ índices especificos desses espaços no tabuleiro.
     */
    private final int movimentos;

//metodo construtor
    public CartaDeMovimento(int sentido, int movimentos, String descricao) {
        super(descricao, "Movimento");
        this.sentido = sentido;
        this.movimentos = movimentos;
    }
//metodos acessores
    public int getSentido() {
        return this.sentido;
    }

    public int getMovimentos() {
        return this.movimentos;
    }

}
