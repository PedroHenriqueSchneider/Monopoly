package gerenciador;

//subclasse de propriedade
public class EstacaoDeMetro extends Propriedade {

    private final int aluguel = 25; // Aluguel inicial.
  
//metodo construtor que inicializa a classe propriedade
    public EstacaoDeMetro(String nome, int preco) {
        super(nome, "Estacao", preco);
    }

//sobrescreve o método, para calcular o aluguel de maneira diferente para as estações.
    @Override
    public int calcularAluguel(int valor) {
        return this.aluguel * this.proprietario.getEstacoes();
    }

}
